/**
 * Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.
 */

 // my implementation, I remember I did this question when I was in MSFT Suzhou
 // ArrayList and HashMap
 // Swap the element to the last position
 class RandomizedSet {

    Random seed;
    List<Integer> list;
    // key: value, val: index
    Map<Integer, Integer> indexMap;

    public RandomizedSet() {
        seed = new Random();
        list = new ArrayList<>();
        indexMap = new HashMap<>();
    }
    
    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }
        list.add(val);
        indexMap.put(val, list.size() - 1);
        return true;
    }
    
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }

        // swap the val with the last element in the list
        int idx = indexMap.get(val);
        if (idx != list.size()-1) {
            list.set(idx, list.get(list.size()-1));
            indexMap.put(list.get(list.size()-1), idx);
        }
        indexMap.remove(val);
        list.remove(list.size() - 1);
        return true;
    }
    
    public int getRandom() {
        int randomIndex = seed.nextInt(list.size());
        return list.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */