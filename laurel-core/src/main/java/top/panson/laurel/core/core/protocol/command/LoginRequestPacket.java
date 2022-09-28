package top.panson.laurel.core.core.protocol.command;


import lombok.Data;

/**
 * 我们拿客户端登录请求为例，定义登录请求数据包
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
