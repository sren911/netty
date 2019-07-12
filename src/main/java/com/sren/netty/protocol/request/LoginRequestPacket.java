package com.sren.netty.protocol.request;

import com.sren.netty.protocol.command.Command;
import com.sren.netty.protocol.Packet;
import lombok.Data;

/**
 * @author: renshuai
 * @date: 2019/07/12 上午10:17
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
