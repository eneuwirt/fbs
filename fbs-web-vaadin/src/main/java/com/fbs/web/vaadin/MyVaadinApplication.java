package com.fbs.web.vaadin;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.ViewManager;
import com.fbs.web.vaadin.ui.auth.LoginScreen;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@Component(value = "vaadinApp")
@Scope(value = "session")
public class MyVaadinApplication extends Application implements ApplicationContext.TransactionListener
{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(MyVaadinApplication.class.getName());
    private static ThreadLocal<MyVaadinApplication> currentApplication = new ThreadLocal<MyVaadinApplication>();

    /* View manager that handlers different screens in the UI. */
    private ViewManager viewManager;

    // UI Components
    private Window mainWindow;
    
    @Resource
	DemoService demoService;



    @Override
    public void init()
    {
        logger.entering(this.getClass().getName(), "init");

        this.getContext().addTransactionListener(this);

        this.mainWindow = new Window(this.getMessage(ApplicationMessages.ApplicationTitle));
        
        this.setMainWindow(mainWindow);

        this.viewManager = new ViewManager(mainWindow);

        // Create the login screen
        viewManager.switchScreen(LoginScreen.class.getName(), new LoginScreen(this));

        logger.exiting(this.getClass().getName(), "init");
    }
    

    public ViewManager getViewManager()
    {
        return viewManager;
    }


    /**
     * Returns a localized message from the resource bundle with the current application locale.
     **/
    public String getMessage(String key)
    {
        final ResourceBundle  i18nBundle = ResourceBundle.getBundle("application", this.getLocale());
        
        return i18nBundle.getString(key);
    }


    public void login(String username, String password) throws AuthenticationException
    {
        // Authenticate the subject by passing the user name and password token into the login method
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // ”Remember Me” built-in, just do this:
        token.setRememberMe(true);

        this.getCurrentUser().login(token);
    }


    public void logout()
    {
        logger.entering(this.getClass().getName(), "logout");
        this.getMainWindow().getApplication().close();

        this.getCurrentUser().logout();
    }


    @Override
    public void transactionStart(Application application, Object transactionData)
    {
        logger.entering(this.getClass().getName(), "transactionStart");

        if (application == MyVaadinApplication.this)
        {
            MyVaadinApplication.currentApplication.set(this);
        }

        logger.exiting(this.getClass().getName(), "transactionStart");
    }


    @Override
    public void transactionEnd(Application application, Object transactionData)
    {
        if (application == MyVaadinApplication.this)
        {
            MyVaadinApplication.currentApplication.set(null);

            MyVaadinApplication.currentApplication.remove();
        }
    }


    // With most of Shiro, you'll always want to make sure you're working with the currently executing user,
    // referred to as the subject
    public Subject getCurrentUser()
    {
        return SecurityUtils.getSubject();
    }


    public static MyVaadinApplication getInstance()
    {
        return MyVaadinApplication.currentApplication.get();
    }
    
    public void doSomething()
    {
    	this.demoService.doSomething();
    }
}
