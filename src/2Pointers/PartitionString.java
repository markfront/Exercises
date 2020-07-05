/*
https://leetcode.com/problems/partition-labels/

The idea is to find start/end index for each char, consider each [start,end] as interval, then transform the problem into merge intervals.
*/

class Solution {
    public List<Integer> partitionLabels(String S) {
        if (S==null || S.length()==0) return null;
        
        Map<Character, int[]> map = new HashMap<>();
        char[] chars = S.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], new int[]{i, i});
            } else {
                int[] a = map.get(chars[i]);
                a[1] = i; // update the last occurrence
            }
        }
        
        Comparator<int[]> comp = new Comparator<>() {
            public int compare(int[] a, int[] b) {
                return (a[0]<b[0])? -1 : ( (a[0]>b[0])? 1 : 0 ); // sort by interval start
            }
        };
        
        int[][] intervals = new int[map.size()][];
        int k=0;
        for(int[] v : map.values()) {
            intervals[k++] = v;
        }
        
        Arrays.sort(intervals, comp);
        
        List<Integer> result = new ArrayList<>();
        int i=0, j=1;
        while(j<intervals.length) {
            if (intervals[j][1] <= intervals[i][1]) {
                j++;
            } else {
                if (intervals[i][1] <= intervals[j][0]) {
                    int size = intervals[i][1] - intervals[i][0]+1;
                    result.add(size);
                    i = j;
                    j++;
                } else {
                    intervals[i][1] = intervals[j][1]; // extend end
                    j++;
                }
            }
        }
        
        result.add(intervals[i][1] - intervals[i][0]+1);
        
        return result;
    }
}
