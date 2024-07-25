package proxy;

import java.util.ArrayList;
import java.util.List;

public class MyProxy {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.getProperties().put("sum.misc.ProxyGenerator.saveGeneratedFiles", "true");
        List instance = new MyInvocationHandler().getInstance(ArrayList.class);
        instance.add(1);
        instance.add(11);
        instance.add(111);

        ProxiedInterface instance2 = new MyInvocationHandler().getInstance(ProxiedClass.class);
        instance2.doSomething();
        
    }
}
