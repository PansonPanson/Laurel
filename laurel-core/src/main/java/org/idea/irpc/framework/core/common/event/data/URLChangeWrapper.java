package top.panson.irpc.framework.core.common.event.data;

import java.util.List;
import java.util.Map;

/**
 * @Author linhao
 * @Date created in 8:56 下午 2021/12/19
 */
public class URLChangeWrapper {

    private String serviceName;

    private List<String> providerUrl;

    //记录每个ip下边的url详细信息，包括权重，分组等
    private Map<String,String> nodeDataUrl;

    public Map<String, String> getNodeDataUrl() {
        return nodeDataUrl;
    }

    public void setNodeDataUrl(Map<String, String> nodeDataUrl) {
        this.nodeDataUrl = nodeDataUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<String> getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(List<String> providerUrl) {
        this.providerUrl = providerUrl;
    }

    @Override
    public String toString() {
        return "URLChangeWrapper{" +
                "serviceName='" + serviceName + '\'' +
                ", providerUrl=" + providerUrl +
                '}';
    }
}
