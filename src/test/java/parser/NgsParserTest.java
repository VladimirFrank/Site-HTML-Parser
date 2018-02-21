package parser;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by sbt-filippov-vv on 21.02.2018.
 */
public class NgsParserTest {

    NgsParser ngsParser = new NgsParser();

    @Test
    public void parseByTagName() throws Exception {
    }

    @Test
    public void parseByTagClass() throws Exception {
    }

    @Test
    public void replaceAllMoreToComments() throws Exception {
    }



    @Test
    public void getListArticlesLink() throws Exception {
        Set<String> linksToArticlesList = new HashSet<>(ngsParser.getListArticlesLink());
        for(String link : linksToArticlesList){
            System.out.println(link);
        }
        System.out.println("------------------------");
        Set<String> linksToArticlesListComments = ngsParser.replaceAllMoreToComments(linksToArticlesList);
        for(String link : linksToArticlesListComments){
            System.out.println(link);
        }
    }



}