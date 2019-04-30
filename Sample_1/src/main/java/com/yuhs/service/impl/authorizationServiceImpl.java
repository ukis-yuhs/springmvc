package com.yuhs.service.impl;

import com.yuhs.shiro.CustomRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuhaisheng on 2019/4/29.
 */
@Service
public class authorizationServiceImpl {

    //注入reaml
    @Autowired
    private CustomRealm customRealm;

    /**
     * 清除缓存
     */
    public void clearShiroCache() {
        customRealm.clearCache();
    }
}
