package com.fbs.web.vaadin.ui.auth;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class LoginScreen extends VerticalLayout
{
	private static final long serialVersionUID = 1L;
	protected static Logger logger = Logger.getLogger(LoginScreen.class.getName());
	private MyVaadinApplication app;


	public LoginScreen(MyVaadinApplication application)
	{
		this.app = application;

		// The application caption is shown in the caption bar of the
		// browser window. We set it here and not in the application
		// init() to make switching language easier.
		this.app.getMainWindow().setCaption(app.getMessage(ApplicationMessages.ApplicationTitle));

		setSizeFull();

		// Language bar in the top-right corner for selecting
		// user interface language
		final HorizontalLayout languageBar = new HorizontalLayout();
		languageBar.setHeight("50px");
		addComponent(languageBar);
		setComponentAlignment(languageBar, Alignment.TOP_RIGHT);

		// German language selection button
		Button german = new Button(app.getMessage(ApplicationMessages.LangGerman));
		// german.setIcon(new ThemeResource("img/de.gif"));
		german.addListener(new SwitchLanguage(this.app, "de"));
		german.setEnabled(!app.getLocale().getLanguage().equals("de"));
		languageBar.addComponent(german);

		// English language selection button
		Button english = new Button(app.getMessage(ApplicationMessages.LangEnglish));
		// english.setIcon(new ThemeResource("img/gb.gif"));
		english.addListener(new SwitchLanguage(this.app, "en"));
		english.setEnabled(!app.getLocale().getLanguage().equals("en"));
		languageBar.addComponent(english);

		// Turkish language selection button
		Button turk = new Button(app.getMessage(ApplicationMessages.LangTurkish));
		turk.addListener(new SwitchLanguage(this.app, "tr"));
		turk.setEnabled(!app.getLocale().getLanguage().equals("tr"));
		languageBar.addComponent(turk);

		Panel loginPanel = new Panel(app.getMessage(ApplicationMessages.Login));
		loginPanel.setWidth("400px");

		LoginForm loginForm = new LoginForm();
		loginForm.setPasswordCaption(app.getMessage(ApplicationMessages.LoginPassword));
		loginForm.setUsernameCaption(app.getMessage(ApplicationMessages.LoginUser));
		loginForm.setLoginButtonCaption(app.getMessage(ApplicationMessages.LoginButton));

		loginForm.setHeight("100px");
		loginForm.addListener(new MyLoginListener(this.app, loginForm));

		loginPanel.addComponent(loginForm);

		Button register = new Button(app.getMessage(ApplicationMessages.LoginRegisterNewUser));
		register.setStyleName("link");
		register.addListener(new RegisterUser(this.app));
		loginPanel.addComponent(register);

		Button forgot = new Button(app.getMessage(ApplicationMessages.LoginForgotPassword));
		forgot.setStyleName("link");
		loginPanel.addComponent(forgot);

		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		addComponent(footer);
	}

	private static class RegisterUser implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;


		public RegisterUser(MyVaadinApplication app)
		{
			this.app = app;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			registerNewUser();
		}


		void registerNewUser()
		{
			// Switch to the registration screen
			this.app.getViewManager().pushScreen(RegistrationScreen.class.getName(), new RegistrationScreen(this.app));
		}
	}

	// Click listener to switch the application locale
	private static class SwitchLanguage implements Button.ClickListener, Serializable
	{
		private static final long serialVersionUID = 7382925259045032373L;
		private MyVaadinApplication app;
		private String lang;


		public SwitchLanguage(MyVaadinApplication app, String lang)
		{
			this.app = app;
			this.lang = lang;
		}


		public void buttonClick(Button.ClickEvent event)
		{
			// Switch the application locale
			app.setLocale(new Locale(this.lang));

			// Switch to a new instance of the login screen to
			// rebuild it with the new locale.
			app.getViewManager().switchScreen(LoginScreen.class.getName(), new LoginScreen(app));
		}
	}

	private static class MyLoginListener implements LoginForm.LoginListener
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;
		private LoginForm loginForm;


		public MyLoginListener(MyVaadinApplication app, LoginForm loginForm)
		{
			this.app = app;
			this.loginForm = loginForm;
		}


		@Override
		public void onLogin(LoginEvent event)
		{
			String username = event.getLoginParameter("username");
			String password = event.getLoginParameter("password");

			logger.severe("Login User: " + username);

			try
			{
				this.app.login(username, password);
			}
			catch (UnknownAccountException uae)
			{
				this.loginForm.getWindow().showNotification(
				        this.app.getMessage(ApplicationMessages.SecurityUnknownAccount),
				        Notification.TYPE_ERROR_MESSAGE);
			}
			catch (IncorrectCredentialsException ice)
			{
				this.loginForm.getWindow().showNotification(
				        this.app.getMessage(ApplicationMessages.SecurityInvalidCredentials),
				        Notification.TYPE_ERROR_MESSAGE);
			}
			catch (LockedAccountException lae)
			{
				this.loginForm.getWindow()
				        .showNotification(this.app.getMessage(ApplicationMessages.SecurityAccountLocked),
				                Notification.TYPE_ERROR_MESSAGE);
			}
			catch (ExcessiveAttemptsException eae)
			{
				this.loginForm.getWindow().showNotification(
				        this.app.getMessage(ApplicationMessages.SecurityExcessiveAttempts),
				        Notification.TYPE_ERROR_MESSAGE);
			}
			catch (AuthenticationException ae)
			{
				this.loginForm.getWindow().showNotification(
				        this.app.getMessage(ApplicationMessages.SecurityAuthenticationException),
				        Notification.TYPE_ERROR_MESSAGE);
			}
			catch (Exception ex)
			{
				this.loginForm.getWindow().showNotification(
				        this.app.getMessage(ApplicationMessages.CommonException) + ex.getMessage(),
				        Notification.TYPE_ERROR_MESSAGE);
			}
		}
	}
}
