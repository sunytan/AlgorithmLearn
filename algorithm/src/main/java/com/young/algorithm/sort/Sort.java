package com.young.algorithm.sort;

import com.young.algorithm.Algorithm;

import java.util.Arrays;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-5-31:下午9:07
 * Description: this is Sort
 */
public abstract class Sort implements Algorithm {
    public static String NAME = Sort.class.getSimpleName();
    public static boolean DEBUG = true;

    protected void dprintf(int[] array,int count){
        System.out.println(NAME + ":" + "count=" + count + " array=" + Arrays.toString(array));
    }

    protected void dprintf(int[] array){
        System.out.println(NAME + ":" + "array=" + Arrays.toString(array));
    }

    protected void dprintf(int count){
        System.out.println(NAME + ":" + "count=" + count);
    }

    protected void printf(int[] array,int count){
        if (DEBUG) {
            System.out.println(NAME + ":" + "count=" + count + " array=" + Arrays.toString(array));
        }
    }

    protected void printf(int[] array){
        if (DEBUG) {
            System.out.println(NAME + ":array=" + Arrays.toString(array));
        }
    }

    @Override
    public abstract void sort(int[] array);

    @Override
    public void find(int[] array, int target) {

    }
}
