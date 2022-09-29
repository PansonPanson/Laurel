package top.panson.laurel.core.core.protocol.request;

import lombok.Data;
import top.panson.laurel.core.core.protocol.Packet;

import static top.panson.laurel.core.core.protocol.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
