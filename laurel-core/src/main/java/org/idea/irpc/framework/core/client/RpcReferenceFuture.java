package top.panson.irpc.framework.core.client;

/**
 * @Author linhao
 * @Date created in 9:11 上午 2022/2/13
 */
public class RpcReferenceFuture<T> {

    private RpcReferenceWrapper rpcReferenceWrapper;

    private Object response;

    public RpcReferenceWrapper getRpcReferenceWrapper() {
        return rpcReferenceWrapper;
    }

    public void setRpcReferenceWrapper(RpcReferenceWrapper rpcReferenceWrapper) {
        this.rpcReferenceWrapper = rpcReferenceWrapper;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
