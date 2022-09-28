package top.panson.laurel.core.core.protocol.request;

import lombok.Data;
import top.panson.laurel.core.core.protocol.command.Command;
import top.panson.laurel.core.core.protocol.Packet;



@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
