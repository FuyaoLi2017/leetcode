/*
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
*/
// fastest solution using indexOf()
class Solution {
    public String addBoldTag(String s, String[] dict) {

        boolean[] bolds =markBolds(s,dict);
        return createBoldedString(s,bolds);
    }

    private boolean[] markBolds(String s,String[] dict){
        boolean[] bolds = new boolean[s.length()];
        int fromIndex;
        int startIndex;

        for(String d:dict){
            fromIndex=0;
            startIndex=-1;
            do{
                // find occurrences
                startIndex = s.indexOf(d,fromIndex);
                if(startIndex!=-1){
                    // mark the characters should be bold
                    for(int i=startIndex;i<startIndex+d.length();i++){
                        bolds[i] = true;

                    }
                    fromIndex=startIndex+1;
                }
            }while(startIndex!=-1);
        }

        return bolds;
    }

    private String createBoldedString(String s,boolean[] bolds){
        StringBuilder bolded = new StringBuilder();
        boolean tagOpen = false;
        int length = s.length();
        for(int i =0;i<length;i++){
            char current = s.charAt(i);
            if(!bolds[i] ){
                if(tagOpen){
                    bolded.append("</b>");
                    tagOpen =false;
                }
            }
            else{
                if(!tagOpen){
                    bolded.append("<b>");
                    tagOpen = true;
                }
            }
            bolded.append(current);
        }

        // close incase there is open tag
        if(tagOpen&&bolds[length-1])
            bolded.append("</b>");

        return bolded.toString();
    }
}

// using startsWith()
class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        int end = 0;
        for(int i=0;i<s.length();i++){
            boolean found = false;
            for(String word:dict){
                if(s.startsWith(word,i)){
                    found = true;
                    end = Math.max(end,i+word.length());
                }
            }
            int j = i;
            if(found){   //once we find a word matches, then set all the index of dp array to be true.
                while(j < end){
                    bold[j] = found;
                    j++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(!bold[i]){
                sb.append(s.charAt(i));
                continue;
            }
            int j = i;
            while(j<s.length()&&bold[j]){
                j++;
            }
            sb.append("<b>"+s.substring(i,j)+"</b>");
            i = j-1;
        }
        return sb.toString();
    }
}

// my TLE solution
class Solution {
    public String addBoldTag(String s, String[] dict) {
        Set<String> set = new HashSet<>();
        for (String str : dict) {
            set.add(str);
        }
        boolean[] dp = new boolean[s.length()];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String check = s.substring(j, i);
                if (set.contains(check)) {
                    for (int k = j; k < i; k++) {
                        dp[k] = true;
                    }
                }
            }
        }

        // the consecutive true value should be covered with bold tag: [start, end]
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == true) {
                if (i == 0 || (i > 0 && dp[i - 1] == false)) {
                    start.add(i);
                }
                if (i == dp.length - 1 || (i < dp.length - 1 && dp[i + 1] == false)) {
                    end.add(i);
                }
            }
        }
        // TTT FFF TT FF
        // construct the result
        StringBuilder sb = new StringBuilder();
        // no letter found
        if (start.size() == 0) {
            return s;
        }
        // append the front part
        sb.append(s.substring(0, start.get(0)));
        for (int i = 0; i < start.size(); i++) {
            int startIndex = start.get(i);
            int endIndex = end.get(i);
            sb.append("<b>").append(s.substring(startIndex,endIndex + 1)).append("</b>");
            if (i + 1 < start.size()) {
                sb.append(s.substring(endIndex + 1, start.get(i + 1)));
            } else {
                sb.append(s.substring(endIndex + 1, s.length()));
            }
        }
        return sb.toString();
    }
}


1 , 2 ,-1, 1, 2
