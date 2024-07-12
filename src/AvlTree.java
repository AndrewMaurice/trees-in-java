public class AvlTree {
    private class AvlTreeNode {
        int value;
        int height;

        AvlTreeNode left;
        AvlTreeNode right;

        public AvlTreeNode(int value) {
            this.value = value;
            height = 0;
            left = null;
            right = null;
        }
    }

    private AvlTreeNode root;

    public AvlTree() {
        root = null;
    }

    public AvlTree(int rootValue) {
        root = new AvlTreeNode(rootValue);
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private AvlTreeNode insert(AvlTreeNode node, int value) {
        if(node == null) {
            return new AvlTreeNode(value);
        }

        if(value <= node.value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        return node;
    }

    public int height() {
        return height(root);
    }

    private int height(AvlTreeNode node) {
        if(node == null) {
            return -1;
        }

        return Math.max(height(node.left), height(node.right)) + 1;
    }
}