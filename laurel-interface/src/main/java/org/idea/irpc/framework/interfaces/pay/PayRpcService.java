package top.panson.irpc.framework.interfaces.pay;

import java.util.List;

/**
 * @Author linhao
 * @Date created in 10:08 上午 2022/3/19
 */
public interface PayRpcService {

    boolean doPay();

    List<String> getPayHistoryByGoodNo(String goodNo);
}
