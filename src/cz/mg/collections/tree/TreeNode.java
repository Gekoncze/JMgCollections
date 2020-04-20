//package cz.mg.collections.tree;
//
//import cz.mg.collections.list.List;
//import cz.mg.collections.list.chainlist.CachedChainList;
//import cz.mg.collections.list.chainlist.ChainList;
//import java.util.Iterator;
//
//
//public abstract class TreeNode<P extends TreeNode, C extends TreeNode> {
//    P parent = null;
//    private final TreeNodeChildren<C> children = new TreeNodeChildren<>(this);
//    final ChainList<TreeNodeParentChangeListener> parentChangeListeners = new ChainList<>();
//
//    public TreeNode() {
//    }
//
//    public TreeNode(P parent) {
//        setParent(parent);
//    }
//
//    public final P getParent() {
//        return parent;
//    }
//
//    public final void setParent(P parent) {
//        if(parent == null && this.parent != null) this.parent.getChildren().remove(this);
//        if(parent != null) parent.getChildren().addCollectionLast(this);
//    }
//
//    public final List<C> getChildren() {
//        return children;
//    }
//
//    public final ChainList<TreeNodeParentChangeListener> getParentChangeListeners() {
//        return parentChangeListeners;
//    }
//
//    public final Iterator<C> iterator() {
//        return children.iterator();
//    }
//
//    public interface TreeNodeParentChangeListener {
//        public void parentChanged();
//    }
//
//    private static class TreeNodeChildren<T extends TreeNode> extends CachedChainList<T> {
//        private final TreeNode parent;
//
//        public TreeNodeChildren(TreeNode parent) {
//            this.parent = parent;
//        }
//
//        @Override
//        protected void onItemAdded(T data) {
//            if(data.parent != null) data.parent.getChildren().remove(data);
//            data.parent = parent;
//            super.onItemAdded(data);
//            notifyParentChanged(data);
//        }
//
//        private void notifyParentChanged(T data){
//            ChainList<TreeNode.TreeNodeParentChangeListener> listeners = data.parentChangeListeners;
//            for(TreeNode.TreeNodeParentChangeListener l : listeners) l.parentChanged();
//        }
//
//        @Override
//        protected void onItemRemoved(T data) {
//            data.parent = null;
//            super.onItemRemoved(data);
//            notifyParentChanged(data);
//        }
//    }
//}
