import java.io.*;
import java.util.*;

public class Jaccard {

    // TODO: put the target test prefix here
    public static final String TARGET_PREFIX = "Given the following code snippet, generate all possible assertions following the formal grammar rules. For each assertion, rank the list of all possible assertions. Code: testGetSources() { Set<File> sources = testMojo.getSources(); int size = sources.size(); <AssertPlaceHolder>; }";

    // Get a set of words from a given line
    public static Set<String> getWords(String line) {
        // Split by whitespace and return a set of words
        return new HashSet<>(Arrays.asList(line.split("\\s+")));
    }

    // Compute the Jaccard similarity between two sets of words
    public static double jaccard(Set<String> t1, Set<String> t2) {
        // Calculate the intersection and union of both sets
        Set<String> intersection = new HashSet<>(t1);
        intersection.retainAll(t2);

        Set<String> union = new HashSet<>(t1);
        union.addAll(t2);

        // Return the Jaccard similarity
        return (double) intersection.size() / union.size();
    }

    public static void main(String[] args) throws FileNotFoundException {

        String filePath = "corpus.txt"; 
        String assertPath = "corpusAsserts.txt";

        // Initialize scanners to read the files
        Scanner scanner = new Scanner(new File(filePath));
        Scanner assertScanner = new Scanner(new File(assertPath));

        // List to store corpus entries (test prefixes and corresponding assertions)
        List<Map.Entry<Set<String>, String>> corpus = new ArrayList<>();

        // Read each line from the corpus and assertions file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String assertion = assertScanner.nextLine();

            // Get words for the test prefix (line)
            Set<String> words = getWords(line);

            // Store the set of words and its corresponding assertion
            corpus.add(new AbstractMap.SimpleEntry<>(words, assertion));
        }

        String retrievedAssertion = "";
        double bestSimilarity = 0;

        // Compute the Jaccard similarity for each test prefix in the corpus
        for (Map.Entry<Set<String>, String> entry : corpus) {
            Set<String> corpusTest = entry.getKey();  // Get words from the corpus test prefix
            String corpusAssertion = entry.getValue();  // Get the corresponding assertion

            // Calculate the Jaccard similarity between the target test prefix and the current corpus test
            double similarity = jaccard(getWords(TARGET_PREFIX), corpusTest);

            // If this similarity is the best, update the retrieved assertion
            if (similarity > bestSimilarity) {
                retrievedAssertion = corpusAssertion;
                bestSimilarity = similarity;
            }
        }

        // Output the most similar assertion
        System.out.println("Retrieved Assertion: " + retrievedAssertion);
    }
}
