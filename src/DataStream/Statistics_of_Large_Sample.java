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
