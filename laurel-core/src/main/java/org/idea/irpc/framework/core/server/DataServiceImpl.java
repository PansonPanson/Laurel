package top.panson.irpc.framework.core.server;

import top.panson.irpc.framework.core.common.exception.IRpcException;
import top.panson.irpc.framework.interfaces.DataService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author linhao
 * @Date created in 5:07 下午 2021/12/5
 */
public class DataServiceImpl implements DataService {

    @Override
    public String sendData(String body) {
        System.out.println("这里是服务提供者，body is " + body);
        return "success";
    }

    @Override
    public List<String> getList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("idea1");
        arrayList.add("idea2");
        arrayList.add("idea3");
        return arrayList;
    }

    @Override
    public void testError() {
        System.out.println(1 / 0);
    }

    @Override
    public String testErrorV2() {
        throw new RuntimeException("测试异常");
//        return "three";
    }
}
