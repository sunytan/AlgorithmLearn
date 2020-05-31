package com.young.algorithm;

import com.young.algorithm.sort.BubbleSort;
import com.young.algorithm.sort.HeapSort;
import com.young.algorithm.sort.SelectionSort;
import com.young.algorithm.sort.Sort;

import java.lang.String;

public class Main {


    public static void main(String argv[]){
        int array[] = Utils.generateRandomArray(100000,300000);
        long start;
        long end;
        Sort sort;
        // 冒泡排序
        sort = new BubbleSort();
        start = System.currentTimeMillis();
        sort.sort(array);
        end = System.currentTimeMillis();
        System.out.println("Sort duration = "+ (end - start));

        // 选择排序
        sort = new SelectionSort();
        start = System.currentTimeMillis();
        sort.sort(array);
        end = System.currentTimeMillis();
        System.out.println("Sort duration = "+ (end - start));

        sort = new HeapSort();
        start = System.currentTimeMillis();
        sort.sort(array);
        end = System.currentTimeMillis();
        System.out.println("Sort duration = "+ (end - start));
    }
}
