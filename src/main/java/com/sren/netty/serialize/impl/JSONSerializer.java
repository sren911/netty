package com.sren.netty.serialize.impl;

import com.sren.netty.serialize.Serializer;

/**
 * @author: renshuai
 * @date: 2019/07/12 上午10:23
 * @Description:
 */
public class SerializerImpl implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return 0;
    }

    @Override
    public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return null;
    }
}
