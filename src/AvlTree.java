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

        return balance(node);
    }

    private AvlTreeNode balance(AvlTreeNode root) {
        
        if(isLeftHeavy(root)) {
            var rootNodeLeftChildBalanceFactor = balanceFactor(root);

            // if the balance factor of the tree is less than 0 then we need to perform a right rotation.
            if(rootNodeLeftChildBalanceFactor < 0) {
                root.left = rotateLeft(root.left);
            }

            // Since the tree is already left heavy then we need to perform left rotation.
            root = rotateRight(root);

        } 
        else if (isRightHeavy(root)) {
            var rootNodeRightChildBalanceFactor = balanceFactor(root.right);

            // if the balance factor of the right child is > 0 then we need to perform a left rotation
            if(rootNodeRightChildBalanceFactor > 0) {
                root.right = rotateRight(root.right);
            }
            // since the tree is right heavy then we must perform a left rotate.
            root = rotateLeft(root);
        }

        return root;
    }

    private AvlTreeNode rotateLeft(AvlTreeNode root) {
        AvlTreeNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        newRoot.height = height(newRoot);
        root.height = height(root);

        return newRoot;
    }

    private AvlTreeNode rotateRight(AvlTreeNode root) {
        AvlTreeNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        newRoot.height = height(newRoot);
        root.height = height(root);

        return newRoot;
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