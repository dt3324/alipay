package com.hnf.alipay.config;

import com.hnf.alipay.domain.AlipayProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @description 配置文件监听器，用来加载自定义配置文件
 * @author caojun
 * @date 2020/2/19
 */
@Component
public class PropertiesListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        AlipayProperties.loadProperties();
    }
}
