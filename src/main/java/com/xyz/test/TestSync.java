package com.xyz.test;

public class TestSync extends Thread {
  private int type;
  private Person person;
  public TestSync(Person person, int type) {
    this.person = person;
    this.type = type;
  }

  @Override
  public void run() {
    try {
      switch (type) {
        case 1: person.sleep(); break;
        case 2: person.breath(); break;
        case 3: person.eat(); break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Person person = new Person();
    TestSync thread1 = new TestSync(person, 1);
    TestSync thread2 = new TestSync(person, 2);
    TestSync thread3 = new TestSync(person, 3);
    thread1.start();
    thread2.start();
    thread3.start();
  }
}

class Person {
  Integer i = 1;

  // 同步代码块，如果同步变量是this，则锁对象，当前锁的是i，其它同步方法不受影响
  public void sleep() throws Exception {
    synchronized(i) {
      Thread.sleep(3000);
      System.out.println("睡觉！");
    }
  }

  // 非同步方法
  public void breath() {
    System.out.println("呼吸！");
  }

  // 同步方法，导致其它同步方法不能访问，非同步方法不受影响
  public synchronized void eat() {
    System.out.println("吃饭！");
  }
}
