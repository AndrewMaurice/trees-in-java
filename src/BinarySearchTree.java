import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private class BinarySearchTreeNode {
        int value;
        BinarySearchTreeNode left;
        BinarySearchTreeNode right;

        public BinarySearchTreeNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    private BinarySearchTreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(int rootValue) {
        root = new BinarySearchTreeNode(rootValue);
    }

    public void insert(int value) {
        if(null == this.root) {
            this.root = new BinarySearchTreeNode(value);
        }

        BinarySearchTreeNode currentNode = this.root;
        
        while(true) {
            if(value <= currentNode.value && null == currentNode.left) {
                currentNode.left = new BinarySearchTreeNode(value);
            }
            else if(value > currentNode.value && null == currentNode.right) {
                currentNode.right = new BinarySearchTreeNode(value);
            }
            else if(value <= currentNode.value) {
                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }
    }

    public boolean find(int value) {

        BinarySearchTreeNode currentNode = this.root;

        while (true) {
            if(currentNode == null) {
                return false;
            }
            else if(value == currentNode.value) {
                return true;
            }
            else if(value < currentNode.value) {
                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }
    }

    @Override()
    public String toString() {
        return toStringDepthOrder(this.root);
    }

    public String toString(boolean levelOrderTraversal) {
        
        if(levelOrderTraversal) {
            return toStringLevelOrder(root);
        }

        return toStringDepthOrder(root);
    }

    private String toStringDepthOrder(BinarySearchTreeNode node) {
        if(null == node) {
            return "";
        }

        return toStringDepthOrder(node.left) + " " + node.value + " " + toStringDepthOrder(node.right) + " ";
    }

    public int height() {
        return height(root);
    }

    private int height(BinarySearchTreeNode node) {
        if(null == node) {
            return -1;
        }

        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public boolean areEqual(BinarySearchTree tree) {
        return areEqual(root, tree.root);
    }

    public boolean areEqual(BinarySearchTreeNode node1, BinarySearchTreeNode node2) {
        
        // in case I reached the end of the twi trees
        if(null == node1 && null == node2) {
            return true;
        }
        // not having the same length
        if((node1 == null && node2 != null) || (node1 != null && node2 == null)) {
            return false;
        }
        // if the two values don't match.
        if(node1.value != node2.value) {
            return false;
        }

        return areEqual(node1.left, node2.left) && areEqual(node1.right, node2.right);
    }

    public List<Integer> getElementsAtDistance(int distance) {
        var elements = new ArrayList<Integer>();
        getElementsAtDistance(root, distance, elements);
        return elements;
    }

    private void getElementsAtDistance(BinarySearchTreeNode node, int distance, ArrayList<Integer> elements) {
        
        // If I traversed and didn't find a level with the privided distance then return nothing.
        if(null == node) {
            return;
        }
        
        // if I'm at distance 0 i.e. I reached my desired location then return the current node value.
        if(distance == 0) {
            elements.add(node.value);
            return;
        }

        // when traversing we need to decrease the distance, to reach zero as this will be the target instead of 
        // adding an accumlator to the method to check if the current level has the same provided distance.
        getElementsAtDistance(node.left, distance - 1, elements);
        getElementsAtDistance(node.right, distance - 1, elements);
    }

    private String toStringLevelOrder(BinarySearchTreeNode node) {
        StringBuilder result = new StringBuilder();
        
        for(int i = 0; i <= height(node); i++) {
            for(var element: getElementsAtDistance(i)) {
                result.append(element + " ");
            }
        }

        return result.toString();
    }

    public int min() {
        return min(root);
    }

    private int min(BinarySearchTreeNode node) {
        if(node.left == null || node.right == null) {
            return node.value;
        }

        var leftMin = min(node.left);
        var rightMin = min(node.right);

        return Math.min(node.value, (Math.min(leftMin, rightMin)));
    }

    public boolean isValidBst() {
        return isValidBst(root.left, Integer.MIN_VALUE, root.value - 1) && isValidBst(root.right, root.value + 1, Integer.MAX_VALUE);
    }

    private boolean isValidBst(BinarySearchTreeNode node, int minValue, int maxValue) {
        if(node == null) {
            return true;
        }

        if(maxValue < node.value || minValue > node.value) {
            return false;
        }
        
        return isValidBst(node.left, minValue, node.value - 1) && isValidBst(node.right, node.value + 1, maxValue);
    }

}
