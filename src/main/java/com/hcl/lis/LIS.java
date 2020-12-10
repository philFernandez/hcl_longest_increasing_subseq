package com.hcl.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://github.com/philFernandez/hcl_longest_increasing_subseq
 */

class LIS {
    public static int length(int[] seq) {
        List<Integer> longSubseq = new ArrayList<>();

        // always add the first element of seq to the longSubseq
        longSubseq.add(seq[0]);

        int idxOfLongSubseq = 0;
        // Iterate over seq. If an element encountered in seq is larger
        // than longestSubseq[]
        for (int i = 1; i < seq.length; i++) {
            if (seq[i] > longSubseq.get(idxOfLongSubseq)) {
                longSubseq.add(seq[i]);
                idxOfLongSubseq++;
            } else if (seq[i] < longSubseq.get(idxOfLongSubseq)) {
                boolean traversedBack = false;
                for (int j = longSubseq.size() - 1; j >= 0; j--) {
                    if (longSubseq.get(j) < seq[i]) {
                        longSubseq.remove(j + 1);
                        longSubseq.add(j + 1, seq[i]);
                        traversedBack = true;
                        break;
                    } else if (longSubseq.get(j) == seq[i]) {
                        traversedBack = true;
                        break;
                    } else if (j == 0 && seq[i] < longSubseq.get(j)) {
                        longSubseq.remove(j);
                        longSubseq.add(j, seq[i]);
                        traversedBack = true;
                        break;
                    }
                }
                if (!traversedBack) {
                    longSubseq.remove(idxOfLongSubseq);
                    longSubseq.add(idxOfLongSubseq, seq[i]);
                }
            }
        }
        System.out.println(longSubseq);
        return longSubseq.size();
    }

    public static void main(String[] args) {
        System.out.println(length(new int[] {4, 10, 4, 3, 8, 9}));
        System.out.println(length(new int[] {1, 5, 2, 5, 3, 5, 4}));
        System.out.println(length(new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7}));
        System.out.println(length(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
