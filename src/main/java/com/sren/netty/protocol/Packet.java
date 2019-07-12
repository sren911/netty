package com.sren.netty.protocol;

import lombok.Data;

/**
 * @author: renshuai
 * @date: 2019/07/12 上午10:14
 * @Description: 通讯抽象类
 */
@Data
public abstract class Packet {

    private Byte version = 1;

    public abstract Byte getCommand();
}
