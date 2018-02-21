package parser;

/**
 * Created by sbt-filippov-vv on 21.02.2018.
 */
public interface SiteParser {

    String parseByTagName(String tagName);

    String parseByTagClass(String tagClass);
}
