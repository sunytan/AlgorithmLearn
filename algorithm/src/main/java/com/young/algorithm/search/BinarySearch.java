package com.young.algorithm.search;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:下午5:35
 * Description: this is BinarySearch 二分查找法，只能查有序序列
 */
public class BinarySearch extends Search {

    public BinarySearch(){
        NAME = this.getClass().getSimpleName();
        DEBUG = true;
    }

    @Override
    public int search(int[] array, int target) {
        printf(array,target);
        return binarySearch(array,0,array.length-1,target);
    }

    /**
     * 可以采用递归法查询
     * @param array
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int binarySearch(int array[],int start,int end,int target){
        if (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] > target) {
                printfMask(array,-1,mid);
                return binarySearch(array,start,mid-1,target);
            }else if (array[mid] < target){
                printfMask(array,-1,mid);
                return binarySearch(array,mid+1,end,target);
            }else {
                return mid;
            }
        }
        return -1;
    }
}
