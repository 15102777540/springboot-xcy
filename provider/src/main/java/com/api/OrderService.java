package com.api;

import org.apache.dubbo.config.annotation.DubboService;


public interface OrderService {
    String getOrder(String ...arr);
}
