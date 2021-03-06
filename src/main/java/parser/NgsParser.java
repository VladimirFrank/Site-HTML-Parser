package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * We need to use URL as example: http://news.ngs.ru/comments/53561341/
 * where 53561341 - number id for unique article (I hope it is unique id);
 *
 */
public class NgsParser{

    private final String articlesTagClass = ".home_article2";
    private final String linkTag = "a";


    public String parseByTagName(String tagName) {
        return null;
    }

    /**
     * Comments are in the tag with classes:
     * '.extended_comment__content' (it's a String body of comment);
     * '..extended_comment-user__name' (it's a header of comment with a user's nickname);
     * @param tagClass
     * @return
     */
    public List<String> parseByTagClass(String url, String tagClass) throws IOException{

        Document document = Jsoup.connect(url).get();
        Elements elements = document.select(tagClass);

        List<String> userNamesList = new ArrayList<>();
        if(elements != null){

            for(Element element : elements){
                userNamesList.add(element.text());
            }
        }
        return userNamesList;
    }


    /**
     *
     * @param lastArticlesPage - last news list on site. Ex: http://news.ngs.ru/news/all/page/2/
     * @return Set <String> URLs to comment pages.
     * @throws IOException
     */
    public Set<String> getArticlesCommentPages(int lastArticlesPage) throws IOException {
        if(lastArticlesPage < 1){}

        String allNewsURLTemplate = "http://news.ngs.ru/news/all/page/";
        Set<String> articlesLinkList = new HashSet<>();


        for(int i = 1; i < lastArticlesPage; i++){
            Document document = Jsoup.connect(allNewsURLTemplate + String.valueOf(i) + "/").get();
            Elements elements = document.select(articlesTagClass).select(linkTag);
            if(elements != null){
                for(Element element : elements){
                    articlesLinkList.add(element.attr("href"));
                }
            }
        }
        return replaceAllMoreToComments(articlesLinkList);
    }

    /**
     *
     * @param links - URLs to news articles.
     * @return unique URLs to comments pages linked with same articles.
     */
    private Set<String> replaceAllMoreToComments(Set<String> links){
        Set<String> linksToCommentsPages = new HashSet<>();
        for(String link : links){
            linksToCommentsPages.add(link.replace("more", "comments"));
        }
        return linksToCommentsPages;
    }
}
