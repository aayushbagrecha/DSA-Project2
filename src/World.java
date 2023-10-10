import java.util.Arrays;
import java.util.Scanner;

/**
 * The `World` class represents a collection of seminars and provides methods
 * for adding, deleting, searching, and printing seminar data.
 *
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * @version 1.0
 */
class World {

    private int worldSize;


    /**
     * Constructs a new World object
     *
     * @param worldSize
     *     The world size for range of x and y coordinates.
     */
    public World(int worldSize) {
        this.worldSize = worldSize;
    }


    /**
     * Reads seminar data from the provided Scanner and inserts a new seminar
     * into multiple Binary Search Trees (BSTs) while performing validation
     * checks.
     *
     * If the provided coordinates (X and Y) fall outside the valid range within
     * the defined world size, the insertion fails, and an error message is
     * displayed. Otherwise, the seminar is inserted into the IDBST, CostBST,
     * DateBST, and KeywordsBST (Binary Search Trees) appropriately.
     *
     * @param scanner
     *     The Scanner object from which seminar data is read.
     * @param idBST
     *     The Binary Search Tree for storing seminars by ID.
     * @param costBST
     *     The Binary Search Tree for storing seminars by cost.
     * @param dateBST
     *     The Binary Search Tree for storing seminars by date.
     * @param keywordsBST
     *     The Binary Search Tree for storing seminars by keywords.
     * @throws Exception
     *     If there is an issue with parsing or if the coordinates fall outside
     *     the valid range.
     */
    public void addAndInsertSeminar(
        Scanner scanner,
        IDBST idBST,
        CostBST costBST,
        DateBST dateBST,
        KeywordsBST keywordsBST,
        Bintree bintree) throws Exception {

        int id = Integer.parseInt(scanner.nextLine().trim());
        String title = scanner.nextLine().trim();
        String dateTime = scanner.next().trim();
        int length = Integer.parseInt(scanner.next().trim());
        short x = Short.parseShort(scanner.next().trim());
        short y = Short.parseShort(scanner.next().trim());
        int cost = Integer.parseInt(scanner.next().trim());

        scanner.nextLine(); // Consume the newline

        String[] keywords = filterKeywords(scanner.nextLine());

        String description = scanner.nextLine().trim();

        // Check if the coordinates are within the valid range
        if (x < 0 || x >= worldSize || y < 0 || y >= worldSize) {
            System.out.println(
                "Insert FAILED - Bad x, y coordinates: " + x + ", " + y);
            // Do not add the seminar
            return;
        }

        Seminar seminar =
            new Seminar(id, title, dateTime, length, x, y, cost, keywords,
                description);

        // Insert seminar into all BSTs
        if (idBST.insertSeminar(id, seminar)) {
            // inert only once when the insertBST is successfull
            costBST.insertSeminar(cost, seminar);
            dateBST.insertSeminar(dateTime, seminar);
            keywordsBST.insertSeminar(seminar, keywords);

            System.out.println("Successfully inserted record with ID " + id);
            System.out.println("ID: " + id + ", Title: " + title);
            System.out.println(
                "Date: " + dateTime + ", Length: " + length + ", X: " + x + ", Y: " + y + ", Cost: " + cost);
            System.out.println("Description: " + description);
            System.out.println("Keywords: " + String.join(", ", keywords));

            bintree.insert(seminar.x(), seminar.y(), seminar);
        }
    }


    /**
     * Filters and processes a line of keywords from the input.
     *
     * @param inputLine
     *     The input line containing keywords separated by spaces.
     * @return An array of keywords extracted from the input line.
     */
    public static String[] filterKeywords(String inputLine) {
        return Arrays.stream(inputLine.split(" "))
            .filter(keyword -> !keyword.isEmpty()).toArray(String[]::new);
    }


    /**
     * Searches for a seminar in the collection based on the provided scanner
     * input.
     *
     * @param scanner
     *     The scanner used to read the seminar ID for searching.
     */
    public void searchSeminarID(Scanner scanner, IDBST idBST) {
        int id = Integer.parseInt(scanner.nextLine().trim());
        idBST.searchByID(id);
        // System.out.println("Search ID is: " + id);
    }


    /**
     * Searches for a seminar in the collection based on the provided scanner
     * input.
     *
     * @param scanner
     *     The scanner used to read the seminar ID for searching.
     * @param keywordsBST
     *     The existing KeywordsBST instance containing seminar data.
     */
    public void searchSeminarKeyword(Scanner scanner, KeywordsBST keywordsBST) {
        String keywordValue = scanner.next().trim();
        keywordsBST.searchByKeyword(keywordValue);
    }


    /**
     * Searches for a seminar in the collection based on the provided scanner
     * input.
     *
     * @param scanner
     *     The scanner used to read the seminar ID for searching.
     */
    public void searchSeminarCost(Scanner scanner, CostBST costBST) {
        int cost1 = Integer.parseInt(scanner.next().trim());
        int cost2 = Integer.parseInt(scanner.next().trim());
        System.out.println(
            "Seminars with costs in range " + cost1 + " to " + cost2 + ":");
        String result = costBST.searchByCost(cost1, cost2);
        System.out.println(result);
    }


    /**
     * Searches for seminars within a specified date range.
     *
     * @param scanner
     *     The scanner used to read start and end dates for the search.
     * @param dateBST
     *     The existing DateBST instance containing seminar data.
     */
    public void searchSeminarDate(Scanner scanner, DateBST dateBST) {
        String date1 = scanner.next().trim();
        String date2 = scanner.next().trim();
        System.out.println(
            "Seminars with dates in range " + date1 + " to " + date2 + ":");
        dateBST.searchByDate(date1, date2);
    }


    public void searchSeminarLocation(Scanner scanner, Bintree bintree) {
        int x = Integer.parseInt(scanner.next().trim());
        int y = Integer.parseInt(scanner.next().trim());
        int radius = Integer.parseInt(scanner.next().trim());

        System.out.println(
            "Seminars with location with coordinates " + x + " and " + y + ":");

//        Seminar seminars = bintree.search(x, y);
//
//        if (seminars == null) {
//            System.out.println();
//        }
    }


    /**
     * Prints the contents of the Binary Search Tree (BST) representing seminars
     * by ID.
     *
     * @param bst
     *     The Binary Search Tree (BST) containing seminars ordered by ID.
     */
    public void printID(IDBST bst) {

        bst.printIDTree();
    }


    /**
     * Prints the contents of the Binary Search Tree (BST) representing seminars
     * by Cost.
     *
     * @param bst
     *     The Binary Search Tree (BST) containing seminars ordered by Cost.
     */
    public void printCost(CostBST bst) {

        bst.printCostTree();
    }


    /**
     * Prints the contents of the Binary Search Tree (BST) representing seminars
     * by Date.
     *
     * @param bst
     *     The Binary Search Tree (BST) containing seminars ordered by Date.
     */
    public void printDate(DateBST bst) {

        bst.printDateTree();
    }


    /**
     * Prints the contents of the Binary Search Tree (BST) representing seminars
     * by Keyword.
     *
     * @param bst
     *     The Binary Search Tree (BST) containing seminars ordered by Keyword.
     */
    public void printKeywords(KeywordsBST bst) {

        bst.printKeywordsTree();
    }


    public void printLocation(Bintree bintree) {
        bintree.print();
    }


    /**
     * Deletes a seminar from the ID Binary Search Tree (BST) based on the given
     * ID.
     *
     * @param scanner
     *     The Scanner object used for input to read the ID of the seminar to be
     *     deleted.
     * @param bst
     *     The IDBST object representing the Binary Search Tree from which the
     *     seminar will be deleted.
     * @throws NumberFormatException
     *     If the input ID cannot be parsed as an integer.
     */
    public void delete(
        Scanner scanner,
        IDBST bst,
        CostBST cost,
        DateBST date,
        KeywordsBST keyword) {
        int id = Integer.parseInt(scanner.nextLine().trim());
        Seminar record = bst.searchByID(id);
        bst.delete(id);
        cost.delete(record.cost());
        date.delete(record.date());
        keyword.delete(record.keywords());
    }

}
