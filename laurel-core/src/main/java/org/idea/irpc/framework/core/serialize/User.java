package top.panson.irpc.framework.core.serialize;

import java.io.Serializable;

/**
 * 测试自定义序列化技术时使用的demo
 *
 * @Author linhao
 * @Date created in 7:28 下午 2022/1/20
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1728196331321496561L;
    private Integer id;

    private Long tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", tel=" + tel +
                '}';
    }

}
