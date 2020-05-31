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
        DEBUG = false;
    }

    @Override
    public void sort(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                // 选择排序，每次选出最小的放到第一位，时间复杂度n+n-1+n-2+...+1 = (n+1) *n / 2
                if (array[i] > array[j]){
                    Utils.swap(array,i,j);
                }
                num ++;
                printf(array,num);
            }
        }
        printf(array);
        dprintf(num);
    }
}
