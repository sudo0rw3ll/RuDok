package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.serialization.filters.PresentationFileFilter;
import dsw.rudok.serialization.filters.ProjectFileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.*;

public class SaveAction extends RudokAction{

    public SaveAction(){
        putValue(NAME, "Save action");
        putValue(SHORT_DESCRIPTION, "Save selected RuNode");
        putValue(SMALL_ICON, loadIcon("images/save.png"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode node = ((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();
        JFileChooser fileChooser = new JFileChooser();

        ObjectOutputStream oos = null;

        if(node instanceof Presentation){
            fileChooser.setFileFilter(new PresentationFileFilter());
            Presentation presentation = (Presentation) node;

            File presentationFile = null;

            if(fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
                String fileName = fileChooser.getSelectedFile().toString();
                if(!fileName.endsWith(".rps")){
                    fileName += ".rps";
                }
                presentationFile = new File(fileName);
            }else{
                return;
            }

            try{
                oos = new ObjectOutputStream(new FileOutputStream(presentationFile));
                oos.writeObject(presentation);
            }catch (IOException ex){
                MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Failed to save presentation","Presentation Error",0);
            }finally{
                if(oos != null){
                    try{
                        oos.close();
                    }catch (IOException ex){
                        System.out.println("[-] [SaveAction] Failed to close object output stream");
                    }
                }
            }
        }

        if(node instanceof Project){
            fileChooser.setFileFilter(new ProjectFileFilter());
            Project project = (Project) node;
            File projectFile = null;

            if(!project.isChanged()) MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Project is already saved","Cannot save",0);

            if(project.getProjectFile() == null){
                if(fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
                    String fileName = fileChooser.getSelectedFile().toString();
                    if(!fileName.endsWith(".rpf")){
                        fileName += ".rpf";
                    }
                    projectFile = new File(fileName);
                }else{
                    return;
                }
            }

            try{
                if(projectFile == null) return;
                oos = new ObjectOutputStream(new FileOutputStream(projectFile));
                oos.writeObject(project);
                project.setProjectFile(projectFile);
                project.setChanged(false);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }finally {
                if(oos != null){
                    try{
                        oos.close();
                    }catch (IOException ex){
                        System.out.println("[-] [SaveAction] Failed to close object output stream");
                    }
                }
            }
        }

        if(node instanceof Workspace){
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt","txt"));
            Workspace workspace = (Workspace) node;

            PrintWriter pw = null;

            File outputFile = null;

            if(fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
                String fileName = fileChooser.getSelectedFile().toString();
                if(!fileName.endsWith(".txt")){
                    fileName += ".txt";
                }
                outputFile = new File(fileName);
            }else{
                return;
            }

            try{
                pw = new PrintWriter(new FileOutputStream(outputFile));
                for(int i=0;i<workspace.getChildren().size();i++){
                    pw.println(((Project)workspace.getChildren().get(i)).getProjectFile().getAbsolutePath());
                }
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("[-] [SaveAction] File not found");
            }finally {
                if(pw != null){
                    pw.close();
                }
            }
        }

    }
}
