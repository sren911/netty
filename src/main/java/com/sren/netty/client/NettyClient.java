package com.sren.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: renshuai
 * @date: 2019/07/11 下午4:37
 * @Description:
 */
public class NettyClient {

    private static String HOST = "127.0.0.1";
    private static int PORT = 8888;

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new CilentHandler());
                    }
                });
        b.connect(HOST, PORT).addListener(f->{
            if (f.isSuccess()) {
                System.err.println("连接成功!");
            }else {
                System.err.println("连接错误!");
            }
        });
    }
}
