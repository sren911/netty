package com.sren.netty.server.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: renshuai
 * @date: 2019/07/12 下午4:14
 * @Description:
 */
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("InBoundHandlerC: " + msg);
        super.channelRead(ctx, msg);
    }
}
