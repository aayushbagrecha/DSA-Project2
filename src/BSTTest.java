import student.TestCase;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The type World test.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
public class BSTTest {

    private GenericBST<Integer, String> bst;
    private IDBST idBST;
    private CostBST costBST;
    private DateBST dateBST;
    private KeywordsBST keywordsBST;

    /**
     * Sets up 1.
     */
    @Before
    public void setUp1() {
        idBST = new IDBST();
        costBST = new CostBST();
        dateBST = new DateBST();
        keywordsBST = new KeywordsBST();
    }


    /**
     * Test idbst insertion and search.
     */
    @Test
    public void testIDBSTInsertionAndSearch() {
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3,
            (short)15, (short)25, 70, new String[] { "keyword2" },
            "Description 2");

        assertTrue(idBST.insertSeminar(1, seminar1));
        assertTrue(idBST.insertSeminar(2, seminar2));

        assertEquals(seminar1, idBST.searchByID(1));
        assertEquals(seminar2, idBST.searchByID(2));
        assertNull(idBST.searchByID(3));
        assertFalse(idBST.insertSeminar(1, seminar1));
    }


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


    /**
     * Test compare to.
     */
    @Test
    public void testCompareTo() {
        // Creating KVPair instances with different keys
        KVPair<Integer, String> pair1 = new KVPair<>(1, "Value1");
        KVPair<Integer, String> pair2 = new KVPair<>(2, "Value2");
        KVPair<Integer, String> pair3 = new KVPair<>(3, "Value3");
        KVPair<Integer, String> pair1Duplicate = new KVPair<>(1,
            "DuplicateValue");

        // Testing comparison using compareTo method
        assertTrue(pair1.compareTo(pair2) < 0); // pair1 key is smaller than
        // pair2 key
        assertTrue(pair2.compareTo(pair1) > 0); // pair2 key is greater than
        // pair1 key
        assertTrue(pair2.compareTo(pair3) < 0); // pair2 key is smaller than
        // pair3 key
        assertTrue(pair3.compareTo(pair2) > 0); // pair3 key is greater than
        // pair2 key
        assertEquals(0, pair1.compareTo(pair1)); // pair1 key is equal to pair1
        // key
        assertEquals(0, pair1.compareTo(pair1Duplicate)); // pair1 key is equal
        // to pair1Duplicate
        // key
        assertTrue(pair1.compareTo(pair3) < 0); // pair1 key is smaller than
        // pair3 key
        assertTrue(pair1Duplicate.compareTo(pair2) < 0); // pair1Duplicate key
        // is smaller than
        // pair2 key
    }


    /**
     * Test delete.
     */
    @Test
    public void testDelete() {

        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        KVPair<Integer, Seminar> data1 = new KVPair<>(1, seminar1);

        Seminar seminar2 = new Seminar(2, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        KVPair<Integer, Seminar> data2 = new KVPair<>(2, seminar1);

        // test deletion of non existent data
        idBST.delete(1);
        assertFalse(idBST.contains(data1));

        // test deletion of inserted data
        idBST.insert(data1);
        idBST.insert(data2);

// idBST.remove(data1);
        idBST.delete(seminar1.id());
        dateBST.delete(seminar1.date());
        costBST.delete(seminar1.cost());
        keywordsBST.delete(seminar1.keywords());

        assertFalse(idBST.contains(data1));
    }


    /**
     * Test get count.
     */
    @Test
    public void testGetCount() {
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        KVPair<Integer, Seminar> data1 = new KVPair<>(1, seminar1);

        // test no of records before insertion
        assertEquals(idBST.getRecordCount(), 0);

        // test after insertion
        idBST.insert(data1);

        assertEquals(idBST.getRecordCount(), 1);
        assertEquals(dateBST.getRecordCount(), 0);
        assertEquals(costBST.getRecordCount(), 0);
        assertEquals(dateBST.getRecordCount(), 0);
        assertEquals(keywordsBST.getRecordCount(), 0);
    }


    /**
     * Test print.
     */
    @Test
    public void testPrint() {

        // test delete without any record present
        idBST.printIDTree();
        costBST.printCostTree();
        dateBST.printDateTree();
        keywordsBST.printKeywordsTree();

        // test delete with an inserted record
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");

        idBST.insertSeminar(1, seminar1);
        dateBST.insertSeminar(seminar1.date(), seminar1);
        costBST.insertSeminar(seminar1.cost(), seminar1);
        keywordsBST.insertSeminar(seminar1, seminar1.keywords());

        idBST.printIDTree();
        dateBST.printDateTree();
        costBST.printTree();
        keywordsBST.printTree();

    }

}
