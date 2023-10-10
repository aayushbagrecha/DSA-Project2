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
 * @version 1.0
 */
public class WorldTest extends TestCase {

    /**
     * Read contents of a file into a string
     *
     * @param path
     *     File name
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

        String[] args = new String[2];
        args[0] = "128";
        args[1] = "P2syntaxInsert_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("P2syntaxInsert_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    @Test
    public void testSearch() throws Exception {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "P2syntaxSearch_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("P2syntaxSearch_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    @Test
    public void testAll() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "P2Sample1_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("P2Sample1_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }

}
