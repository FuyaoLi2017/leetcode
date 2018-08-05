public char findTheDifference(String s, String t) {
	char c = 0;
	for (int i = 0; i < s.length(); ++i) {
		c ^= s.charAt(i);
	}
	for (int i = 0; i < t.length(); ++i) {
		c ^= t.charAt(i);
	}
	return c;
}

// a more simple Solution
// the String t is one letter longer than String s
// we can use this way to loop through all letter
// letters using XOR twice will become 1
class Solution {
    public char findTheDifference(String s, String t) {
	int n = t.length();
	char c = t.charAt(n - 1);
	for (int i = 0; i < n - 1; ++i) {
		c ^= s.charAt(i);
		c ^= t.charAt(i);
	}
	return c;
}
}

// we can also use ASCII char code method
public class Solution {
    public char findTheDifference(String s, String t) {
        // Initialize variables to store sum of ASCII codes for
        // each string
        int charCodeS = 0, charCodeT = 0;
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) charCodeS += (int)s.charAt(i);
        for (int i = 0; i < t.length(); ++i) charCodeT += (int)t.charAt(i);
        // Return the difference between 2 strings as char
        return (char)(charCodeT - charCodeS);
    }
}

// and since s.length is just one digit smaller than that of t.length
// we can design a one-pass solution
public class Solution {
public char findTheDifference(String s, String t) {
        int charCode = t.charAt(s.length());
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) {
              charCode -= (int)s.charAt(i);
              charCode += (int)t.charAt(i);
        }
        return (char)charCode;
    }
}
