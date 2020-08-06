package com.young.algorithm;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-2:下午12:42
 * Description: this is Test
 */
public class Test {

    private static Test INSTANCE;

    public static Test getInstance(){
        if (INSTANCE == null) {
            synchronized (Test.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Test();
                }
            }
        }
        return INSTANCE;
    }

    private boolean flag = true;
    void twoThread(){
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
//                    System.out.println(flag);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("loop");
                    if (!flag) {
                        System.out.println("exit");
                        break;
                    }
                }
            }
        };
        thread1.start();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                super.run();
                flag = false;
                System.out.println("修改了");
            }
        };
        thread2.start();

    }
}
