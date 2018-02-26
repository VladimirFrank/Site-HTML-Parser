package repository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * In-memory data storage class.
 */
public class NgsDataRepository {


    private Set<String> urlToArticles;         // Contains URLs to news articles as - http://news.ngs.ru/more/53540751/
    private List<String> allUserNicknames;      // Contains all user's nick names who wrote in comments
    private Set<String> uniqueUserNicknames;    // Contains unique user's nick names

    public NgsDataRepository(){
        this.urlToArticles = new HashSet<>();
        this.allUserNicknames = new LinkedList<>();
        this.uniqueUserNicknames = new HashSet<>();
    }

    public void addUrlToArticles(String urlString){
        urlToArticles.add(urlString);
    }

    public void addNonUniqueUserNickname(String nickname){
        allUserNicknames.add(nickname);
    }

    public void addNonUniqueUserNickname(List<String> nicknames){
        allUserNicknames.addAll(nicknames);
    }

    public void addUniqueUserNickname(String nickname){
        uniqueUserNicknames.add(nickname);
    }



    public Set<String> getUrlToArticles() {
        return urlToArticles;
    }

    public List<String> getAllUserNicknames() {
        return allUserNicknames;
    }

    public Set<String> getUniqueUserNicknames() {
        return uniqueUserNicknames;
    }

    public void setUrlToArticles(Set<String> urlToArticles) {
        this.urlToArticles = urlToArticles;
    }

    public void setAllUserNicknames(List<String> allUserNicknames) {
        this.allUserNicknames = allUserNicknames;
    }

    public void setUniqueUserNicknames(Set<String> uniqueUserNicknames) {
        this.uniqueUserNicknames = uniqueUserNicknames;
    }
}
