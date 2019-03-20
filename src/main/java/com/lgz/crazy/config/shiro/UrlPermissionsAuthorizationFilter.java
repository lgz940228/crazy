package com.lgz.crazy.config.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lgz on 2019/3/7.
 */
@WebFilter(dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST,DispatcherType.ASYNC,DispatcherType.ERROR})
public class UrlPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        if(request instanceof HttpServletRequest){
            String servletPath = ((HttpServletRequest) request).getServletPath();
            if(StringUtils.isNotBlank(servletPath)){
                servletPath = servletPath.replace(".do", "");
                servletPath = servletPath.replace(".html","");
            }
            System.out.println("servletPath---"+servletPath);
            String[] split = servletPath.split("/");
            if(split.length>4){
                int i = servletPath.lastIndexOf("/");
                servletPath = servletPath.substring(0,i);
            }
            return super.isAccessAllowed(request, response, new String[]{servletPath});
        }
        return false;
    }
}
