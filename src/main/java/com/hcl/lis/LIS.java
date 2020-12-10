package com.hcl.lis;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/philFernandez/hcl_longest_increasing_subseq
 */

class LIS {
    public static int length(int[] seq) {
        List<Integer> longSubseq = new ArrayList<>();

        // always add the first element of seq to the longSubseq
        longSubseq.add(seq[0]);

        // Iterate over seq. If an element encountered in seq is larger
        // than the last element in longSubseq, then iterate backword
        // from end of longSubseq until an element is found that less than
        // or equal to the element in seq. If the element was less then move
        // forward one index and replace the value at that index in longSubseq
        // with the element of seq
        for (int i = 1; i < seq.length; i++) {
            if (seq[i] > longSubseq.get(longSubseq.size() - 1)) {
                longSubseq.add(seq[i]);
            } else if (seq[i] < longSubseq.get(longSubseq.size() - 1)) {
                boolean traversedBack = false;
                // We need to traverse back over longest seq to make sure there
                // isn't a number that is smaller than the one we are inserting
                // into longestSubseq. If there is such a number, insert the current
                // seq[i] directly after it
                for (int j = longSubseq.size() - 1; j >= 0; j--) {
                    if (longSubseq.get(j) < seq[i]) {
                        longSubseq.remove(j + 1);
                        longSubseq.add(j + 1, seq[i]);
                        traversedBack = true;
                        break;
                        // if we find a number that is same as seq[i] just do nothing
                    } else if (longSubseq.get(j) == seq[i]) {
                        traversedBack = true;
                        break;
                        // if we've reached the first element of longestSubseq, and
                        // that element is less than the first element of longestSubseq,
                        // then replace the first element of longestSubseq with seq[i]
                    } else if (j == 0 && seq[i] < longSubseq.get(j)) {
                        longSubseq.remove(j);
                        longSubseq.add(j, seq[i]);
                        traversedBack = true;
                        break;
                    }
                }
                // If we did not have to traverse back to insert a number
                // then we'll replace the last element in longestSubseq with seq[i]
                if (!traversedBack) {
                    longSubseq.remove(longSubseq.size() - 1);
                    longSubseq.add(longSubseq.size() - 1, seq[i]);
                }
            }
        }
        System.out.println("Longest Increasing Subsequence: " + longSubseq);

        return longSubseq.size();
    }

    public static void main(String[] args) {
        System.out.println(length(new int[] {4, 10, 4, 3, 8, 9}));
        System.out.println(length(new int[] {1, 5, 2, 5, 3, 5, 4}));
        System.out.println(length(new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7}));
        System.out.println(length(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
