package com.sren.netty.client;

import com.sren.netty.client.handler.LoginResponseHandler;
import com.sren.netty.client.handler.MessageResponseHandler;
import com.sren.netty.codec.PacketDecoder;
import com.sren.netty.protocol.PacketCodeC;
import com.sren.netty.protocol.request.MessageRequestPacket;
import com.sren.netty.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

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
                        ch.pipeline().addLast(new PacketDecoder())
                                        .addLast(new LoginResponseHandler())
                        .addLast(new MessageResponseHandler()).addLast(new PacketDecoder());
                    }
                });
        b.connect(HOST, PORT).addListener(f->{
            if (f.isSuccess()) {
                System.err.println("连接成功!");
                Channel channel = ((ChannelFuture) f).channel();
                startConsoleThread(channel);
            }else {
                System.err.println("连接错误!");
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(()->{
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.err.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
