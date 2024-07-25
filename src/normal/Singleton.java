package normal;

/**
 * 单例
 **/
public class Singleton {
    /**
     * volatile的必要性：
     * instance = new Singleton()可以拆解为3步：
     * 1.分配内存
     * 2.初始化对象
     * 3.指向分配的地址
     * 这三步是可能发生指令重排序的
     * 假设A线程执行了1和3，还没有执行2，B线程来到了第一个null判断，
     * 因为已经分配了地址，所以此判断为false，会直接返回还没初始化的对象
     **/
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
