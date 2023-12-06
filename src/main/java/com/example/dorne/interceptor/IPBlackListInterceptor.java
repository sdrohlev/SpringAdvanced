package com.example.dorne.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

public class IPBlackListInterceptor implements HandlerInterceptor {

    private List<String> blackListedIPAddresses = new ArrayList<>();

    public IPBlackListInterceptor() {
        blackListedIPAddresses.add("192.168.1.2");
        blackListedIPAddresses.add("192.168.2.3");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        System.out.println(ipAddress);
        return !blackListedIPAddresses.contains(ipAddress);
    }
}
