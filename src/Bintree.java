public class Bintree {

    private BintreeNode root;


    public Bintree() {
        root = null;
    }


    // Insert method without using in-built data structures
    public void insert(int x, int y, Seminar seminar) {
        root = insert(root, x, y, seminar);
    }


    private BintreeNode insert(
        BintreeNode node, int x, int y, Seminar seminar) {
        if (node == null) {
            return new BintreeNode(x, y, seminar);
        }

        if (x < node.x || (x == node.x && y < node.y)) {
            node.left = insert(node.left, x, y, seminar);
        }
        else if (x > node.x || (x == node.x && y > node.y)) {
            node.right = insert(node.right, x, y, seminar);
        }
        else {
            // Handle duplicate key: add seminar to the linked list
            node.seminars.insert(seminar);
//            System.out.println("PRINTING LINKEDLIST");
            node.seminars.print();
//            System.out.println("PRINTING ENDED");
        }

        return node;
    }

    // Search method without using in-built data structures
//    public SeminarNode search(int x, int y) {
//        return search(root, x, y);
//    }

//    public Seminar search(int x, int y) {
//        return null;
//    }


    private SeminarNode search(BintreeNode node, int x, int y) {
        if (node == null) {
            return null;
        }

        if (node.seminars.seminar.x() == x && node.seminars.seminar.y() == y) {
            return node.seminars;
        }

        // Check left subtree if x-coordinate is greater
        if (node.seminars.seminar.x() > x) {
            return search(node.left, x, y);
        }
        // Check right subtree if x-coordinate is smaller
        else {
            return search(node.right, x, y);
        }
    }

    // Delete method without using in-built data structures
//    public void delete(int x, int y) {
//        root = delete(root, x, y);
//    }

//    private BintreeNode delete(BintreeNode node, int x, int y) {
//        if (node == null) {
//            return null;
//        }
//
//        if (node.seminar.x() == x && node.seminar.y() == y) {
//            // Case 1: Node with one child or no child
//            if (node.left == null) {
//                return node.right;
//            }
//            else if (node.right == null) {
//                return node.left;
//            }
//
//            // Case 2: Node with two children
//            BintreeNode successor = findMin(node.right);
//            node.seminar = successor.seminar;
//            node.right = delete(node.right, successor.seminar.x(),
//                successor.seminar.y());
//        }
//        else if (node.seminar.x() > x || (node.seminar.x() == x && node.seminar.y() > y)) {
//            node.left = delete(node.left, x, y);
//        }
//        else {
//            node.right = delete(node.right, x, y);
//        }
//
//        return node;
//    }


    private BintreeNode findMin(BintreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    // Print method without using in-built data structures
    public void print() {
        print(root, "");
    }


    private void print(BintreeNode node, String prefix) {
        if (node == null) {
            System.out.println(prefix + "E");
            return;
        }

        // Preorder traversal
//        System.out.println(
//            prefix + node.x + " " + node.y + " WITH NODES:" + node.seminars.getNumberofSeminars());

        if (node.left == null && node.right == null) {
            System.out.println(
                prefix + "Leaf with " + node.seminars.getNumberofSeminars() + " objects:");
        }
        else {
            System.out.println(
                node.left.seminars.seminar.x() + " " + node.right.seminars);
        }
        // Recur on left and right subtrees
        print(node.left, prefix + " ");
        print(node.right, prefix + " ");
    }
}
