package com.sren.netty.server;

import com.sren.netty.codec.PacketDecoder;
import com.sren.netty.codec.PacketEncoder;
import com.sren.netty.server.handler.LoginRequestHandler;
import com.sren.netty.server.handler.MessageRequestHandler;
import com.sren.netty.server.inbound.InBoundHandlerA;
import com.sren.netty.server.inbound.InBoundHandlerB;
import com.sren.netty.server.inbound.InBoundHandlerC;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: renshuai
 * @date: 2019/07/11 下午4:36
 * @Description:
 */
public class NettyServer {

    private static final int PORT = 8888;

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new PacketDecoder())
                                    .addLast(new LoginRequestHandler())
                                    .addLast(new MessageRequestHandler())
                                    .addLast(new PacketEncoder());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
