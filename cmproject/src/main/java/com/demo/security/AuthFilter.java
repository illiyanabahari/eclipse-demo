package com.demo.security;

import java.io.IOException;

import com.demo.models.AppUser;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthFilter implements Filter 
{

    @Override
    public void doFilter(jakarta.servlet.ServletRequest req, jakarta.servlet.ServletResponse res, FilterChain chain)
            throws IOException, ServletException 
    {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        // allow public endpoints
        if (isPublicPath(path)) 
        {
            chain.doFilter(req, res);
            return;
        }

        AppUser authUser = null;
        if (request.getSession(false) != null) 
        {
            authUser = (AppUser) request.getSession(false).getAttribute("authUser");
        }

        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // authenticated
        chain.doFilter(req, res);
    }

    private boolean isPublicPath(String path) 
    {

        // pages
        if (path.equals("/login")) return true;

        // one-time init - keep true while you are setting up, later remove
        if (path.equals("/init-user")) return true;

        // static resources (adjust if you have /assets, /css, /js)
        if (path.startsWith("/css/")) return true;
        if (path.startsWith("/js/")) return true;
        if (path.startsWith("/images/")) return true;
        if (path.startsWith("/assets/")) return true;

        // allow favicon
        if (path.equals("/favicon.ico")) return true;

        return false;
    }
}
