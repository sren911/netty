package coms.ren.first;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @author: renshuai
 * @date: 2019/07/11 下午4:14
 * @Description:
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new StringEncoder());
                    }
                });

        ChannelFuture channelFuture = b.connect("127.0.0.1", 8888).sync();
        Channel channel = channelFuture.channel();
        while (true) {
            channel.writeAndFlush(new Date() + "hello netty");
            Thread.sleep(2000);
        }
    }
}
