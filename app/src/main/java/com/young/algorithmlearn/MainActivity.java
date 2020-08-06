package com.young.algorithmlearn;

import android.app.Activity;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-7:上午8:58
 * Description: this is MainActivity
 */
public class MainActivity extends Activity {

    List<Character> list = new ArrayList<>();


    public void read(){
        try {
            FileInputStream stream = new FileInputStream("path");
            stream.getChannel().tryLock();

        }catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      rg  Collection
        List<String> a = new ArrayList();
        a.add("0");
        String b = a.get(0)+"1";
        Integer f;
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(1);
        Lock lock;
        ReentrantLock lock1;
        CopyOnWriteArrayList list;
        AtomicInteger integer;
        AtomicReference reference;
        ClassLoader classLoader;
        BaseDexClassLoader dexClassLoader;
        DexClassLoader dexClassLoader1;
        Field field;

    }
}
