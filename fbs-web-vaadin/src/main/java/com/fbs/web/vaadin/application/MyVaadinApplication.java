package com.fbs.web.vaadin.application;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fbs.datasource.Catalog;
import com.fbs.datasource.TenantContextHolder;
import com.fbs.datasource.Item;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;
import com.fbs.security.exception.*;
import com.fbs.security.service.Authentication;
import com.fbs.security.service.SecurityService;
import com.fbs.security.service.TenantService;
import com.fbs.security.service.UserRole;
import com.fbs.security.service.UserService;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.ViewManager;
import com.fbs.web.vaadin.ui.admin.AdminScreen;
import com.fbs.web.vaadin.ui.auth.LoginScreen;
import com.fbs.web.vaadin.ui.user.UserScreen;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import com.vaadin.terminal.Terminal;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@Component(value = "applicationBean")
@Scope("prototype")
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
	transient Catalog catalog;
	@Resource
	transient private SecurityService securityService;
	@Resource
	transient private TenantService tenantService;
	@Resource
	transient private UserService userService;
	@Resource
	transient private SeedService seedService;
	@Resource(name="crudServiceParty")
	transient private CrudService<Party, Integer> crudPartyService;

	public void setCrudPartyService(CrudService<Party, Integer> crudPartyService)
    {
	    this.crudPartyService = crudPartyService;
    }


	public CrudService<Party, Integer> getCrudPartyService()
    {
	    return crudPartyService;
    }


	public void setSeedService(SeedService seedService)
    {
	    this.seedService = seedService;
    }


	public SeedService getSeedService()
    {
	    return seedService;
    }


	public SecurityService getSecurityService()
	{
		return this.securityService;
	}


	public void setSecurityService(SecurityService securityService)
	{
		this.securityService = securityService;
	}


	public TenantService getTenantService()
	{
		return tenantService;
	}


	public void setTenantService(TenantService tenantService)
	{
		this.tenantService = tenantService;
	}


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
	 * Returns a localized message from the resource bundle with the current
	 * application locale.
	 **/
	public String getMessage(String key)
	{
		final ResourceBundle i18nBundle = ResourceBundle.getBundle("application", this.getLocale());

		return i18nBundle.getString(key);
	}


	public void login(String userName, String password) throws Exception
	{
		Authentication authentication;

		authentication = this.securityService.login(userName, password);

		this.setUser(authentication);

		TenantContextHolder.setTenant(authentication.getTenantId());

		// Switch to the protected view
		if (authentication.getUserRole() == UserRole.ROLE_USER)
		{
			this.getViewManager().switchScreen(UserScreen.class.getName(), new UserScreen(this));
		}
		else
		{
			this.getViewManager().switchScreen(UserScreen.class.getName(), new AdminScreen(this));
		}
		
		this.seedService.defaultFill();
		
		return;
	}


	public void logout()
	{
		logger.entering(this.getClass().getName(), "logout");

		this.close();
	}


	@Override
	@PreDestroy
	// In case the application is destroyed by the container
	public void close()
	{
		super.close();

		this.setUser(null);

		TenantContextHolder.clearTenant();

		this.getContext().removeTransactionListener(this);

		this.securityService.logout();
	}


	@Override
	public void transactionStart(Application application, Object transactionData)
	{
		logger.entering(this.getClass().getName(), "transactionStart");

		if (application == MyVaadinApplication.this)
		{
			MyVaadinApplication.currentApplication.set(this);

			Authentication auth = (Authentication) this.getUser();
			if (auth != null)
			{
				TenantContextHolder.setTenant(auth.getTenantId());
			}
			else
			{
				TenantContextHolder.clearTenant();
			}

			/*
			 * The security context holder uses the thread local pattern to
			 * store its authentication credentials. As requests may be handled
			 * by different threads, we have to update the security context
			 * holder in the beginning of each transaction.
			 */
			if (this.securityService != null)
			{
				this.securityService.rollContextIn(application.getUser());
			}
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

			TenantContextHolder.clearTenant();

			if (this.securityService != null)
			{
				this.securityService.rollContextOut();
			}
		}
	}


	@Override
	public Authentication getUser()
	{
		return (Authentication) super.getUser();
	}


	public static MyVaadinApplication getInstance()
	{
		return MyVaadinApplication.currentApplication.get();
	}


	// TODO remove it
	public String doShow()
	{
		String result;
		List<Item> goldItems = catalog.getItems();

		goldItems = catalog.getItems();

		result = "gold items: " + goldItems;

		logger.log(Level.SEVERE, "did Something: " + result);

		return result;
	}


	// TODO remove it
	public void doCreate()
	{
		catalog.createItem();

		logger.log(Level.SEVERE, "created ");
	}


	@Override
	public void terminalError(Terminal.ErrorEvent event)
	{
		// Call the default implementation.
		super.terminalError(event);

		// Some custom behavior.
		if (getMainWindow() != null)
		{
			getMainWindow().showNotification("An unchecked exception occured!", event.getThrowable().toString(),
			        Notification.TYPE_ERROR_MESSAGE);
		}
	}


	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}


	public UserService getUserService()
	{
		return userService;
	}


	public void showErrorMessage(AbstractComponent component, Exception ex)
	{
		String msg = this.getMessage(ApplicationMessages.ErrorCommon);
		int notificationType = Notification.TYPE_ERROR_MESSAGE;

		if (ex instanceof SQLException)
		{
			msg = this.getMessage(ApplicationMessages.ErrorDatabase);
		}
		else if (ex instanceof AlreadyExists)
		{
			msg = this.getMessage(ApplicationMessages.UserAlreadyExists);
		}

		getMainWindow().showNotification(msg, notificationType);
	}
}