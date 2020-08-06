package com.young.algorithm;

import com.young.algorithm.algorithm.AlgorithmImpl;
import com.young.algorithm.algorithm.BackTraceAlgorithm;
import com.young.algorithm.algorithm.DynamicProgrammingAlgorithm;
import com.young.algorithm.algorithm.IAlgorithm;
import com.young.algorithm.search.BinarySearch;
import com.young.algorithm.search.Search;
import com.young.algorithm.search.SequentialSearch;
import com.young.algorithm.sort.BubbleSort;
import com.young.algorithm.sort.HeapSort;
import com.young.algorithm.sort.InsertSort;
import com.young.algorithm.sort.MergeSort;
import com.young.algorithm.sort.QuickSort;
import com.young.algorithm.sort.SelectionSort;
import com.young.algorithm.sort.Sort;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.String;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

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

    private static void sort(Sort sort,int array[]){
        long start;
        long end;
        start = System.currentTimeMillis();
        sort.sort(array);
        end = System.currentTimeMillis();
        System.out.println("Sort duration = "+ (end - start));
    }

    // 查找算法
    private static void search(Search search){
        int array[] = Utils.generateArray(21);
        int target = array[(int)(Math.random()*array.length)];
        long start;
        long end;
        int result;
        start = System.currentTimeMillis();
        result = search.search(array,target);
        end = System.currentTimeMillis();
        System.out.println("Search duration = "+ (end - start)+",target="+target+",result="+result);
    }

    private static void search(Search search,int[] array,int target){
        long start;
        long end;
        int result;
        start = System.currentTimeMillis();
        result = search.search(array,target);
        end = System.currentTimeMillis();
        System.out.println("Search duration = "+ (end - start)+",target="+target+",result="+result);
    }

    private static void process(AlgorithmImpl algorithm,int[] array){
        long start;
        long end;
        start = System.currentTimeMillis();
        algorithm.process(array);
        end = System.currentTimeMillis();
        System.out.println("Search duration = "+ (end - start));
    }

    private static void socketDemo(){

        Socket socket;
        try {
            socket = new Socket(InetAddress.getByName("192.168.110.105"), 9999);
            socket.setKeepAlive(true);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            int i = 0;
            while (true) {
                i++;
                outputStream.writeUTF("msg"+i);
                outputStream.flush();
                Thread.sleep(3000);
            }
        }catch (UnknownHostException e){
            System.out.println("e = "+e.getMessage());
        }catch (IOException e){
            System.out.println("e = "+e.getMessage());
        }catch (InterruptedException e){
            System.out.println("e = "+e.getMessage());
        }
    }

    List<String> list = new ArrayList<>();

    private synchronized void add(String s) {
        list.add(s);
    }

    private void printf(){
        synchronized (list) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }

    public static void main(String argv[]){
//        sort(new QuickSort());
//        int array[] = Utils.generateArray(21);
//        sort(new MergeSort(),array);
//        search(new BinarySearch(),array,array[(int) (array.length*Math.random())]);
        process(new DynamicProgrammingAlgorithm(),Utils.generateRandomArray(20,10));


    }
}
