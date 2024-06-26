package top.panson.irpc.framework.interfaces;

import java.util.List;

/**
 * @Author linhao
 * @Date created in 6:41 下午 2022/3/8
 */
public interface OrderService {

    List<String> getOrderNoList();

    String testMaxData(int i);
}
