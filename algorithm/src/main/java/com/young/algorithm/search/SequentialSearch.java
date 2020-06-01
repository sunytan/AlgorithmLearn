package com.young.algorithm.search;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:上午10:38
 * Description: this is SequentialSearch 顺序查找
 */
public class SequentialSearch extends Search {

    public SequentialSearch(){
        NAME = this.getClass().getSimpleName();
        DEBUG = false;
    }

    /**
     * 顺序查找又是暴力查找，单循环顺序从头查找
     * @param array
     * @param target
     */
    @Override
    public int search(int[] array, int target) {
        int length = array.length;
        int num = 0;
        for (int i = 0; i < length; i++) {
            num ++;
            printf(array,num);
            if (array[i] == target){
                dprintf(num);
                return i;
            }
        }
        return -1;
    }
}
