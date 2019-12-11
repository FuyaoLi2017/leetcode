/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/
// Binary tree could be constructed from preorder/postorder and inorder traversal.
//
// Inorder traversal of BST is an array sorted in the ascending order: inorder = sorted(preorder).
// Java solution using preorder and queue to deserialize the tree
// https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/93175/Java-PreOrder-%2B-Queue-solution
public class Codec {
    private static final String SEP = ",";
    private static final String NULL = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return NULL;
        //traverse it recursively if you want to, I am doing it iteratively here
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.empty()) {
            root = st.pop();
            sb.append(root.val).append(SEP);
            if (root.right != null) st.push(root.right);
            if (root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
        if (data.equals(NULL)) return null;
        String[] strs = data.split(SEP);
        Queue<Integer> q = new LinkedList<>();
        for (String e : strs) {
            q.offer(Integer.parseInt(e));
        }
        return getNode(q);
    }

    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
        if (q.isEmpty()) return null;
        TreeNode root = new TreeNode(q.poll());//root (5)
        Queue<Integer> samllerQueue = new LinkedList<>();
        while (!q.isEmpty() && q.peek() < root.val) {
            samllerQueue.offer(q.poll());
        }
        //smallerQueue : 3,2   storing elements smaller than 5 (root)
        root.left = getNode(samllerQueue);
        //q: 6,7   storing elements bigger than 5 (root)
        root.right = getNode(q);
        return root;
    }
}

// USING postorder is more concise
public class Codec {
  public StringBuilder postorder(TreeNode root, StringBuilder sb) {
    if (root == null) return sb;
    postorder(root.left, sb);
    postorder(root.right, sb);
    sb.append(root.val);
    sb.append(' ');
    return sb;
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = postorder(root, new StringBuilder());
    return sb.toString();
  }

  public TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
    if (nums.isEmpty()) return null;
    int val = nums.getLast();
    if (val < lower || val > upper) return null;

    nums.removeLast();
    TreeNode root = new TreeNode(val);
    root.right = helper(val, upper, nums);
    root.left = helper(lower, val, nums);
    return root;
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isEmpty()) return null;
    ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
    for (String s : data.split("\\s+"))
      nums.add(Integer.valueOf(s));
    return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
  }
}

// encode the BST without delimiter, encode the integer into the solution, solution from answer
public class Codec {
  // Encodes a tree to a list.
  public void postorder(TreeNode root, StringBuilder sb) {
    if (root == null) return;
    postorder(root.left, sb);
    postorder(root.right, sb);
    sb.append(intToString(root.val));
  }

  // Encodes integer to bytes string
  public String intToString(int x) {
    char[] bytes = new char[4];
    for(int i = 3; i > -1; --i) {
      bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
    }
    return new String(bytes);
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    postorder(root, sb);
    return sb.toString();
  }

  // Decodes list to tree.
  public TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
    if (nums.isEmpty()) return null;
    int val = nums.getLast();
    if (val < lower || val > upper) return null;

    nums.removeLast();
    TreeNode root = new TreeNode(val);
    root.right = helper(val, upper, nums);
    root.left = helper(lower, val, nums);
    return root;
  }

  // Decodes bytes string to integer
  public int stringToInt(String bytesStr) {
    int result = 0;
    for(char b : bytesStr.toCharArray()) {
      result = (result << 8) + (int)b;
    }
    return result;
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
    int n = data.length();
    for(int i = 0; i < (int)(n / 4); ++i) {
      nums.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
    }

    return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
  }
}

// deserialize a binary tree, a general way!!!
public class Codec {

    private static final String SPLITER = ",";
    private static final String NULL = "N";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder st = new StringBuilder();
        serialize(root, st);
        return st.toString();
    }
    public void serialize(TreeNode root, StringBuilder st) {
        if (root == null) st.append(NULL + SPLITER);
        else {
            st.append(root.val + SPLITER);
            serialize(root.left, st);
            serialize(root.right, st);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(SPLITER)));
        return deseralize(queue);
    }
    public TreeNode deseralize(Queue<String> queue) {
        String cur = queue.poll();
        if (cur.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = deseralize(queue);
        root.right = deseralize(queue);
        return root;
    }
}
