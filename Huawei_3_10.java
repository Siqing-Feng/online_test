package online_test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Huawei_3_10 {
    public static void main(String[] args) {
//        solution1();
//        solution2();
        solution3();
    }

    /**
     * 1.A、B均为两个警告ID的列表，警告ID为十六进制整数0 - 0XFFFFFFFF
     * 并且固定为8个字符，例如 0000ABCD
     * 输入：警告列表A和B，均为无序，警告ID之间以空格分割，A、B列表之间以换行分割
     *      警告ID均为合法格式，无需验证
     *      警告ID序列A和B的长度范围[1, 1000]
     * 输出：列表A和B的并集，并且以字典序升序排列，用空格分隔
     *
     * TreeSet
     * 通过率 50%
     */
    public static void solution1() {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String[] A = s1.split(" ");
        String[] B = s2.split(" ");
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        TreeSet<String> set = new TreeSet<>();
        set.addAll(Arrays.asList(A));
        set.addAll(Arrays.asList(B));
        StringBuilder sb = new StringBuilder();
        for(String s: set) {
            sb.append(s + " ");
        }
        if(sb.length() == 0) {
            System.out.println(sb.toString());
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    /**
     * 2.一排包装箱从1编号，各个包装箱存放的货物数组成一个集合M={M1,M2,...,Mn}
     * 货车一次最多运送K件货物
     * 小王想一次从中挑选K的整数倍件货物，再分批运输。
     * 仓库管理员为了方便要求小王必须选择连续的包装箱，比如可选择1、2、3号箱，不能选2、4、6
     * 如果运输K整数倍件货物，请帮小王计算有多少种挑选方式
     * 输入：
     * 包装箱数N，货车最大一次运送的数量为K件
     * 各个箱子存放的货物M1，M2，M3。。。
     * 输入为两行：N K
     * M1 M2 M3。。。
     * N和K取值范围为[1,100000]
     * 第i个包装箱存放货物的取值范围也是[1,100000]
     * 输出：一行输出有多少种方式，如果不存在可行的方式，输出0
     * 例子：
     * 输入：6 4
     *      1 2 3 4 5 6
     * 输出：3
     *
     * 前缀和 + dp
     * 通过率 100%
     */
    public static void solution2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n == 0) {
            System.out.println(0);
            System.exit(0);
        }
        int k = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] sum = new int[n + 1];
        sum[0] = 0;
        for(int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int[] dp = new int[n + 1];
        int res = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = i - 1; j >= 0; j--) {
                int sub = sum[i] - sum[j];
                if((sub % k) == 0) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
            res += dp[i];
        }
        System.out.println(res);

    }

    /**
     * 3.N个广播站，站点之间有些有连接，有些没有。有连接的站点在接受到广播后会互相发送。给定一个N*N的二维数组matrix,
     * 数组的元素都是字符'0'或者'1'。matrix[i][j]='1',则代表i和j站点之间有连接，matrix[i][j] = '0'代表没连接，
     * 现在要发一条广播，问初始最少给几个广播站发送，才能保证所有的广播站都收到消息
     * 输入：一行数据代表二维数组的各行，用逗号分隔。保证每个字符串所含的字符数一样
     * 比如：110,110,001 输出：2
     * 输出：返回初始最少需要发送广播站个数
     *
     * 并查集
     * 通过率 100%
     */
    public static void solution3() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if(input == null || input.length() == 0) {
            System.out.println(0);
            System.exit(0);
        }
        String[] ss = input.split(",");
        int n = ss.length;
        char[][] matrix = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = ss[i].charAt(j);
            }
        }
        DSU u = new DSU(n);
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    u.union(i, j);
                }
            }
        }
        System.out.println(u.count);
    }

    static class DSU {
        private int[] parent;
        private int count;

        public DSU(int n) {
            parent = new int[n];
            count = n;
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                parent[rootY] = rootX;
                count--;
            }
        }
    }
}


