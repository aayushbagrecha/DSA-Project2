public class BintreeNode {
    Seminar seminar;
    BintreeNode next; // Reference to the next seminar with the same coordinates
    BintreeNode left;
    BintreeNode right;


    public BintreeNode(Seminar seminar) {
        this.seminar = seminar;
        this.next = null;
        this.left = null;
        this.right = null;
    }
}
