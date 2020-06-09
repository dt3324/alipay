package com.hnf.alipay.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 应用启动加载文件
 * @author caojun
 * @date 2020/2/19
 */
@Component
@Getter
@Slf4j
public class AlipayProperties {
    private static final String APP_ID = "appId";
    private static final String PRIVARY_KEY = "privateKey";
    private static final String PUBLIC_KEY = "publicKey";
    private static final String NOTIFY_URL = "notifyUrl";
    private static final String RETURN_URL = "returnUrl";
    private static final String SIGN_TYPE = "signType";
    private static final String CHARSET = "charset";
    private static final String GATEWAY_URL = "gatewayUrl";
    private static final String LOG_PATH = "logPath";

    /**
     * 保存加载配置参数
     */
    private static Map<String, String> propertiesMap = new HashMap<>();

    /**
     * 加载属性
     */
    public static void loadProperties() {
        // 获得PathMatchingResourcePatternResolver对象
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource resources = resolver.getResource("classpath:alipay.properties");
            PropertiesFactoryBean config = new PropertiesFactoryBean();
            config.setLocation(resources);
            config.afterPropertiesSet();
            Properties prop = config.getObject();
            // 循环遍历所有得键值对并且存入集合
            assert prop != null;
            for (String key : prop.stringPropertyNames()) {
                propertiesMap.put(key, (String) prop.get(key));
            }
        } catch (Exception e) {
            log.info("配置加载失败");
        }
    }

    /**
     * 获取配置参数值
     */
    public static String getKey(String key) {
        return propertiesMap.get(key);
    }

    public static String getAppId() {
        return propertiesMap.get(APP_ID);
    }

    public static String getPrivateKey() {
        return propertiesMap.get(PRIVARY_KEY);
    }

    public static String getPublicKey() {
        return propertiesMap.get(PUBLIC_KEY);
    }

    public static String getNotifyUrl() {
        return propertiesMap.get(NOTIFY_URL);
    }

    public static String getReturnUrl() {
        return propertiesMap.get(RETURN_URL);
    }

    public static String getSignType() {
        return propertiesMap.get(SIGN_TYPE);
    }

    public static String getCharset() {
        return propertiesMap.get(CHARSET);
    }

    public static String getGatewayUrl() {
        return propertiesMap.get(GATEWAY_URL);
    }

    public static String getLogPath() {
        return propertiesMap.get(LOG_PATH);
    }
}
