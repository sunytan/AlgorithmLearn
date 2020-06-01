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
QuickSort:sort:[22]:array=[32, 39, 23, 86, 53, 84, 18, 88, 80, 89]
QuickSort:quickSort:[35]:array=[32, 39, 23, 86, 53, 84, 18, 88, 80, 89]
QuickSort:quickSortPart:[61]:array=[32, ^^39^^, 23, 86, 53, 84, 18, 88, 80, **89**]
QuickSort:quickSortPart:[64]:array=[32, ^^39^^, 23, 86, 53, 84, 18, 88, 80, **89**]
QuickSort:quickSortPart:[76]:array=[32, ^^39^^, 23, 86, 53, 84, 18, 88, **80**, 89]
QuickSort:quickSortPart:[76]:array=[32, ^^39^^, 23, 86, 53, 84, 18, **88**, 80, 89]
QuickSort:quickSortPart:[76]:array=[32, ^^39^^, 23, 86, 53, 84, **18**, 88, 80, 89]
QuickSort:quickSortPart:[64]:array=[32, ^^18^^, 23, 86, 53, 84, **39**, 88, 80, 89]
QuickSort:quickSortPart:[70]:array=[32, 18, ^^23^^, 86, 53, 84, **39**, 88, 80, 89]
QuickSort:quickSortPart:[70]:array=[32, 18, 23, ^^86^^, 53, 84, **39**, 88, 80, 89]
QuickSort:quickSortPart:[76]:array=[32, 18, 23, ^^86^^, 53, **84**, 39, 88, 80, 89]
QuickSort:quickSortPart:[76]:array=[32, 18, 23, ^^86^^, **53**, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[76]:array=[32, 18, 23, ^^**86^^**, 53, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[76]:array=[32, 18, **23**, ^^86^^, 53, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[85]:array=[32, 18, **23**, ^^86^^, 53, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[103]:count=2
QuickSort:quickSort:[35]:array=[23, 18, 32, 86, 53, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[61]:array=[23, ^^**18^^**, 32, 86, 53, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[85]:array=[23, ^^**18^^**, 32, 86, 53, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[103]:count=1
QuickSort:quickSort:[35]:array=[18, 23, 32, 86, 53, 84, 39, 88, 80, 89]
QuickSort:quickSort:[35]:array=[18, 23, 32, 86, 53, 84, 39, 88, 80, 89]
QuickSort:quickSort:[35]:array=[18, 23, 32, 86, 53, 84, 39, 88, 80, 89]
QuickSort:quickSortPart:[61]:array=[18, 23, 32, 86, ^^53^^, 84, 39, 88, 80, **89**]
QuickSort:quickSortPart:[64]:array=[18, 23, 32, 86, ^^53^^, 84, 39, 88, 80, **89**]
QuickSort:quickSortPart:[70]:array=[18, 23, 32, 86, 53, ^^84^^, 39, 88, 80, **89**]
QuickSort:quickSortPart:[70]:array=[18, 23, 32, 86, 53, 84, ^^39^^, 88, 80, **89**]
QuickSort:quickSortPart:[70]:array=[18, 23, 32, 86, 53, 84, 39, ^^88^^, 80, **89**]
QuickSort:quickSortPart:[76]:array=[18, 23, 32, 86, 53, 84, 39, ^^88^^, **80**, 89]
QuickSort:quickSortPart:[64]:array=[18, 23, 32, 86, 53, 84, 39, ^^80^^, **88**, 89]
QuickSort:quickSortPart:[70]:array=[18, 23, 32, 86, 53, 84, 39, 80, ^^**88^^**, 89]
QuickSort:quickSortPart:[76]:array=[18, 23, 32, 86, 53, 84, 39, **80**, ^^88^^, 89]
QuickSort:quickSortPart:[85]:array=[18, 23, 32, 86, 53, 84, 39, **80**, ^^88^^, 89]
QuickSort:quickSortPart:[103]:count=7
QuickSort:quickSort:[35]:array=[18, 23, 32, 80, 53, 84, 39, 86, 88, 89]
QuickSort:quickSortPart:[61]:array=[18, 23, 32, 80, ^^53^^, 84, **39**, 86, 88, 89]
QuickSort:quickSortPart:[64]:array=[18, 23, 32, 80, ^^53^^, 84, **39**, 86, 88, 89]
QuickSort:quickSortPart:[70]:array=[18, 23, 32, 80, 53, ^^84^^, **39**, 86, 88, 89]
QuickSort:quickSortPart:[64]:array=[18, 23, 32, 80, 53, ^^39^^, **84**, 86, 88, 89]
QuickSort:quickSortPart:[70]:array=[18, 23, 32, 80, 53, 39, ^^**84^^**, 86, 88, 89]
QuickSort:quickSortPart:[76]:array=[18, 23, 32, 80, 53, **39**, ^^84^^, 86, 88, 89]
QuickSort:quickSortPart:[85]:array=[18, 23, 32, 80, 53, **39**, ^^84^^, 86, 88, 89]
QuickSort:quickSortPart:[103]:count=5
QuickSort:quickSort:[35]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, 89]
QuickSort:quickSortPart:[61]:array=[18, 23, 32, 39, ^^**53^^**, 80, 84, 86, 88, 89]
QuickSort:quickSortPart:[85]:array=[18, 23, 32, 39, ^^**53^^**, 80, 84, 86, 88, 89]
QuickSort:quickSortPart:[103]:count=3
QuickSort:quickSort:[35]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, 89]
QuickSort:quickSort:[35]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, 89]
QuickSort:quickSort:[35]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, 89]
QuickSort:quickSort:[35]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, 89]
QuickSort:quickSortPart:[61]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, ^^**89^^**]
QuickSort:quickSortPart:[85]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, ^^**89^^**]
QuickSort:quickSortPart:[103]:count=8
QuickSort:quickSort:[35]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, 89]
QuickSort:quickSort:[35]:array=[18, 23, 32, 39, 53, 80, 84, 86, 88, 89]
Sort duration = 8
```

