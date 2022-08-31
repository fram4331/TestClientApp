package netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class AppClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("initChannel ===========>>");
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new AppClientChannelDecoder());
        pipeline.addLast(new AppClientChannelHandler());
    }
}
