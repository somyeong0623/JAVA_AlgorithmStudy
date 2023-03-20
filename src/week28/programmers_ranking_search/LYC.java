import java.util.*;
import java.util.function.Consumer;

class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> scoreMap = preProcessing(info);
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            answer[i] = getNumberOfPassed(query[i], scoreMap);
        }
        return answer;
    }

    private int binarySearch(int score, List<Integer> scores) {
        int start = 0;
        int end = scores.size() - 1;
        int mid = (start + end) / 2;
        while (start < end) {       
            if (scores.get(mid) >= score) end = mid;
            else start = mid + 1;
            mid = (start + end) / 2;
        }
        if (scores.get(mid) < score) return scores.size();
        return mid;
    }

    private int getNumberOfPassed(String query, Map<String, List<Integer>> scoreMap) {
        String[] tokens = query.replace("and ", "").split(" ");
        String key = String.join("", Arrays.copyOf(tokens, tokens.length - 1));
        if (!scoreMap.containsKey(key)) return 0;
        List<Integer> scores = scoreMap.get(key);
        int score = Integer.parseInt(tokens[tokens.length - 1]);

        return scores.size() - binarySearch(score, scoreMap.get(key));
    }

    private void forEachKey(int index, String prefix, String[] tokens, Consumer<String> action) {
        if (index == tokens.length - 1) {
            action.accept(prefix);
            return;
        }
        forEachKey(index + 1, prefix + tokens[index], tokens, action);
        forEachKey(index + 1, prefix + "-", tokens, action);
    }

    private Map<String, List<Integer>> preProcessing(String[] infos) {
        Map<String, List<Integer>> scoreMap = new HashMap<>();
        for (String info: infos) {
            String[] tokens = info.split(" ");
            int score = Integer.parseInt(tokens[tokens.length - 1]);
            forEachKey(0, "", tokens, key -> {
                scoreMap.putIfAbsent(key, new ArrayList<>());
                scoreMap.get(key).add(score);
            });
        }
        for (List<Integer> list: scoreMap.values()) {
            Collections.sort(list);
        }
        return scoreMap;
    }
}