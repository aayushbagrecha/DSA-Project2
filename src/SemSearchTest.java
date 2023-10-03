import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * 
 * @version 1.0
 */
public class SemSearchTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx() {
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
    }


    /**
     * Check the validArgument parameter
     */
    public void testValidArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = { "128", "SingleInput.txt" };
        SemSearch.main(args);
        // Restore the standard error stream
        System.setErr(System.err);
        String errorMessage = outContent.toString().trim();
        assertEquals("", errorMessage);
    }


    /**
     * Check the validArgument parameter
     */
//    public void testInvalidArgumentcount() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        String[] args = { "128", "SingleInput.txt" };
//        SemSearch.main(args);
//        String expectedOutput = "Please provide exactly two arguments"
//            + " in the correct format.\n";
//        assertEquals(expectedOutput, outContent.toString());
//    }


    /**
     * Check the validArgument parameter
     */
    public void testInvalidFileParameter() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = { "16", "InvalidFile.txt" };
        SemSearch.main(args);
        String expectedOutput = "File not found:\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    /**
     * Check the validArgument parameter
     */
//    public void testInvalidWorldSize() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        String[] args = { "not_an_integer", "P2Sample_input.txt" };
//        SemSearch.main(args);
//        String expectedOutput = "Invalid integer input."
//            + " Please provide valid integer input.\n";
//        assertEquals(expectedOutput, outContent.toString());
//    }


    /**
     * Check the validArgument parameter
     */
    public void testInvalidNonPowerTwoInteger() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = { "63", "P2Sample_input.txt" };
        SemSearch.main(args);
        String expectedOutput = "Argument must be power of two.\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    /**
     * Check the isPowerOfTwo method
     */
    public void testIsPowerOfTwo() {
        assertTrue(SemSearch.isPowerOfTwo(1)); // 2^0 = 1
        assertTrue(SemSearch.isPowerOfTwo(2)); // 2^1 = 2
        assertTrue(SemSearch.isPowerOfTwo(4)); // 2^2 = 4
        assertTrue(SemSearch.isPowerOfTwo(8)); // 2^3 = 8
        assertTrue(SemSearch.isPowerOfTwo(16)); // 2^4 = 16
        assertFalse(SemSearch.isPowerOfTwo(0)); // Not a power of two
        assertFalse(SemSearch.isPowerOfTwo(3)); // Not a power of two
        assertFalse(SemSearch.isPowerOfTwo(-8)); // Not a power of two
    }

    /**
     * Check the parseFile method
     */
    /*
     * public void testParserFileWithMultipleLines() throws Exception {
     * File file = new File("P2syntaxinsert_input.txt");
     * World world = new World();
     * assertTrue(SemSearch.parseFile(file, world));
     * }
     */

    /**
     * Check the parseFile method
     */
    /*
     * public void testParserFileWithNoLine() throws Exception {
     * File file = new File("InputBlank.txt");
     * World world = new World(1024);
     * assertTrue(SemSearch.parseFile(file, world));
     * }
     */

    /**
     * Check the parseFile method
     */
    /*
     * public void testParserFileWithNoFile() throws Exception {
     * File file = new File("InvalidFile.txt");
     * World world = new World(16);
     * assertFalse(SemSearch.parseFile(file, world));
     * }
     */
    /**
     * Check the parseFile method
     */

    /*
     * public void testParserFileWithSingleLine() throws Exception {
     * File file = new File("SingleInput.txt");
     * World world = new World();
     * assertTrue(SemSearch.parseFile(file, world));
     * }
     */
}
