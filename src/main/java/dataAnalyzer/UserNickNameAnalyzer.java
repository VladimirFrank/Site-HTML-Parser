package dataAnalyzer;

import java.util.*;

/**
 * Created by sbt-filippov-vv on 22.02.2018.
 */
public class UserNickNameAnalyzer {

    public static Map<Integer, String> getSortedNicknameAndScore(List<String> nonUniqueNicknames, Set<String> uniqueNicknames){
        Map<Integer, String> nicknamesAndScores = new TreeMap<>();
        for(String nickname : uniqueNicknames){
            nicknamesAndScores.put(Collections.frequency(nonUniqueNicknames, nickname), nickname);
        }
        return nicknamesAndScores;
    }
}
