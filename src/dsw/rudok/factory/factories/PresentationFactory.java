package dsw.rudok.factory.factories;

import dsw.rudok.factory.AbstractNodeFactory;
import dsw.rudok.model.workspace.Presentation;
import dsw.rudok.model.workspace.nodes.RuNode;

public class PresentationFactory extends AbstractNodeFactory {

    @Override
    protected RuNode createNode(RuNode node, int num) {
        Presentation presentation = new Presentation("Presentation " + num, node);
        return presentation;
    }
}
