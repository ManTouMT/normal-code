package proxy;

/**
 * 被代理类
 **/
public class ProxiedClass implements ProxiedInterface{
    @Override
    public Long doSomething() {
        System.out.println("i'm doing something");
        return 1L;
        //return "Hello world";
    }

    @Override
    public Long doSomething2() {
        System.out.println("i'm doing something 2");
        return 2L;
    }
}