package com.dzero.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * IP
 *
 * @author dzero
 * @date 2020/7/20 14:14
 */
public class IpUtil {

    /**
     * 获取域名（不包含端口）
     * @param request 请求头
     * @return 域名(无端口号)
     */
    public static String getDomainNameExitPort(HttpServletRequest request) {
        //获取域名
        StringBuffer url = request.getRequestURL();
        String domainName =
                url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        if (domainName.contains(":")) {
            int i = domainName.length() - domainName.indexOf(":");
            domainName = domainName.substring(0, (i - 2));
        }
        String tempDomainName = domainName.substring(domainName.length() - 1);
        if (!tempDomainName.contains("/")) {
            domainName = domainName + "/";
        }
        return domainName;
    }

    /**
     * @param request 请求头
     * @return 域名(带端口号)
     */
    public static String getDomainNam(HttpServletRequest request) {
        //获取域名
        return "http://" + request.getLocalAddr() + ":" + request.getLocalPort();
    }


    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip;
        ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }


}
