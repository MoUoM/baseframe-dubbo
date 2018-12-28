package com.vc.jj.service;

import com.vc.jj.api.TokenManager;
import com.vc.jj.dto.TokenModel;
import com.vc.jj.util.SysConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 * @see
 */
@Service(value = "manager")
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<Long, String> redis;

    @Autowired
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public TokenModel createToken(long userId, String appid, String LoginName) {
        //使用uuid作为源token
        String token =  UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token,appid,LoginName);
        //存储到redis并设置过期时间
        redis.boundValueOps(userId).set(userId+"_"+token+"_"+appid+"_"+LoginName, SysConst.TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
        return model;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 4) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        String appid = param[2];
        String loginName =  param[3];
        return new TokenModel(userId, token, appid,loginName);
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getUserId()).expire(SysConst.TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
        return true;
    }

    @Override
    public void deleteToken(long userId) {
        redis.delete(userId);
    }
}
