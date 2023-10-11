/**
 * The type World test.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class BintreeNode {
    /**
     * The X.
     */
    protected int x;
    /**
     * The Y.
     */
    protected int y;
    /**
     * The Seminars.
     */
    protected SeminarNode seminars;
    /**
     * The Left.
     */
    protected BintreeNode left;
    /**
     * The Right.
     */
    protected BintreeNode right;

    /**
     * Instantiates a new Bintree node.
     *
     * @param x
     *            the x
     * @param y
     *            the y
     * @param seminar
     *            the seminar
     */
    BintreeNode(int x, int y, Seminar seminar) {
        this.x = x;
        this.y = y;
        this.seminars = new SeminarNode(seminar);
        this.left = null;
        this.right = null;
    }

}




/**
 * The type World test.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class SeminarNode {
    /**
     * The Seminar.
     */
    protected Seminar seminar;
    /**
     * The Next.
     */
    protected SeminarNode next;

    /**
     * Instantiates a new Seminar node.
     *
     * @param seminar
     *            the seminar
     */
    public SeminarNode(Seminar seminar) {
        this.seminar = seminar;
        this.next = null;
    }


    /**
     * Insert.
     *
     * @param seminar
     *            the seminar
     */
    // Insert a seminar at the end of the linked list
    public void insert(Seminar seminarToInsert) {
        SeminarNode newNode = new SeminarNode(seminarToInsert);
        SeminarNode current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }


    /**
     * print.
     *
     * @return the string builder
     */
    public StringBuilder print() {
        StringBuilder output = new StringBuilder();
        SeminarNode current = this;
        while (current != null) {
            output.append(current.seminar.id()).append(" ");
            current = current.next;
        }
        // output.append("\n");
        return output;
    }


    /**
     * Search seminar.
     *
     * @param id
     *            the id
     * @return the seminar
     */
    // Search for a seminar with a specific ID in the linked list
    public Seminar search(int id) {
        SeminarNode current = this;
        while (current != null) {
            if (current.seminar.id() == id) {
                return current.seminar;
            }
            current = current.next;
        }
        return null; // Seminar not found
    }


    /**
     * Delete boolean.
     *
     * @param id
     *            the id
     * @return the boolean
     */
    // Delete a seminar with a specific ID from the linked list
    public boolean delete(int id) {
        SeminarNode current = this;
        SeminarNode prev = null;
        while (current != null) {
            if (current.seminar.id() == id) {
                if (prev != null) {
                    prev.next = current.next; // Skip the current node
                }
                else {
                    // If the node to be deleted is the first node
                    // Update the head of the linked list
                    this.seminar = current.next.seminar;
                    this.next = current.next.next;
                }
                return true; // Seminar deleted successfully
            }
            prev = current;
            current = current.next;
        }
        return false; // Seminar with given ID not found
    }


    /**
     * Gets numberof seminars.
     *
     * @return the numberof seminars
     */
    public int getNumberofSeminars() {
        SeminarNode current = this;

        if (this.seminar == null) {
            return 0;
        }

        int count = 1;
        while (current.next != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }
}
