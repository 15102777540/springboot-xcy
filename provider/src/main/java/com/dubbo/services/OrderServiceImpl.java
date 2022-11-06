package com.dubbo.services;

import com.api.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class OrderServiceImpl implements OrderService {
    @Override
    public String getOrder(String... arr) {
        return "数据是:"+String.join(",",arr);
    }
}
