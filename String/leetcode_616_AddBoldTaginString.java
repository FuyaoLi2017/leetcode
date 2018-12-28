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
