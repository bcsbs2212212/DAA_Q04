import java.util.Scanner;
public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter weight and benefit of each item:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter max weight capacity: ");
        int W = scanner.nextInt();

        knapsack(weights, values, W);
    }

    public static void knapsack(int[] weights, int[] values, int W) {
        int n = values.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        int w = W;
        System.out.println("Items selected for maximum value:");
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Item " + (i - 1) + " with weight " + weights[i - 1] + " and value " + values[i - 1]);
                w -= weights[i - 1];
            }
        }
    }
}