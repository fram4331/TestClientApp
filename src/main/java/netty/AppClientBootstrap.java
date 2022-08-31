package netty;

import agent.SessionManage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppClientBootstrap {
    private String host;
    private int port;

    EventLoopGroup bossGroup;
    Bootstrap bootstrap;
    Channel clientChannel;

    public AppClientBootstrap(String host, int port) {
        this.host = host;
        this.port = port;
        bossGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
    }

    public void start() throws Exception {
        try {
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new AppClientChannelInitializer());
            ChannelFuture f = bootstrap.connect().sync();
            clientChannel = f.channel().closeFuture().sync().channel();
        } finally {
            bossGroup.shutdownGracefully().sync();
            stop();
        }
    }

    public void stop() {
        System.out.println("======================================================");
        SessionManage.AllClearAgent();
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("Server Stop:" + df.format(d));
        System.out.println("======================================================");
    }
}
