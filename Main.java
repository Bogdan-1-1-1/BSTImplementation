public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        for (int i = 0; i < 10; i++) {
            bst.add(i);
        }
        bst.printTree();
        System.out.println(bst.contains(5));
        bst.remove(5);
        bst.printTree();
        System.out.println(bst.contains(5));

        bst.removeAll();
        System.out.println(bst.size());
        bst.printTree();

        for (int i = 0; i < 50; i++) {
            bst.add((int)(Math.random()*100)); //important remark: tree can contain only
            // one instance of every integer so result size may be smaller than 50
        }
        bst.printTree();
        System.out.println(bst.size());
    }
}
