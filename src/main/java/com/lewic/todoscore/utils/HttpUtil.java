package com.lewic.todoscore.utils;

import com.lewic.todoscore.common.ResponseCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lewic
 * @since 2020/3/14 17:15
 */

public class HttpUtil {

    public static void setResponse(HttpServletResponse response, ResponseCode responseCode) throws IOException {
        response.sendError(responseCode.getCode(), responseCode.getMessage());
    }

    /**
     * 获取客户端真实ip地址
     */
    public static String getReqIp(HttpServletRequest request) {
        // 获取距离服务器最远的那个ip
        String ip = request.getHeader("x-forwarded-for");
        if (ipIsNullOrEmpty(ip)) {
            // apache http服务代理加上的ip
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ipIsNullOrEmpty(ip)) {
            // weblogic插件加上的头
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipIsNullOrEmpty(ip)) {
            // 真实ip
            ip = request.getHeader("X-Real-IP");
        }
        if (ipIsNullOrEmpty(ip)) {
            // 最后真实的ip
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 当前ip是否为空
     */
    private static boolean ipIsNullOrEmpty(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }

    /**
     * 同getReqIp()
     */
    public static String getVisitorIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ipAddress;
        if (realIp == null) {
            if (forwarded == null) {
                ipAddress = remoteAddr;
            } else {
                ipAddress = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ipAddress = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ipAddress = realIp + "/" + forwarded;
            }
        }
        return ipAddress;
    }

}
