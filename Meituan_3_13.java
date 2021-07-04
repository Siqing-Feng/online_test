package online_test;

import java.util.*;

public class Meituan_3_13 {
    public static void main(String[] args) {
//        solution1();
//        solution2();
//        solution3();
//        solution4();
        solution5();
    }

    /**
     * 180°翻转二维矩阵
     */
    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(matrix[j][i]);
                if(j != n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 从只有数字和小写字母的字符串中提取有效数字，按数字从小到大输出
     */
    private static void solution2() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> list = new ArrayList<>();
        char[] cs = input.toCharArray();
        int i = 0;
        int j = 0;
        while(j < cs.length) {
            char c = cs[i];
            if(!Character.isDigit(c)) {
                i++;
                j = i;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while(j < cs.length && Character.isDigit(cs[j])) {
                if(sb.length() == 0 && cs[j] == '0') {
                    j++;
                    continue;
                }
                sb.append(cs[j++]);
            }
            if(sb.length() == 0) {
                sb.append("0");
            }
            list.add(sb.toString());
            i = j;
        }
        Collections.sort(list, (s1, s2) -> {
            int res = s1.length() - s2.length();
            return res == 0 ? s1.compareTo(s2) : res;
        });
        for(String s: list) {
            System.out.println(s);
        }
    }

    /**
     * K长度子树的的众数，若有多个众数的情况，选取数值更小的那一个
     */
    private static void solution3() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        List<Item> list = new ArrayList<>();
        Map<Integer, Item> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            if(map.containsKey(nums[i])) {
                Item item = map.get(nums[i]);
                item.add();
            } else {
                Item item = new Item(nums[i], 1);
                map.put(nums[i], item);
                list.add(item);
            }
            if(i + 1 >= k) {
                if(i >= k) {
                    map.get(nums[i - k]).delete();
                }
                Collections.sort(list, (o1, o2) -> {
                    int res = o2.fre - o1.fre;
                    return res == 0 ? o1.val - o2.val : res;
                });
                System.out.println(list.get(0).val);
            }
        }
    }

    static class Item {
        int val;
        int fre;

        public Item(int val, int fre) {
            this.val = val;
            this.fre = fre;
        }

        public void add() {
            fre++;
        }

        public void delete() {
            fre--;
        }
    }

    private static int[] use;
    private static int[] not;
    private static int[] use_small;
    private static int[] not_small;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    /**
     * 带权重二叉树，选取节点，节点不能相邻，返回选取的节点的权重和的最大值，和选取的节点中每个权重的最小值。
     * n 二叉树节点数
     * m 二叉树边数
     * n 个数字代表权重
     * m 行，两个数字 x y，代表 x 和 y 相连
     *
     * 输出：两个数字 x y
     * x：最大权重和
     * y：选区的所有节点中，权重值的最小值
     *
     * 例：
     * 5 4
     * 3 4 1 4 9
     * 1 2
     * 1 3
     * 2 4
     * 3 5
     *
     * 输出：
     * 16 3
     *
     * 通过 18%
     */
    private static void solution4() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] weight = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            weight[i] = sc.nextInt();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            List<Integer> l1 = map.getOrDefault(from, new ArrayList<>());
            l1.add(to);
            map.put(from, l1);
        }
        use = new int[n + 1];
        not = new int[n + 1];
        use_small = new int[n + 1];
        Arrays.fill(use_small, Integer.MAX_VALUE);
        not_small = new int[n + 1];
        Arrays.fill(not_small, Integer.MAX_VALUE);

        dfs(1, map, weight);

        System.out.println(max + " " + min);
    }

    //这是第二版本，没写完
    private static void dfs(int cur, Map<Integer, List<Integer>> map, int[] weight) {
        if(!map.containsKey(cur)) {
            use[cur] = weight[cur];
            not[cur] = 0;
            use_small[cur] = weight[cur];
            max = Math.max(max, use[cur]);
            min = Math.min(min, weight[cur]);
        }
        List<Integer> list = map.get(cur);
        for(int next: list) {
            dfs(next, map, weight);
        }
        for(int next: list) {
            if(use[next] > not[next]) {

            }
        }
    }

    /**
     * 无向图中寻找严格单调递减的路径的最大长度，看作节点带高度
     * n 节点数
     * m 边数
     * n 个数字代表每个节点高度
     * m 行，两个数字 x y，代表 x 和 y 相连
     *
     * 输出：高度严格单调递减的最长路径长度
     *
     * 例：
     * 5 4
     * 3 2 3 4 6
     * 1 2
     * 2 3
     * 3 4
     * 4 5
     * 输出：
     * 4
     *
     * 通过 82%
     */
    private static int[] len;

    private static void solution5() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] height = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            height[i] = sc.nextInt();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            List<Integer> l1 = map.getOrDefault(x, new ArrayList<>());
            List<Integer> l2 = map.getOrDefault(y, new ArrayList<>());
            l1.add(y);
            l2.add(x);
            map.put(x, l1);
            map.put(y, l2);
        }
        len = new int[n + 1];
//        Arrays.fill(len, -1);
        int res = 1;
        for(int i = 1; i <= n; i++) {
            len[i] = dfs(i, map, height, new boolean[n + 1]);
            res = Math.max(res, len[i]);
        }
        System.out.println(res);
    }

    private static int dfs(int cur, Map<Integer, List<Integer>> map, int[] height, boolean[] visited) {
        if(len[cur] != 0) {
            return len[cur];
        }
        visited[cur] = true;
        int res = 1;
        List<Integer> list = map.getOrDefault(cur, new ArrayList<>());
        for(int next: list) {
            if(visited[next] || height[next] >= height[cur]) {
                continue;
            }
            res = Math.max(res, dfs(next, map, height, visited) + 1);
        }
        return res;
    }
}

