/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/
// https://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/

// a standard hierholzers algorithm
/*
Below is the Algorithm:
Remember that a directed graph has an Eulerian cycle if following conditions are true
(1) All vertices with nonzero degree belong to a single strongly connected component.
(2) In degree and out degree of every vertex is same. The algorithm assumes that the given graph has Eulerian Circuit.
*/
class Solution {
    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();

        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }
}



// my TLE solution
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        int trip = tickets.size();
        // key: form, value: the possible dest list
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(from)) {
                List<String> list = new ArrayList<>();
                list.add(to);
                map.put(from, list);
            } else {
                map.get(from).add(to);
            }
        }

        List<String> cur = new ArrayList<>();
        cur.add("JFK");

        List<List<String>> allResults = new ArrayList<>();

        dfs(allResults, cur, trip, map);

        Collections.sort(allResults, (a, b) -> {
            for (int i = 0; i <= trip; i++) {
                if (a.get(i).compareTo(b.get(i)) != 0) {
                    return a.get(i).compareTo(b.get(i));
                }
            }
            return 0;
        });
        return allResults.get(0);
    }

    private void dfs(List<List<String>> allResults, List<String> cur, int trip, Map<String, List<String>> map) {
        if (cur.size() == trip+1) {
            allResults.add(new ArrayList<String>(cur));
            return;
        }
        String previous = cur.get(cur.size() - 1);
        List<String> list = map.get(previous);

        // didn't get enough destination
        if (list == null || list.size() == 0) return;
        List<String> cloneList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            cloneList.add(list.get(i));
        }
        for (int i = 0; i < cloneList.size(); i++) {
            String curString = cloneList.get(i);
            cur.add(curString);
            list.remove(curString);
            dfs(allResults, cur, trip, map);
            list.add(curString);
            cur.remove(cur.size()-1);
        }
    }
}
