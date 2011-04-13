package com.fbs.web.vaadin;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.ViewManager;
import com.fbs.web.vaadin.ui.auth.LoginScreen;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
public class MyVaadinApplication extends Application
{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(MyVaadinApplication.class.getName());

    /* Internationalized strings. */
    private ResourceBundle i18nBundle;

    /* View manager that handlers different screens in the UI. */
    private ViewManager viewManager;

    // UI Components
    private Window mainWindow;


    @Override
    public void init()
    {
        logger.entering(this.getClass().getName(), "init");

        // Initialize the view manager for the main window
        final ResourceBundle i18n = ResourceBundle.getBundle("application", getLocale());

        this.mainWindow = new Window(i18n.getString(ApplicationMessages.ApplicationTitle));
        setMainWindow(mainWindow);

        viewManager = new ViewManager(mainWindow);

        // Create the login screen
        viewManager.switchScreen(LoginScreen.class.getName(), new LoginScreen(this));

        logger.exiting(this.getClass().getName(), "init");
    }


    @Override
    public void setLocale(Locale locale)
    {
        super.setLocale(locale);

        try
        {
            i18nBundle = ResourceBundle.getBundle("application", getLocale());
        }
        catch (MissingResourceException mre)
        {
            // Log the exception
            logger.log(Level.SEVERE, "Resource not found", mre);
        }
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
        return i18nBundle.getString(key);
    }
    
    public void logout()
    {
        this.getMainWindow().getApplication().close();
    }

}
