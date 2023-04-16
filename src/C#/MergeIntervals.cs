using System.Collections.Generic;

public class Solution {

    public static int CompareInterval(int[] v1, int[] v2)
    {
        if (v1[0] < v2[0]) return -1;
        else if (v1[0] > v2[0]) return 1;
        else return 0;
    }


    public int[][] Merge(int[][] intervals) {
        List<int[]> list = intervals.ToList();

        list.Sort(CompareInterval);

        List<int[]> result = new List<int[]>();

        int[] t = list[0];
        for(int i=1; i<list.Count; i++)
        {
            if (list[i][0] <= t[1])
            {
                t[1] = Math.Max(list[i][1], t[1]);
            }
            else
            {
                result.Add(t);
                t = list[i];
            }
        }

        result.Add(t);

        return result.ToArray();
    }
}
