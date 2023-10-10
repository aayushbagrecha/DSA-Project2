/**
 * {BST Trees}
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
//
// @author Aayush Bagrecha
// @author Yash Shrikant
// @version 1.0
//
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The `SemSearch` class is responsible for managing and processing input
 * arguments, initializing data structures, and parsing commands from a file to
 * interact with the `World` class.
 *
 * This program is designed to read input arguments, ˳ validate them, create
 * necessary data structures, and execute commands specified in an input file to
 * manage seminars and associated data.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
public class SemSearch {
    /**
     * @param args
     *     Command line parameters
     * @return `true` if the program executed successfully, `false` otherwise.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Please provide exactly two arguments.");
            return;
        }

        int worldSize;

        try {
            worldSize = Integer.parseInt(args[0]);

            if (!isPowerOfTwo(worldSize)) {
                System.err.println("Argument must be power of two.");
                return;
            }

        }
        catch (NumberFormatException e) {
            System.err.println(
                "Invalid integer input." + " Please provide a valid input.");
            return;
        }

        File file = new File(args[1]);

        World world = new World(worldSize);

        IDBST idBST = new IDBST();
        CostBST costBST = new CostBST();
        DateBST dateBST = new DateBST();
        KeywordsBST keywordsBST = new KeywordsBST();
        Bintree bintree = new Bintree();

        try {
            parseFile(file, world, idBST, costBST, dateBST, keywordsBST,
                bintree);
            return;
        }
        catch (Exception e) {
            System.err.println("File not found.");
            return;
        }
    }


    /**
     * Checks if a given number is a power of two.
     *
     * @param number
     *     The number to check.
     * @return `true` if the number is a power of two, `false` otherwise.
     */
    public static boolean isPowerOfTwo(int number) {
        return (number > 0) && ((number & (number - 1)) == 0);
    }


    /**
     * Parses a text file containing operations and data related to a "World"
     * and populates various Binary Search Trees (BSTs) with the extracted
     * information.
     *
     *
     * For "insert" operations, data is used to create and insert seminars into
     * multiple Binary Search Trees, such as IDBST, CostBST, DateBST, and
     * KeywordsBST, depending on the operation's parameters. If an unknown
     * operation is encountered, an error message is displayed, and the current
     * line is skipped.
     *
     * @param file
     *     The File object representing the text file to be parsed.
     * @param world
     *     The World object where seminar data will be stored.
     * @param idBST
     *     The Binary Search Tree for storing seminars by ID.
     * @param costBST
     *     The Binary Search Tree for storing seminars by cost.
     * @param dateBST
     *     The Binary Search Tree for storing seminars by date.
     * @param keywordsBST
     *     The Binary Search Tree for storing seminars by keywords.
     * @return True if the parsing and execution of operations from the file
     *     were successful, False if the file was not found or if an exception
     *     occurred during parsing.
     * @throws Exception
     *     If any unexpected errors occur during the parsing process.
     */
    public static boolean parseFile(
        File file,
        World world,
        IDBST idBST,
        CostBST costBST,
        DateBST dateBST,
        KeywordsBST keywordsBST,
        Bintree bintree) throws Exception {

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNext()) {
                String operation = scanner.next().trim();

                switch (operation) {
                    case "insert":
                        world.addAndInsertSeminar(scanner, idBST, costBST,
                            dateBST, keywordsBST, bintree);
                        break;

                    case "search":
                        String searchType = scanner.next().trim();
                        switch (searchType) {
                            case "keyword":
                                world.searchSeminarKeyword(scanner,
                                    keywordsBST);
                                break;

                            case "ID":
                                world.searchSeminarID(scanner, idBST);
                                break;

                            case "cost":
                                world.searchSeminarCost(scanner, costBST);
                                break;

                            case "date":
                                world.searchSeminarDate(scanner, dateBST);
                                break;

//                            case "location":
//                                world.searchSeminarLocation(scanner, bintree);
//                                break;
                        }
                        break;

                    case "print":
                        String printType = scanner.next().trim();
                        switch (printType) {
                            case "ID":
                                world.printID(idBST);
                                break;

                            case "date":
                                world.printDate(dateBST);
                                break;

                            case "keyword":
                                world.printKeywords(keywordsBST);
                                break;

                            case "cost":
                                world.printCost(costBST);
                                break;

                            case "location":
                                world.printLocation(bintree);
                                break;
                        }
                        break;

                    case "delete":
                        world.delete(scanner, idBST, costBST, dateBST,
                            keywordsBST);
                        break;

                    default:
                        System.err.println("Unknown operation: " + operation);
                        scanner.nextLine();
                        break;
                }
            }
            return true;
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found:");
            return false;
        }
    }
}
