/**
 * Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?
 */


 class SparseVector {
    
    private Map<Integer, Integer> valMap;
    SparseVector(int[] nums) {
        valMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                valMap.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        Map<Integer, Integer> map1 = this.valMap;
        Map<Integer, Integer> map2 = vec.valMap;

        int res = 0;

        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            int index = entry.getKey();
            int val = entry.getValue();

            if (map2.containsKey(index)) {
                res += map2.get(index) * val;
            }
        }

        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);


// Improvement: go through the map with smaller size

// Note: Inside a class, a method can access a private variable of the reference object of its own.