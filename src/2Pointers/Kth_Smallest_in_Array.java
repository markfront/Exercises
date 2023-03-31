public class Kth_Smallest_in_Array {
  // given an integer array, and an integer k, find the k-th smallest element in the array.
  public int find(int[] nums, int k) {
    if (nums==null || nums.length == 0 || k > nums.length) return -1;

    return helper(nums, 0, nums.length-1, k); // k starts from 1
  }

  // return the kth smallest in section [b,e].
  public int helper(int[] nums, int b, int e, int k) {
    if (b>=e || k>(e-b+1)) return nums[b]; // base case

    int p = partition(nums, b, e);

    if ( k == p-b+1 ) return nums[p]; // from index b to p, there are (p-b+1) elements.
    else if ( k > (p-b+1) ) return helper(nums, p+1, e, k - (p-b+1)); // section [b,p] has (p-b+1) elements
    else return helper(nums, b, p-1, k - (b + 1)); // section [0,b] has (b+1) elements
  }

  // return the index of the pivot element in the section [b, e] of the array,
  // such that elements in [b, e] less than the pivot are on its left,
  // while those larger than it are on its right.
  private int partition(int[] nums, int b, int e) {
    int v = nums[b];
    int lastS1=b, firstS2=b+1;
    for(int i=b+1; i<=e; i++) {
      if (nums[i] < v) {
        swap(nums, i, firstS2);
        firstS2++;
        lastS1++;
      }
    }
    swap(nums, b, lastS1);

    return lastS1;
  }

  private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}
