/*
https://leetcode.com/problems/non-overlapping-intervals/

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping. 

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Note:

    You may assume the interval's end point is always bigger than its start point.
    Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
*/


/*
Idea: Actually, the problem is the same as "Given a collection of intervals, find the maximum number of intervals that are non-overlapping." 
(the classic Greedy problem: Interval Scheduling). With the solution to that problem, guess how do we get the minimum number of intervals to remove?

Sorting Interval.end in ascending order is O(nlogn), then traverse intervals array to get the maximum number of non-overlapping intervals is O(n). 
Total is O(nlogn).
*/
import java.util.*;

class Solution {
    public class Interval {
        int idx;
        int start;
        int end;
        public Interval(int i, int s, int e) {
            idx = i;
            start = s;
            end = e;
        }
    }

    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return new Integer(a.end).compareTo(new Integer(b.end));
        }
    };
    
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals==null || intervals.length<2) return 0;
        
        List<Interval> list = new ArrayList<>();
        for(int i=0; i<intervals.length; i++) {
            int[] x = intervals[i];
            list.add(new Interval(i, x[0], x[1]));
        }
        
        Collections.sort(list, new IntervalComparator());
        
        int prev_end = list.get(0).end;
        int count = 1; // count non-overlap intervals
        for(int i=1; i<list.size(); i++) {
            int curr_start = list.get(i).start;
            int curr_end = list.get(i).end; 
            if (prev_end <= curr_start) {
                count++;
                prev_end = curr_end;
            }
        }        
        
        return list.size() - count; // intervals to be removed
    }
}

