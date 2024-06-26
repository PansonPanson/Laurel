package top.panson.irpc.framework.core.filter.server;

import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.common.annotations.SPI;
import top.panson.irpc.framework.core.filter.IServerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端日志过滤器
 *
 * @Author linhao
 * @Date created in 8:01 下午 2022/1/29
 */
@SPI("before")
public class ServerLogFilterImpl implements IServerFilter {

    private static Logger logger = LoggerFactory.getLogger(ServerLogFilterImpl.class);

    @Override
    public void doFilter(RpcInvocation rpcInvocation) {
        logger.debug(rpcInvocation.getAttachments().get("c_app_name") + " do invoke -----> " + rpcInvocation.getTargetServiceName() + "#" + rpcInvocation.getTargetMethod());
    }

}
