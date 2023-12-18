/**
 * Design a logger system that receives a stream of messages along with their timestamps. Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).

All messages will come in chronological order. Several messages may arrive at the same timestamp.

Implement the Logger class:

Logger() Initializes the logger object.
bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the given timestamp, otherwise returns false.
 */

 // My solution using hashmap
 class Logger {

    private Map<String, Integer> msgMap;

    public Logger() {
        msgMap = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer lastPrintTime = msgMap.get(message);
        if (lastPrintTime == null || timestamp - lastPrintTime >= 10) {
            msgMap.put(message, timestamp);
            return true;
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

 // other solution mentioned by Editorial, maintain a queue+set
 class Pair<U, V> {
  public U first;
  public V second;

  public Pair(U first, V second) {
    this.first = first;
    this.second = second;
  }
}

class Logger {
  private LinkedList<Pair<String, Integer>> msgQueue;
  private HashSet<String> msgSet;

  /** Initialize your data structure here. */
  public Logger() {
    msgQueue = new LinkedList<Pair<String, Integer>>();
    msgSet = new HashSet<String>();
  }

  /**
   * Returns true if the message should be printed in the given timestamp, otherwise returns false.
   */
  public boolean shouldPrintMessage(int timestamp, String message) {

    // clean up.
    while (msgQueue.size() > 0) {
      Pair<String, Integer> head = msgQueue.getFirst();
      if (timestamp - head.second >= 10) {
        msgQueue.removeFirst();
        msgSet.remove(head.first);
      } else
        break;
    }

    if (!msgSet.contains(message)) {
      Pair<String, Integer> newEntry = new Pair<String, Integer>(message, timestamp);
      msgQueue.addLast(newEntry);
      msgSet.add(message);
      return true;
    } else
      return false;

  }
}