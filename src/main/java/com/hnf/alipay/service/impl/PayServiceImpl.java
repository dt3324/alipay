package com.hnf.alipay.service.impl;

import com.alibaba.fastjson.JSON;
import com.hnf.alipay.domain.*;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hnf.alipay.domain.AlipayProperties;
import com.hnf.alipay.service.PayService;
import org.springframework.stereotype.Service;
/**
 * @description pay的service具体实现
 * @author caojun
 * @date 2020/2/19
 */
@Service
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return pay(alipayBean);
    }

    /**
     * 支付接口
     * @param alipayBean 支付参数
     */
    private static String pay(AlipayBean alipayBean) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        String serverUrl = AlipayProperties.getGatewayUrl();
        String appId = AlipayProperties.getAppId();
        String privateKey = AlipayProperties.getPrivateKey();
        String format = "json";
        String charset = AlipayProperties.getCharset();
        String alipayPublicKey = AlipayProperties.getPublicKey();
        String signType = AlipayProperties.getSignType();
        String returnUrl = AlipayProperties.getReturnUrl();
        String notifyUrl = AlipayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

        // 3、请求支付宝进行付款，并获取支付结果
        // 返回付款信息
        return alipayClient.pageExecute(alipayRequest).getBody();
    }
}
