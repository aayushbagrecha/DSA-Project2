/**
 * Represents a Binary Search Tree (BST) specialized for storing Seminar objects
 * sorted by their associated ID.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class IDBST extends GenericBST<Integer, Seminar> {

    /**
     * Constructs a new empty `IDBST`.
     */
    public IDBST() {
        super(); // Call the constructor of the superclass
    }


    /**
     * Inserts a seminar with an ID into the tree.
     *
     * @param id
     *            The ID of the seminar.
     * @param seminar
     *            The Seminar object to insert.
     * @return True if the insertion was successful, false if an ID collision
     *         occurred.
     */
    public boolean insertSeminar(int id, Seminar seminar) {
        KVPair<Integer, Seminar> data = new KVPair<>(id, seminar);
        if (!contains(data)) { // Check if ID already exists
            insert(data);
            return true;
        }
        else {
            System.out.println("Insert FAILED - "
                + "There is already a record with ID " + id);
            return false;
        }
    }


    /**
     * Get the count of records in the IDBST.
     *
     * @return The number of records in the tree.
     */
    public int getRecordCount() {
        return size();
    }


    /**
     * Prints the contents of the ID Binary Search Tree (BST). This method
     * displays the ID tree by calling the {@link #printTree()} method,
     * providing an overview of the seminars sorted by their IDs.
     */
    public void printIDTree() {
        System.out.println("ID Tree:");
        printTree();
    }


    /**
     * Search for a seminar by its ID.
     *
     * @param id
     *            The ID of the seminar to search for.
     * @return The Seminar object if found, or null if not found.
     */
    public Seminar searchByID(int id) {
        KVPair<Integer, Seminar> data = new KVPair<>(id, null);
        KVPair<Integer, Seminar> result = find(data);
        if (result != null) {
            return result.getValue();
        }
        else {
            return null;
        }
    }


    /**
     * Delete.
     *
     * @param id
     *            the id
     */
    public void delete(int id) {
        KVPair<Integer, Seminar> data = new KVPair<>(id, null);

        if (contains(data)) {
            remove(data);
        }
    }

}




/**
 * Represents a Binary Search Tree (BST) specialized for storing Seminar objects
 * sorted by their associated cost.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class CostBST extends GenericBST<Integer, Seminar> {

    private int visitCount;

    /**
     * Constructs a new empty `IDBST`.
     */
    public CostBST() {
        super(); // Call the constructor of the superclass
    }


    /**
     * Inserts a seminar with an ID into the tree.
     *
     * @param id
     *            The ID of the seminar.
     * @param seminar
     *            The Seminar object to insert.
     */
    public void insertSeminar(int id, Seminar seminar) {

        KVPair<Integer, Seminar> data = new KVPair<>(id, seminar);
        insert(data);
    }


    /**
     * Get the count of records in the IDBST.
     *
     * @return The number of records in the tree.
     */
    public int getRecordCount() {
        return size();
    }


    /**
     * Prints the contents of the Cost Binary Search Tree (BST). This method
     * displays the cost tree by calling the {@link #printTree()} method,
     * providing an overview of the seminars sorted by cost.
     */
    public void printCostTree() {
        System.out.println("Cost Tree:");
        printTree();
    }


    /**
     * Searches for seminars within a specified cost range.
     *
     * @param cost1
     *            The lower bound of the cost range.
     * @param cost2
     *            The upper bound of the cost range.
     */
    private void searchByCost(
        Node node,
        int cost1,
        int cost2,
        StringBuilder result) {

        if (node == null) {
            visitCount++;
            return;
        }

        int nodeCost = node.data.getKey();

        if (nodeCost >= cost1 && nodeCost <= cost2) {
            // The current node's cost is within the specified range
            result.insert(0, node.data.getValue() + "\n");
        }

        visitCount++;

        if (nodeCost >= cost1) {
            // Search in the left subtree
            searchByCost(node.left, cost1, cost2, result);
        }

        if (nodeCost <= cost2) {
            // Search in the right subtree
            searchByCost(node.right, cost1, cost2, result);
        }
    }


    /**
     * Public method to initiate the search and return the result as a string.
     *
     * @param cost1
     *            the cost 1
     * @param cost2
     *            the cost 2
     */
    public void searchByCost(int cost1, int cost2) {
        StringBuilder result = new StringBuilder();
        searchByCost(root, cost1, cost2, result);
        result.append(visitCount).append(" nodes visited in this search");
        System.out.println(result);
    }


    /**
     * Delete.
     *
     * @param cost
     *            the cost
     */
    public void delete(int cost) {
        KVPair<Integer, Seminar> data = new KVPair<>(cost, null);
        if (contains(data)) {
            remove(data);
        }
    }
}




/**
 * Represents a Binary Search Tree (BST) specialized for storing Seminar objects
 * sorted by their associated date.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class DateBST extends GenericBST<String, Seminar> {

    private int visitCount;

    /**
     * Constructs a new empty `IDBST`.
     */
    public DateBST() {
        super(); // Call the constructor of the superclass
    }


    /**
     * Inserts a seminar with an ID into the tree.
     *
     * @param id
     *            The ID of the seminar.
     * @param seminar
     *            The Seminar object to insert.
     */
    public void insertSeminar(String id, Seminar seminar) {

        KVPair<String, Seminar> data = new KVPair<>(id, seminar);
        insert(data);

    }


    /**
     * Get the count of records in the IDBST.
     *
     * @return The number of records in the tree.
     */
    public int getRecordCount() {
        return size();
    }


    /**
     * Prints the contents of the Date Binary Search Tree (BST). This method
     * displays the date tree by calling the {@link #printTree()} method,
     * providing an overview of the seminars sorted by date.
     */
    public void printDateTree() {
        System.out.println("Date Tree:");
        printTree();
    }


    /**
     * Searches for seminars within a specified date range.
     *
     * @param date1
     *            The start date of the date range.
     * @param date2
     *            The end date of the date range.
     */
    public void searchByDate(String date1, String date2) {
        searchByDate(root, date1, date2);
        System.out.println(visitCount + " nodes visited in this search");
    }


    private void searchByDate(Node node, String date1, String date2) {
        if (node == null) {
            visitCount++; // null nodes are counted as visited
            return;
        }

        String nodeDate = node.data.getKey(); // Get the date as a string

        visitCount++;

        // Compare strings using compareTo method
        if (nodeDate.compareTo(date1) >= 0 && nodeDate.compareTo(date2) <= 0) {
            // The current node's date is within the specified range
            System.out.println(node.data.getValue());
            searchByDate(node.left, date1, date2);
            searchByDate(node.right, date1, date2);
        }

        // if node value is greater than given value then always go to the left
        if (nodeDate.compareTo(date1) < 0) {
            searchByDate(node.left, date1, date2);
        }

        // if node value is lesser, then always go to the right
        if (nodeDate.compareTo(date2) >= 0) {
            searchByDate(node.right, date1, date2);
        }
    }


    /**
     * Delete.
     *
     * @param date
     *            the date
     */
    public void delete(String date) {
        KVPair<String, Seminar> data = new KVPair<>(date, null);
        if (contains(data)) {
            remove(data);
        }
    }

}




/**
 * Represents a Binary Search Tree (BST) specialized for storing Seminar objects
 * sorted by their associated keywords.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class KeywordsBST extends GenericBST<String, Seminar> {

    /**
     * Constructs a new empty `IDBST`.
     */
    public KeywordsBST() {
        super(); // Call the constructor of the superclass
    }


    /**
     * Inserts a seminar with an ID into the tree.
     *
     * @param seminar
     *            The Seminar object to insert.
     * @param ids
     *            the ids
     */
    public void insertSeminar(Seminar seminar, String... ids) {

        for (String id : ids) {
            KVPair<String, Seminar> data = new KVPair<>(id, seminar);
            insert(data);
        }
    }


    /**
     * Get the count of records in the IDBST.
     *
     * @return The number of records in the tree.
     */
    public int getRecordCount() {
        return size();
    }


    /**
     * Prints the contents of the Keyword Binary Search Tree (BST). This method
     * displays the keywords tree by calling the {@link #printTree()} method,
     * providing an overview of the seminars sorted by keywords.
     */
    public void printKeywordsTree() {
        System.out.println("Keyword Tree:");
        printTree();
    }


    /**
     * Search for seminars by their keyword and print them.
     *
     * @param keywordValue
     *            The keyword to search for.
     */
    public void searchByKeyword(String keywordValue) {
        System.out.println("Seminars matching keyword " + keywordValue + ":");
        StringBuilder output = new StringBuilder();
        searchByKeyword(root, keywordValue, output);
        System.out.print(output);
    }


    private void searchByKeyword(
        Node node,
        String keywordValue,
        StringBuilder output) {
        if (node == null) {
            return;
        }

        String nodeKeyword = node.data.getKey();

        // Compare strings using equals method
        if (nodeKeyword.equals(keywordValue)) {
            // The current node's keyword matches the specified keyword
            output.insert(0, node.data.getValue() + "\n");

        }

        // Search in both subtrees
        searchByKeyword(node.left, keywordValue, output);
        searchByKeyword(node.right, keywordValue, output);
    }


    /**
     * Delete.
     *
     * @param keywords
     *            the keywords
     */
    public void delete(String[] keywords) {
        for (String keyword : keywords) {
            KVPair<String, Seminar> data = new KVPair<>(keyword, null);
            if (contains(data)) {
                remove(data);
            }
        }
    }
}
