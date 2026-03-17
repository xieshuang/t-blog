package com.xsh.blog.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.xsh.blog.model.Bo.RestResponseBo;
import com.xsh.blog.utils.GsonUtils;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Value("${blog.rate-limit.enabled:true}")
    private boolean enabled;

    @Value("${blog.rate-limit.comment.permits:5}")
    private int commentPermits;

    @Value("${blog.rate-limit.comment.period:60}")
    private int commentPeriod;

    @Value("${blog.rate-limit.login.permits:10}")
    private int loginPermits;

    @Value("${blog.rate-limit.login.period:60}")
    private int loginPeriod;

    @Value("${blog.rate-limit.default.permits:60}")
    private int defaultPermits;

    @Value("${blog.rate-limit.default.period:60}")
    private int defaultPeriod;

    @PostConstruct
    public void init() {
        if (enabled) {
            rateLimiterMap.put("comment", RateLimiter.create(commentPermits * 1.0 / commentPeriod * 60));
            rateLimiterMap.put("login", RateLimiter.create(loginPermits * 1.0 / loginPeriod * 60));
            rateLimiterMap.put("default", RateLimiter.create(defaultPermits * 1.0 / defaultPeriod * 60));
            log.info("Rate limit initialized: comment={}/min, login={}/min, default={}/min",
                    commentPermits, loginPermits, defaultPermits);
        } else {
            log.info("Rate limit disabled");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!enabled) {
            return true;
        }

        String uri = request.getRequestURI();
        String limiterKey = getLimiterKey(uri);

        RateLimiter rateLimiter = rateLimiterMap.getOrDefault(limiterKey, rateLimiterMap.get("default"));

        if (!rateLimiter.tryAcquire()) {
            log.warn("Rate limit exceeded: uri={}, ip={}", uri, getClientIp(request));
            response.setStatus(429);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(GsonUtils.toJsonString(RestResponseBo.fail("请求过于频繁，请稍后再试")));
            return false;
        }

        return true;
    }

    private String getLimiterKey(String uri) {
        if (uri.contains("/comment")) {
            return "comment";
        }
        if (uri.contains("/login") || uri.contains("/auth")) {
            return "login";
        }
        return "default";
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
