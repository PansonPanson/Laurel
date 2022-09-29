package top.panson.laurel.core.core.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.panson.laurel.core.core.protocol.Packet;

import static top.panson.laurel.core.core.protocol.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
