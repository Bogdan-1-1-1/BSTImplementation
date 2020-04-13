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
    private Node next(Node currNode) {
        int searchValue = currNode.value + 1;
        while(currNode != null) {
            if(currNode.value == searchValue) return currNode;
            if(currNode.value > searchValue) {
                currNode = currNode.left;
            } else currNode = currNode.right;
        }
        return currNode.parent;
    }
    private void localPrint(Node printNode) {
        try {
            if (printNode.right != null) localPrint(printNode.right);
            if (printNode.left != null) localPrint(printNode.left);
            System.out.print(printNode.value + " ");
        } catch (NullPointerException e) {
            System.out.print("Tree is empty");
        }
    }
    private int valOfLeavesByNode(Node currNode) {
        try {
            if (currNode.right != null && currNode.left != null)
                return valOfLeavesByNode(currNode.right)+ valOfLeavesByNode(currNode.left) + 1;
            if (currNode.left != null) return valOfLeavesByNode(currNode.left)+1;
            if(currNode.right != null) return valOfLeavesByNode(currNode.right)+1;
        } catch (NullPointerException e) {
            return 0;
        }
        return 0;
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
    public void remove(int value) {
        Node remNode = root;
        size--;

        while(remNode != null) {
            if(remNode.value == value) break;
            if(remNode.value > value) remNode = remNode.left;
            else if(remNode.value < value) remNode = remNode.right;
        }
        if(remNode == null) return;

        if(remNode.left == null && remNode.right == null) {
            if(remNode.parent.value > remNode.value) remNode.parent.left = null;
            else remNode.parent.right = null;
            return;
        }
        if(remNode.left == null) {
            if(remNode.parent.value > remNode.value) {
                remNode.parent.left = remNode.right;
            } else {
                remNode.parent.right = remNode.right;
            }
            return;
        }
        if(remNode.right == null) {
            if(remNode.parent.value > remNode.value) {
                remNode.parent.left = remNode.left;
            } else {
                remNode.parent.right = remNode.left;
            }
            return;
        }
        if(remNode.left != null && remNode.right != null) {
            Node repNode = next(remNode);
            repNode.right.parent = repNode.parent;

            if(repNode.parent.value > repNode.value) {
                repNode.parent.left = repNode.right;
            } else repNode.parent.right = repNode.right;

            repNode.parent = remNode.parent;
            repNode.left = remNode.left;
            remNode.left.parent = repNode;
            repNode.right = remNode.right;
            remNode.right.parent = repNode;

            if(remNode.parent.value > remNode.value) {
                remNode.parent.left = repNode;
            } else remNode.parent.right = repNode;

            return;
        }
    }
    public void removeAll() {
        size = 0;
        root = null;
    }
    public void printTree() {
        localPrint(root);
        System.out.println();
    }
    public int valOfLeaves(int value) {
        Node currentNode = root;

        while(currentNode != null) {
            if(currentNode.value == value) break;
            if(currentNode.value > value) currentNode = currentNode.left;
            else if(currentNode.value < value) currentNode = currentNode.right;
        }
        if(currentNode == null) return 0;

        return valOfLeavesByNode(currentNode);
    }

}

