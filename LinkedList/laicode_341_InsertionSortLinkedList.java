/*
Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The insertion sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
*/
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
// LC 147
if(head == null || head.next == null) return head;
       ListNode dummy = new ListNode(0);
       dummy.next = head;
       ListNode cur = head;
       ListNode temp = null, prev = null;
       while(cur != null && cur.next != null){
           if(cur.val <= cur.next.val){       //如果按顺序排列，将cur往后移动
               cur = cur.next;
           } else {
               //把cur.next (temp)这个值跳过，cur直接先连接到cur.next.next
               temp = cur.next;
               cur.next = temp.next;
               // 从dummy开始往后遍历寻找insert的位置
               prev = dummy;
               while (prev.next.val <= temp.val){ // 用next 判断！
                   prev = prev.next;
               }
               //将temp那个存的值插入prev后面的位置
               temp.next = prev.next; //确定temp的后继节点
               prev.next = temp;      //确定temp的前驱节点

           }
       }
       return dummy.next;
