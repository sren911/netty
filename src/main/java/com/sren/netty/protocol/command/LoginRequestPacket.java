package com.sren.netty.protocol.command;

import lombok.Data;

/**
 * @author: renshuai
 * @date: 2019/07/12 上午10:17
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String username;

    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
