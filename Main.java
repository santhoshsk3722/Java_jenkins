import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[][] grid = {
                { 1, 5 },
                { 15, 4 }
        };
        int k = 3;

        int result = countSubmatricesWithSumK(grid, k);
        System.out.println(result);
    }

    private static int countSubmatricesWithSumK(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        // Calculate prefix sums
        int[][] prefixSum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefixSum[i][j] = grid[i - 1][j - 1] + prefixSum[i - 1][j] + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1];
            }
        }

        // Count submatrices with sum k
        for (int r1 = 1; r1 <= n; r1++) {
            for (int r2 = r1; r2 <= n; r2++) {
                Map<Integer, Integer> sumCountMap = new HashMap<>();
                sumCountMap.put(0, 1); // Base case for empty submatrix

                for (int c = 1; c <= m; c++) {
                    int currentSum = prefixSum[r2][c] - prefixSum[r1 - 1][c];
                    if (sumCountMap.containsKey(currentSum - k)) {
                        count += sumCountMap.get(currentSum - k);
                    }
                    sumCountMap.put(currentSum, sumCountMap.getOrDefault(currentSum, 0) + 1);
                }
            }
        }

        return count;
    }
}
