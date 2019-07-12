package com.sren.netty.protocol.command;

/**
 * @author: renshuai
 * @date: 2019/07/12 上午10:18
 * @Description:
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
