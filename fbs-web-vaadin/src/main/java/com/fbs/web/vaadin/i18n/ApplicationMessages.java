package com.fbs.web.vaadin.i18n;

import java.io.Serializable;

public interface ApplicationMessages extends Serializable
{
    // Application
    public static String ApplicationTitle = "Application.Title";
    
    public static String CommonException = "Common.Exception";

    // Login Form
    public static String Login = "Login";
    public static String LoginButton = "Login.Button";
    public static String LoginForgotPassword = "Login.ForgotPassword";
    public static String LoginPassword = "Login.Password";
    public static String LoginRegisterNewUser = "Login.RegisterNewUser";
    public static String LoginUser = "Login.User";

    // Language List
    public static String LangEnglish = "Lang.English";
    public static String LangGerman = "Lang.German";
    public static String LangTurkish = "Lang.Turkish";
    
    //Register Form
    public static String RegisterTitle = "Register.Title";
    public static String RegisterNewUser = "Register.NewUser";
    
    public static String SecurityAccountLocked = "Security.AccountLocked";
    public static String SecurityAuthenticationException = "Security.AuthenticationException";
    public static String SecurityExcessiveAttempts = "Security.ExcessiveAttempts";
    public static String SecurityInvalidCredentials = "Security.InvalidCredentials";
	public static String SecurityUnknownAccount = "Security.UnknownAccount";

}
