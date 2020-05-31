package com.young.algorithm.sort;

import com.young.algorithm.Utils;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-5-31:下午7:24
 * Description: this is BubbleSort 冒泡排序
 */
public class BubbleSort extends Sort {

    public BubbleSort(){
        NAME = this.getClass().getSimpleName();
        DEBUG = false;
    }

    // 冒泡排序，每次比较相邻的两个数，把较大的数放到最后
    @Override
    public void sort(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length-i; j++) {
                // 比较相邻的两个数，把较大的放到后面,时间复杂度1+2+3+...+n-1 = n^2/2
                if (array[j-1] > array[j]){
                    Utils.swap(array,j-1,j);
                }
                num ++;
                printf(array,num);
            }
        }
        printf(array);
        dprintf(num);
    }
}
