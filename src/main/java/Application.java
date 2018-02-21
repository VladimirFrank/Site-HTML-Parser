import collections.FileStringWriter;
import parser.NgsParser;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sbt-filippov-vv on 20.02.2018.
 */
public class Application {

    private static final String tagClassName = ".extended_comment-user__name";

    // Add article number as a String in URL template format: "1010101/"
    private static String urlTemplate = "comments/";

    private static final String linesSplitter = "--------------------------------------";

    private static final AtomicLong numbersOfExceptions = new AtomicLong();

    public static void main(String[] args) {

        // Start time for execution
        Instant startTime = Instant.now();

        NgsParser ngsParser = new NgsParser();

        // Array of articles numbers.
        List<String> articlesNumbers = new ArrayList<>();



        for(int i = 53_549_800; i < 53_550_000; i++){
            articlesNumbers.add(String.valueOf(i) + "/");
        }

        articlesNumbers.add("53561341/");
        articlesNumbers.add("53559771/");
        articlesNumbers.add("53549661/");

        // Array of user's nicknames.
        List<String> userNameList = new LinkedList<>();

        for(String articleNumber : articlesNumbers){
            try {
                userNameList.addAll(ngsParser.parseByTagClass(urlTemplate + articleNumber, tagClassName));
                System.out.println(linesSplitter);
                System.out.println(userNameList.size());
                System.out.println(linesSplitter);
            } catch (IOException e) {
                numbersOfExceptions.getAndIncrement();
                e.printStackTrace();
            }
        }

        for(String userName : userNameList){
            System.out.println(userName);
        }

        printNickNamesCount(userNameList);


        System.out.println(linesSplitter);
        System.out.println("TOTAL NON UNIQUE USER'S NAMES ARRAY:");
        System.out.println(userNameList.size());
        System.out.println(linesSplitter);


        System.out.println(linesSplitter);
        System.out.println("COUNT OF EXCEPTIONS:");
        System.out.println(numbersOfExceptions.get());
        System.out.println(linesSplitter);



        // End time for execution
        Instant endTime = Instant.now();
        System.out.println(linesSplitter);
        System.out.println("TIME OF EXECUTION:");
        System.out.println(Duration.between(startTime, endTime));
        System.out.println(linesSplitter);


    }

    private static void printNickNamesCount(List<String> userNames){
        Set<String> uniqueUserNames = new HashSet<>(userNames);
        for(String userName : uniqueUserNames){
            System.out.println(userName + ": " + Collections.frequency(userNames, userName));
        }
    }
}
