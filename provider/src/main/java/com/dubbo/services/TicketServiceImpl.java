package com.dubbo.services;

import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "给你票";
    }
}
