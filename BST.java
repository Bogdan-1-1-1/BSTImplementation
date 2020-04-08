public class BST<Integer>{
    private Node root;
    private int size;

    private class Node {
        Node left, right, parent;
        int value;

        public Node(int value) {
            this.value = value;
            left = right = parent = null;
        }
        public Node(Node parent, int value) {
            this.value = value;
            left = right = null;
            this.parent = parent;
        }
    }

    public BST() {
        size = 0;
        root = null;
    }

    public void add(int value) {
        if(size > 0) {
            Node currentNode = root, parentNode = null;

            while(currentNode != null) {
                parentNode = currentNode;
                if(currentNode.value == value) return;
                if(currentNode.value < value) {
                    currentNode = currentNode.right;
                } else  {
                    currentNode = currentNode.left;
                }
            }

            currentNode = new Node(parentNode, value);
            if(parentNode.value <= value) parentNode.right = currentNode;
                else parentNode.left = currentNode;

        } else root = new Node(value);

        size++;
    }
    public boolean contains(int value) {
        Node currentNode = root;

        while(currentNode != null) {
            if(currentNode.value == value) return true;
            if(currentNode.value > value) currentNode = currentNode.left;
                else if(currentNode.value < value) currentNode = currentNode.right;
        }

        return false;
    }
    public int size() { return size;}

    public void printTree() {
        localPrint(root);
    }
    private void localPrint(Node printNode) {
        if(printNode.right != null) localPrint(printNode.right);
        if(printNode.left != null) localPrint(printNode.left);
        System.out.print(printNode.value + " ");
    }
}
