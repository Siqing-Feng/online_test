package online_test;

import java.util.*;

public class Alibaba_3_22 {

    public static void main(String[] args) {
        solution1();
    }

    public static void solution1() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for(int j = 0; j < n; j++) {
                nums[j] = sc.nextInt();
            }
            list.add(nums);
        }
        for(int i = 0; i < T; i++) {
            System.out.println(helper(list.get(i)));
        }
    }

    private static int helper(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = dp[0] + (nums[1] + 1) / 2;
        for(int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i] , dp[i - 1] + (nums[i] + 1) / 2);
        }
        return dp[dp.length - 1];
    }


    /**
     * 每次操作，把数组分为两部分，保留和较小的一部分，丢弃和较大的一部分，
     * 得分为较小那部分的和，重复操作，直至数组长度只剩下 1
     * 例子：
     * 6
     * 6 2 3 4 4 5
     * 输出：
     * 18
     * 输入格式：
     * n 数组长度 n
     * num[0] ..... num[n - 1]  n 个数字
     * 输出格式：
     * 最高得分
     */
    public static void solution2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int[] sum = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[n][n];
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for(int k = i; k < j; k++) {
                    int part1 = sum[k + 1] - sum[i];
                    int part2 = sum[j + 1] - sum[k + 1];
                    int add1 = dp[i][k];
                    int add2 = dp[k + 1][j];
                    if(part1 < part2) {
                        dp[i][j] = Math.max(dp[i][j], part1 + add1);
                    } else if(part1 > part2) {
                        dp[i][j] = Math.max(dp[i][j], part2 + add2);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], Math.max(part1 + add1, part2 + add2));
                    }
                }
            }
        }
        System.out.println(dp[0][n - 1]);
    }
}
