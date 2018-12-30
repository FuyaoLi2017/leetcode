/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

Example 1:

Given buf = "abc"
read("abc", 1) // returns "a"
read("abc", 2); // returns "bc"
read("abc", 1); // returns ""
Example 2:

Given buf = "abc"
read("abc", 4) // returns "abc"
read("abc", 1); // returns ""
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    // a b c d
    char[] temp = new char[4];
    int bufferPointer = 0;
    int bufferCount = 0;
    public int read(char[] buf, int n) {
        int pointer = 0;  // 本次读取一共实际上成功读取了多少个字符
        while (pointer < n) {
            if (bufferPointer == 0) {
                bufferCount = read4(temp);
                /*
                "a"
                [read(2)]
                */
                // 读完所有还不够读的在这里return
                if (bufferCount == 0) return pointer;  // 在后面令bufferPointer = 0 之后回到这个位置
            }
            while (bufferPointer < bufferCount && pointer < n) {
                buf[pointer++] = temp[bufferPointer++];
            }
            if (bufferPointer >= bufferCount) {
                bufferPointer = 0;
            }
        }
        // 没有读到底的在这个位置return
        return pointer;
    }
}


// a more specific solution
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private int offset = 0;
    private int remaining = 0;
    private boolean eof = false;
    private char[] buffer = new char[4];
    public int read(char[] buf, int n) {
        int readByte = 0;
        // need to read && have the char to read
        while (readByte < n && (remaining != 0 || !eof)) {
            int readSize = 0;
            // if we do not have the previous char, we continue read, otherwise we use the previous char
            if (remaining != 0) {
                readSize = remaining;
            } else {
                offset = 0;
                readSize = read4(buffer);
                if (readSize < 4) {
                    eof = true;
                }
            }
            int len = Math.min(n - readByte, readSize);
            for (int i = offset; i < offset + len; i++) {
                buf[readByte++] = buffer[i];
            }
            remaining = readSize - len;
            if (remaining != 0) {
                offset += len;
            }
        }
        return readByte;
    }
}
