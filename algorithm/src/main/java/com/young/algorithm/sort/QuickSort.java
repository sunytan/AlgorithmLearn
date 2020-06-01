package com.young.algorithm.sort;

import com.young.algorithm.Utils;

import java.util.Arrays;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:下午2:49
 * Description: this is QuickSort 快速排序
 */
public class QuickSort extends Sort {

    public QuickSort(){
        NAME = this.getClass().getSimpleName();
        DEBUG = true;
    }

    @Override
    public void sort(int[] array) {
        printf(array);
        final int length = array.length;
        quickSort(array,0,length-1);
    }

    /**
     * 快速排序
     * @param array 需要排序的序列
     * @param start 序列中划分出来的较小序列的起始位置
     * @param end   序列中划分出来的较小序列的结束位置
     */
    public void quickSort(int array[],int start,int end){
        // 每次排序前，都把序列先按照一个目标数左右划分开
        printf(array);
        if (end > start) {
            int targetIndex = quickSortPart(array, start, end);
            // 对target左边再次进行快速排序
            // 因为target位置已经固定住了，不会再变了。
            // 再次排序只需要左右两边，不需要包含target
            quickSort(array, start, targetIndex - 1);
            // 对target右边也再次进行快速排序
            quickSort(array, targetIndex + 1, end);
        }
    }

    /**
     * 对部分序列进行快速划分，使其满足，左边都比目标数小，右边都比目标数大
     * 每次把序列划分为部分序列，使数组根据某一个数左右分布
     * @param array
     * @return  返回值为划分之后，目标数所在的下标
     */
    public int quickSortPart(int array[],int first,int last){
        // 取第一个值为目标值
        int targetIndex = first;
        int target = array[first];
        // 进行插入排序的左边值，
        int low = targetIndex + 1;
        // 进行插入排序选择的右边值
        int high = last;
        printfMask(array,low,high);
        // 依次从两端忘target进行靠拢
        while (low < high) {
            printfMask(array,low,high);
            // 从前往后一直比较，只要比目标值小，就往后移。直到找到比target大的值
            // 找到值之后，用来和比target小的值进行位置互换，就是下面的high值
            // 此次array[low] <= target 取等号，则下方high的不能取等号，保证等于的值都在左侧
            while (low <= high && array[low] <= target){
                low++;
                printfMask(array,low,high);
            }
            // 从后往前一直比较，只要比目标值大，就往左移。直到找到比target小的值
            // 找到值之后，用来和比target大的值进行位置互换，就是上序的low值
            while (low <= high && array[high] > target) {
                high--;
                printfMask(array,low,high);
            }

            // 把比target大的值和比target小的值进行互换
            if (low < high) {
                Utils.swap(array,low,high);
            }
        }

        printfMask(array,low,high);

        // 此处array[high] 不可能大于target了，但是可能等于target
        // 如果等于的话还要继续往前移动，直到找到小于的值
        while (high > targetIndex && array[high] >= target) {
            high--;
        }

        // 上述循环结束之后，序列必然已经以target为基准划分开了
        // 左边都比target小，右边都比target大
        // 此时需要做的是把target的元素放到那个位置合理
        // 通过上一步的循环，就必定target > array[high]把小于的值和target交换
        if (target > array[high]){
            Utils.swap(array,targetIndex,high);
            targetIndex = high;
        }
        // 之所以不比较和low的值，是因为array[high]左边的值已经都是小于array[high] 以及其右边值
        // 目的已经达到了，下一次左边的数组还可以进行
        dprintf(targetIndex);
        return targetIndex;
    }
}
