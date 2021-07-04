package online_test;

import java.util.*;

public class Alibaba_3_12 {
    public static void main(String[] args) {
//        solution1();
        solution2();
    }

    /**
     * 打电话问题，A给B打电话，不知道电话号，只可以通过通讯录间接查询
     * 寻找A能最终联系上B的情况下，最少拨通的电话数
     * 输入：
     * 第一行：n m
     * n表示电话通讯录涉及的人数，m表示通讯录中电话存储数量
     * 记下来 m 行，两个正整数 a b。表示 a 有 b 的电话号。
     * 第 m + 2行，输入一个正整数 x
     * 接下来 x 行，每行输入两个整数 x y。表示一次独立询问，代表 A 和 B 两人。
     * 每两次查询之间不会有影响。
     * 输出：
     * 对于每个询问 A 联系到 B 的最少拨通数。 找不到输出 -1
     *
     * 通过率 100%
     */
    public static void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            List<Integer> list = map.getOrDefault(from, new ArrayList<>());
            list.add(to);
            map.put(from, list);
        }
        int times = sc.nextInt();
        int[][] pairs = new int[times][2];
        for(int i = 0; i < times; i++) {
            pairs[i][0] = sc.nextInt();
            pairs[i][1] = sc.nextInt();
        }
        for(int z = 0; z < times; z++) {
            int x = pairs[z][0];
            int y = pairs[z][1];
            if(x > n || y > n) {
                System.out.println(-1);
                continue;
            }
            LinkedList<Integer> q = new LinkedList<>();
            q.offer(x);
            int step = 0;
            boolean[] visited = new boolean[n + 1];
            boolean reach = false;
            A:
            while(!q.isEmpty()) {
                int size = q.size();
                for(int i = 0; i < size; i++) {
                    int cur = q.poll();
                    if(visited[cur]) {
                        continue;
                    }
                    visited[cur] = true;
                    List<Integer> nexts = map.getOrDefault(cur, new ArrayList<>());
                    if(nexts.size() == 0) {
                        continue;
                    }
                    for(int next: nexts) {
                        if (next == y) {
                            reach = true;
                            System.out.println(step + 1);
                            break A;
                        }
                        q.offer(next);
                    }
                }
                step++;
            }
            if(!reach) {
                System.out.println(-1);
            }
        }
    }

    /**
     * n * n 的棋盘，m 个车，可以水平垂直移动。在移动过程中不能让其他车打到他。
     * 输出：让所有车移动到对角线的最少步骤
     * 输入：
     * 第一行，T 表示 T 组测试用例
     * 剩下为 T 组的测试用例，首先一行 n 和 m，n 表示棋盘大小，m 表示车的数量，
     * 接下来 m 行表示每个车的坐标
     * 例子：
     * 输入：
     * 2
     * 3 1
     * 2 3
     * 3 2
     * 2 1
     * 1 2
     * 输出：
     * 1
     * 3
     *
     * 通过率 100%
     */
    public static void solution2() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        List<List<int[]>> lists = new ArrayList<>();
        int[] ns = new int[T];
        for(int z = 0; z < T; z++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            ns[z] = n;
            List<int[]> list = new ArrayList<>();
            for(int i = 0; i < m; i++) {
                int[] p = new int[]{sc.nextInt(), sc.nextInt()};
                list.add(p);
            }
            lists.add(list);
        }
        for(int z = 0; z < T; z++) {
            List<int[]> list = lists.get(z);
            int n = ns[z];
            int m = list.size();
            DSU u = new DSU(n + 1);
            int res = 0;
            for(int[] p: list) {
                if(p[0] == p[1]) {
                    continue;
                }
                int px = u.find(p[0]);
                int py = u.find(p[1]);
                if(px == py) {
                    res += 2;
                } else {
                    res += 1;
                    u.parent[p[0]] = p[1];
                }
            }
            System.out.println(res);
        }
    }

    static class DSU {
        int[] parent;
        int count;

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

/*
3 2
1 2
2 3
2
1 3
3 1
 */


