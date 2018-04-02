package com.slc.configure;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FilterWS
 *
 * @author Stiven Lenisa
 * @since 08/12/2015
 * Historia de Modificaciones
 * -------------------------------------------------------------
 * Autor	            Fecha           Modificacion
 * -----------------  -------------- ----------------------------
 */
public class FilterWS implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        System.out.println("servletRequest"+ servletRequest);
        System.out.println("servletResponse"+ servletResponse);
        System.out.println("filterChain"+ filterChain);
        
       HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse;
        
        httpServletResponse = (HttpServletResponse) servletResponse;
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST");
                    
                    filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void destroy()
    {

    }
}
