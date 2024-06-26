package top.panson.framework.user.provider.service;

import top.panson.irpc.framework.interfaces.good.GoodRpcService;
import top.panson.irpc.framework.interfaces.pay.PayRpcService;
import top.panson.irpc.framework.interfaces.user.UserRpcService;
import top.panson.irpc.framework.spring.starter.common.IRpcReference;
import top.panson.irpc.framework.spring.starter.common.IRpcService;

import java.util.*;

/**
 * @Author linhao
 * @Date created in 10:07 上午 2022/3/19
 */
@IRpcService
public class UserRpcServiceImpl implements UserRpcService {

    @IRpcReference
    private GoodRpcService goodRpcService;
    @IRpcReference
    private PayRpcService payRpcService;

    @Override
    public String getUserId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<Map<String, String>> findMyGoods(String userId) {
        List<String> goodsNoList = goodRpcService.selectGoodsNoByUserId(userId);
        List<Map<String, String>> finalResult = new ArrayList<>();
        goodsNoList.forEach(goodsNo -> {
            Map<String, String> item = new HashMap<>(2);
            List<String> payHistory = payRpcService.getPayHistoryByGoodNo(goodsNo);
            item.put(goodsNo, payHistory.toString());
            finalResult.add(item);
        });
        return finalResult;
    }
}
