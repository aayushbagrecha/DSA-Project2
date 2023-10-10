class BintreeNode {
    int x;
    int y;
    SeminarNode seminars;
    BintreeNode left;
    BintreeNode right;


    BintreeNode(int x, int y, Seminar seminar) {
        this.x = x;
        this.y = y;
        this.seminars = new SeminarNode(seminar);
        this.left = null;
        this.right = null;
    }

}




class SeminarNode {
    Seminar seminar;
    SeminarNode next;


    public SeminarNode(Seminar seminar) {
        this.seminar = seminar;
        this.next = null;
    }


    // Insert a seminar at the end of the linked list
    public void insert(Seminar seminar) {
        SeminarNode newNode = new SeminarNode(seminar);
        SeminarNode current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }


    public void print() {
        SeminarNode current = this;
        while (current != null) {
            System.out.println(current.seminar);
            current = current.next;
        }
    }


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


    public int getNumberofSeminars() {
        SeminarNode current = this;

        if (this.seminar == null) {
            return 0;
        }

        int count = 0;
        while (current.next != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }
}
