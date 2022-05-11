package dsw.rudok.factory.factories;

import dsw.rudok.factory.AbstractNodeFactory;
import dsw.rudok.model.workspace.Slide;
import dsw.rudok.model.workspace.nodes.RuNode;

public class SlideFactory extends AbstractNodeFactory {
    @Override
    protected RuNode createNode(RuNode node, int num) {
        Slide slide = new Slide("Slide " + num, node);
        slide.setCount(num);
        return slide;
    }
}
