package dsw.rudok.model.workspace;

import dsw.rudok.app.MainFrame;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;

public class Project extends RuNodeComposite implements Serializable {

    private transient boolean changed;
    private File projectFile;

    public Project(String name, RuNode parent) {
        super(name, parent);
        this.changed = false;
        this.projectFile = null;
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Presentation){
            super.addChild(child);
            setChanged(true);
            System.out.println(this.changed);
        }
    }

    @Override
    public String toString() {
        return "Project ";
    }

    public boolean isChanged() {
        return changed;
    }

    public File getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(File projectFile) {
        this.projectFile = projectFile;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getRudokTree());
    }
}
