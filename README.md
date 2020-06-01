# AlgorithmLearn

### 一、冒泡排序

**算法描述：** 从前往后以此比较相邻两个数，每次把大的数，放到后面，循环完成，最大的数在最右边

核心代码如下：

``` java
	public void sort(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length-i; j++) {
                // 比较相邻的两个数，把较大的放到后面,时间复杂度1+2+3+...+n-1 = n^2/2
                if (array[j-1] > array[j]){
                    Utils.swap(array,j-1,j);
                }
                num ++;
                printf(array,num);
            }
        }
    }
```

**复杂度:** 循环次数为 n-1+n-2+...+1 = n^2^ /2   所以时间复杂度为 O( n^2^ )

### 二、选择排序

**算法描述：** 选择排序，每次循环都选出最小值放到循环左边。

核心代码如下:

``` java
    public void sort(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                // 选择排序，每次选出最小的放到第一位，时间复杂度n+n-1+n-2+...+1 = (n+1) *n / 2
                if (array[i] > array[j]){
                    Utils.swap(array,i,j);
                }
                num ++;
                printf(array,num);
            }
        }
    }
```

**时间复杂度：** 循环次数`1+2+3+...+n = n^2^/2 + n/2` 所以时间复杂度为 O(n^2^)

选择排序修改：

``` java
    public void sort(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                // 选择排序，每次选出最小的放到第一位，时间复杂度n+n-1+n-2+...+1 = (n+1) *n / 2
                // 修改每次记录最小的位置，然后再继续和后面比较，选出最终的最小值
                if (array[min] > array[j]){
                    // 修改，不要每次都交换
                    //Utils.swap(array,i,j);
                    min = j;
                }
                num ++;
            }
            if (min != i) {
                // 把最小值交换到开头位置
                Utils.swap(array,min,i);
            }
            printf(array,num);
        }
        printf(array);
        dprintf(num);
    }
```

**修改目的:** 每次查找最小数只需要记录最小值坐标，不要每次都交换

### 三、堆排序

* 堆排序首先需要知道两个概念：大根堆和小根堆

* **大根堆：** 堆可以用一个完全二叉树存储，二叉树中所有的节点的值都大于其左右子节点的值，

  即 `arr[i] > arr[2*i+1] && arr[i] > arr[2*i+2]`

* **小根堆：** 堆可以用一个完全二叉树存储，二叉树中所有的节点的值都小于其左右子节点的值，

  即 `arr[i] < arr[2*i+1] && arr[i] < arr[2*i+2]`

**算法步骤：**

1. 首先把数组排序生成为一个大根堆
2. 每次把大根堆的顶点和最后元素进行互换，把剩下的`n-1`个元素再次调整为大根堆
3. 直到只剩最后一个顶点元素

核心代码如下：

``` java
    public void sort(int[] array) {
        produceMaxTopHeap(array);
        int len = array.length;
        int count = 0;
        for (int i = len; i > 1; i--) {
            count = count + heapAjust(array,i);
        }
    }

    /**
     * 产生一个大顶堆
     * @param array
     * 采用插入法产生大顶堆
     * array[i] 的parent的index = (i -1)/2
     */
    private void produceMaxTopHeap(int array[]){
        int length = array.length;
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
```

**图解参考：** [https://blog.csdn.net/u010452388/article/details/81283998](https://blog.csdn.net/u010452388/article/details/81283998)

### 四、直接插入排序

**思路：** 直接插入是将一次次将记录插入到已经排好序的的序列中，从而得到一个新的，数据依次增一的序列 。实际就相当于边遍历边排序。

**算法步骤**

1. 数组的第`0`位，只有一位，属于一个已经排好序的序列。

2. 从`1`位开始循环往后查找需要插入的值

3. 往后选出第`1`位，遍历前面的有序序列，然后插入到合适位置，得到的一个新的长度为`2`的有序序列

4. 再往后选择第`2`位，遍历前面的第`0` 、`1` 位有序序列，然后依次往前比较，每次比较相邻的，如果比自己小的，则依次交换，直到没有比自己小的为止。如果相邻的比自己大，说明前面的序列都比自己大，则重新寻找需要插入的值，因为前面已经是有序序列。

5. 再往后选择第`n`位，遍历前面第`0` ... `n-1`位重复上序步骤`4` 

核心代码如下：

``` java
    public void sort(int[] array) {
        // 记录插入位置
        int j = 0;
        // 从第一位开始，依次往后选一个数
        for (int i = 1; i < array.length; i++) {
            j = i-1;
            // 取出要插入的数
            int temp = array[i];
            // 将取出来的数依次于数组前面的数字进行比较，当比前面的数小的话
            // 就挨个交换，直到遇到比自己小的，或者到数组头
            while (j>=0 && temp < array[j]) {
                Utils.swap(array,j,j+1);
                j--;
            }
        }
    }
```

### 五、归并排序

**简介：** 归并排序又属于 **分治算法** 一种应用，通过把序列每次分成两部分，递归直到序列长度为`1`然后依次进行每一部分的排序，最后对两部分进行合并

核心代码如下：

``` java
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
            printf(arrLeft);
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
```

运行结果如下：

``` shell
MergeSort:sort:[24]:array=[92, 49, 56, 94, 61, 33, 9, 32, 86, 31]
MergeSort:sort:[19]:array=[92, 49, 56, 94, 61]
MergeSort:mergeSort:[32]:array=[92, 49]
MergeSort:mergeSort:[32]:array=[92]
MergeSort:mergeSort:[32]:array=[49]
MergeSort:mergeSort:[44]:array=[49, 92]
MergeSort:mergeSort:[32]:array=[56, 94, 61]
MergeSort:mergeSort:[41]:array=[56]
MergeSort:mergeSort:[41]:array=[94, 61]
MergeSort:mergeSort:[41]:array=[94]
MergeSort:mergeSort:[41]:array=[61]
MergeSort:mergeSort:[44]:array=[61, 94]
MergeSort:mergeSort:[44]:array=[56, 61, 94]
MergeSort:mergeSort:[44]:array=[49, 56, 61, 92, 94]
MergeSort:sort:[19]:array=[33, 9, 32, 86, 31]
MergeSort:mergeSort:[41]:array=[33, 9]
MergeSort:mergeSort:[32]:array=[33]
MergeSort:mergeSort:[32]:array=[9]
MergeSort:mergeSort:[44]:array=[9, 33]
MergeSort:mergeSort:[41]:array=[32, 86, 31]
MergeSort:mergeSort:[41]:array=[32]
MergeSort:mergeSort:[41]:array=[86, 31]
MergeSort:mergeSort:[41]:array=[86]
MergeSort:mergeSort:[41]:array=[31]
MergeSort:mergeSort:[44]:array=[31, 86]
MergeSort:mergeSort:[44]:array=[31, 32, 86]
MergeSort:mergeSort:[44]:array=[9, 31, 32, 33, 86]
MergeSort:mergeSort:[44]:array=[9, 31, 32, 33, 49, 56, 61, 86, 92, 94]
```

