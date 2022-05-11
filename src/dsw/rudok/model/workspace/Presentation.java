package dsw.rudok.model.workspace;

import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;

public class Presentation extends RuNodeComposite{

    private String author;
    private String themePath;

    public Presentation(String name, RuNode parent) {
        super(name, parent);
        this.author = "Unknown Author";
        this.themePath = "slike/background.jpg";
    }

    public Presentation(String name, RuNode parent, String author, String themePath){
        super(name,parent);
        this.author = author;
        this.themePath = themePath;
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Slide){
            super.addChild(child);
        }
    }

    @Override
    public String toString() {
        return "Presentation ";
    }

    public String getAuthor() {
        return author;
    }

    public String getThemePath() {
        return themePath;
    }

    public void setAuthor(String author) {
        this.author = author;
        notifyRudokSubscriber(this, "changedAuthor");
    }

    public void setThemePath(String themePath) {
        this.themePath = themePath;
        notifyRudokSubscriber(themePath, "changedTheme");
    }
}
