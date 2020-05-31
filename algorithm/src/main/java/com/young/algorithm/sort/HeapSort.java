package com.young.algorithm.sort;

import com.young.algorithm.Utils;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-5-31:下午10:11
 * Description: this is HeapSort 堆排序
 */
public class HeapSort extends Sort {


    public HeapSort(){
        NAME = this.getClass().getSimpleName();
        DEBUG = false;
    }

    @Override
    public void sort(int[] array) {
        produceMaxTopHeap(array);
        int len = array.length;
        int count = 0;
        for (int i = len; i > 1; i--) {
            count = count + heapAjust(array,i);
        }
        printf(array);
        dprintf(count);
    }

    /**
     * 产生一个大顶堆
     * @param array
     * 采用插入法产生大顶堆
     * array[i] 的parent的index = (i -1)/2
     */
    private void produceMaxTopHeap(int array[]){
        int length = array.length;

        // 2*index < length 保证在范围内
        for (int i = 0; i < length; i++) {
            // 当前节点
            int current = i;
            // 父节点
            int parent = (current -1) /2;
            // 最大顶堆，如果父节点小于子节点，需要调换
            while (array[current] > array[parent]) {
                Utils.swap(array,current,parent);
                current = parent; // 当前节点上移，直到找不到比自己小的为止
                parent = (current - 1) /2;
            }
        }
        printf(array);
    }

    // 调整堆
    private int heapAjust(int[] array,int n){
        int target=0;
        int num = 0;
        if (n <= 1) {
            return num;
        }
        // 子左节点为2*target+1,子左节点不超过总长度
        int child = 0;
        while (2*target + 1 <n) {
            num ++;
            child = 2*target +1;
            // 找出子节点中最大的项
            if (child != n-1 && array[child] < array[child + 1]) {
                child ++;
            }
            if (array[target] > array[child]) {
                // 因为是最大顶堆，所以只要找到比自己大的就可以退出。
                break;
            }else {
                // 否者就交换
                Utils.swap(array,target,child);
                target = child;
            }
        }
        // 调整最大顶堆之后，把顶移到最后
        Utils.swap(array,0,n-1);
        printf(array);
        return num;
    }
}
