/**
 * The type Bintree.
 */
public class Bintree {

    private BintreeNode root;


    /**
     * Instantiates a new Bintree.
     */
    public Bintree() {
        root = null;
    }


    /**
     * Insert.
     *
     * @param x
     *     the x
     * @param y
     *     the y
     * @param seminar
     *     the seminar
     */
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
        else if (x > node.x || y > node.y) {
            node.right = insert(node.right, x, y, seminar);
        }
        else {
            // Handle duplicate key: add seminar to the linked list
            node.seminars.insert(seminar);
        }

        return node;
    }


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
    public void delete(int id) {
        root = delete(root, id);
    }


    private BintreeNode delete(BintreeNode node, int id) {
        return node;

    }


    private BintreeNode findMin(BintreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    /**
     * Print.
     */
// Print method without using in-built data structures
    public void print() {
        System.out.println("Location Tree:");
        print(root, " ");
    }


    private void print(BintreeNode node, String prefix) {
        if (node == null) {
            System.out.println(prefix + "E");
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.println(
                prefix + "Leaf with " + node.seminars.getNumberofSeminars() + " objects: " + node.seminars.print());
        }
        else {
            System.out.println(prefix + "I");
        }

        // Recur on left and right subtrees
        print(node.left, prefix + "  ");
        print(node.right, prefix + "  ");
    }
}
