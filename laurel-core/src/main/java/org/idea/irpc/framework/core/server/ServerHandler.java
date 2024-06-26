package top.panson.irpc.framework.core.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.common.RpcProtocol;
import top.panson.irpc.framework.core.common.exception.IRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.Semaphore;

import static top.panson.irpc.framework.core.common.cache.CommonServerCache.*;

/**
 * 非共享模式，不存在线程安全问题
 *
 * @Author linhao
 * @Date created in 9:02 下午 2021/11/28
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ServerChannelReadData serverChannelReadData = new ServerChannelReadData();
        serverChannelReadData.setRpcProtocol((RpcProtocol) msg);
        serverChannelReadData.setChannelHandlerContext(ctx);
        SERVER_CHANNEL_DISPATCHER.add(serverChannelReadData);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //这里统一做异常捕获？
        cause.printStackTrace();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.error("this is channelInactive,ctx is {}",ctx);
        super.channelInactive(ctx);
    }
}
