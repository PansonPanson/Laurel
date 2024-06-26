package top.panson.irpc.framework.core.spi.jdk;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author linhao
 * @Date created in 2:50 下午 2022/2/4
 */
public class TestSpiDemo {

    public static void doTest(ISpiTest iSpiTest){
        System.out.println("begin");
        iSpiTest.doTest();
        System.out.println("end");
    }

    public static void main(String[] args) {
        ServiceLoader<ISpiTest> serviceLoader = ServiceLoader.load(ISpiTest.class);
        Iterator<ISpiTest> iSpiTestIterator = serviceLoader.iterator();
        while (iSpiTestIterator.hasNext()){
            ISpiTest iSpiTest = iSpiTestIterator.next();
            TestSpiDemo.doTest(iSpiTest);
        }
    }
}
