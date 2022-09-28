package top.panson.laurel.core.core.protocol.response;

import lombok.Data;
import top.panson.laurel.core.core.protocol.command.Command;
import top.panson.laurel.core.core.protocol.Packet;


@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
