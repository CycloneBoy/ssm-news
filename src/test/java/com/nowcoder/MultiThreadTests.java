package com.nowcoder;


import com.sun.tools.classfile.StackMapTable_attribute;
import jdk.nashorn.internal.ir.Block;
import sun.java2d.SurfaceDataProxy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CycloneBoy on 2017/9/4.
 */
class MyThread extends  Thread{
    private int tid;
    public MyThread(int tid){
        this.tid = tid;
    }

    @Override
    public void run() {
        try{
            for(int i = 0 ; i < 10;i++){
                Thread.sleep(1000);
                System.out.println(String.format("T%d:%d",tid,i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable{

    private BlockingQueue<String> q;

    public Producer(BlockingQueue<String > q ){
        this.q = q;
    }

    @Override
    public void run() {
        try {
            for(int i =0;i < 100;i++){
                Thread.sleep(10);
                q.put(String.valueOf(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    private BlockingQueue<String > q;

    public Consumer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try{
            while (true){
                System.out.println(Thread.currentThread().getName() + ": " +q.take());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class MultiThreadTests {
    public static void testThread(){
        for(int i = 0; i< 10 ;i++){
            //new MyThread(i).start();
        }

        for(int i = 0; i< 10 ;i++){
            final int tid = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        for(int i = 0 ; i < 10;i++){
                            Thread.sleep(1000);
                            System.out.println(String.format("T%d:%d",tid,i));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static Object obj = new Object();

    public static void testSynchronized1(){
        synchronized(obj){
            try{
                for(int i=0;i <10; i++){
                    Thread.sleep(1000);
                    System.out.println(String.format("T4%d",i));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void testSynchronized2(){
        synchronized(new Object()){
            try{
                for(int i=0;i <10; i++){
                    Thread.sleep(1000);
                    System.out.println(String.format("T3%d",i));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void testSynchronized(){
        for(int i = 0 ; i< 10;++i){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testSynchronized1();
                    testSynchronized2();
                }
            }).start();
        }
    }

    public static void testBlockingQueue(){
        BlockingQueue<String> q = new ArrayBlockingQueue<String>(10);
        new Thread(new Producer(q)).start();
        new Thread(new Consumer(q),"consumer1").start();
        new Thread(new Consumer(q),"consumer2").start();
    }

    private static int counter = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void sleep(int mils){
        try {
            Thread.sleep(mils);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testWithAtomic(){
        for(int i = 0;i <10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(1000);
                    for(int j = 0; j< 10;j++){
                        System.out.println(atomicInteger.incrementAndGet());
                    }
                }
            }).start();
        }
    }

    public static void testWithoutAtomic(){
        for(int i = 0;i <10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(1000);
                    for(int j = 0; j< 10;j++){
                        counter++;
                        System.out.println(counter);
                    }
                }
            }).start();
        }
    }

    public static void testAtomic(){
        testWithAtomic();
        testWithoutAtomic();
    }
    public static void main(String[] args) {
        //testThread();
        //testSynchronized();
        //testBlockingQueue();
        testAtomic();
    }
}
