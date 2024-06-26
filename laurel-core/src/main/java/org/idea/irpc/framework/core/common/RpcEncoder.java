package top.panson.irpc.framework.core.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static top.panson.irpc.framework.core.common.constants.RpcConstants.DEFAULT_DECODE_CHAR;

/**
 * RPC编码器
 *
 * @Author linhao
 * @Date created in 9:50 上午 2021/12/4
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol msg, ByteBuf out) throws Exception {
        out.writeShort(msg.getMagicNumber());
        out.writeInt(msg.getContentLength());
        out.writeBytes(msg.getContent());
        out.writeBytes(DEFAULT_DECODE_CHAR.getBytes());
    }
}
