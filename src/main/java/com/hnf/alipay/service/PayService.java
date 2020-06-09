package com.hnf.alipay.service;

import com.alipay.api.AlipayApiException;
import com.hnf.alipay.domain.AlipayBean;

/*支付服务*/
public interface PayService {
    /*支付宝*/
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}
