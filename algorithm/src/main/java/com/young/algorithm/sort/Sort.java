package com.young.algorithm.sort;

import com.young.algorithm.AbstractAlgorithm;
import com.young.algorithm.Algorithm;

import java.util.Arrays;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-5-31:下午9:07
 * Description: this is Sort
 */
public abstract class Sort extends AbstractAlgorithm {

    @Override
    public abstract void sort(int[] array);

    @Override
    public int search(int[] array, int target) {
        return -1;
    }
}
