package com.young.algorithm;

import java.util.Arrays;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:上午10:41
 * Description: this is AbstractAlgorithm
 */
public abstract class AbstractAlgorithm implements Algorithm {

    public static String NAME = AbstractAlgorithm.class.getSimpleName();
    public static boolean DEBUG = false;

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

}
