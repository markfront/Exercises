/*
We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.
Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.

(Recall that the median of a sample is:

    The middle element, if the elements of the sample were sorted and the number of elements is odd;
    The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.) 

Example 1:

Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,3.00000,2.37500,2.50000,3.00000]

Example 2:

Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,4.00000,2.18182,2.00000,1.00000]

Constraints:

    count.length == 256
    1 <= sum(count) <= 10^9
    The mode of the sample that count represents is unique.
    Answers within 10^-5 of the true value will be accepted as correct.
*/

// need 2 scans
class Solution {
    public double[] sampleStats(int[] count) {
        double min=-1, max=0, mean=0, median=0, mode=0;
        
        int mode_count=0;
        long sum = 0l;
        int num_count=0;
        for(int i=0; i<=255; i++) {
            if (count[i]>0) { // i is sampled
                if (min==-1) min = i; // min not set yet
                if (max<i) max = i;
                if (mode_count < count[i]) {
                    mode = i;
                    mode_count = count[i];
                }
                
                num_count += count[i];
                
                sum += i * count[i];
            }
        }
        
        mean = 1.0 * sum / num_count;
        
        int total_num = num_count;
        num_count = 0;
        int i=0;
        while(i<=255) {
            if (count[i]>0) {
                num_count += count[i];
            }
            if (num_count*2>=total_num) break;
            i++;
        }
        int j=255;
        num_count = 0;
        while(j>=0) {
            if (count[j]>0) {
                num_count += count[j];
            }
            if (num_count*2>=total_num) break;
            j--;
        }
        if (i==j) median = i;
        else median = (i+j) * 0.5;
        
        return new double[] {min, max, mean, median, mode};
    }
}

// 1 scan solution

class Solution {
    public double[] sampleStats(int[] count) {
        double min, max, mean, median, mode;
        
        min = 255; //Double.MAX_VALUE;
        max = 0; //Double.MIN_VALUE;
        mode = 0;
        long sum = 0;
        int num_count = 0;
        int mode_count = count[0];
        
        List<int[]> history = new ArrayList<>();
        
        for(int i=0; i<count.length; i++) {
            if (count[i]==0) continue;
            
            sum += count[i] * i;
            num_count += count[i];
            
            history.add(new int[]{num_count, i});
            
            if (min > i) { min = i; }
            if (max < i) { max = i; }
            if (mode_count < count[i]) { mode = i; mode_count = count[i]; }
        }
        
        mean = sum * 1.0 / num_count;
        
        median = findMedian(history, num_count);
        
        return new double[]{min, max, mean, median, mode};
    }
    
    private double findMedian(List<int[]> history, int total_count) {
        int low = 0, high = history.size()-1;
        
        int mid1=low;        
        while(mid1<high) {            
            int mid1_count = history.get(mid1)[0];
            if (mid1_count * 2 >= total_count) {
                break;
            } else {
                mid1++;
            }
        }
        
        int mid2=high;        
        while(mid2>low) {            
            int mid2_count = total_count - history.get(mid2-1)[0];
            if (mid2_count * 2 >= total_count) {
                break;
            } else {
                mid2--;
            }
        }
        
        double median;
        if (mid1 == mid2) {
            median = history.get(mid1)[1];
        } else {
            median = ( history.get(mid1)[1] + history.get(mid2)[1] ) * 0.5 ;
        }
        
        return median;
    }
    
    // this binary search approach should work, but need fine tune.
    private double findMedian1(List<int[]> history, int total_count) {
        int low = 0, high = history.size()-1;
        
        int mid1=0, mid2=0;
        while(low<=high) {
            mid1 = low + (high-low)/2;
            mid2 = low + (high-low+1)/2;
            
            int mid1_count = history.get(mid1)[0];
            int mid2_count = history.get(mid2)[0];
            
            // must hold: mid1_count <= mid2_count
            
            if (mid2_count * 2 < total_count) {
                low = mid1;
            } else if (mid1_count * 2 > total_count) {
                high = mid2;
            } else {
                break;
            }
        }
        
        double median;
        if (mid1 == mid2) {
            median = history.get(mid1)[1];
        } else {
            median = ( history.get(mid1)[1] + history.get(mid2)[1] ) * 0.5 ;
        }
        
        return median;
    }
}
