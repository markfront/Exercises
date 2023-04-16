import java.util.*;

/*
 * C(n, r) = n! / (r! * (n-r)!)
 * P(n, r) = n! / (n-r)!
 */
public class Combinations {
    public static List<String[]> combine(String[] strings, int k) {
        List<String[]> result = new ArrayList<String[]>();

        int n = strings.length;
        if (n <= 0 || n < k || k < 1)
            return result;

        List<String> item = new ArrayList<String>();
        dfsHelper(strings, n, k, 0, item, result);

        return result;
    }

    private static void dfsHelper(String[] strings, int n, int k, int start, List<String> item,
            List<String[]> result) {
        if (item.size() == k) {
            result.add(item.toArray(new String[0]));
            return;
        }

        for (int i = start; i < n; i++) {
            item.add(strings[i]);
            dfsHelper(strings, n, k, i + 1, item, result);
            item.remove(item.size() - 1);
        }
    }

    public static void printCombinations(List<String[]> list, String delimiter) {
        for(String[] a : list) {
            System.out.println(String.join(delimiter, a));
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[] { "a", "b", "c", "d", "e" };
        int k = 3;

        List<String[]> combinations = combine(strings, k);

        printCombinations(combinations, ",");

        System.out.println("Total combinations = " + combinations.size());
    }
}
