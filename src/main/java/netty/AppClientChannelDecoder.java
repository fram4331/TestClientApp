package netty;

import agent.ChannelAgent;
import agent.SessionManage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import trDefine.header.AppComHeader;
import util.TypeHelper;

import java.util.List;

public class AppClientChannelDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int iReadLen;
        byte[] readBytes;
        try {
            System.out.println("decode Event  ===========>>" + in.readableBytes());
            while (in.readableBytes() >= AppComHeader.LENGTH) {
                System.out.println("---------------------------------- " + ctx.channel().toString());

                readBytes = new byte[AppComHeader.LENGTH];
                try {
                    in.getBytes(in.readerIndex(), readBytes);

                    System.out.println("[(Readable:" + in.readableBytes() + " | R:" + in.readerIndex() + "-W:" + in.writerIndex() +
                            "H:" + TypeHelper.byteArrayToHexString(readBytes));
                } catch (Exception e) {
                    System.out.println("Logging stream data fail : " + e.getMessage());
                }

                if (in.readableBytes() >= (iReadLen = AppComHeader.parsePacketLength(in))) {
                    System.out.println("[Read]-length: " + iReadLen + " > readadbleBytes:" + in.readableBytes());
                    readBytes = new byte[iReadLen];
                    in.readBytes(readBytes);
                    System.out.println("[Read-Data]:" + new String(readBytes));

                    ChannelAgent agentManage = SessionManage.MakeAgent(ctx, readBytes);

                    out.add(agentManage);
                    System.out.println("[decode]:out.add >>>>>>>>>>>>>>");
                }
            }
        } catch (Exception e) {
            System.out.println("======================================================");
            System.out.println("==========AppClientChannelDecoder Error===============");
            System.out.println(e.getMessage());
            System.out.println("======================================================");
        }
    }
}
