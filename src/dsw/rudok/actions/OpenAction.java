package dsw.rudok.actions;

import dsw.rudok.app.MainFrame;
import dsw.rudok.command.concrete.AddNodeCommand;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;
import dsw.rudok.serialization.filters.PresentationFileFilter;
import dsw.rudok.serialization.filters.ProjectFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class OpenAction extends RudokAction{

    public OpenAction(){
        putValue(NAME,"Open file");
        putValue(SHORT_DESCRIPTION, "Opens selected file from system");
        putValue(SMALL_ICON, loadIcon("images/open_action.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new ProjectFileFilter());
        fileChooser.addChoosableFileFilter(new PresentationFileFilter());

        ObjectInputStream ois = null;
        BufferedReader br = null;

        RuNode selectedNode = ((RudokTreeNode)MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent()).getRuNode();

        File fileToLoad = null;

        if(fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            fileToLoad = fileChooser.getSelectedFile();
        }else{
            MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("You must choose file", "File Error",0);
            return;
        }

        if(selectedNode instanceof Workspace){
            if(fileToLoad.toString().endsWith(".txt")){
                try{
                    br = new BufferedReader(new FileReader(fileToLoad));

                    while(br.readLine() != null){
                        ois = new ObjectInputStream(new FileInputStream(br.readLine()));

                        Project p = null;

                        try{
                            p = (Project) ois.readObject();
                        }catch (ClassNotFoundException ex){
                            System.out.println("[-] Error OpenAction Workspace");
                        }

                        Workspace ws = (Workspace) selectedNode;
                        RuNode child = (RuNode) p;
                        child.setParent((RuNodeComposite) ws);
                        ((RuNodeComposite) ws).addChild(child);
                        RudokTreeNode childTree = new RudokTreeNode(p);
                        MainFrame.getInstance().getCommandManager().addCommand(new AddNodeCommand((RudokTreeNode) MainFrame.getInstance().getRudokTreeModel().getRoot(), childTree));

                    }
                }catch (IOException ex){
                    System.out.println("[-] Error OpenAction");
                }finally {
                    if(br != null){
                        try{
                            br.close();
                        }catch (IOException ex){
                            System.out.println("[-] OpenAction error while closing br");
                        }
                    }

                    if(ois != null){
                        try{
                            ois.close();
                        }catch (IOException ex){
                            System.out.println("[-] OpenAction error while closing ois");
                        }
                    }
                }
            }

            if(fileToLoad.toString().endsWith(".rpf")){
                try{
                    ois = new ObjectInputStream(new FileInputStream(fileToLoad));
                }catch (IOException ex){
                    System.out.println("[-] Error OpenAction ois");
                }

                Project p = null;

                try{
                    p = (Project) ois.readObject();
                }catch (ClassNotFoundException | IOException ex){
                    System.out.println("[-] OpenAction error while reading project");
                }finally {
                    if(ois != null){
                        try{
                            ois.close();
                        }catch (IOException ex){
                            System.out.println("[-] OpenAction error while closing ois");
                        }
                    }
                }
                Workspace ws = (Workspace) selectedNode;
                RuNode child = (RuNode) p;
                child.setParent((RuNodeComposite) ws);
                ((RuNodeComposite) ws).addChild(child);
                RudokTreeNode childTree = new RudokTreeNode(p);
                MainFrame.getInstance().getCommandManager().addCommand(new AddNodeCommand((RudokTreeNode) MainFrame.getInstance().getRudokTreeModel().getRoot(), childTree));
            }
        }

        if(selectedNode instanceof Project){
            if(fileToLoad.toString().endsWith(".rps")){
                try{
                    ois = new ObjectInputStream(new FileInputStream(fileToLoad));
                }catch (IOException ex){
                    System.out.println("[-] OpenAction Ois error");
                }

                Presentation presentation = null;

                try{
                    if(ois == null) return;
                    presentation = (Presentation) ois.readObject();
                }catch (ClassNotFoundException | IOException ex){
                    System.out.println("[-] OpenAction error reading presentation");
                }finally{
                    if(ois != null){
                        try{
                            ois.close();
                        }catch (IOException ex){
                            System.out.println("[-] OpenAction error, failed to close ois");
                        }
                    }
                }

                Project project = (Project) selectedNode;
                RuNode child = (RuNode) presentation;
                child.setParent((RuNodeComposite) project);
                ((RuNodeComposite) project).addChild(child);
                RudokTreeNode childTree = new RudokTreeNode(presentation);
                MainFrame.getInstance().getCommandManager().addCommand(new AddNodeCommand((RudokTreeNode) MainFrame.getInstance().getRudokTree().getLastSelectedPathComponent(), childTree));
            }
        }
    }
}
