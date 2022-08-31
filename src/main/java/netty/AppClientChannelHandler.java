package netty;

import agent.AppTrMake;
import agent.ChannelAgent;
import agent.SessionManage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import trDefine.header.AppComHeader;

import java.nio.charset.StandardCharsets;

public class AppClientChannelHandler  extends SimpleChannelInboundHandler<ChannelAgent> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("channelActive Event  ===========>>");
        SessionManage.RegisterAgent(ctx);

        ByteBuf bytebuf = Unpooled.copiedBuffer(AppTrMake.makeHandshak_RQ());
        ctx.writeAndFlush(bytebuf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChannelAgent agent) throws Exception {
        System.out.println("channelRead0 Event  ===========>>" + ctx.channel().toString());

        if (agent.getAppInBytes()==null)
            System.out.println("channelRead0 Event [agent.getAppInBytes()==null]");

        byte function = agent.getAppComHeaderFunction();
        System.out.println("channelRead0 Event  ===========>>  function:" + function);

        switch (function){
            case AppComHeader.FUNCTION_HEARTBEAT: break;
            case AppComHeader.FUNCTION_RQRP: {
                System.out.println("channelRead0 remoteAddress:" + ctx.channel().remoteAddress().toString());

                System.out.println("channelRead0 FUNCTION_RQRP : " + agent.channelBufferToString(StandardCharsets.UTF_8));
                //agent.AppRequestProcess();
            } break;
            default: {
                System.out.println("channelRead0 unKnownFunction : " + agent.channelBufferToString(StandardCharsets.UTF_8));
            }
            break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws InterruptedException {
        System.out.println("exceptionCaught Event  ===========>>");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive Event  ===========>>");

        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered Event  ===========>>");
        ChannelAgent newAgent = SessionManage.GetAgent(ctx.channel());
        if (newAgent!=null) {
            System.out.println("channelUnregistered  ===========>>HandleID:" + newAgent.toString());
            System.out.println("channelUnregistered RQ:" + newAgent.channelBufferToString(StandardCharsets.UTF_8));
            SessionManage.RemoveAgent(ctx.channel());
        }
        super.channelUnregistered(ctx);
    }
}
