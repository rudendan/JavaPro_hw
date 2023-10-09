package homework10;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Parcer {

    private String bookName, path;
    private final List<String> allWords = new LinkedList<>();
    private final Map<String, Integer> calcOccurrence = new HashMap<>();
    private Map<String, Integer> topWords = new HashMap<>();
    private final StringBuffer report = new StringBuffer();


    public void start() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter book name");
        bookName = scanner.nextLine();
        path = "src/" + bookName + ".txt";

        if (readFileToList()) {

            countOfEachWord();
            topTen();
            createReport();
            printReport();
            writeReportToFile();
        }
    }

    private boolean readFileToList() {

        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null)
                Arrays.stream(line.replaceAll("[^a-zA-Z]", " ")
                                .toLowerCase()
                                .split("\\s+"))
                                .forEach(allWords::add);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please enter valid name");
            return false;
        } catch (IOException e) {
            System.out.println("Ooops... another error!");
            return false;
        }
    }

    private void countOfEachWord() {

        for (String word : allWords) {

            int count = 0;
            if (calcOccurrence.containsKey(word))
                continue;

            for (String w : allWords)
                if (word.equals(w))
                    count++;

            calcOccurrence.put(word, count);
        }
    }

    private void topTen() {

        topWords = calcOccurrence.entrySet().stream()
                .filter(w -> w.getKey().length() > 2)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private int uniqCount() {

        List<String> temp = new ArrayList<>();
        for (String word : allWords) {
            if (!temp.contains(word))
                temp.add(word);
        }
        return temp.size();
    }

    private void writeReportToFile() {

        path = "src/" + bookName + "statistic.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(report.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createReport() {

        report.append("TOP 10: \n");
        for (Map.Entry<String, Integer> entry : topWords.entrySet()) {
            report.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }

        report.append("_______________________________\n");
        report.append("Count of the uniq words is: ").append(uniqCount()).append("\n");
        report.append("Count of the all words is: ").append(allWords.size());
    }

    private void printReport() {

        System.out.println(report);
    }
}
