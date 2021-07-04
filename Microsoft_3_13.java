package online_test;

import java.util.*;

public class Microsoft_3_13 {
    public static void main(String[] args) {
        System.out.println(solution1(new int[]{-1, 1, 3, 3, 3, 2, 3, 2, 1, 0}));
        System.out.println(solution2(new int[]{2, 1}, new int[]{5, 1}, 3, 6));
    }

    /**
     * Microsoft Problem 3
     * 寻找数组 A 中等差子数组的个数，子数组长度至少为 3
     * 若结果超过 1,000,000,000，返回 -1
     * @param A 数组
     * @return 等差数组的子个数
     */
    private static int solution1(int[] A) {
        int limit = 1000000000;
        int n = A.length;
        if(n < 3) {
            return 0;
        }
        int[] dp = new int[n];
        int res = 0;
        for(int i = 2; i < n; i++) {
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
            res += dp[i];
            if(res > limit) {
                return -1;
            }
        }
        return res;
    }

    /**
     * Microsoft Problem 2
     * 在吊车帮助下，B 位置的东西是否可以移动到 E 位置
     * 对于吊车 K，活动范围为 [A[K] - P[K], A[K] + P[K]]
     * @param A 每个吊车的臂长
     * @param P 每个吊车的位置，不可移动
     * @param B 物体起始位置
     * @param E 物体目标位置
     * @return 是否可以将物体从 B 移动到 E
     */
    private static boolean solution2(int[] A, int[] P, int B, int E) {
        if(B == E) {
            return true;
        }
        //提取区间
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < A.length; i++) {
            int[] range = new int[]{P[i] - A[i], P[i] + A[i]};
            list.add(range);
        }
        //排序区间
        Collections.sort(list, (o1, o2) -> {
            int res = o1[0] - o2[0];
            return res == 0 ? o1[1] - o2[1] : res;
        });
        List<int[]> update = new ArrayList<>();
        //合并区间
        int[] pre = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            int[] cur = list.get(i);
            if(cur[0] > pre[1]) {
                update.add(pre);
                pre = cur;
            } else {
                pre[1] = Math.max(pre[1], cur[1]);
            }
        }
        update.add(pre);
        //目标移动区间
        int[] target = new int[]{Math.min(B, E), Math.max(B, E)};
        //判断目标移动区间，是否在某个覆盖区间内
        for(int[] r: update) {
            if(r[0] <= target[0] && r[1] >= target[1]) {
                return true;
            }
        }
        return false;
    }

}
