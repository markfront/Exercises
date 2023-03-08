import java.util.*;

public class StringPairsEqualTarget {
    public static int numOfPairs(String[] nums, String target) {
        Map<String, List<Integer>> prefix_map = new HashMap<>();
        Map<String, List<Integer>> suffix_map = new HashMap<>();

        int tar_len = target.length();

        Set<String> pairs = new HashSet<>();

        for(int i=0; i<nums.length; i++) {
            int num_len = nums[i].length();
            if (num_len < tar_len) {
                if (suffix_map.containsKey(nums[i])) {  
                    // a previous string expects this one as suffix
                    List<Integer> idx_list = suffix_map.get(nums[i]);
                    for(int k : idx_list) {
                        pairs.add(k + "+" + i);
                    }
                } 
                
                if (prefix_map.containsKey(nums[i])) {
                    // a previous string expects this one as prefix
                    List<Integer> idx_list = prefix_map.get(nums[i]);
                    for(int k : idx_list) {
                        pairs.add(i + "+" + k);
                    }
                } 
                
                if (target.startsWith(nums[i])) {
                    String suffix = target.substring(num_len);
                    if (!suffix_map.containsKey(suffix)) {
                        List<Integer> idx_list = new ArrayList<>();
                        idx_list.add(i);
                        suffix_map.put(suffix, idx_list);
                    } else {
                        List<Integer> idx_list = suffix_map.get(suffix);
                        idx_list.add(i);
                    }
                }
                
                if (target.endsWith(nums[i])) {
                    String prefix = target.substring(0, tar_len - num_len);
                    if (!prefix_map.containsKey(prefix)) {
                        List<Integer> idx_list = new ArrayList<>();
                        idx_list.add(i);
                        prefix_map.put(prefix, idx_list);
                    } else {
                        List<Integer> idx_list = prefix_map.get(prefix);
                        idx_list.add(i);
                    }               
                }
            }
        }

        printSet(pairs);

        return pairs.size();        
    }

    static private void printSet(Set<String> set) {
        for(String s : set) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        String[] nums; 
        String target; 
        int count_pairs;
        
        // test 1:
        // nums = new String[] { "777", "7", "77", "77" };
        // target = "7777";

        // count_pairs = numOfPairs(nums, target);

        // System.out.println("count_pairs = " + count_pairs);

        // test 2:
        // nums = new String[] { "123","4","12","34" };
        // target = "1234";

        // count_pairs = numOfPairs(nums, target);

        // System.out.println("count_pairs = " + count_pairs);

        // test 3:
        nums = new String[] { "1","1","1" };
        target = "11";

        count_pairs = numOfPairs(nums, target);

        System.out.println("count_pairs = " + count_pairs);
    }
}
