class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder first = new StringBuilder(num1);
        first = first.reverse();
        StringBuilder second = new StringBuilder(num2);
        second = second.reverse();
        StringBuilder res = new StringBuilder();
        int len = Math.max(first.length(), second.length());
        int x = 0; //digits in the first string
        int y = 0; //digits in the second string
        int carry = 0;
        int temp = 0;
        for(int i = 0; i < len; i++){
            x = (i > first.length()-1) ? 0 : first.charAt(i) - '0';
            y = (i > second.length()-1) ? 0 : second.charAt(i) - '0';
            temp = x + y + carry;
            carry = temp / 10;
            res.append(temp % 10);
        }
        if(carry > 0)
            res.append(carry);
        return res.reverse().toString();
    }

}

// high vote answer，calculate directly, not need to reverse first
public class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--){
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }
}
