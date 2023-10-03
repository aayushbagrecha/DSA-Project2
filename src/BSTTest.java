import student.TestCase;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BSTTest {

    private GenericBST<Integer, String> bst;
    private IDBST idBST;
    private CostBST costBST;
    private DateBST dateBST;
    private KeywordsBST keywordsBST;

    @Before
    public void setUp1() {
        idBST = new IDBST();
        costBST = new CostBST();
        dateBST = new DateBST();
        keywordsBST = new KeywordsBST();
    }


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
    }

// @Test
// public void testCostBSTInsertionAndSearch() {
// Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2, (short) 10,
// (short) 20, 50, new String[]{"keyword1"}, "Description 1");
// Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3, (short) 15,
// (short) 25, 70, new String[]{"keyword2"}, "Description 2");
//
// costBST.insertSeminar(1, seminar1);
// costBST.insertSeminar(2, seminar2);
//
// assertEquals(seminar1, costBST.searchByCost(40, 60));
// assertEquals(seminar2, costBST.searchByCost(60, 80));
// assertNull(costBST.searchByCost(30, 50));
// }


    @Test
    public void testDateBSTInsertionAndSearch() {
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3,
            (short)15, (short)25, 70, new String[] { "keyword2" },
            "Description 2");

        dateBST.insertSeminar("2023-10-10", seminar1);
        dateBST.insertSeminar("2023-10-12", seminar2);

        assertEquals(seminar1, dateBST.searchByDate("2023-10-10",
            "2023-10-15"));
        assertEquals(seminar2, dateBST.searchByDate("2023-10-11",
            "2023-10-13"));
        assertNull(dateBST.searchByDate("2023-10-08", "2023-10-09"));
    }


    @Test
    public void testKeywordsBSTInsertionAndSearch() {
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3,
            (short)15, (short)25, 70, new String[] { "keyword2" },
            "Description 2");

        keywordsBST.insertSeminar(seminar1, "keyword1");
        keywordsBST.insertSeminar(seminar2, "keyword2");

        assertEquals(seminar1, keywordsBST.searchByKeyword("keyword1"));
        assertEquals(seminar2, keywordsBST.searchByKeyword("keyword2"));
        assertNull(keywordsBST.searchByKeyword("keyword3"));
    }


    public void setUp() {
        bst = new GenericBST<>();
    }


    public void testInsertEmptyTree() {
        bst.insert(new KVPair<>(5, "Five"));
        assertEquals(1, bst.size());
    }


    public void testInsertSmallerKeyToLeft() {
        bst.insert(new KVPair<>(5, "Five"));
        bst.insert(new KVPair<>(3, "Three"));

        assertTrue(bst.contains(new KVPair<>(5, "Five")));
        assertTrue(bst.contains(new KVPair<>(3, "Three")));
        assertEquals(2, bst.size());
    }


    public void testInsertLargerKeyToRight() {
        bst.insert(new KVPair<>(5, "Five"));
        bst.insert(new KVPair<>(7, "Seven"));

        assertTrue(bst.contains(new KVPair<>(5, "Five")));
        assertTrue(bst.contains(new KVPair<>(7, "Seven")));
        assertEquals(2, bst.size());
    }


    public void testInsertEqualKeysToLeft() {
        bst.insert(new KVPair<>(5, "Five"));
        bst.insert(new KVPair<>(5, "Duplicate"));

        assertTrue(bst.contains(new KVPair<>(5, "Five")));
        assertTrue(bst.contains(new KVPair<>(5, "Duplicate")));
        assertEquals(2, bst.size());
    }


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


    @Test
    public void testSearchByID() {
        IDBST bst = new IDBST();

        // Creating sample seminars
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3,
            (short)15, (short)25, 70, new String[] { "keyword2" },
            "Description 2");

        // Inserting seminars into the BST
        bst.insertSeminar(1, seminar1);
        bst.insertSeminar(2, seminar2);

        // Testing search by ID
        Seminar result1 = bst.searchByID(1);
        assertNotNull(result1);
        assertEquals(seminar1, result1);

        Seminar result2 = bst.searchByID(2);
        assertNotNull(result2);
        assertEquals(seminar2, result2);

        Seminar result3 = bst.searchByID(3);
        assertNull(result3);
    }


    @Test
    public void testSearchByCost() {
        CostBST bst = new CostBST();

        // Creating sample seminars
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3,
            (short)15, (short)25, 70, new String[] { "keyword2" },
            "Description 2");
        Seminar seminar3 = new Seminar(3, "Seminar 3", "2023-10-14", 1,
            (short)5, (short)15, 40, new String[] { "keyword3" },
            "Description 3");

        // Inserting seminars into the BST
        bst.insertSeminar(1, seminar1);
        bst.insertSeminar(2, seminar2);
        bst.insertSeminar(3, seminar3);

        // Testing search by cost
        Seminar result1 = bst.searchByCost(40, 60);
        assertNotNull(result1);
        assertEquals(seminar1, result1);

        Seminar result2 = bst.searchByCost(60, 80);
        assertNotNull(result2);
        assertEquals(seminar2, result2);

        Seminar result3 = bst.searchByCost(30, 50);
        assertNull(result3);
    }


    @Test
    public void testSearchByDate() {
        DateBST bst = new DateBST();

        // Creating sample seminars
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3,
            (short)15, (short)25, 70, new String[] { "keyword2" },
            "Description 2");
        Seminar seminar3 = new Seminar(3, "Seminar 3", "2023-10-14", 1,
            (short)5, (short)15, 40, new String[] { "keyword3" },
            "Description 3");

        // Inserting seminars into the BST
        bst.insertSeminar("2023-10-10", seminar1);
        bst.insertSeminar("2023-10-12", seminar2);
        bst.insertSeminar("2023-10-14", seminar3);

        // Testing search by date
        Seminar result1 = bst.searchByDate("2023-10-09", "2023-10-11");
        assertNotNull(result1);
        assertEquals(seminar1, result1);

        Seminar result2 = bst.searchByDate("2023-10-11", "2023-10-13");
        assertNotNull(result2);
        assertEquals(seminar2, result2);

        Seminar result3 = bst.searchByDate("2023-10-15", "2023-10-16");
        assertNull(result3);
    }


    @Test
    public void testSearchByKeyword() {
        KeywordsBST bst = new KeywordsBST();

        // Creating sample seminars with keywords
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)10, (short)20, 50, new String[] { "keyword1" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-12", 3,
            (short)15, (short)25, 70, new String[] { "keyword2", "keyword3" },
            "Description 2");

        // Inserting seminars into the BST
        bst.insertSeminar(seminar1, "keyword1");
        bst.insertSeminar(seminar2, "keyword2", "keyword3");

        // Testing search by keyword
        Seminar result1 = bst.searchByKeyword("keyword1");
        assertNotNull(result1);
        assertEquals(seminar1, result1);

        Seminar result2 = bst.searchByKeyword("keyword2");
        assertNotNull(result2);
        assertEquals(seminar2, result2);

        Seminar result3 = bst.searchByKeyword("keyword3");
        assertNotNull(result3);
        assertEquals(seminar2, result3);

        Seminar result4 = bst.searchByKeyword("nonexistentkeyword");
        assertNull(result4);
    }


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

}
