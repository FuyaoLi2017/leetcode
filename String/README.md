- Java 中int、String的类型转换
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
1如何将字串 String 转换成整数 int?

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
