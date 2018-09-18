/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21


Example 2:

Input: 21
Output: -1
*/
// my solution
// 一开始没有考虑数据溢出，最后用double解决，这个地方可以用try catch块抛出异常
class Solution {
    public int nextGreaterElement(int n) {
        String s = Integer.toString(n);
        char[] chars = s.toCharArray();
        int len = s.length();
        System.out.println("length:" + chars.length);
        for(int i = len-1; i > 0; i--){
            if(chars[i] <= chars[i-1]){
                continue;
            }else{
                // we can find the break point of autonomous increasing array here for index (i-1)
                for(int j = len-1; j >= i; j--){
                    if(chars[j] > chars[i-1]){
                        System.out.println("before: " + chars[0] + " " + chars[1]);
                        char temp = chars[i-1];
                        chars[i-1] = chars[j];
                        chars[j] = temp;
                        System.out.println("after: " + chars[0] + " " + chars[1]);
                        break;
                    }
                }
                // 这个地方用 Arrays.sort(number, i, number.length);更简洁
                // 此处利用对称的性质交换元素
                while(i < len-1){
                    char temp = chars[len-1];
                    chars[len-1] = chars[i];
                    chars[i] = temp;
                    i++;
                    len--;
                }
//                 防止数据Integer溢出
                String res = String.valueOf(chars);
                if(Double.parseDouble(res) > Integer.MAX_VALUE)
                    return -1;
                return Integer.parseInt(res);
            }
        }
        return -1;
    }
}

// answer solution

public class Solution {
    public int nextGreaterElement(int n) {
        char[] a = ("" + n).toCharArray();
        int i = a.length - 2;
        while (i >= 0 && a[i + 1] <= a[i]) {
            i--;
        }
        if (i < 0)
            return -1;
        int j = a.length - 1;
        while (j >= 0 && a[j] <= a[i]) {
            j--;
        }
        swap(a, i, j);
        reverse(a, i + 1);
        try {
            return Integer.parseInt(new String(a));
        } catch (Exception e) {
            return -1;
        }
    }
    private void reverse(char[] a, int start) {
        int i = start, j = a.length - 1;
        while (i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
    }
    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

// best Solution, fast solution
public class Solution {
    public int nextGreaterElement(int n) {
        char[] number = (n + "").toCharArray();

        int i, j;
        // I) Start from the right most digit and
        // find the first digit that is
        // smaller than the digit next to it.
        for (i = number.length-1; i > 0; i--)
            if (number[i-1] < number[i])
               break;

        // If no such digit is found, its the edge case 1.
        if (i == 0)
            return -1;

         // II) Find the smallest digit on right side of (i-1)'th
         // digit that is greater than number[i-1]
        int x = number[i-1], smallest = i;
        for (j = i+1; j < number.length; j++)
            if (number[j] > x && number[j] <= number[smallest])
                smallest = j;

        // III) Swap the above found smallest digit with
        // number[i-1]
        char temp = number[i-1];
        number[i-1] = number[smallest];
        number[smallest] = temp;

        // IV) Sort the digits after (i-1) in ascending order
        Arrays.sort(number, i, number.length);

        long val = Long.parseLong(new String(number));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }
}
