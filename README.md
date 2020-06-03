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

### 六、快速排序

**思路：** 快速排序是选择一个target值，每次都把序列以target值进行划分开，左边的都比target小，右边的都比target大，这样target的位置就固定住了。然后再对target左边和右边的序列再次执行相同的操作。采用递归的思想

核心代码如下：

``` java
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
```

运行结果如下：

``` shell
QuickSort:sort:[22]:array=[33, 5, 95, 30, 91, 1, 40, 25, 34, 38]
QuickSort:quickSort:[35]:array=[33, 5, 95, 30, 91, 1, 40, 25, 34, 38]
QuickSort:quickSortPart:[61]:array=[33, &&5&&, 95, 30, 91, 1, 40, 25, 34, **38**]
QuickSort:quickSortPart:[64]:array=[33, &&5&&, 95, 30, 91, 1, 40, 25, 34, **38**]
QuickSort:quickSortPart:[70]:array=[33, 5, &&95&&, 30, 91, 1, 40, 25, 34, **38**]
QuickSort:quickSortPart:[76]:array=[33, 5, &&95&&, 30, 91, 1, 40, 25, **34**, 38]
QuickSort:quickSortPart:[76]:array=[33, 5, &&95&&, 30, 91, 1, 40, **25**, 34, 38]
QuickSort:quickSortPart:[64]:array=[33, 5, &&25&&, 30, 91, 1, 40, **95**, 34, 38]
QuickSort:quickSortPart:[70]:array=[33, 5, 25, &&30&&, 91, 1, 40, **95**, 34, 38]
QuickSort:quickSortPart:[70]:array=[33, 5, 25, 30, &&91&&, 1, 40, **95**, 34, 38]
QuickSort:quickSortPart:[76]:array=[33, 5, 25, 30, &&91&&, 1, **40**, 95, 34, 38]
QuickSort:quickSortPart:[76]:array=[33, 5, 25, 30, &&91&&, **1**, 40, 95, 34, 38]
QuickSort:quickSortPart:[64]:array=[33, 5, 25, 30, &&1&&, **91**, 40, 95, 34, 38]
QuickSort:quickSortPart:[70]:array=[33, 5, 25, 30, 1, &&**91&&**, 40, 95, 34, 38]
QuickSort:quickSortPart:[76]:array=[33, 5, 25, 30, **1**, &&91&&, 40, 95, 34, 38]
QuickSort:quickSortPart:[85]:array=[33, 5, 25, 30, **1**, &&91&&, 40, 95, 34, 38]
QuickSort:quickSortPart:[103]:count=4
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[61]:array=[1, &&5&&, 25, **30**, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[64]:array=[1, &&5&&, 25, **30**, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[76]:array=[1, &&5&&, **25**, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[76]:array=[1, &&**5&&**, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[76]:array=[**1**, &&5&&, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[85]:array=[**1**, &&5&&, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[103]:count=0
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[61]:array=[1, 5, &&25&&, **30**, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[64]:array=[1, 5, &&25&&, **30**, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[76]:array=[1, 5, &&**25&&**, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[76]:array=[1, **5**, &&25&&, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[85]:array=[1, **5**, &&25&&, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[103]:count=1
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[61]:array=[1, 5, 25, &&**30&&**, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[85]:array=[1, 5, 25, &&**30&&**, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[103]:count=2
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 91, 40, 95, 34, 38]
QuickSort:quickSortPart:[61]:array=[1, 5, 25, 30, 33, 91, &&40&&, 95, 34, **38**]
QuickSort:quickSortPart:[64]:array=[1, 5, 25, 30, 33, 91, &&40&&, 95, 34, **38**]
QuickSort:quickSortPart:[70]:array=[1, 5, 25, 30, 33, 91, 40, &&95&&, 34, **38**]
QuickSort:quickSortPart:[64]:array=[1, 5, 25, 30, 33, 91, 40, &&38&&, 34, **95**]
QuickSort:quickSortPart:[70]:array=[1, 5, 25, 30, 33, 91, 40, 38, &&34&&, **95**]
QuickSort:quickSortPart:[70]:array=[1, 5, 25, 30, 33, 91, 40, 38, 34, &&**95&&**]
QuickSort:quickSortPart:[76]:array=[1, 5, 25, 30, 33, 91, 40, 38, **34**, &&95&&]
QuickSort:quickSortPart:[85]:array=[1, 5, 25, 30, 33, 91, 40, 38, **34**, &&95&&]
QuickSort:quickSortPart:[103]:count=8
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 34, 40, 38, 91, 95]
QuickSort:quickSortPart:[61]:array=[1, 5, 25, 30, 33, 34, &&40&&, **38**, 91, 95]
QuickSort:quickSortPart:[64]:array=[1, 5, 25, 30, 33, 34, &&40&&, **38**, 91, 95]
QuickSort:quickSortPart:[76]:array=[1, 5, 25, 30, 33, 34, &&**40&&**, 38, 91, 95]
QuickSort:quickSortPart:[76]:array=[1, 5, 25, 30, 33, **34**, &&40&&, 38, 91, 95]
QuickSort:quickSortPart:[85]:array=[1, 5, 25, 30, 33, **34**, &&40&&, 38, 91, 95]
QuickSort:quickSortPart:[103]:count=5
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 34, 40, 38, 91, 95]
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 34, 40, 38, 91, 95]
QuickSort:quickSortPart:[61]:array=[1, 5, 25, 30, 33, 34, 40, &&**38&&**, 91, 95]
QuickSort:quickSortPart:[85]:array=[1, 5, 25, 30, 33, 34, 40, &&**38&&**, 91, 95]
QuickSort:quickSortPart:[103]:count=7
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 34, 38, 40, 91, 95]
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 34, 38, 40, 91, 95]
QuickSort:quickSort:[35]:array=[1, 5, 25, 30, 33, 34, 38, 40, 91, 95]
Sort duration = 4
```

### 七、顺序查找

顺序查找也是暴力查找，从头到尾进行遍历匹配。不详细说明了。

核心代码如下:

``` java
    /**
     * 顺序查找又是暴力查找，单循环顺序从头查找
     * @param array
     * @param target
     */
    @Override
    public int search(int[] array, int target) {
        int length = array.length;
        int num = 0;
        for (int i = 0; i < length; i++) {
            num ++;
            printf(array,num);
            if (array[i] == target){
                dprintf(num);
                return i;
            }
        }
        return -1;
    }
```

### 八、二分查找

二分查找只适用于有序序列。每次从中间对比。不详细说明了。

核心代码如下：

``` java
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
```

### 九、回溯算法

**简介：** 回溯算法其实就相当于是一种穷举法，或者试探法，一步一步的进行试探正确性，采用的有点类似于深度优先遍历，一条路试探到底，没有路了，就回退到上一步，继续试探。实现采用递归的思路

#### 1、八皇后问题

整体步骤加解释都在代码中有注释

核心代码如下：

``` java
    private void queue(int array[]){
        backTrace(array,0);
        System.out.println("total_solution="+total_solution);
    }

    private void printfQueue(int arr[]){
        final int length = arr.length;
        int result[][] = new int[length][length];
        System.out.println("arr = "+ Arrays.toString(arr));
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (arr[i] == j) {
                    result[i][j] = 1;
                }
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 回溯算法解决 八皇后问题
     * @param array
     * @param row
     */
    private void backTrace(int array[],int row){
        // 从每一行开始，逐个遍历列看是否合适
        if (array.length == row) {
            total_solution++; // 记录总共有多少中解法
            printfQueue(array);//把每一种解法都打印出来
            return; //必须return，否则继续下去超出限制
        }
        for (int i = 0; i < array.length; i++) {
            // row 存储列，array[row]表示行，数组下标表示列，数组值表示行
            array[row] = i;
            if (isQueueOk(array,row)) {
                printf(array);
                // 递归遍历下一个位置
                if (row < array.length) {
                    backTrace(array, row + 1);
                }
            }
        }
    }

    /**
     * 判断新放入的这个皇后能不能行的通，需要和前面的进行比较，是否在同一行或同一列以及对角线
     * @param array
     * @param row
     * @return
     */
    private boolean isQueueOk(int array[], int row){
        // 判断当前皇后是否ok，只需遍历row之前的所有皇后
        for (int i = 0; i < row; i++) {
            // 同一行不能相等
            if (array[i] == array[row]) {
                return false;
            }
            // 同一正对角线不能相等(左上到右下，正对角线都是行等于列)
            if (i-array[i] == row -array[row]) {
                return false;
            }
            // 同一反对角线不能相等(右上到左下，反对角线是行和列相加相等)
            if (row + array[row] == i + array[i]) {
                return false;
            }
        }
        return true;
    }
```

八皇后结果太多，四皇后运行结果如下：

``` shell
arr = [1, 3, 0, 2]
0 1 0 0 
0 0 0 1 
1 0 0 0 
0 0 1 0 
arr = [2, 0, 3, 1]
0 0 1 0 
1 0 0 0 
0 0 0 1 
0 1 0 0 
total_solution=2
```

#### 2、背包问题

题目：有一个容量为 V 的背包，和一些物品。这些物品分别有两个属性，体积 w 和价值 v，每种物品只有一个。要求用这个背包装下价值尽可能多的物品，求该最大价值，背包可以不被装满。

**算法思路：** 对于所有物品，只有两种选择，放入背包，或者不放入背包。所以可以物品在背包中表示为`1`，物品不放入背包表示为`0`

**步骤：**

1. 找子问题：物品只有两种选择，能放入或者不能放入。假定每次放入第`i`物品。第一、如果包的容量比物品的总重量小，那么最大价值和前`i-1`个物品的价值是一样的。第二、如果包还能放下该物品，但是装了不一定能大于当前相同容量的最大价值，所以需要比较。
2. 确定状态：由上，所有‘状态’对应的’值‘即为背包容量为w时，物品的总价值v。所以每次放入物品都对应`deepFindSearch(i,v,w)`,初始是为`deepFindSearch(0,0,0)`没有放入物品也没有价值和重量。
3. 状态转移：确定状态转移方程，由上述分析，第i个物品放入的时候，背包总价值v，总重量w。则状态转移方程为
   * w>V 当前背包总重量大于容量，背包已经装不下此物品，所以最大价值还是前一个的价值（相当于当前物品及以后物品放入重量为0，这个在之前已经是遍历过了），不变。
   * w<=V 当前背包重量小于等于容量，则可以继续放入。

核心代码如下：

``` java
    /************************背包问题开始********************************/
    int COUNT = 10;
    int weight[]; // 物品重量
    int value[];  //物品单价
    int x[]; //物品的放入情况
    int bestVal;
    int bestX[]; // 最佳的放入情况
    int maxV = 10;
    private void packSolution(){
        Scanner scanner = new Scanner(System.in);
        weight = new int[COUNT];
        value = new int[COUNT];
        x= new int[COUNT];
        bestX = new int[COUNT];
        int i = 0;
        System.out.println("请输入物品重量：");
        while (scanner.hasNext()) {
            if (i >= COUNT) {
                break;
            }
            int in = scanner.nextInt();
            weight[i] = in;
            i++;
        }
        printf(weight);
        System.out.println("请输入物品价值：");
        i = 0;
        while (scanner.hasNext()) {
            if (i >= COUNT) {
                break;
            }
            int in = scanner.nextInt();
            value[i] = in;
            i++;
        }
        printf(weight);
        deepFindSearch(0,0,0);
        System.out.println("物品重量分别为：");
        printf(weight);
        System.out.println("物品价值分别为：");
        printf(value);
        System.out.println("背包最大价值为："+bestVal);
        printf(bestX);
    }

    /**
     * 对所有物品一个个遍历，对每个物品可以有放入和不放入的选择
     * 但是要把所有物品遍历完成，然后看价值是多少。
     * @param n 放入第几个物品了
     * @param v 当前背包里的物品总价值
     * @param w 当前背包里的物品总重量
     */
    private void deepFindSearch(int n,int v,int w){
        // 所有物品都遍历完成了，然后看当前价值是不是最大的。
        if (n >= COUNT) {
            // 如果已经大于最大值了， 需要记录当前放入的物品个数和总价值。
            if (v > bestVal) {
                bestVal = v;
                System.out.println("全部放入物品价值最大 = "+bestVal);
                for (int i = 0; i < COUNT; i++) {
                    bestX[i] = x[i];
                }
                printf(bestX);
            }
        }
        else {
            // 物品只有两种选择，放入或者不放入,所以遍历这两种情况
            for (int i = 0; i <= 1; i++) {
                x[n] = i; // 第N个物品是放入或者不放入，记录状态
                if (w+x[n] * weight[n] < maxV) { //如果总重量小于背包容量才可以继续递归放入下一个
                    deepFindSearch(n+1,v+x[n] * value[n], w + x[n] * weight[n]);//继续搜索
                }else {
//                    System.out.println("物品已经超出背包重量：");
//                    printf(x);
                }
            }
        }
    }

    /************************背包问题结束********************************/

```

算法结果为：

``` shell
请输入物品重量：
3 4 5 6 7 8 9 6 3 2 1
BackTraceAlgorithm:packSolution:[125]:array=[3, 4, 5, 6, 7, 8, 9, 6, 3, 2]
请输入物品价值：
10 3 4 7 13 8 9 6 6 2
BackTraceAlgorithm:packSolution:[136]:array=[3, 4, 5, 6, 7, 8, 9, 6, 3, 2]
全部放入物品价值最大 = 6
BackTraceAlgorithm:deepFindSearch:[163]:array=[0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
全部放入物品价值最大 = 12
BackTraceAlgorithm:deepFindSearch:[163]:array=[0, 0, 0, 0, 0, 0, 0, 0, 1, 1]
全部放入物品价值最大 = 15
BackTraceAlgorithm:deepFindSearch:[163]:array=[0, 0, 0, 0, 0, 0, 0, 1, 0, 1]
全部放入物品价值最大 = 16
BackTraceAlgorithm:deepFindSearch:[163]:array=[0, 1, 0, 0, 0, 0, 0, 0, 0, 1]
全部放入物品价值最大 = 22
BackTraceAlgorithm:deepFindSearch:[163]:array=[0, 1, 0, 0, 0, 0, 0, 0, 1, 1]
物品重量分别为：
BackTraceAlgorithm:packSolution:[139]:array=[3, 4, 5, 6, 7, 8, 9, 6, 3, 2]
物品价值分别为：
BackTraceAlgorithm:packSolution:[141]:array=[1, 10, 3, 4, 7, 13, 8, 9, 6, 6]
背包最大价值为：22
BackTraceAlgorithm:packSolution:[143]:array=[0, 1, 0, 0, 0, 0, 0, 0, 1, 1]
```

