package com.young.algorithm.algorithm;

import java.util.Arrays;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:上午10:41
 * Description: this is AbstractAlgorithm
 */
public abstract class AbstractAlgorithm implements IAlgorithm {

    public static String NAME = AbstractAlgorithm.class.getSimpleName();
    public static boolean DEBUG = false;

    public final static int STACK_INDEX = 2;

    protected void dprintf(int[] array, int count){
        StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
        System.out.println(getLogHeader(element)+"count=" + count + " array=" + Arrays.toString(array));
    }

    protected void dprintf(int[] array){
        StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
        System.out.println(getLogHeader(element) + "array=" + Arrays.toString(array));
    }

    protected void dprintf(int count){
        StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
        System.out.println(getLogHeader(element)+"count=" + count);
    }

    protected void printf(int[] array,int count){
        if (DEBUG) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
            System.out.println(getLogHeader(element)+"count=" + count + " array=" + Arrays.toString(array));
        }
    }

    protected void printf(int[] array){
        if (DEBUG) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
            System.out.println(getLogHeader(element) + "array=" + Arrays.toString(array));
        }
    }

    protected void printfMask(int[] array,int index){
        if (DEBUG) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
            System.out.println(getLogHeader(element) + "array=" + Arrays.toString(array));
        }
    }

    protected void printfMask(int[] array,int low,int high){
        if (DEBUG) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
            System.out.println(getLogHeader(element) + "array=" + toString(array,low,high));
        }
    }

    private String toString(int var0[],int low,int high){
        if (var0 == null) {
            return "null";
        } else {
            int var1 = var0.length - 1;
            if (var1 == -1) {
                return "[]";
            } else {
                StringBuilder var2 = new StringBuilder();
                var2.append('[');
                int var3 = 0;

                while(true) {
                    if (low == var3) {
                        var2.append("&&");
                    }
                    if (high == var3) {
                        var2.append("**");
                    }
                    var2.append(var0[var3]);
                    if (low == var3) {
                        var2.append("&&");
                    }
                    if (high == var3) {
                        var2.append("**");
                    }
                    if (var3 == var1) {
                        return var2.append(']').toString();
                    }

                    var2.append(", ");
                    ++var3;
                }
            }
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
