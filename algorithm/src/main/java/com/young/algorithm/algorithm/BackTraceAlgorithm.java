package com.young.algorithm.algorithm;

import java.util.Arrays;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:下午6:37
 * Description: this is BackTraceAlgorithm
 */
public class BackTraceAlgorithm extends AlgorithmImpl {

    int total_solution = 0; // 总共有几种解法

    @Override
    public void process(int[] array) {
        queue(array);
    }


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
}
