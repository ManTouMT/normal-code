package concurrent;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    
    private String type = "A";
    private int count = 0;
    
    public void printA() {
        lock.lock();
        try{
            while(!Objects.equals(type, "A")) {
                try {
                    conditionA.await();
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "--A" + "count:" + count++);
            type = "B";
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while(!Objects.equals(type, "B")) {
                try {
                    conditionB.await();
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "--B" + "count:" + count++);
            type = "C";
            conditionC.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        while(!type.equals("C")) {
            try {
                conditionC.await();
            }  catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + "--C" + "count:" + count++);
        type = "A";
        conditionA.signal();
        lock.unlock();
    }
    
    public static void main(String[] args) {
        PrintABC pc = new PrintABC();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                pc.printA();
            }
        }, "A");

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                pc.printB();
            }
        }, "B");

        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                pc.printC();
            }
        }, "C");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
