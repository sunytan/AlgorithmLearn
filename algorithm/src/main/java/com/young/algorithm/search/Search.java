package com.young.algorithm.search;

import com.young.algorithm.AbstractAlgorithm;
import com.young.algorithm.Algorithm;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-5-31:下午10:07
 * Description: this is Search
 */
public abstract class Search extends AbstractAlgorithm {

    @Override
    public void sort(int[] array) {

    }

    @Override
    public abstract int search(int[] array, int target);
}
