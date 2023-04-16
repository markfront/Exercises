
import java.util.*;

/*
 * C(n, r) = n! / (r! * (n-r)!)
 * P(n, r) = n! / (n-r)!
 */
public class Permutations {

    public static List<String[]> permute(String[] strings, int k) {
        List<String[]> result = new ArrayList<>();
        helper(0, k, strings, result);
        return result;
    }

    private static void helper(int start, int k, String[] strings, List<String[]> result){
        if(start==k){
            result.add(Arrays.copyOf(strings, k));
            return;
        }

        for(int i=start; i<strings.length; i++){
            swap(strings, i, start);

            helper(start+1, k, strings, result);

            swap(strings, i, start);
        }
    }

    private static void swap(String[] strings, int i, int j){
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
    }

    public static void printPermutations(List<String[]> list, String delimiter) {
        for(String[] a : list) {
            System.out.println(String.join(delimiter, a));
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[] { "a", "b", "c", "d", "e" };

        List<String[]> permutations = permute(strings, 3);

        printPermutations(permutations, ",");

        System.out.println("Total permutations = " + permutations.size());
    }
}
