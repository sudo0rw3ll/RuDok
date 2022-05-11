package dsw.rudok.gui.tab;

import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.observer.RudokSubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JPanel implements RudokSubscriber{

    private Project project;
    private JLabel projectName;
    private JTabbedPane tabbedPane;
    private List<PresentationView> presentationViews;

    public ProjectView(){
        this.projectName = new JLabel("Empty project");
        this.tabbedPane = new JTabbedPane();
        this.presentationViews = new ArrayList<PresentationView>();
        this.setLayout(new BorderLayout());
        this.add(projectName, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public void setProject(Project project) {
        if(this.project != null){
            this.project.removeRudokSubscriber(this);
        }
        presentationViews.clear();
        tabbedPane.removeAll();

        this.project = project;

        if(project != null){
            makePresentationViews();
            this.project.addRudokSubscriber(this);
            update(project, "name");
        }else{
            update(null, "emptyName");
        }
    }

    private void makePresentationViews(){
        for(RuNode child : project.getChildren()){
            if(child instanceof Presentation){
                Presentation presentation = (Presentation) child;
                presentation.addRudokSubscriber(this);
                PresentationView presentationView = new PresentationView(presentation);
                this.presentationViews.add(presentationView);
                this.tabbedPane.addTab(presentationView.getPresentation().getName(), presentationView);
            }
        }
    }

    private void addPresentationViews(Presentation presentation){
        presentation.addRudokSubscriber(this);
        PresentationView presentationView = new PresentationView(presentation);
        presentationViews.add(presentationView);
        tabbedPane.addTab(presentation.getName(),presentationView);
    }

    private void removePresentationViews(Presentation presentation){
        for(int i=0;i<presentationViews.size();i++){
            if(presentationViews.get(i).getPresentation().equals(presentation)){
                presentationViews.get(i).getPresentation().removeRudokSubscriber(this);
                tabbedPane.removeTabAt(i);
                presentationViews.remove(i);
                break;
            }
        }
    }

    public void setProjectName(JLabel projectName) {
        this.projectName = projectName;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public void setPresentationViews(List<PresentationView> presentationViews) {
        this.presentationViews = presentationViews;
    }

    public JTabbedPane getTabbedPane(){
        return this.tabbedPane;
    }

    @Override
    public void update(Object notification, String action) {
        if(action.equalsIgnoreCase("name") && notification instanceof Project){
            projectName.setText(project.getName());
        }

        if(action.equalsIgnoreCase("emptyName")){
            projectName.setText("Empty project");
        }

        if(action.equalsIgnoreCase("name") && notification instanceof Presentation){
            for(int i=0;i < presentationViews.size(); i++){
                if(presentationViews.get(i).getPresentation().equals(notification)){
                    tabbedPane.setTitleAt(i,((Presentation) notification).getName());
                    break;
                }
            }
        }

        if(action.equalsIgnoreCase("addAction") && notification instanceof Presentation){
            addPresentationViews((Presentation) notification);
        }

        if(action.equalsIgnoreCase("removeAction") && notification instanceof Presentation){
            removePresentationViews((Presentation) notification);
        }
    }
}