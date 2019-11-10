/*
Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.


Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist


Note:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
Accepted
*/

// store in reverse order and check it
class StreamChecker {

    class TrieNode {
        boolean isWord;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root = new TrieNode();
    StringBuilder sb = new StringBuilder();

    public StreamChecker(String[] words) {
        createTrie(words);
    }

    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        for (int i = sb.length() - 1; i >= 0 && node != null; i--) {
            char c = sb.charAt(i);
            node = node.next[c - 'a'];
            if (node != null && node.isWord) {
                return true;
            }
        }
        return false;
    }

    private void createTrie(String[] words) {
        for (String s : words) {
            TrieNode node = root;
            int len = s.length();
            for (int i = len - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.isWord = true;
        }
    }
}


// my solution get TLE, the string construction part is too complicated
class StreamChecker {

    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode(){
            children = new TrieNode[26];
        }
    }

    TrieNode root;
    int longestWord = 0;
    char[] charList;
    // current scan position
    int cursor = 0;
    int totalLen = 0;

    public StreamChecker(String[] words) {
        root = new TrieNode();


        for(String word : words){
            TrieNode prev = root;
            longestWord = Math.max(longestWord, word.length());
            for (int i = 0; i < word.length(); i++) {
                if (prev.children[word.charAt(i) - 'a'] == null) {
                    prev.children[word.charAt(i) - 'a'] = new TrieNode();
                }
                prev = prev.children[word.charAt(i) - 'a'];
            }
            prev.isWord = true;
        }

        charList = new char[longestWord];
    }

    public boolean query(char letter) {
        // System.out.println("totalLen:" + totalLen + " curChar:" + letter);
        int pos = totalLen % longestWord;
        charList[pos] = letter;
        totalLen++;

        if(totalLen <= longestWord || pos == longestWord-1) {
            for(int i = 0; i <= pos; i++){
                String curString = new String(charList, pos-i, i+1);
                // System.out.println("string examine:" + curString);
                if(searchWord(curString)) return true;
            }
            return false;
        }

        // create a cycle
        String prev = new String(charList, pos+1, longestWord-pos-1);
        String preceding = new String(charList, 0, pos+1);
        String fullString = prev + preceding;
        // System.out.println("fullString:" + fullString);
        for(int i = 0; i < longestWord; i++){
            if(searchWord(fullString.substring(longestWord-i-1))) return true;
        }
        return false;

    }

    private boolean searchWord(String word){
        if(word == null || word.length() == 0) return false;
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null)
                return false;
            else
                cur = cur.children[word.charAt(i) - 'a'];
        }

        return cur.isWord;
    }
}

// don't create new array
class StreamChecker {

    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode(){
            children = new TrieNode[26];
        }
    }

    TrieNode root;
    int longestWord = 0;
    char[] charList;
    // current scan position
    int cursor = 0;
    int totalLen = 0;

    public StreamChecker(String[] words) {
        root = new TrieNode();


        for(String word : words){
            TrieNode prev = root;
            longestWord = Math.max(longestWord, word.length());
            for (int i = 0; i < word.length(); i++) {
                if (prev.children[word.charAt(i) - 'a'] == null) {
                    prev.children[word.charAt(i) - 'a'] = new TrieNode();
                }
                prev = prev.children[word.charAt(i) - 'a'];
            }
            prev.isWord = true;
        }

        charList = new char[longestWord];
    }

    public boolean query(char letter) {
        int pos = totalLen % longestWord;
        charList[pos] = letter;
        totalLen++;
        if(totalLen <= longestWord) {
            for(int i = 0; i < totalLen; i++){
                if(searchWord(charList, pos, i+1)) return true;
            }
            return false;
        }

        // have a cycle check
        for(int i = 0; i < longestWord; i++){
            if(searchWord(charList, pos, i+1)) return true;
        }
        return false;
    }

    // end consider position, count
    private boolean searchWord(char[] word, int offset, int count){
        if(word == null || word.length == 0) return false;
        TrieNode cur = root;
        for (int i = count-1; i >= 0; i--) {
            int pos = (offset - i < 0) ? (offset - i + longestWord) : (offset - i);
            if (cur.children[charList[pos] - 'a'] == null)
                return false;
            else
                cur = cur.children[charList[pos] - 'a'];
        }

        return cur.isWord;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
