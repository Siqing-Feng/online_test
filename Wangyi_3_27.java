package online_test;

import javax.print.attribute.IntegerSyntax;
import java.util.*;

public class Wangyi_3_27 {
    public static void main(String[] args) {
        solution4();
    }

    public static void solution1() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int target = sc.nextInt();
        input = input.substring(1, input.length() - 1);
        if(input.length() == 0) {
            System.out.println("[]");
            return;
        }
        String[] ss = input.split(",");
        int[] nums = new int[ss.length];
        for(int i = 0; i < ss.length; i++) {
            nums[i] = ss[i].equals("null") ? -1 : Integer.valueOf(ss[i]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        LinkedList<Integer> q1 = new LinkedList<>();
        LinkedList<List<Integer>> q2 = new LinkedList<>();
        q1.offer(0);
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        list.add(nums[0]);
        q2.offer(list);
        List<Integer> res = null;
        while(!q1.isEmpty()) {
            int pos = q1.poll();
            List<Integer> l = q2.pop();
            int left = 2 * (pos + 1) - 1;
            int right = 2 * (pos + 1);
            if(left < nums.length && nums[left] != -1) {
                List<Integer> copy = new ArrayList<>(l);
                copy.add(nums[left]);
                copy.set(0, copy.get(0) + nums[left]);
                if(copy.get(0) == target) {
                    if(res == null || res.size() < copy.size()) {
                        res = copy;
                    }
                } else if(copy.get(0) < target){
                    q1.offer(left);
                    q2.offer(copy);
                }
            }
            if(right < nums.length && nums[right] != -1) {
                List<Integer> copy = new ArrayList<>(l);
                copy.add(nums[right]);
                copy.set(0, copy.get(0) + nums[right]);
                if(copy.get(0) == target) {
                    if(res == null || res.size() < copy.size()) {
                        res = copy;
                    }
                } else if(copy.get(0) < target){
                    q1.offer(right);
                    q2.offer(copy);
                }
            }
        }
        if(res == null) {
            System.out.println("[]");
            return;
        }
        for(int i = 1; i < res.size(); i++) {
            sb.append(res.get(i));
            sb.append(",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        System.out.println(sb.toString());
    }

    public static void solution2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = 0;
        for(int i = 0; i < nums.length - res; i++) {

        }
        System.out.println(res);
    }

    public static void solution3() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

    }

    public static int res = -1;

    public static void solution4() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int y = sc.nextInt();
        sc.nextLine();
        String input = sc.nextLine();
        String[] ss = input.split(" ");
        int[] nums = new int[ss.length];
        for(int i = 0; i < ss.length; i++) {
            nums[i] = Integer.valueOf(ss[i]);
        }
        Arrays.sort(nums);
        helper(nums, 0, new boolean[nums.length], T);
        String ans = res >= T ? res - y + "": res + "";
        System.out.println(ans);
    }

    public static void helper(int[] nums, int sum, boolean[] visited, int T) {
        if(Math.abs(res - T) > Math.abs(sum - T)) {
            res = sum;
        }
        if(sum >= T) {
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i] || i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            visited[i] = true;
            sum += nums[i];
            helper(nums, sum, visited, T);
            visited[i] = false;
            if(sum >= T) {
                break;
            }
            sum -= nums[i];
        }
    }
}
