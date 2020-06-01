package com.young.algorithm.sort;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-1:下午1:07
 * Description: this is MergeSort 归并排序
 */
public class MergeSort extends Sort {

    public MergeSort(){
        NAME = this.getClass().getSimpleName();
        DEBUG = true;
    }

    @Override
    public void sort(int[] array) {
        printf(array);
        mergeSort(array);
    }

    private void mergeSort(int array[]){
        // 如果长度大于1,就可以把数组进行分割
        if (array.length >1) {
            // 左边的数组长度为length/2
            int leftLength = array.length/2;
            int arrLeft[]= new int[leftLength];
            // 把array的第0 - length-1位拷贝给arrLeft
            System.arraycopy(array,0,arrLeft,0,leftLength);
            printf(arrLeft);
            // 对arrLeft再次进行归并排序直到只剩一个元素
            mergeSort(arrLeft);


            // 右边的数组长度为length/2+1
            int rightLength = array.length - leftLength;
            int arrRight[] = new int[rightLength];
            System.arraycopy(array,leftLength,arrRight,0,rightLength);
            printf(arrRight);
            // 对arrRight再次进行归并排序，直到只剩一个元素
            mergeSort(arrRight);

            // 当分割完成之后，需要堆左右两边进行合并得到一个新的数组
            int data[] = merge(arrLeft,arrRight);
            // 把合并完成的新的数组拼合并到原始数组里
            System.arraycopy(data,0,array,0,array.length);
        }
    }

    /**
     * 合并左右两个有序数组
     * @param arrLeft
     * @param arrRight
     * @return
     */
    private int[] merge(int arrLeft[],int arrRight[]){
        int data[] = new int[arrLeft.length+arrRight.length];
        int indexData = 0;
        int indexLeft = 0;
        int indexRight = 0;

        //
        while (indexLeft < arrLeft.length && indexRight < arrRight.length) {
            if (arrLeft[indexLeft] < arrRight[indexRight]) {
                // 如果左边小，就把左边的值放入data数组
                data[indexData] = arrLeft[indexLeft];
                indexData ++;
                indexLeft ++;
            }else {
                // 如果右边小，就把左边的值放入data数组
               data[indexData] = arrRight[indexRight];
               indexData ++;
               indexRight++;
            }
        }

        // 上述循环退出，至少有一个index 已经达到数组末端.
        // 所以就从另一个未达到末端的里面去除全部放入data
        // 如下两个循环必然满足一个
        while (indexLeft < arrLeft.length) {
            data[indexData] = arrLeft[indexLeft];
            indexData ++;
            indexLeft ++;
        }
        while (indexRight < arrRight.length) {
            data[indexData] = arrRight[indexRight];
            indexData ++;
            indexRight ++;
        }

        printf(data);
        return data;
    }
}
