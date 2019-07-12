package com.sren.netty.protocol.request;

import com.sren.netty.protocol.Packet;
import com.sren.netty.protocol.command.Command;
import lombok.Data;

/**
 * @author: renshuai
 * @date: 2019/07/12 下午3:32
 * @Description:
 */

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
