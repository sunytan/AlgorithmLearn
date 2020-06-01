package com.young.algorithm;

import com.young.algorithm.search.Search;
import com.young.algorithm.search.SequentialSearch;
import com.young.algorithm.sort.BubbleSort;
import com.young.algorithm.sort.HeapSort;
import com.young.algorithm.sort.InsertSort;
import com.young.algorithm.sort.MergeSort;
import com.young.algorithm.sort.SelectionSort;
import com.young.algorithm.sort.Sort;

import java.lang.String;
import java.util.Arrays;

public class Main {


    // 排序算法
    private static void sort(Sort sort){
        int array[] = Utils.generateRandomArray(10,100);
        long start;
        long end;
        start = System.currentTimeMillis();
        sort.sort(array);
        end = System.currentTimeMillis();
        System.out.println("Sort duration = "+ (end - start));
    }

    // 查找算法
    private static void search(Search search){
        int array[] = Utils.generateArray(1000000);
        int target = array[(int)(Math.random()*array.length)];
        long start;
        long end;
        int result;
        start = System.currentTimeMillis();
        result = search.search(array,target);
        end = System.currentTimeMillis();
        System.out.println("Search duration = "+ (end - start)+",target="+target+",result="+result);
    }

    public static void main(String argv[]){
        sort(new MergeSort());
//        search();
    }
}
