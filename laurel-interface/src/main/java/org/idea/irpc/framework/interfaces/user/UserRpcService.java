package top.panson.irpc.framework.interfaces.user;

import java.util.List;
import java.util.Map;

/**
 * @Author linhao
 * @Date created in 10:08 上午 2022/3/19
 */
public interface UserRpcService {

    String getUserId();

    List<Map<String, String>> findMyGoods(String userId);
}
