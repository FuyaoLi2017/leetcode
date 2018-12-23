### Java 中int、String的类型转换
int -> String

int i=12345;
String s="";
第一种方法：s=i+"";
第二种方法：s=String.valueOf(i);
这两种方法有什么区别呢？作用是不是一样的呢？是不是在任何下都能互换呢？

String -> int

s="12345";
int i;
第一种方法：i=Integer.parseInt(s);
第二种方法：i=Integer.valueOf(s).intValue();
这两种方法有什么区别呢？作用是不是一样的呢？是不是在任何下都能互换呢？

以下是答案：

第一种方法：s=i+"";   //会产生两个String对象
第二种方法：s=String.valueOf(i); //直接使用String类的静态方法，只产生一个对象

第一种方法：i=Integer.parseInt(s);//直接使用静态方法，不会产生多余的对象，但会抛出异常
第二种方法：i=Integer.valueOf(s).intValue();//Integer.valueOf(s) 相当于 new Integer(Integer.parseInt(s))，也会抛异常，但会多产生一个对象

--------------------------------------------------------------------
### 如何将字串 String 转换成整数 int?

A. 有两个方法:

1). int i = Integer.parseInt([String]); 或
i = Integer.parseInt([String],[int radix]);

2). int i = Integer.valueOf(my_str).intValue();

注: 字串转成 Double, Float, Long 的方法大同小异.
2 如何将整数 int 转换成字串 String ?
A. 有叁种方法:

1.) String s = String.valueOf(i);

2.) String s = Integer.toString(i);

3.) String s = "" + i;

注: Double, Float, Long 转成字串的方法大同小异.

- String比较
用equals()方法！

### Regex

[abc]	A single character of: a, b, or c
[^abc]	Any single character except: a, b, or c
[a-z]	Any single character in the range a-z
[a-zA-Z]	Any single character in the range a-z or A-Z
^	Start of line
$	End of line
\A	Start of string
\z	End of string
.	Any single character
\s	Any whitespace character
\S	Any non-whitespace character
\d	Any digit
\D	Any non-digit
\w	Any word character (letter, number, underscore)
\W	Any non-word character
\b	Any word boundary
(...)	Capture everything enclosed
(a|b)	a or b
a?	Zero or one of a
a*	Zero or more of a
a+	One or more of a
a{3}	Exactly 3 of a
a{3,}	3 or more of a
a{3,6}	Between 3 and 6 of a
options: i case insensitive m make dot match newlines x ignore whitespace in regex o perform #{...} substitutions only once

### String初始化问题
String s;和String s=null;和String s="a";有什么区别？


第一个只是定义了一个String类型变量s，并没有给它赋初值，在Java中，默认在使用一个变量的时候必须赋予它初值（降低风险）。 
第二个和第三个都定义了String类型变量s，并赋予它初值，只不过第二个赋予的值为null（空）罢了

主要要理解的是
String s; s为一个引用～～它不是对象   

第一个是没有初始化的引用；   
第二个为空引用；
第三个是在字符串池里写入一个字符'a',然后用s指向它。


String s;只是给s分配一个内存空间   
String s=null;是分配的空间中存储的值为空值   
String s="a";这句就不用我多说了分配的空间的值为字符a


特别关注：String s="a"和String s=new String("a");是有本质上的区别的 
  
前者是在字符串池里写入一个字符'a',然后用s指向它；
后者是在堆上创建一个内容为"a"的字符串对象。

### char to Int
（1）把char转成字符串， Integer.parseInt(""+'1')
或
String a = "12345";
int d = Integer.parseInt(String.valueOf(a.charAt(2)));
int c = Integer.parseInt(String.valueOf(a.charAt(3)));

Character.digit(char ch, int radix);

### Check if it is a characters
Character.isDigit(c)

### indexOf()
- one parameter: str.indexOf(matchString): find the index first matches the match String in the str
- two parameter: str.indexOf(matchString, fromIndex): find the first index of matchString from the given index

### StringBuilder
- You can append something to a string consecutively. sth.append().append()...
- setCharAt(int index, char c), can change the value at a specific position

### Permutations类型的题目建议用toCharArray()，转换成char array做题

### different between null string, empty string and "" String
https://blog.csdn.net/o0DarkNessYY0o/article/details/52526411

### substring
subString()
- convert char[] to String Object
new String​(char[] value, int offset, int count)  -> count!

### Sliding window template
https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
