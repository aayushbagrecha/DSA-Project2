public class Bintree {
    private class BintreeNode {
        private Seminar seminar;
        private BintreeNode left;
        private BintreeNode right;


        public BintreeNode(Seminar seminar) {
            this.seminar = seminar;
            left = null;
            right = null;
        }
    }




    private BintreeNode root;


    public Bintree() {
        root = null;
    }


    // Insert method without using in-built data structures
    public void insert(int x, int y, Seminar seminar) {
        root = insert(root, seminar);
    }


    private BintreeNode insert(BintreeNode node, Seminar seminar) {
        if (node == null) {
            return new BintreeNode(seminar);
        }

        // Compare seminars using x-coordinate and y-coordinate
        int cmpX = Integer.compare(seminar.x(), node.seminar.x());
        int cmpY = Integer.compare(seminar.y(), node.seminar.y());

        // Insert recursively based on x-coordinate and y-coordinate
        if (cmpX < 0 || (cmpX == 0 && cmpY < 0)) {
            node.left = insert(node.left, seminar);
        }
        else if (cmpX > 0 || (cmpX == 0 && cmpY > 0)) {
            node.right = insert(node.right, seminar);
        }

        return node;
    }


    // Search method without using in-built data structures
    public Seminar search(int x, int y) {
        return search(root, x, y);
    }


    private Seminar search(BintreeNode node, int x, int y) {
        if (node == null) {
            return null;
        }

        if (node.seminar.x() == x && node.seminar.y() == y) {
            return node.seminar;
        }

        // Check left subtree if x-coordinate is greater
        if (node.seminar.x() > x) {
            return search(node.left, x, y);
        }
        // Check right subtree if x-coordinate is smaller
        else {
            return search(node.right, x, y);
        }
    }


    // Delete method without using in-built data structures
    public void delete(int x, int y) {
        root = delete(root, x, y);
    }


    private BintreeNode delete(BintreeNode node, int x, int y) {
        if (node == null) {
            return null;
        }

        if (node.seminar.x() == x && node.seminar.y() == y) {
            // Case 1: Node with one child or no child
            if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }

            // Case 2: Node with two children
            BintreeNode successor = findMin(node.right);
            node.seminar = successor.seminar;
            node.right = delete(node.right, successor.seminar.x(),
                successor.seminar.y());
        }
        else if (node.seminar.x() > x || (node.seminar.x() == x && node.seminar.y() > y)) {
            node.left = delete(node.left, x, y);
        }
        else {
            node.right = delete(node.right, x, y);
        }

        return node;
    }


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
            System.out.println(prefix + "null");
            return;
        }

        // Preorder traversal
        System.out.println(prefix + node.seminar.x() + " " + node.seminar.y());

        // Recur on left and right subtrees
        print(node.left, prefix + " ");
        print(node.right, prefix + " ");
    }
}
