package com.fbs.security.service.impl;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.fbs.security.service.SecurityService;

public class SecurityServiceShiroImpl implements SecurityService, Serializable
{
    private static final long serialVersionUID = 1L;


    @Override
    public void login(String userName, String password) throws Exception
    {
        Subject subject = SecurityUtils.getSubject();

        // Authenticate the subject by passing the user name and password token
        // into the login method
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        // ”Remember Me” built-in, just do this:
        token.setRememberMe(true);

        subject.login(token);

        // security reason, clear token if someone uses memory-dumps
        token.clear();
    }


    @Override
    public String getTenant(String userName)
    {
        String tenantId = "0";

        Subject subject = SecurityUtils.getSubject();

        String username = (String) subject.getPrincipal();

        if (username.equals("demo"))
        {
            tenantId = "1";
        }
        else if (username.equals("view"))
        {
            tenantId = "2";
        }

        return tenantId;
    }


    @Override
    public void logout()
    {
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated())
        {
            subject.logout();
        }
    }

}
