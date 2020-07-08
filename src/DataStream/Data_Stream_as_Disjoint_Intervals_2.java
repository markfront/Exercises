/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class SummaryRanges {
    public class Range extends Interval implements Comparable<Range> {
        public Range(int s, int e) {
            super(s, e);
        }
        public int compareTo(Range range) {
            if (this.end+1 < range.start) return -1;
            else if (this.start > range.end+1) return 1; 
            else return 0; // overlap or contain or adjacent
        }
    }
    
    List<Range> ranges;
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        this.ranges = new ArrayList<>();
    }
    
    public void addNum(int val) {
        Range curr = new Range(val, val);
        addRange(curr);
        Collections.sort(this.ranges);
    }
    
    public void addRange(Range curr) { // keep in sorted order, and disajoint
        int n = ranges.size();
        int low=0, high=n-1;

        if (curr.compareTo(ranges.get(low)) < 0) {
            ranges.add(0, curr);
        } else if (curr.compareTo(ranges.get(high)) > 0) {
            ranges.add(curr);
        } else {

            int mid=-1, check=-1;
            Range target = null;
            while(low<=high) {
                mid = (low+high)/2;
                check = curr.compareTo(ranges.get(mid));
                if (check < 0) {
                    high = mid-1;
                } else if (check > 0) {
                    low = mid+1;
                } else {
                    target = ranges.get(mid);
                    break;
                }
            }

            if (target!=null) { // the curr is overlapping with the target
                target.start = Math.min(target.start, curr.start);
                target.end = Math.max(target.end, curr.end);

                // now the target might overlap with the one on its left or on its right
                if (mid-1 >= 0) {
                    checkAndMerge(target, mid-1);
                }
                if (mid+1 < this.ranges.size()) {
                    checkAndMerge(target, mid+1);
                }

            } else {
                // the curr range is not overlapping with any others
                ranges.add(mid, curr);
            }
        }        
    }
    
    public void checkAndMerge(Range target, int index) {
        Range range = this.ranges.get(index);
        int check = target.compareTo(range);
        if (check==0) {
            target.start = Math.min(target.start, range.start);
            target.end = Math.max(target.end, range.end);
            this.ranges.remove(index);
        }
    }
    
    public List<Interval> getIntervals() {
        List<Interval> result = new ArrayList<>(this.ranges.size());
        for(Range r: this.ranges) {
            result.add(new Interval(r.start, r.end));
        }
        return result;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
 
