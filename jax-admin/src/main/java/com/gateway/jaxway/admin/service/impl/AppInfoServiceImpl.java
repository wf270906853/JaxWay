package com.gateway.jaxway.admin.service.impl;

import com.gateway.common.beans.JaxClientAuthentication;
import com.gateway.common.beans.JaxServerAuthentication;
import com.gateway.jaxway.admin.beans.JaxRouteDefinition;
import com.gateway.jaxway.admin.service.AppInfoService;
import com.gateway.jaxway.admin.service.RedisService;
import com.gateway.jaxway.admin.support.SerializeDeserializeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.gateway.jaxway.admin.support.JaxAdminConstant.*;

/**
 * @Author huaili
 * @Date 2019/5/13 15:20
 * @Description AppInfoServiceImpl
 **/
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Resource(name = "redisTemplateProtoStuff")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;
    @Override
    public List<JaxClientAuthentication> getJaxClientAuthentication(String appId, Long versionId) {
        String redisKey = CLIENT_APP_INFO_REDIS_KEY+appId;
        return getServerInfoFromRedis(redisKey,versionId,JaxClientAuthentication.class);
    }

    @Override
    public List<JaxServerAuthentication> getJaxServerAuthentication(String appId, Long versionId) {
        String redisKey = SERVER_APP_INFO_REDIS_KEY+appId;
        return getServerInfoFromRedis(redisKey,versionId,JaxServerAuthentication.class);
    }

    @Override
    public List<JaxServerAuthentication> getServerWhiteListInfo(String appId, Long versionId) {
        String redisKey = SERVER_WHITE_LIST_REDIS_KEY+appId;
        return getServerInfoFromRedis(redisKey,versionId,JaxServerAuthentication.class);
    }

    @Override
    public List<JaxRouteDefinition> getJaxRouteDefinitions(String appId, Long versionId) {
        String redisKey = SERVER_ROUTES_INFO_REDIS_KEY+appId;

        return getServerInfoFromRedis(redisKey,versionId,JaxRouteDefinition.class);
    }


    <T> List<T> getServerInfoFromRedis(String key,Long versionId,Class<T> clazz){

        List<T> list = new ArrayList<>();
        Set<String> versionIds = redisTemplate.opsForHash().keys(key);
        versionIds.stream().filter(e-> Long.parseLong(e)>versionId).forEach(e->{
            list.addAll(redisService.getObject(key,e,List.class));
        });

        return list;
    }
}
