package com.mobiquity.packer.util;

import com.mobiquity.packer.model.Package;
import com.mobiquity.packer.model.Thing;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * This problem reduces to a classic knapsack problem
 * Algorithm modified based on the requirements
 *
 */
public class PackageProcessor {

    /**
     * Method to process each package and return suitable list of list of items
     * @param aPackage package
     * @return list of suitable items
     */
    public static String processPackage(Package aPackage) {

        Set<Integer> result = new TreeSet();

        int[] wt = aPackage.getThings().stream().mapToInt(Thing::getWeight).toArray();
        int[] val = aPackage.getThings().stream().mapToInt(Thing::getCost).toArray();

        int n = val.length;
        // form a  knapsack of capacity W
        int W = aPackage.getMaxWeight();
        int i, w;

        int K[][] = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] +
                            K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        // stores the result of Knapsack
        int res = K[n][W];

        w = W;
        for (i = n; i > 0 && res > 0; i--) {

            // either the result comes from the top
            // (K[i-1][w]) or from (val[i-1] + K[i-1]
            // [w-wt[i-1]]) as in Knapsack table. If
            // it comes from the latter one/ it means
            // the item is included.
            if (res == K[i - 1][w])
                continue;
            else {
                result.add(aPackage.getThings().get(i - 1).getId());
                // Since this weight is included its
                // value is deducted
                res = res - val[i - 1];
                w = w - wt[i - 1];
            }
        }
        return result.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
