package com.hcl.lis;

import java.util.Arrays;

class LIS {

    public int length(int[] seq) {
        if (seq == null || seq.length == 0)
            return 0;
        int[] sorted = new int[seq.length];
        sorted[0] = seq[0];
        int len = 1;
        for (int num : seq) {
            int index = Arrays.binarySearch(sorted, 0, len, num);
            if (index < 0) {
                index = -(index + 1);
            }

            sorted[index] = num;
            if (len == index)
                len++;
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new LIS().length(new int[] {1, 5, 2, 5, 3, 5, 4}));
        System.out.println(new LIS()
                .length(new int[] {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,}));
    }
}
