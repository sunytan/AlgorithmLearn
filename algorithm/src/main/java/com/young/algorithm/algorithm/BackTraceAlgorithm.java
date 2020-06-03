package com.young.algorithm.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:下午6:37
 * Description: this is BackTraceAlgorithm
 */
public class BackTraceAlgorithm extends AlgorithmImpl {

    public BackTraceAlgorithm(){
        NAME = this.getClass().getSimpleName();
        DEBUG = true;
    }

    @Override
    public void process(int[] array) {
//        queue(array);
        packSolution();
    }

    /************************八皇后问题开始********************************/

    int total_solution = 0; // 总共有几种解法
    private void queue(int array[]){
        backTrace(array,0);
        System.out.println("total_solution="+total_solution);
    }

    private void printfQueue(int arr[]){
        final int length = arr.length;
        int result[][] = new int[length][length];
        System.out.println("arr = "+ Arrays.toString(arr));
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (arr[i] == j) {
                    result[i][j] = 1;
                }
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 回溯算法解决 八皇后问题
     * @param array
     * @param row
     */
    private void backTrace(int array[],int row){
        // 从每一行开始，逐个遍历列看是否合适
        if (array.length == row) {
            total_solution++; // 记录总共有多少中解法
            printfQueue(array);//把每一种解法都打印出来
            return; //必须return，否则继续下去超出限制
        }
        for (int i = 0; i < array.length; i++) {
            // row 存储列，array[row]表示行，数组下标表示列，数组值表示行
            array[row] = i;
            if (isQueueOk(array,row)) {
                printf(array);
                // 递归遍历下一个位置
                if (row < array.length) {
                    backTrace(array, row + 1);
                }
            }
        }
    }

    /**
     * 判断新放入的这个皇后能不能行的通，需要和前面的进行比较，是否在同一行或同一列以及对角线
     * @param array
     * @param row
     * @return
     */
    private boolean isQueueOk(int array[], int row){
        // 判断当前皇后是否ok，只需遍历row之前的所有皇后
        for (int i = 0; i < row; i++) {
            // 同一行不能相等
            if (array[i] == array[row]) {
                return false;
            }
            // 同一正对角线不能相等(左上到右下，正对角线都是行等于列)
            if (i-array[i] == row -array[row]) {
                return false;
            }
            // 同一反对角线不能相等(右上到左下，反对角线是行和列相加相等)
            if (row + array[row] == i + array[i]) {
                return false;
            }
        }
        return true;
    }
    /************************八皇后问题结束********************************/

    /************************背包问题开始********************************/
    int COUNT = 10;
    int weight[]; // 物品重量
    int value[];  //物品单价
    int x[]; //物品的放入情况
    int bestVal;
    int bestX[]; // 最佳的放入情况
    int maxV = 10;
    private void packSolution(){
        Scanner scanner = new Scanner(System.in);
        weight = new int[COUNT];
        value = new int[COUNT];
        x= new int[COUNT];
        bestX = new int[COUNT];
        int i = 0;
        System.out.println("请输入物品重量：");
        while (scanner.hasNext()) {
            if (i >= COUNT) {
                break;
            }
            int in = scanner.nextInt();
            weight[i] = in;
            i++;
        }
        printf(weight);
        System.out.println("请输入物品价值：");
        i = 0;
        while (scanner.hasNext()) {
            if (i >= COUNT) {
                break;
            }
            int in = scanner.nextInt();
            value[i] = in;
            i++;
        }
        printf(weight);
        deepFindSearch(0,0,0);
        System.out.println("物品重量分别为：");
        printf(weight);
        System.out.println("物品价值分别为：");
        printf(value);
        System.out.println("背包最大价值为："+bestVal);
        printf(bestX);
    }

    /**
     * 对所有物品一个个遍历，对每个物品可以有放入和不放入的选择
     * 但是要把所有物品遍历完成，然后看价值是多少。
     * @param n 放入第几个物品了
     * @param v 当前背包里的物品总价值
     * @param w 当前背包里的物品总重量
     */
    private void deepFindSearch(int n,int v,int w){
        // 所有物品都遍历完成了，然后看当前价值是不是最大的。
        if (n >= COUNT) {
            // 如果已经大于最大值了， 需要记录当前放入的物品个数和总价值。
            if (v > bestVal) {
                bestVal = v;
                System.out.println("全部放入物品价值最大 = "+bestVal);
                for (int i = 0; i < COUNT; i++) {
                    bestX[i] = x[i];
                }
                printf(bestX);
            }
        }
        else {
            // 物品只有两种选择，放入或者不放入,所以遍历这两种情况
            for (int i = 0; i <= 1; i++) {
                x[n] = i; // 第N个物品是放入或者不放入，记录状态
                if (w+x[n] * weight[n] < maxV) { //如果总重量小于背包容量才可以继续递归放入下一个
                    deepFindSearch(n+1,v+x[n] * value[n], w + x[n] * weight[n]);//继续搜索
                }else {
//                    System.out.println("物品已经超出背包重量：");
//                    printf(x);
                }
            }
        }
    }

    /************************背包问题结束********************************/

}
