
using System.Collections.Generic;

public class TopK_Frequent {
    // Solution 1: use Dictionary and Sort it by value
    public int[] TopKFrequent(int[] nums, int k) {
        Dictionary<int, int> map = new Dictionary<int, int>();
        foreach(int x in nums) {
            if (map.ContainsKey(x)) {
                map[x] = map[x] + 1;
            } else {
                map[x] = 1;
            }
        }

        int[] result = new int[k];
        int count = 0;
        foreach(var kvp in map.OrderByDescending(x => x.Value)) {
            if (count>=k) break;
            else {
                result[count] = kvp.Key;
                count++;
            }
        }
        return result;
    }

    // Solution 2: use Dictionary and PriorityQueue

    public int[] TopKFrequent2(int[] nums, int k) {
        Dictionary<int, int> numsFreq = new();

        for(int i=0; i<nums.Length; i++){
            if(numsFreq.ContainsKey(nums[i])){
                numsFreq[nums[i]]++;
            }
            else{
                numsFreq.Add(nums[i], 1);
            }
        }

        PriorityQueue<int, int> pq = new();

        foreach(var kvp in numsFreq){
            pq.Enqueue(kvp.Key, -kvp.Value);
        }

        int[] result = new int[k];

        for(int i=0; i<k; i++){
            result[i] = pq.Dequeue();
        }

        return result;
    }
}