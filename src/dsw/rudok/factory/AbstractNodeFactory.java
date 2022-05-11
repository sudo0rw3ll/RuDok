package dsw.rudok.factory;

import dsw.rudok.model.workspace.nodes.RuNode;

public abstract class AbstractNodeFactory {

    public RuNode getRuNode(RuNode node, int num){return createNode(node, num);}

    protected abstract RuNode createNode(RuNode node, int num);
}
