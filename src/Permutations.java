
import java.util.*;

public class Permutations {

    public static List<String[]> permute(String[] strings) {
        List<String[]> result = new ArrayList<>();
        helper(0, strings, result);
        return result;
    }

    private static void helper(int start, String[] strings, List<String[]> result){
        if(start==strings.length-1){
            result.add(Arrays.copyOf(strings, strings.length));
            return;
        }

        for(int i=start; i<strings.length; i++){
            swap(strings, i, start);
            helper(start+1, strings, result);
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
        String[] strings = new String[] { "a", "b", "c", "d" };

        List<String[]> permutations = permute(strings);

        printPermutations(permutations, ",");

        System.out.println("Total permutations = " + permutations.size());
    }
}
