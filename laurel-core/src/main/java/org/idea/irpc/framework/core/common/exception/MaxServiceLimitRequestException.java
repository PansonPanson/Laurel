package top.panson.irpc.framework.core.common.exception;

import top.panson.irpc.framework.core.common.RpcInvocation;

/**
 * @Author linhao
 * @Date created in 9:53 下午 2022/3/5
 */
public class MaxServiceLimitRequestException extends IRpcException{

    public MaxServiceLimitRequestException(RpcInvocation rpcInvocation) {
        super(rpcInvocation);
    }
}
