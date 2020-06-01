package com.young.algorithm.sort;

import com.young.algorithm.Utils;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-5-31:下午9:00
 * Description: this is SelectionSort 选择排序
 */
public class SelectionSort extends Sort {

    public SelectionSort(){
        NAME = this.getClass().getSimpleName();
        DEBUG = true;
    }

    @Override
    public void sort(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                // 选择排序，每次选出最小的放到第一位，时间复杂度n+n-1+n-2+...+1 = (n+1) *n / 2
                // 修改每次记录最小的位置，然后再继续和后面比较，选出最终的最小值
                if (array[min] > array[j]){
                    // 修改，不要每次都交换
                    //Utils.swap(array,i,j);
                    min = j;
                }
                num ++;
            }
            if (min != i) {
                // 把最小值交换到开头位置
                Utils.swap(array,min,i);
            }
            printf(array,num);
        }
        printf(array);
        dprintf(num);
    }
}
