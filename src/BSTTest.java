import static org.junit.Assert.*;

/**
 * The type Bst test.
 */
public class BSTTest {

    private GenericBST<Integer, String> bst;


    /**
     * Sets up.
     */
    public void setUp() {
        bst = new GenericBST<>();
    }


    /**
     * Test insert empty tree.
     */
    public void testInsertEmptyTree() {
        bst.insert(new KVPair<>(5, "Five"));
        assertEquals(1, bst.size());
    }


    /**
     * Test insert smaller key to left.
     */
    public void testInsertSmallerKeyToLeft() {
        bst.insert(new KVPair<>(5, "Five"));
        bst.insert(new KVPair<>(3, "Three"));

        assertTrue(bst.contains(new KVPair<>(5, "Five")));
        assertTrue(bst.contains(new KVPair<>(3, "Three")));
        assertEquals(2, bst.size());
    }


    /**
     * Test insert larger key to right.
     */
    public void testInsertLargerKeyToRight() {
        bst.insert(new KVPair<>(5, "Five"));
        bst.insert(new KVPair<>(7, "Seven"));

        assertTrue(bst.contains(new KVPair<>(5, "Five")));
        assertTrue(bst.contains(new KVPair<>(7, "Seven")));
        assertEquals(2, bst.size());
    }


    /**
     * Test insert equal keys to left.
     */
    public void testInsertEqualKeysToLeft() {
        bst.insert(new KVPair<>(5, "Five"));
        bst.insert(new KVPair<>(5, "Duplicate"));

        assertTrue(bst.contains(new KVPair<>(5, "Five")));
        assertTrue(bst.contains(new KVPair<>(5, "Duplicate")));
        assertEquals(2, bst.size());
    }


    /**
     * Test insert multiple keys.
     */
    public void testInsertMultipleKeys() {
        bst.insert(new KVPair<>(5, "Five"));
        bst.insert(new KVPair<>(3, "Three"));
        bst.insert(new KVPair<>(7, "Seven"));
        bst.insert(new KVPair<>(2, "Two"));

        assertTrue(bst.contains(new KVPair<>(5, "Five")));
        assertTrue(bst.contains(new KVPair<>(3, "Three")));
        assertTrue(bst.contains(new KVPair<>(7, "Seven")));
        assertTrue(bst.contains(new KVPair<>(2, "Two")));
        assertEquals(4, bst.size());
    }
}
