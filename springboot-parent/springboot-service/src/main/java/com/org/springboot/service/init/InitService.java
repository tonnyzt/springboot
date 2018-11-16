package com.org.springboot.service.init;

import com.org.springboot.dao.SystemCfgDao;
import com.org.springboot.entity.SystemCfg;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 需要初始化的服务
 */
@Component
public class InitService implements InitializingBean {
    public final static String system = "SYSTEMCFG";
    public final static String FLUSH = "FLUSH"; // 强制刷新缓存
    public final static String NO_FLUSH = "NO_FLUSH"; //不刷新

    private HashMap<String, Map<String, String>> cfg = null;
    @Resource
    private SystemCfgDao systemCfgDao;

    @Override
    public void afterPropertiesSet() {
        initLoaclCache();
    }

    public synchronized void initLoaclCache() {
        if (null == cfg) {
            cfg = new HashMap<>();
            //加载应用系统配置
            HashMap<String, String> systemCfg = new HashMap<>();
            List<SystemCfg> systemCfgList = systemCfgDao.getAll();
            if (!CollectionUtils.isEmpty(systemCfgList)) {
                for (Iterator<SystemCfg> it = systemCfgList.iterator(); it.hasNext(); ) {
                    SystemCfg cfg = it.next();
                    systemCfg.put(cfg.getKey(), cfg.getValue());
                }
                cfg.put(system, systemCfg);
            }
        }
    }

    public String getLocalCache(String model, String type, String key) {
        Map<String, String> cfg = getLocalCacheCfg(model, type, key);
        if (null != cfg) {
            return cfg.get(key);
        }
        return null;
    }

    public Map<String, String> getLocalCacheCfg(String model, String type, String key) {
        if (FLUSH.equals(model)) {
            //同步开启
            synchronized (FLUSH) {
                if (system.equals(type)) {
                    Map<String, String> systemCfg = cfg.get(type);
                    if (null != systemCfg) {
                        SystemCfg systemCfgFind = new SystemCfg();
                        systemCfgFind.setKey(key);
                        SystemCfg systemCfgRs = systemCfgDao.getOne(systemCfgFind);
                        if (null != systemCfgRs) {
                            systemCfg.put(key, systemCfgRs.getValue());
                        }
                    }
                }
            }
        }
        return cfg.get(type);
    }
}
