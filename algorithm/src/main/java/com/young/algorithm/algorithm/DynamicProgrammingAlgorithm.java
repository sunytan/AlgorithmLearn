package com.young.algorithm.algorithm;

import com.young.algorithm.sort.HeapSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-3:下午3:18
 * Description: this is DynamicProgrammingAlgorithm 动态规划算法
 */
public class DynamicProgrammingAlgorithm extends AlgorithmImpl {

    public DynamicProgrammingAlgorithm(){
        NAME = this.getClass().getSimpleName();
        DEBUG = true;
    }

    @Override
    public void process(int[] array) {
//        countMaxLenStr(array);
        int a[][] = inputNumTower(5);
//        numTower(a);
//        numTower2(a);
//        maxPublicStr();
//        coinBest();
        canFinish(4,a);
    }

    private int[] userInput(int length){
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int[] input = new int[length];
        while (scanner.hasNext()) {
            input[i] = scanner.nextInt();
            i++;
            if (i>=length) break;
        }
        return input;
    }

    /***********************最长递增子序列start***********************/
    int maxLen[]; // 记录每个阶段的最长递增子序列
//    int input[]; // 由用户输入的数组
//    int length = 10;
    private void countMaxLenStr(int[] input){
        int length = input.length;
        maxLen = new int[length];
        for (int j = 0; j < length; j++) {
            maxLen[j] = 1; // 因为针对每个单独的元素长度都是1.
        }

        // 从第一个子序列开始,依次往后遍历,第1,2,3...length的子序列，求最大值
        for (int j = 0; j < input.length; j++) {
            // 从前面遍历，没次求较短的子序列的最大值
            for (int k = 0; k < j; k++) {
                // input[i] > input[k] 表示当前值比第k个值要大，
                // 所以相对于0-K的子序列来加入i，又增加了递增子序列的长度，
                // 但是需要比较此长度加上增加的这个1 之后的长度是否比当前i的最大长度还要大。
                if (input[j] > input[k] && maxLen[k] +1 > maxLen[j]) {
                    maxLen[j] = maxLen[k]+1;
                }
            }
        }
        // 对maxLen进行排序，找到最大值
        printf(maxLen);
        new HeapSort(true).sort(maxLen);


    }

    /***********************最长递增子序列start***********************/


    /***********************最大连续子序列和start***********************/
    public void maxSum(int input[]) {
        printf(input);
        int length = input.length;
        int sum = input[0];
        int max = input[0];
        for (int i = 1; i < length; i++) {
            // 如果都为正数，下面毫无意义，因为必然是全部之和最大。
            // 只有当存在负数时才有意义。加上一个负数必然使总和变小，
            // 当前面的和加上后一个数之后比其本身都小，
            // 说明前面是一个很小的负数，那就可以把前面的全部序列放弃了。
            sum = Math.max(sum+input[i],input[i]);
            if (sum > max) {
                max = sum;
                System.out.println("max = "+max);
            }
        }

    }
    /***********************最大连续子序列和end***********************/

    /***********************卡尔最大组合问题和start***********************/
    /**
     * dota2 的卡尔目前只有冰、火、雷三种属性。组合的技能有10种。分别是：
     * 冰冰冰
     * 冰冰火
     * 冰冰雷
     * 冰火雷
     * 冰火火
     * 冰雷雷
     * 火火雷
     * 火雷雷
     * 火火火
     * 雷雷雷
     * 计算如果要加一个风属性，最大技能数是多少
     *
     */
    private void kaerSkill(int array[]){
        int count = 0;
        int len = array.length;
        int dp[] = new int[len];
        for (int i = 0; i < len; i++) {
            dp[0] = i;
            for (int j = 0; j < len; j++) {
                if (j>=dp[0]) {
                    dp[1] = j;
                    for (int k = 0; k < len; k++) {
                        if (k>=dp[1]) {
                            dp[2] = k;
                            for (int l = 0; l < len; l++) {
                                if (l>=dp[2]) {
                                    count ++;
                                    dp[3] = l;
                                    printf(dp);
                                }
                            }
                        }
                    }
                }
            }
        }
        dprintf(count);
    }

    // i 访问下标
    int count = 0;
//    int len = 4;
    private void kaerSkillDp(int i,int array[]){
        if (i>=array.length) {
            count ++;
            dprintf(count);
            printf(array);
            return;
        }
        for (int j = 0; j < array.length; j++) {
            if (i == 0 || j >= array[i-1]) {
                array[i]= j;
                kaerSkillDp(i+1,array);
            }
        }
    }

    private void kaerSkillDp2(int row,int array[]){
        if (count >= array.length) {
            count ++;
            dprintf(count);
            printf(array);
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (row == 0 || i > array[row -1]) {
                array[row] = i;
                kaerSkillDp(row+1,array);
            }
        }
    }

    /***********************最大组合问题和end***********************/


    private int[][] inputNumTower(int length){
        int a[][] = new int[length][length];
        for (int l = 0; l < length; l++) {
            int in[] = userInput(l+1);
            for (int k = 0; k < in.length; k++) {
                a[l][k] = in[k];
            }
        }
        return a;
    };

    /***********************数字塔问题start***********************/
    private void numTower(int a[][]){
        int maxRow = a.length;
        System.out.println("输入的数字塔：");
        for (int i = 0; i < maxRow; i++) {
            printf(a[i]);
        }
        int dp[][] = new int[maxRow][maxRow];
        dp[0][0] = a[0][0]; // 第一个边界值
        int max = dp[0][0];
        for (int i = 1; i < maxRow; i++) {
            dp[i][0]=dp[i-1][0] + a[i][0]; //每一行的第一列
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j])+a[i][j];
                max = Math.max(dp[i][j],max);
            }
            max = Math.max(dp[i][0],max);
        }
        // 打印最终的数字塔
        System.out.println("最终的数字塔：max = "+max);
        for (int i = 0; i < maxRow; i++) {
            printf(dp[i]);
        }
    }

    private void numTower2(int a[][]){
        int maxRow = a.length;
        int dp[] = new int[maxRow];
        System.out.println("输入的数字塔：");
        for (int i = 0; i < maxRow; i++) {
            printf(a[i]);
        }
        dp[0] = a[0][0];
        int max = dp[0];
        for (int i = 1; i < maxRow; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == 0) {
                    dp[0] = a[i][0] + dp[0];
                }else if (j==i) {
                    dp[j] = dp[j-1]+a[i][j];
                }else {
                    dp[j] = Math.max(dp[j],dp[j-1]) +a[i][j];
                }
                max = Math.max(dp[j],max);
                printf(dp);
            }
        }
        // 打印最终的数字塔
        System.out.println("最终的数字塔：max = "+max);
        printf(dp);
    }

    /***********************数字塔问题end***********************/


    /***********************最大公共子序列start***********************/
    // 不需要连续
    private void maxPublicStr(){
        String str1 = "qweroiuskla";
        String str2 = "qefqweroisadkalfas";
        int m = str1.length();
        int n = str2.length();
        int dp[][] = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        dprintf(dp[m][n]);
        for (int i = 0; i <= m; i++) {
            printf(dp[i]);
        }
    }

    /***********************虽大公共子序列end***********************/


    /***********************虽大公共子序列start***********************/
    int coin[] = {1,2,5,10,20,50};
    int aim = 10;
    public void coinBest(){
        // 行表示使用的零钱的下标，
        // dp[i][j]表示从a[0...i]的零钱，组成总数钱为j的方案数
        int dp[][] = new int[coin.length][aim +1];
        for (int i = 0; i < coin.length; i++) {
            dp[i][0] = 1; // 这个表示需要换的钱为0，就只有一种方案。
        }
        for (int i = 0; i <= aim; i++) {
            if (i % coin[0] == 0){
                dp[0][i] = 1; // 表示只用第一个零钱来换，如果能被整除就表示有一种方案，
            }else {
                dp[0][i] = 0; // 如果不能被整除，就表示没法换。
            }
        }
        for (int i = 1; i < coin.length; i++) {
            for (int j = 1; j <= aim; j++) {
                int tmp = 0;
                for (int k = 0; k*coin[i] <= j; k++) {
                    tmp += dp[i-1][j-k*coin[i]];
                }
                dp[i][j] = tmp;
            }
        }
        dprintf(dp[coin.length-1][aim]);
        for (int i = 0; i < coin.length; i++) {
            printf(dp[i]);
        }
    }

    /***********************虽大公共子序列end***********************/

    /***********************和可被5整除的子序列start***********************/
    /**
     * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目
     *
     * 示例：
     * 输入：A = [4,5,0,-2,-3,1], K = 5
     * 输出：7
     * 解释：
     * 有 7 个子数组满足其元素之和可被 K = 5 整除：
     * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     *
     */
    public int subarraysDivByK(int[] A, int K) {
        int sum = 0;
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];//序列和
            int alpha = (sum % K +K) %K;
            int same = map.getOrDefault(sum,0);
            count += alpha;
            map.put(sum,same+1);
        }

        return -1;
    }


    /***********************和可被5整除的子序列end***********************/

    /***********************逆时针遍历矩阵start*****************************/
    public int[] spiralOrder(int[][] matri) {
        int matrix[][] ={{1}};
        int m = matrix.length;
        int n = matrix[0].length;
        int output[] = new int[m*n];
        int i = 0;
        int j = 0;
        int rmn[][] = new int[m][n];//已经遍历过需要至为1
        int hasM = 0; //已经遍历过的行数
        int hasN = 0; // 已经遍历过的列数
        int c = 0;
        while (c < m*n-1) {
            hasM ++;
            while (c < m*n){
                output[c] = matrix[i][j];
                rmn[i][j] = 1;
                if (hasM % 2 ==1) {
                    if (j == n-1 || rmn[i][j+1] == 1) { // 如果遇到已经遍历过的就停止
                        break;
                    }
                    j++;
                }else {
                    if (j == 0 || rmn[i][j-1] == 1) { // 如果遇到已经遍历过的就停止
                        break;
                    }
                    j--;
                }
                c++;
                System.out.println("hasM = "+hasM);
                System.out.println("i = "+i);
                System.out.println("j = "+j);
                System.out.println("c = "+c);
            }
            hasN ++;
            while (c < m*n-1) {
                rmn[i][j] = 1;
                if (hasN % 2 ==1) {
                    if (i == m-1 || rmn[i+1][j] == 1) { // 如果遇到已经遍历过的就停止
                        break;
                    }
                    i++;
                }else {
                    if (i == 0 || rmn[i-1][j] == 1) { // 如果遇到已经遍历过的就停止
                        break;
                    }
                    i--;
                }
                c++;
                output[c] = matrix[i][j];
                System.out.println("hasN = "+hasM);
                System.out.println("i = "+i);
                System.out.println("j = "+j);
                System.out.println("c = "+c);
            }
        }
        printf(output);

        return output;
    }



    //
    public int[] spiralOrder1(int[][] matri) {
        int matrix[][] ={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int m = matrix.length;
        int n = matrix[0].length;
        int output[] = new int[m*n];
        int l= 0,t =0,r = n-1,b = m-1;  // 左上右下的边界值
        int c = 0;
        while (true) {
            // 从左往右
            for (int i = l; i <= r; i++) {
                output[c] = matrix[t][i];
                c++;
                System.out.println("l = "+l);
                System.out.println("t = "+t);
                System.out.println("r = "+r);
                System.out.println("b = "+b);
                System.out.println("c = "+c);
                printf(output);
            }
            if (c >= m*n) break;
            t++;
            // 从上往下
            for (int i = t; i <= b; i++) {
                output[c] = matrix[i][r];
                c++;
                System.out.println("l = "+l);
                System.out.println("t = "+t);
                System.out.println("r = "+r);
                System.out.println("b = "+b);
                System.out.println("c = "+c);
                printf(output);
            }
            if (c >= m*n) break;
            r--;
            // 从右往左
            for (int i = r; i >= l; i--) {
                output[c] = matrix[b][i];
                c++;
                System.out.println("l = "+l);
                System.out.println("t = "+t);
                System.out.println("r = "+r);
                System.out.println("b = "+b);
                System.out.println("c = "+c);
                printf(output);
            }
            if (c >= m*n) break;
            b--;
            // 从下网上
            for (int i = b; i >= t; i--) {
                output[c] = matrix[i][l];
                c++;
                System.out.println("l = "+l);
                System.out.println("t = "+t);
                System.out.println("r = "+r);
                System.out.println("b = "+b);
                System.out.println("c = "+c);
                printf(output);
            }
            if (c >= m*n) break;
            l++;
        }
        printf(output);

        return output;
    }

    /***********************逆时针遍历矩阵end****************************/

    /***********************最长连续序列start******************************/
    public void longestConsecutive(int array[]){
        new HeapSort().sort(array); // 首先进行堆排序
        int max  = 1;
        int len = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i-1]+1) {
                len++;
            }else {
                len =1;
            }
            max = Math.max(max,len);
        }
        dprintf(array);
        dprintf(max);
    }

    /***********************最长连续序列end******************************/

    /***********************课程表 leetcode 207 start ******************************/
    /**
     *
     * @param numCourses 课程数
     * @param prerequisites 关联表
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses]; // 入度,表示需要读了多少本书才能读自己
        List<List<Integer>> matrix = new ArrayList<>(numCourses);
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            matrix.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            indegree[prerequisite[0]]++;// 有入度
            matrix.get(prerequisite[1]).add(prerequisite[0]);
        }


        return false;
    }
    /***********************课程表 leetcode 207 end ******************************/
}
