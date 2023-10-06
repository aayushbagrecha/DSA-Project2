import org.junit.Test;
import student.TestCase;
import static org.junit.Assert.assertArrayEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * 
 * @version 1.0
 * 
 */
public class WorldTest extends TestCase {

    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Full parser test
     * 
     * @throws IOException
     */

    public void testparserfull() throws IOException {
        String[] args = new String[2];
        args[0] = "1024";
        args[1] = "P2syntaxInsert_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("P2syntaxInsert_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Check the filterKeywords parameter
     */
    public static void testFilterKeywordsTrueCase() {
        String inputLine = "java programming  python   code";
        String[] expectedKeywords = { "java", "programming", "python", "code" };
        String[] result = World.filterKeywords(inputLine);

        assertArrayEquals(expectedKeywords, result);
    }


    /**
     * Check the filterKeywords parameter
     */
    public void testFilterKeywordsEmptyInput() {
        String inputLine = "";
        String[] expectedKeywords = {};

        String[] result = World.filterKeywords(inputLine);

        assertArrayEquals(expectedKeywords, result);
    }


    /**
     * Check the filterKeywords parameter
     */
    public void testFilterKeywordsWhitespaceInput() {
        String inputLine = "        ";
        String[] expectedKeywords = {};

        String[] result = World.filterKeywords(inputLine);

        assertArrayEquals(expectedKeywords, result);
    }


    /**
     * Check the filterKeywords parameter
     */
    public void testFilterKeywordsSingleKeyword() {
        String inputLine = "programming";
        String[] expectedKeywords = { "programming" };

        String[] result = World.filterKeywords(inputLine);

        assertArrayEquals(expectedKeywords, result);
    }


    /**
     * Check the filterKeywords parameter
     */
    public void testFilterKeywordsMultipleSpacesBetweenKeywords() {
        String inputLine = "java     python     code";
        String[] expectedKeywords = { "java", "python", "code" };

        String[] result = World.filterKeywords(inputLine);
        assertArrayEquals(expectedKeywords, result);
    }

    private World world;
    private IDBST idBST;
    private CostBST costBST;
    private DateBST dateBST;
    private KeywordsBST keywordsBST;

    public void setUp() {
        // Initialize the World, IDBST, CostBST, DateBST, and KeywordsBST
        // objects here
        world = new World(128);
        idBST = new IDBST();
        costBST = new CostBST();
        dateBST = new DateBST();
        keywordsBST = new KeywordsBST();
    }


    public void testAddAndInsertSeminarSuccess() throws Exception {
        // Create a sample Scanner with seminar data
        String seminarData =
            "1\nSeminar Title\n2023-09-24\n60\n5\n5\n100\nKeyword1,"
                + " Keyword2\nSeminar Description";
        Scanner scanner = new Scanner(seminarData);

        // Call the addAndInsertSeminar method
        world.addAndInsertSeminar(scanner, idBST, costBST, dateBST,
            keywordsBST);

        // Assert that the seminar with ID 1 was inserted into the IDBST
        assertTrue(idBST.contains(new KVPair<>(1, new Seminar(1,
            "Seminar Title", "2023-09-24", 60, (short)5, (short)5, 100,
            new String[] { "Keyword1", "Keyword2" }, "Seminar Description"))));
    }


    @Test
    public void testSearch() throws Exception {
    }


    @Test
    public void testPrint() throws Exception {
        String seminarData = """
            1
            Seminar Title
            2023-09-24
            60
            5
            5
            100
            Keyword1 Keyword2
            Seminar Description
            """;
        Scanner scanner = new Scanner(seminarData);

        world.addAndInsertSeminar(scanner, idBST, costBST, dateBST,
            keywordsBST);

        world.printID(idBST);
        world.printCost(costBST);
        world.printDate(dateBST);
        world.printKeywords(keywordsBST);
    }


    @Test
    public void testDelete() throws Exception {
        String seminarData = """
            1
            Seminar Title
            2023-09-24
            60
            5
            5
            100
            Keyword1 Keyword2
            Seminar Description
            1""";
        Scanner scanner = new Scanner(seminarData);

        world.addAndInsertSeminar(scanner, idBST, costBST, dateBST,
            keywordsBST);

        world.delete(scanner, idBST, costBST, dateBST, keywordsBST);

    }

}
