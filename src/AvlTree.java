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

        // setting the node height.
        node.height = height(node);

        if(isLeftHeavy(node)) {
            System.out.println(node.value + " is left heavy a right rotation is required");
        } 
        else if (isRightHeavy(node)) {
            System.out.println(node.value + " is right heavy, a left rotation is required");
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

    private int balanceFactor(AvlTreeNode node) {
        return (height(node.left) - height(node.right));
    }

    private boolean isLeftHeavy(AvlTreeNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AvlTreeNode node) {
        return balanceFactor(node) < -1;
    }
}