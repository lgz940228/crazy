package com.lgz.crazy.config.shiro;

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
            System.out.println("servletPath---"+servletPath);
            return super.isAccessAllowed(request, response, new String[]{servletPath});
        }
        return false;
    }
}
