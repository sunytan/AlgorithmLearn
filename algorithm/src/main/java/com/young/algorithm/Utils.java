package com.young.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-5-31:下午7:13
 * Description: this is Utils
 */
public class Utils {
    public static int[] generateArray(final int count){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int array[] = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = list.get(i);
        }
        System.out.println("generateArray="+ Arrays.toString(array));
        return array;
    }

    public static int[] generateRandomArray(final int count,final int valueMax){
        int array[] = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) ((valueMax+1)*Math.random()/* - valueMax*Math.random()*/);
        }
        return array;
    }

    public static void swap(int[] array,int first,int end){
        int temp = array[first];
        array[first] = array[end];
        array[end] = temp;
    }
}
