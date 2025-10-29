package matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Math;

public class Matrix {
    private static int[][] memo;

    private static int getLongestIncreasingPath(List<List<Integer>> matrix) {
        if (matrix == null || matrix.isEmpty()) {
            return 0;
        }

        int rows = matrix.size();
        int cols = matrix.get(0).size();
        memo = new int[rows][cols];

        int maxLength = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, i, j, -1));
            }
        }

        return maxLength;
    }

    private static int dfs(List<List<Integer>> matrix, int i, int j, int prev) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        if (i < 0 || j < 0 || i >= rows || j >= cols || matrix.get(i).get(j) <= prev) {
            return 0;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int maxPath = 1;
        maxPath = Math.max(maxPath, 1 + dfs(matrix, i, j + 1, matrix.get(i).get(j)));
        maxPath = Math.max(maxPath, 1 + dfs(matrix, i + 1, j, matrix.get(i).get(j)));
        maxPath = Math.max(maxPath, 1 + dfs(matrix, i, j - 1, matrix.get(i).get(j)));
        maxPath = Math.max(maxPath, 1 + dfs(matrix, i - 1, j, matrix.get(i).get(j)));

        memo[i][j] = maxPath;
        return maxPath;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<List<Integer>> matrix = readMatrix(reader);

            System.out.println(getLongestIncreasingPath(matrix));
        }

    }

    private static List<List<Integer>> readMatrix(BufferedReader reader) throws IOException {
        String[] sizes = reader.readLine().strip().split(" ");
        int n = Integer.parseInt(sizes[0]);
        List<List<Integer>> matrix = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Integer.parseInt(token))
                .collect(Collectors.toList());
    }
}
