import parser.NgsParser;
import repository.NgsDataRepository;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sbt-filippov-vv on 20.02.2018.
 */
public class Application {

    private static final String usernameTagClass = ".extended_comment-user__name";

    // Add article number as a String in URL template format: "1010101/"
    private static String urlTemplate = "comments/";

    private static final String linesSplitter = "--------------------------------------";

    private static final AtomicLong numbersOfExceptions = new AtomicLong();

    public static void main(String[] args) {

        // Start time for execution
        Instant startTime = Instant.now();

        NgsParser ngsParser = new NgsParser();
        NgsDataRepository dataRepository = new NgsDataRepository();

        try {
            dataRepository.setUrlToArticles(ngsParser.getArticlesCommentPages(5));

            for(String commentPageUrl : dataRepository.getUrlToArticles()){
                dataRepository.addNonUniqueUserNickname(ngsParser.parseByTagClass(commentPageUrl, usernameTagClass));

            }

        } catch (IOException e) {
            e.printStackTrace();
            numbersOfExceptions.getAndIncrement();
        }

        for(String userName : dataRepository.getAllUserNicknames()){
            System.out.println(userName);
        }

        System.out.println(linesSplitter);
        System.out.println("TOTAL NON UNIQUE USER'S NAMES ARRAY:");
        System.out.println(dataRepository.getAllUserNicknames().size());
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
}
