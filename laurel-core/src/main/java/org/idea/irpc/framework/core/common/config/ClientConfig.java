package top.panson.irpc.framework.core.common.config;

/**
 * @Author linhao
 * @Date created in 10:41 上午 2021/12/11
 */
public class ClientConfig {

    private String applicationName;

    private String registerAddr;

    private String registerType;

    /**
     * 代理类型 example: jdk,javassist
     */
    private String proxyType;

    /**
     * 负载均衡策略 example:random,rotate
     */
    private String routerStrategy;

    /**
     * 客户端序列化方式 example: hession2,kryo,jdk,fastjson
     */
    private String clientSerialize;

    /**
     * 客户端发数据的超时时间
     */
    private Integer timeOut;

    /**
     * 客户端最大响应数据体积
     */
    private Integer maxServerRespDataSize;

    public Integer getMaxServerRespDataSize() {
        return maxServerRespDataSize;
    }

    public void setMaxServerRespDataSize(Integer maxServerRespDataSize) {
        this.maxServerRespDataSize = maxServerRespDataSize;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public String getClientSerialize() {
        return clientSerialize;
    }

    public void setClientSerialize(String clientSerialize) {
        this.clientSerialize = clientSerialize;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public String getRegisterAddr() {
        return registerAddr;
    }

    public void setRegisterAddr(String registerAddr) {
        this.registerAddr = registerAddr;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getRouterStrategy() {
        return routerStrategy;
    }

    public void setRouterStrategy(String routerStrategy) {
        this.routerStrategy = routerStrategy;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }
}
