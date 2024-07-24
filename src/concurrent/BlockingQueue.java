package concurrent;

import java.lang.reflect.Array;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自己实现的阻塞队列
 **/
public class BlockingQueue<T> {

    private final T[] tab; //队列容器

    private int takeIndex; //出队下标

    private int putIndex; //入队下标

    private int size;//元素数量

    private final Lock reentrantLock = new ReentrantLock();

    private final Condition emptyLock;//读条件

    private final Condition fullLock;//写条件

    @SuppressWarnings("unchecked")
    public BlockingQueue(Class<T> tClass, int tabCount) {
        if (tabCount <= 0) {
            throw new NullPointerException();
        }
        tab = (T[]) Array.newInstance(tClass, tabCount);
        emptyLock = reentrantLock.newCondition();
        fullLock = reentrantLock.newCondition();
    }

    public boolean offer(T obj) {
        try {
            //获取锁
            reentrantLock.lock();
            //队列已满
            while (size == tab.length){
                System.out.println("队列已满");
                //堵塞
                fullLock.await();
            }
            
            tab[putIndex]=obj;
            size++;
            if(++putIndex == tab.length){
                putIndex = 0;
            }
            //唤醒读线程
            emptyLock.signal();
            return true;
        } catch (Exception e) {
            //唤醒读线程
            emptyLock.signal();
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public T take(){
        try {
            reentrantLock.lock();
            while (size==0){
                System.out.println("队列空了");
                //堵塞
                emptyLock.await();
            }
            T value= tab[takeIndex];
            tab[takeIndex]=null;
            //如果到了最后一个，则从头开始
            if(++takeIndex == tab.length){
                takeIndex=0;
            }
            size--;
            //唤醒写线程
            fullLock.signal();
            return value;
        }catch (Exception e){
            //唤醒写线程
            fullLock.signal();
            throw new RuntimeException(e);
        }finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue =new BlockingQueue<>(Integer.class, 5);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.offer(i);
                System.out.println("生产者生产了："+i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i<  100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer take = blockingQueue.take();
                System.out.println("消费者消费了："+ take);
            }
        });
        
        thread1.start();
        thread2.start();
    }
}
