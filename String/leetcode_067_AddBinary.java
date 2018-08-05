// correct answer, use carry digits StringBuilder
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        // if the carry is not zero, it should add to the sb
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
//Computation from string usually can be simplified by using a carry as such.

// my solution, which is wrong
// Integer is not enough to hold long String values and will overflow, this is a typical case
class Solution {
    public String addBinary(String a, String b) {
        int numberA = 0;
        int numberB = 0;
        // calculate a
        for(int i = a.length()-1; i >= 0; i--){
            // char必须加单引号的
            if(a.charAt(i) == '1'){
                numberA += Math.pow(2, a.length()-1-i);
            }
        }
        // calculate B
        for(int i = b.length()-1; i >= 0; i--){
            if(b.charAt(i) == '1'){
                numberB += Math.pow(2, b.length()-1-i);
            }
        }
        int sum = numberA + numberB;
        if(sum == 0) return new String("0");
        List<Integer> list = new ArrayList<>();
        while(sum > 0){
            int digit = sum % 2;
            list.add(digit);
            sum /= 2;
        }
        StringBuilder res = new StringBuilder();
        for(int i = list.size()-1; i >= 0; i--){
            res.append(list.get(i));
        }
        return res.toString();
    }
}
