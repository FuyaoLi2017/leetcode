/*
Heap Sort is a comparison based sorting algorithm with O(nlogn) time and O(1) space.

Requirements:

You have to do it in place, extra space used is no more than O(1).
Time complexity is O(nlogn).
*/

// O(nlogn) time complexity
// O(1) space complexity
public class Solution {

  public int[] heapsort(int[] arr)
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end, the node at position 0 is the largest node
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
        return arr;
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap

    // function: transform the array's sequence, heapify the nodes after i to a maxheap, the max range is index n
    // the max value should be at the node i after heapify
    void heapify(int[] arr, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}
