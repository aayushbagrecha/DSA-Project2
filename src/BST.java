/**
 * The `KVPair` class represents a key-value pair with a comparable key.
 *
 * @param <K>
 *     The type of the key, which must extend Comparable.
 * @param <E>
 *     The type of the value.
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class KVPair<K extends Comparable<K>, E> implements Comparable<KVPair<K, E>> {

    private K theKey; // The key
    private E theVal; // The value


    /**
     * Constructs a new `KVPair` instance with the given key and value.
     *
     * @param key
     *     The key of the key-value pair.
     * @param value
     *     The value associated with the key.
     */
    public KVPair(K key, E value) {
        this.theKey = key;
        this.theVal = value;
    }


    /**
     * Gets the key of the key-value pair.
     *
     * @return The key.
     */
    public K getKey() {
        return theKey;
    }


    /**
     * Sets the key.
     *
     * @param key
     *     the key
     */
    public void setTheKey(K key) {
        this.theKey = key;
    }


    /**
     * Sets the val.
     *
     * @param val
     *     the val
     */
    public void setTheVal(E val) {
        this.theVal = val;
    }


    /**
     * Gets the value associated with the key.
     *
     * @return The value.
     */
    public E getValue() {
        return theVal;
    }


    /**
     * Compares this `KVPair` to another `KVPair` based on their keys.
     *
     * @param otherPair
     *     The other `KVPair` to compare to.
     * @return A negative integer, zero, or a positive integer as this `KVPair`
     *     is less than, equal to, or greater than the specified `KVPair`.
     */
    public int compareTo(KVPair<K, E> otherPair) {
        return this.theKey.compareTo(otherPair.getKey());
    }


    /**
     * Compares this `KVPair` to a key based on their keys.
     *
     * @param it
     *     The key to compare to.
     * @return A negative integer, zero, or a positive integer as this `KVPair`
     *     key is less than, equal to, or greater than the specified key.
     */
    public int compareTo(K it) {
        return theKey.compareTo(it);
    }
}




/**
 * The `GenericBST` class represents a generic binary search tree (BST) that
 * stores key-value pairs.
 *
 * @param <K>
 *     The type of the key, which must extend Comparable.
 * @param <V>
 *     The type of the value.
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class GenericBST<K extends Comparable<K>, V> {
    // Inner class representing a node in the BST




    /**
     * Represents a node in the Binary Search Tree (BST). Each node holds a
     * key-value pair and references to its left and right children.
     */
    protected class Node {
        /**
         * The key-value pair stored in the node.
         */
        KVPair<K, V> data;
        /**
         * Reference to the left child node.
         */
        protected Node left;
        /**
         * Reference to the right child node.
         */
        protected Node right;


        /**
         * Constructs a new node with the given data.
         *
         * @param data
         *     The key-value pair to store in the node.
         */
        public Node(KVPair<K, V> data) {
            this.data = data;
            left = null;
            right = null;
        }
    }




    /**
     * The root node of the Binary Search Tree (BST). It represents the starting
     * point of the tree structure, from which all other nodes are descended.
     */
    protected Node root;
    private int nodeCount; // To keep track of the number of nodes


    /**
     * Constructs a new empty `GenericBST`.
     */
    public GenericBST() {
        root = null;
        nodeCount = 0;
    }


    /**
     * Inserts a key-value pair into the binary search tree.
     *
     * @param data
     *     The key-value pair to insert.
     */
    public void insert(KVPair<K, V> data) {
        root = inserthelp(root, data);
        nodeCount++; // Increment nodeCount when a new node is inserted
    }


    /**
     * Recursively inserts a new key-value pair into the Binary Search Tree
     * (BST) rooted at the given node.
     *
     * @param root
     *     The root node of the BST where the new data will be inserted.
     * @param data
     *     The key-value pair to be inserted into the BST.
     * @return The updated root node of the BST after insertion.
     */
    private Node inserthelp(Node root, KVPair<K, V> data) {
        // If the current node is null, create a new node with the given data
        // and return it
        if (root == null) {
            root = new Node(data);
            return root;
        }
        // Compare the data with the current node's data and insert accordingly
        if (data.compareTo(root.data) <= 0) {
            root.left = inserthelp(root.left, data);
        }
        else if (data.compareTo(root.data) > 0) {
            root.right = inserthelp(root.right, data);
        }
        return root;
    }


    /**
     * Finds and retrieves the value associated with a given key.
     *
     * @param data
     *     The key-value pair to search for.
     * @return The value associated with the key, or null if the key is not
     *     found.
     */
    public KVPair<K, V> find(KVPair<K, V> data) {
        return findhelp(root, data);
    }


    /**
     * Recursively finds a key-value pair with the given key in the Binary
     * Search Tree (BST) rooted at the specified node.
     *
     * @param root
     *     The root node of the BST where the search will be performed.
     * @param data
     *     The key-value pair to be searched for in the BST.
     * @return The key-value pair found in the BST, or null if not found.
     */
    private KVPair<K, V> findhelp(Node root, KVPair<K, V> data) {
        // If the current node is null, the data is not found in the BST
        if (root == null)
            return null;

        if (root.data.compareTo(data) > 0)
            return findhelp(root.left, data);

        else if (root.data.compareTo(data) == 0)
            return root.data;

        else
            return findhelp(root.right, data);
    }


    /**
     * Checks if the binary search tree contains a given key.
     *
     * @param data
     *     The key to check for.
     * @return True if the key is found in the tree, false otherwise.
     */
    public boolean contains(KVPair<K, V> data) {
        return findhelp(root, data) != null;
    }


    /**
     * Gets the number of key-value pairs in the binary search tree.
     *
     * @return The number of key-value pairs.
     */
    public int size() {
        return nodeCount;
    }


    /**
     * Prints the contents of the binary search tree. If the tree is empty, it
     * prints a message indicating that.
     */
    public void printTree() {
        if (root == null) {
            System.out.println("This tree is empty");
        }
        else {

            printTreeUtil(root, " ");
            System.out.println("Number of records: " + size());
        }
    }


    private void printTreeUtil(Node node, String prefix) {
        if (node == null) {
            System.out.println(prefix + "null");
            return;
        }

        printTreeUtil(node.right, prefix + "  ");
        System.out.println(prefix + node.data.getKey());
        printTreeUtil(node.left, prefix + "  ");
    }


    /**
     * Remove the node from the tree
     *
     * @param data
     *     the data
     */
    public void remove(KVPair data) {
        root = deletehelp(root, (K)data.getKey());
        nodeCount--;
    }


    private Node deletehelp(Node node, K key) {
        if (node == null) {
            return null; // Key not found
        }

        int cmp = key.compareTo(node.data.getKey());
        if (cmp < 0) {
            node.left = deletehelp(node.left, key);
        }
        else if (cmp > 0) {
            node.right = deletehelp(node.right, key);
        }
        else {
            // Key found, delete the node
            if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }
            else {
                Node temp = getMax(node.left);
                node.data.setTheKey(temp.data.getKey());
                node.data.setTheVal(temp.data.getValue());
                node.left = deletemax(node.left);
            }
        }

        return node;
    }


    private Node getMax(Node node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }


    private Node deletemax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deletemax(node.right);
        return node;
    }

}




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
     *     The ID of the seminar.
     * @param seminar
     *     The Seminar object to insert.
     * @return True if the insertion was successful, false if an ID collision
     *     occurred.
     */
    public boolean insertSeminar(int id, Seminar seminar) {
        KVPair<Integer, Seminar> data = new KVPair<>(id, seminar);
        if (!contains(data)) { // Check if ID already exists
            insert(data);
            return true;
        }
        else {
            System.out.println(
                "Insert FAILED - " + "There is already a record with ID " + id);
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
     *     The ID of the seminar to search for.
     * @return The Seminar object if found, or null if not found.
     */
    public Seminar searchByID(int id) {
        KVPair<Integer, Seminar> data = new KVPair<>(id, null);
        KVPair<Integer, Seminar> result = find(data);
        if (result != null) {
            Seminar foundSeminar = result.getValue();
            System.out.println("Found record with ID " + id + ":");
            System.out.println(foundSeminar);
            return foundSeminar;
        }
        else {
            System.out.println(
                "Search FAILED -- There is no record with ID " + id);
            return null;
        }
    }


    /**
     * Delete.
     *
     * @param id
     *     the id
     */
    public void delete(int id) {
        KVPair<Integer, Seminar> data = new KVPair<>(id, null);

        if (contains(data)) {
            System.out.println(
                "Record with ID " + id + " successfully deleted from the database");
            remove(data);
        }
        else
            System.out.println(
                "Delete FAILED -- There is no record with ID " + id);
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
     *     The ID of the seminar.
     * @param seminar
     *     The Seminar object to insert.
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
     *     The lower bound of the cost range.
     * @param cost2
     *     The upper bound of the cost range.
     */
    private void searchByCost(
        Node node, int cost1, int cost2, StringBuilder result) {

        if (node == null) {
            visitCount++;
            return;
        }

        int nodeCost = node.data.getKey();

        if (nodeCost >= cost1 && nodeCost <= cost2) {
            // The current node's cost is within the specified range
            result.append(node.data.getValue()).append("\n");
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
     */
    public String searchByCost(int cost1, int cost2) {
        StringBuilder result = new StringBuilder();
        searchByCost(root, cost1, cost2, result);
        result.append(visitCount).append(" nodes visited in this search");
        return result.toString();
    }


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
     *     The ID of the seminar.
     * @param seminar
     *     The Seminar object to insert.
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
     *     The start date of the date range.
     * @param date2
     *     The end date of the date range.
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
        }

        // Search in both left and right subtrees regardless of the comparison
        searchByDate(node.left, date1, date2);
        searchByDate(node.right, date1, date2);
    }


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
     *     The Seminar object to insert.
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
     *     The keyword to search for.
     */
    public void searchByKeyword(String keywordValue) {
        System.out.println("Seminars matching keyword " + keywordValue + ":");
        searchByKeyword(root, keywordValue);
    }


    private void searchByKeyword(Node node, String keywordValue) {
        if (node == null) {
            return;
        }

        String nodeKeyword = node.data.getKey();

        // Compare strings using equals method
        if (nodeKeyword.equals(keywordValue)) {
            // The current node's keyword matches the specified keyword
            System.out.println(
                "Found record with keyword " + keywordValue + ":");
            System.out.println(node.data.getValue());
        }

        // Search in both subtrees
        searchByKeyword(node.left, keywordValue);
        searchByKeyword(node.right, keywordValue);
    }


    public void delete(String[] keywords) {
        for (String keyword : keywords) {
            KVPair<String, Seminar> data = new KVPair<>(keyword, null);
            if (contains(data)) {
                remove(data);
            }
        }
    }
}
