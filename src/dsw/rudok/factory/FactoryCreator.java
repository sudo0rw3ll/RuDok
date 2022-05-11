package dsw.rudok.factory;

import dsw.rudok.factory.factories.PresentationFactory;
import dsw.rudok.factory.factories.ProjectFactory;
import dsw.rudok.factory.factories.SlideFactory;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.model.workspace.nodes.RuNode;

public class FactoryCreator {

    private static final PresentationFactory presantationFactory = new PresentationFactory();
    private static final ProjectFactory projectFactory = new ProjectFactory();
    private static final SlideFactory slideFactory = new SlideFactory();

    public static AbstractNodeFactory createFactory(RuNode node){
        if(node instanceof Workspace)
            return projectFactory;
        if(node instanceof Project)
            return presantationFactory;
        if(node instanceof Presentation)
            return slideFactory;
        return null;
    }

}
