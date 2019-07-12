package com.sren.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.sren.netty.serialize.Serializer;
import com.sren.netty.serialize.SerializerAlogrithm;

/**
 * @author: renshuai
 * @date: 2019/07/12 上午10:23
 * @Description:
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
