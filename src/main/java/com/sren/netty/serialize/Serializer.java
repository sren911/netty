package com.sren.netty.serialize;

import com.sren.netty.serialize.impl.JSONSerializer;

/**
 * @author: renshuai
 * @date: 2019/07/12 上午10:20
 * @Description:
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列号算法
     */

    byte getSerializerAlogrithm();

    /**
     * java对象转换成为二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换为java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
