package com.putoet.math;

import java.util.Optional;

public final class Cramer {
    private Cramer() {}

    public static Optional<double[]> solve(double[][] a, double[] b) {
        assert a != null;
        assert b != null;
        assert a.length == a[0].length;
        assert a.length == b.length;

        final int n = a.length;
        final double[] x = new double[n];

        final double det = det(a);
        if (det == 0)
            return Optional.empty();

        for (int i = 0; i < n; i++) {
            final double[][] ai = new double[n][n];
            for (int j = 0; j < n; j++) {
                System.arraycopy(a[j], 0, ai[j], 0, n);
            }

            for (int j = 0; j < n; j++) {
                ai[j][i] = b[j];
            }

            x[i] = det(ai) / det;
        }

        return Optional.of(x);
    }

    private static double det(double[][] a) {
        final int n = a.length;
        if (n == 1)
            return a[0][0];

        if (n == 2)
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];

        double det = 0;
        for (int i = 0; i < n; i++) {
            final double[][] ai = new double[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k < i)
                        ai[j - 1][k] = a[j][k];
                    else if (k > i)
                        ai[j - 1][k - 1] = a[j][k];
                }
            }

            det += (i % 2 == 0 ? 1 : -1) * a[0][i] * det(ai);
        }

        return det;
    }
}
