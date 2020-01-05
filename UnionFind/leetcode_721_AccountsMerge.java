// UnionFind
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU();
        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailToID = new HashMap();
        int id = 0;
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> ans = new HashMap();
        for (String email: emailToName.keySet()) {
            int index = dsu.find(emailToID.get(email));
            ans.computeIfAbsent(index, x-> new ArrayList()).add(email);
        }
        for (List<String> component: ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList(ans.values());
    }
}
class DSU {
    int[] parent;
    public DSU() {
        parent = new int[10001];
        for (int i = 0; i <= 10000; ++i)
            parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}

// DFS
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email);
                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }
}

// my uf solution
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        int[] roots = new int[accounts.size()];
        for(int i = 0; i < roots.length; i++){
            roots[i] = i;
        }

        for(int i = 0; i < accounts.size(); i++){
            List<String> account = accounts.get(i);
            String name = account.get(0);
            for(int j = 1; j < account.size(); j++){
                String email = account.get(j);

                if(map.containsKey(email)){
                    union(roots, map.get(email), i);
                }
                map.put(account.get(j), i);
            }
        }

        boolean[] visited = new boolean[accounts.size()];
        for(int i = 0; i < roots.length; i++){
            if(roots[i] == i){
                List<Integer> groups = new ArrayList<>();
                dfs(roots, groups, visited, i);

                Set<String> curRes = new TreeSet<>();
                for(int group : groups){
                    List<String> account = accounts.get(group);
                    for(int j = 1; j < account.size(); j++){
                        curRes.add(account.get(j));
                    }
                }


                List<String> person = new ArrayList<>();
                person.add(accounts.get(i).get(0));

                Iterator iter = curRes.iterator();
                while(iter.hasNext()){
                    person.add((String)iter.next());
                }

                result.add(person);
            }

        }
        return result;

    }

    private void dfs(int[] roots, List<Integer> groups, boolean[] visited, int index){

        visited[index] = true;
        groups.add(index);
        for(int i = 0; i < roots.length; i++){
            if(!visited[i] && roots[i] == index){
                dfs(roots, groups, visited, i);
            }
        }
    }

    private void union(int[] roots, int i, int j){
        int first = find(roots, i);
        int second = find(roots, j);

        if(first == second) return;
        else if(first < second) {
            roots[second] = first;
        }
        else {
            roots[first] = second;
        }
    }

    private int find(int[] roots, int num){
        while(roots[num] != num){
            roots[num] = roots[roots[num]];
            num = roots[num];
        }

        return num;
    }
}
