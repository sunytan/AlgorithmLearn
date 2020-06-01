package com.young.algorithm.sort;

import com.young.algorithm.Utils;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:上午11:27
 * Description: this is InsertSort 直接插入排序
 */
public class InsertSort extends Sort {

    public InsertSort(){
        NAME = this.getClass().getSimpleName();
        DEBUG = true;
    }

    @Override
    public void sort(int[] array) {
        // 记录插入位置
        int j = 0;
        int num = 0;
        // 从第一位开始，依次往后选一个数
        for (int i = 1; i < array.length; i++) {
            j = i-1;
            num++;
            // 取出要插入的数
            int temp = array[i];
            // 将取出来的数依次于数组前面的数字进行比较，当比前面的数小的话
            // 就挨个交换，直到遇到比自己小的，或者到数组头
            while (j>=0 && temp < array[j]) {
                Utils.swap(array,j,j+1);
                j--;
                num ++;
                dprintf(array,num);
            }
        }
    }
}
