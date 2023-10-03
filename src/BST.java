/**
 * The `KVPair` class represents a key-value pair with a comparable key.
 *
 * @param <K>
 *     The type of the key, which must extend Comparable.
 * @param <E>
 *     The type of the value.
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version v1
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
        return this.theKey;
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
 */
class GenericBST<K extends Comparable<K>, V> {
    /**
     * The type Node.
     */
// Inner class representing a node in the BST
    protected class Node {
        /**
         * The Data.
         */
        KVPair<K, V> data;
        /**
         * The Left.
         */
        protected Node left;
        /**
         * The Right.
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
     * The Root.
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


    private Node inserthelp(Node root, KVPair<K, V> data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

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


    private KVPair<K, V> findhelp(Node root, KVPair<K, V> data) {
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
     *     the data
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
 * The type Idbst.
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
     * Print id tree.
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
 * The type Cost bst.
 */
class CostBST extends GenericBST<Integer, Seminar> {

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
     * Print cost tree.
     */
    public void printCostTree() {
        System.out.println("Cost Tree:");
        printTree();
    }
}




/**
 * The type Date bst.
 */
class DateBST extends GenericBST<String, Seminar> {

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
     * Print date tree.
     */
    public void printDateTree() {
        System.out.println("Date Tree:");
        printTree();
    }
}




/**
 * The type Keywords bst.
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
     * @param ids
     *     the ids
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
     * Print keywords tree.
     */
    public void printKeywordsTree() {
        System.out.println("Keyword Tree:");
        printTree();
    }
}
