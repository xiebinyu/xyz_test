package com.xyz.ThreadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java线程：线程池
 *
 */
public class Test {
  public static void main(String[] args) throws InterruptedException {
    // 创建一个可重用固定线程数的线程池
    ExecutorService pool = Executors.newFixedThreadPool(5);
    CountDownLatch latch = new CountDownLatch(5);
    // 创建线程
    Thread t1 = new MyThread(latch, 1);
    Thread t2 = new MyThread(latch, 2);
    Thread t3 = new MyThread(latch, 3);
    Thread t4 = new MyThread(latch, 4);
    Thread t5 = new MyThread(latch, 5);
    System.out.println("线程池执行开始！");
    // 将线程放入池中进行执行
    pool.execute(t1);
    pool.execute(t2);
    pool.execute(t3);
    pool.execute(t4);
    pool.execute(t5);
    // latch归零时主线程开始继续执行
    latch.await();
    System.out.println("线程池执行完毕！");
    // 关闭线程池
    pool.shutdown();
  }
}

class MyThread extends Thread {
  private int i;
  CountDownLatch latch;
  MyThread(CountDownLatch latch, int i) {
    this.latch = latch;
    this.i = i;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "正在执行，输出：" + i);
    // latch递减
    latch.countDown();
  }
}
