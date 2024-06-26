package top.panson.irpc.framework.core.common.exception;

import top.panson.irpc.framework.core.common.RpcInvocation;

/**
 * @Author linhao
 * @Date created in 9:04 上午 2022/3/3
 */
public class IRpcException extends RuntimeException {

    private RpcInvocation rpcInvocation;

    public RpcInvocation getRpcInvocation() {
        return rpcInvocation;
    }

    public void setRpcInvocation(RpcInvocation rpcInvocation) {
        this.rpcInvocation = rpcInvocation;
    }

    public IRpcException(RpcInvocation rpcInvocation) {
        this.rpcInvocation = rpcInvocation;
    }

}
