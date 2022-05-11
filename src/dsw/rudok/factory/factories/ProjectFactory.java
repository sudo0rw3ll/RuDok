package dsw.rudok.factory.factories;

import dsw.rudok.factory.AbstractNodeFactory;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.nodes.RuNode;

public class ProjectFactory extends AbstractNodeFactory {
    @Override
    protected RuNode createNode(RuNode node, int num) {
        Project project = new Project("Projekat " + num, node);
        return project;
    }
}
