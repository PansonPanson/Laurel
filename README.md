Laurel is a java based open source RPC framework.


# 如何设计一个并实现一个 RPC 框架？

1. 使用注册中心：服务的注册与发现
2. 服务通信：通信协议
3. 数据传输：序列化
4. 集群：负载均衡
5. 优雅上下线……


step1： 设计通信协议
```java
public class RpcProtocol implements Serializable {

    /**
     * 魔法数，协议标识
     */
    private short magicNumber = MAGIC_NUMBER;

    /**
     * 协议传输数据长度
     */
    private int contentLength;

    /**
     * 传输数据
     */
    private byte[] content;
}
```

```java
public class RpcInvocation implements Serializable {

    /**
     * 远程调用目标方法
     */
    private String targetMethod;

    /**
     * 
     */
    private String targetServiceName;

    private Object[] args;

    private String uuid;

    private Object response;

    private Throwable e;

    private int retry;

    private Map<String, Object> attachments = new ConcurrentHashMap<>();
}

```
