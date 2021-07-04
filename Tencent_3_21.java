package online_test;

import java.util.*;

public class Tencent_3_21 {
    public static void main(String[] args) {
        solution4();
    }

    public static void solution1() {
        Scanner sc = new Scanner(System.in);

    }

    /**
     * T 组数据，每次操作，可以使 n ：
     * 1、如果 n 是 2 的倍数，n 变为 n / 2
     * 2、如果 n 是 3 的倍数，n 变为 n / 3
     * 3、使 n 减一
     * 输出：使 n 变为 0 的最少操作次数
     * 输入：
     * 1 ： T 组测试
     * 10
     * 输出：
     * 4
     */
    public static void solution2() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] nums = new int[T];
        for(int i = 0; i < T; i++) {
            nums[i] = sc.nextInt();
        }
        int max = 0;
        for(int num: nums) {
            max = Math.max(num, max);
        }
        int[] dp = new int[max + 1];
        Arrays.fill(dp, max + 1);
        dp[0] = 0;
        for(int i = 1; i <= max; i++) {
            int o1 = dp[i - 1] + 1;
            int o2 = i % 2 == 0 ? dp[i / 2] + 1: max + 1;
            int o3 = i % 3 == 0 ? dp[i / 3] + 1: max + 1;
            dp[i] = Math.min(Math.min(o1, o2), o3);
        }
        for(int num: nums) {
            System.out.println(dp[num]);
        }
    }

    /**
     * n 个数组，q 次询问
     * 每次询问，指定挑选的数组下标，返回组合数组第 k 小值
     * 输入：
     * 2
     * 1 2
     * 2 1 3
     * 2
     * 1 1 1
     * 2 1 2 2
     * 输出：
     * 2
     * 2
     */
    public static void solution3() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int len = sc.nextInt();
            int[] nums = new int[len];
            for(int j = 0; j < len; j++) {
                nums[j] = sc.nextInt();
            }
            list.add(nums);
        }
        int q = sc.nextInt();
        int[] ks = new int[q];
        List<int[]> merge = new ArrayList<>();
        for(int i = 0; i < q; i++) {
            int len = sc.nextInt();
            int[] nums = new int[len];
            for(int j = 0; j < len; j++) {
                nums[j] = sc.nextInt() - 1;
            }
            merge.add(nums);
            ks[i] = sc.nextInt();
        }
        for(int i = 0; i < q; i++) {
            System.out.println(getK(list, merge.get(i), ks[i]));
        }
    }

    private static int getK(List<int[]> list, int[] target, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        for(int i: target) {
            int[] nums = list.get(i);
            for(int num: nums) {
                q.offer(num);
                if(q.size() > k) {
                    q.poll();
                }
            }
        }
        return q.peek();
    }

    /**
     * n w : n 人， w 奖金总数
     * low[0] up[0]
     * ...
     * low[n - 1] up[n - 1]
     *
     * 输出所有员工奖金中位数最大值
     *
     * 输入：
     * 3 20
     * 8 10
     * 1 4
     * 7 9
     * 输出：
     * 9
     */
    public static void solution4() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int limit = sc.nextInt();
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int low = sc.nextInt();
            int up = sc.nextInt();
            list.add(new int[]{low, up});
            limit -= low;
        }
        Collections.sort(list, (o1, o2) -> {
            int res = o1[1] - o2[1];
            return res == 0 ? o1[1] - o2[1] : res;
        });
        int mid = n / 2;
        List<int[]> l = new ArrayList<>();
        for(int i = n / 2; i < n; i++) {
            l.add(list.get(i));
        }
        Collections.sort(l, (o1, o2) -> {
            int res = o1[0] - o2[0];
            return res == 0 ? o1[1] - o2[1] : res;
        });
        while(limit > 0) {
            l.get(0)[0]++;
            limit--;
            Collections.sort(l, (o1, o2) -> {
                int res = o1[0] - o2[0];
                return res == 0 ? o1[1] - o2[1] : res;
            });
            if(l.get(0)[0] == l.get(0)[1]) {
                break;
            }
        }
        System.out.println(l.get(0)[0]);
    }

    /**
     * n 件商品，只能挑 m 的倍数的价格结账，尽量结更多的钱，输出剩下的钱数，有的钱数等于所有商品总价
     * T : T 组测试数据
     * n m : n 件商品， m 钱的基数
     * prices[0] .... prices[n - 1]
     * 输入：
     * 2
     * 3 3
     * 3 6 9
     * 3 5
     * 9 6 3
     * 输出：
     * 0
     * 3
     */
    public static void solution5() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] ns = new int[T];
        int[] ms = new int[T];
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < T; i++) {
            ns[i] = sc.nextInt();
            ms[i] = sc.nextInt();
            int[] prices = new int[ns[i]];
            for(int j = 0; j < ns[i]; j++) {
                prices[j] = sc.nextInt();
            }
            list.add(prices);
        }
        for(int i = 0; i < T; i++) {
            System.out.println(getRemain(ns[i], ms[i], list.get(i)));
        }
    }

    private static int getRemain(int n, int m, int[] prices) {
        int total = 0;
        for(int p: prices) {
            total += p;
        }
        if(total < m) {
            return 0;
        }
        int times = total / m;
        int target = total - m * times;
        Arrays.sort(prices);
        for(int i = times; i >= 0; i--) {
            int aim = target + i * m;
            if(check(aim, prices, 0, 0)) {
                return aim;
            }
        }
        return total;
    }

    private static boolean check(int target, int[] nums, int cur, int index) {
        if(cur == target) {
            return true;
        }
        for(int i = index; i < nums.length; i++) {
            cur += nums[i];
            if(cur > target) {
                break;
            }
            if(check(target, nums, cur, i + 1)) {
                return true;
            }
            cur -= nums[i];
        }
        return false;
    }
}
/*
5 60
5 10
6 12
7 7
8 19
9 20
 */
