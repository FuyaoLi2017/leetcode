/*
https://www.hackerrank.com/contests/hack-it-to-win-it-paypal/challenges/q4-traveling-is-fun/problem
*/

// a reference non-TLE solution
public static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
    int[] root = new int[n + 1];
    int[] ids = new int[n + 1];

    for (int i = 0; i <= n; i++) {
        root[i] = i;
        ids[i] = 1;
    }

    for (int i = g + 1; i <= n; i++)
        for (int j = 2 * i; j <= n; j += i)
            unionFind(j, i, root, ids);

    int[] res = new int[originCities.length];

    int index = 0;
    while (index < res.length){
        res[index] = (getRoot(originCities[index], root) == getRoot(destinationCities[index], root) ? 1 : 0);
        index++;
    }

    return res;
}

// union by weight
private static void unionFind(int a, int b, int[] root, int[] ids) {
    int aRoot = getRoot(a, root);
    int bRoot = getRoot(b, root);

    if (aRoot == bRoot)
        return;

    if (ids[aRoot] < ids[bRoot]) {
        root[aRoot] = root[bRoot];
        ids[bRoot] += ids[aRoot];
    } else {
        root[bRoot] = root[aRoot];
        ids[aRoot] += ids[bRoot];
    }
}

private static int getRoot(int a, int[] root) {
    while (a != root[a])
        a = root[a];
    return a;
}





// my solution, TLE
static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
    // Complete this function
    // find paths
    UF uf = new UF(n);
    for(int i = Math.max(1,g);  i <= n-1; i++){
        for(int j = i+1; j <= n; j++){
            if(isValidPath(i-1,j-1,g)){
                uf.union(i-1,j-1);
            }
        }
    }

    int[] result = new int[originCities.length];
    for(int i = 0; i < originCities.length; i++){
        if(uf.find(originCities[i]-1) == uf.find(destinationCities[i]-1)){
            result[i] = 1;
        } else {
            result[i] = 0;
        }
    }
    return result;
}

private static boolean isValidPath(int i, int j, int threshold){
    return gcd(i+1, j+1) > threshold;
}

private static int gcd(int i, int j){
    // i is garanteed to be smaller than j
    if(j % i == 0) return i;
    int diff = j-i;
    return (diff > i) ? gcd(i, diff) : gcd(diff, i);
}

static class UF {
    int[] parent;
    int[] rank;
    public UF(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        rank = new int[n];
    }
    public int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // union by rank
    public void union(int p , int q) {
        int pX = find(p),  qX = find(q);
        if (pX != qX) {
            if (rank[pX] > rank[qX])
                parent[qX] = pX;
            else if (rank[pX] < rank[qX])
                parent[pX] = qX;
            else {
                parent[qX] = pX;
                rank[pX]++;
            }
        }
    }
}
