### 对dummy node的使用
一定要记住把dummy node和head连起来，返回值是dummy.next,方便对头部的处理

### 常见的corner cases
- fast, slow pointer应该移动到的位置
    - 首先移动的fast pointer应该到哪个位置停下
- 是否需要dummy node, reference的listnode跟dummy node的关系


### Selection sort linked list衍生问题
https://piazza.com/class/j0eqhhdregb3i?cid=3969
由于此题debug用了很久，进而由此题衍生出一个子问题，提供给大家参考，也许能提醒到大家，避免踩到同样的坑里



题目：已知两个ListNode及它们各自的Previous ListNode，如何swap这两个ListNode？（不能仅仅swap值）

Assumption: 输入的四个ListNode都不是null
```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public void swapNode(ListNode one, ListNode two, ListNode onePrev, ListNode twoPrev) {
    ListNode tmp = one.next; // line1
    twoPrev.next = one;      // line2
    one.next = two.next;
    onePrev.next = two;
    two.next = tmp;
  }
}
```
这个题看上去很容易，我们可能随手就写出上面这样的code，但是实际是错的。

假如one.next就是two，那么这个code就会使链表产生loop

改正的话，只要把line1和line2的顺序对调一下即可



这个corner case真的很容易漏掉，有些同学可能无意中写对了，但其实没有意识到这个corner case

尤其是这个swap只是某一个题的helper的时候，可能思路还放在主函数那里，就更容易漏掉了


### 关于变量创建
如果要是可以用一个node 和它的next node来表示，那就其实不需要重新声明一个ListNode
