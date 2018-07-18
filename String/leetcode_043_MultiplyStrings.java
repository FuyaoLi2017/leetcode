public String multiply(String num1, String num2) {
    int m = num1.length(), n = num2.length();
    int[] pos = new int[m + n];

    //loop from higher digits to lower digits
    for(int i = m - 1; i >= 0; i--) {
        for(int j = n - 1; j >= 0; j--) {
          //multiply to get the result of influence for every digit
            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            int p1 = i + j, p2 = i + j + 1;
            //iterate to update the value of each digit
            //because we iterate the lower digits first, we should add the influence of previously
            //calculated lower digits
            int sum = mul + pos[p2];
            //pos[p1] is the higher digit for the calculated influence of two Integer
            //pos[p2] is the lower digit for the calculated influence of two Integer
            pos[p1] += sum / 10;
            pos[p2] = (sum) % 10;
        }
    }

    StringBuilder sb = new StringBuilder();
    for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
    return sb.length() == 0 ? "0" : sb.toString();
}
