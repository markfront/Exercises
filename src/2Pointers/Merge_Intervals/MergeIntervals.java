/**

 * Definition for an interval.

 * public class Interval {

 *     int start;

 *     int end;

 *     Interval() { start = 0; end = 0; }

 *     Interval(int s, int e) { start = s; end = e; }

 * }

 */

public class Solution {

    private class MyComparator implements Comparator<Interval> {

        public int compare(Interval v1, Interval v2) {

            /*

            if (v1.start < v2.start) return -1;

            else if (v1.start > v2.start) return 1;

            else if (v1.end < v2.end) return -1;

            else if (v1.end > v2.end) return 1;

            else return 0;

            */

            //return (v1.start!=v2.start)? (new Integer(v1.start)).compareTo(new Integer(v2.start)) : (new Integer(v1.end)).compareTo(new Integer(v2.end));

            if (v1.start!=v2.start) return v1.start - v2.start;

            else return v1.end - v2.end;

        }

    }

    

    public List<Interval> merge(List<Interval> intervals) {

        if (intervals==null || intervals.size()<=1) return intervals;

        

        Collections.sort(intervals, new MyComparator());

        

        List<Interval> result = new ArrayList<>();

        

        int i=0, j=1;

        result.add(intervals.get(i));

        while(j<intervals.size()) {

            Interval v1 = result.get(i);

            Interval v2 = intervals.get(j);

            if (v1.end < v2.start) { // not overlap

                result.add(v2);

                i++; 

            } else {

                //v1.start = Math.min(v1.start, v2.start);

                v1.end = Math.max(v1.end, v2.end);

            }

            j++;

        }

        return result;

    }

}