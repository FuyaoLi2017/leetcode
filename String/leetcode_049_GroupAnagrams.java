class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0){
            return new ArrayList();
        }
        Map<String, List> ans = new HashMap<>();
        for(String s : strs){
            char[] element = s.toCharArray();
            Arrays.sort(element);
            String key = String.valueOf(element);
            if(!ans.containsKey(key)){
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
