package com.lgz.crazy.config.test;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by lgz on 2019/3/5.
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("myFilter process....+++++++++++++++");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
