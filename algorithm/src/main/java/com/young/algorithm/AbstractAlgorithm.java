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
        StackTraceElement element = Thread.currentThread().getStackTrace()[3];
        System.out.println(getLogHeader(element)+"count=" + count + " array=" + Arrays.toString(array));
    }

    protected void dprintf(int[] array){
        StackTraceElement element = Thread.currentThread().getStackTrace()[3];
        System.out.println(getLogHeader(element) + "array=" + Arrays.toString(array));
    }

    protected void dprintf(int count){
        StackTraceElement element = Thread.currentThread().getStackTrace()[3];
        System.out.println(getLogHeader(element)+"count=" + count);
    }

    protected void printf(int[] array,int count){
        if (DEBUG) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[3];
            System.out.println(getLogHeader(element)+"count=" + count + " array=" + Arrays.toString(array));
        }
    }

    protected void printf(int[] array){
        if (DEBUG) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[3];
            System.out.println(getLogHeader(element) + "array=" + Arrays.toString(array));
        }
    }

    private String getLogHeader(StackTraceElement element){
        String name = NAME;
        String method = element.getMethodName();
        int line = element.getLineNumber();
        StringBuilder builder = new StringBuilder();
        builder.append(name+":").append(method+":").append("["+line+"]:");
        return builder.toString();
    }

}
