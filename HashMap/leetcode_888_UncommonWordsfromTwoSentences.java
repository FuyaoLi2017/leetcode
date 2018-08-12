// brute force solution
class Solution {
public String[] uncommonFromSentences(String A, String B) {
    Map<String, Integer> map1 = oneTime(A);
    Map<String, Integer> map2 = oneTime(B);
    Set<String> temp = new HashSet<>();

    for(Map.Entry<String, Integer> entry : map1.entrySet()) {
        if(entry.getValue() == 1 && !map2.containsKey(entry.getKey())) {
            temp.add(entry.getKey());
        }
    }

    for(Map.Entry<String, Integer> entry : map2.entrySet()) {
        if(entry.getValue() == 1 && !map1.containsKey(entry.getKey())) {
            temp.add(entry.getKey());
        }
    }

    String[] result = new String[temp.size()];
    int i = 0;

    for(String s : temp) {
        result[i++] = s;
    }
    return result;
}

private HashMap<String, Integer> oneTime(String A) {
    String[] str = A.split(" ");
    HashMap<String, Integer> map = new HashMap<>();

    for(String s : str) {
        map.put(s, map.getOrDefault(s, 0) + 1);
    }
    return map;
}
}

// similar ideas, better solution use toArray() keySet()
/**
* How to use toArray()
* https://www.cnblogs.com/qianqian528/p/7985118.html
*/
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> count = new HashMap<>();
        String[] words = (A + " " + B).split(" ");
        for (String w : words)
            count.put(w, count.getOrDefault(w, 0) + 1);
        ArrayList<String> res = new ArrayList<>();
        for (String w : count.keySet())
            if (count.get(w) == 1)
                res.add(w);
        return res.toArray(new String[0]);
    }
}
