package com.yuhs.redis;

import com.yuhs.utils.secure.MD5Utils_1;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * Created by yuhaisheng on 2019/5/13.
 */
public class JavaUuidSessionIdGenerator implements SessionIdGenerator {

    /**
     * Ignores the method argument and simply returns
     * {@code UUID}.{@link java.util.UUID#randomUUID() randomUUID()}.{@code toString()}.
     *
     * @param session the {@link Session} instance to which the ID will be applied.
     * @return the string value of the JDK's next {@link UUID#randomUUID() randomUUID()}.
     */
    public Serializable generateId(Session session) {
        MD5Utils_1 m = new MD5Utils_1();
        String host = session.getHost();
        String id = null;
        try {

            String string = InetAddress.getLocalHost().toString();

            // <property name="keyPrefix" value="shiro_redis_session:"/>
            id = m.getMD5ofStr(UUID.randomUUID().toString() + host + string); // clientIP + serverIP
            while (!RedisClient.setnx("shiro_redis_session:" + id, "")) {
                id = m.getMD5ofStr(UUID.randomUUID().toString() + host + string); // clientIP + serverIP
            }
            return id;

        } catch (UnknownHostException e) {

            id = m.getMD5ofStr(UUID.randomUUID().toString() + host);
            while (!RedisClient.setnx("shiro_redis_session:" + id, "")) {
                id = m.getMD5ofStr(UUID.randomUUID().toString() + host); // clientIP + serverIP
            }
            return id;
        }

    }
}
