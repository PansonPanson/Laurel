package top.panson.laurel.core.core.protocol.response;

import lombok.Data;
import top.panson.laurel.core.core.protocol.Packet;

import static top.panson.laurel.core.core.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
