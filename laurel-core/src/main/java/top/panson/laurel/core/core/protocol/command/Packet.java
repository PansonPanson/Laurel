package top.panson.laurel.core.core.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 通信过程中的 Java 对象
 *
 * 通信过程中 Java 对象的抽象类，可以看到，我们定义了一个版本号（默认值为 1 ）以及一个获取指令的抽象方法，
 * 所有的指令数据包都必须实现这个方法，这样我们就可以知道某种指令的含义。
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    /**
     * 获取指令的抽象方法
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
