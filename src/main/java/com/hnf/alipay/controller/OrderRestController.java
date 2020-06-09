package com.hnf.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.hnf.alipay.domain.AlipayBean;
import com.hnf.alipay.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description 订单接口
 * @author caojun
 * @date 2020/2/19
 */

@RestController
@RequestMapping("/order")
public class OrderRestController {
    @Resource
    private PayService payService;

    /**
     * 支付接口
     * @param outTradeNo 商户订单
     * @param subject 订单名称
     * @param totalAmount 付款金额
     * @param body 商品描述
     */
    @PostMapping(value = "/alipay")
    public String alipay(String outTradeNo, String subject, String totalAmount, String body) throws AlipayApiException {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        return payService.aliPay(alipayBean);
    }
}
