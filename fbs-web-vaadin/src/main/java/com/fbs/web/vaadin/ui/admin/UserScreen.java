package com.fbs.web.vaadin.ui.admin;

import java.util.List;

import com.fbs.security.model.User;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsComponentSimple;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class UserScreen extends ItemsListScreen<User>
{
    private static final long serialVersionUID = 1L;
    private static String COL_USERNAME = "userName";
    private static String COL_PASSWORD = "password";
    private static String COL_TENANTID = "tenantId";
    private static final String[] VISIBLE_COLUMNS =
    { COL_USERNAME, COL_TENANTID };
    private static final String[] VISIBLE_FIELDS = new String[]
    { COL_USERNAME, COL_TENANTID };

    public UserScreen(MyVaadinApplication app)
    {
        super(app, User.class, new DetailsComponentSimple<User>(app, new UserFormFieldFactory(app), VISIBLE_FIELDS), VISIBLE_COLUMNS);
    }

    @Override
    public User createBeanInstance()
    {
        User user;
        String password;

        user = new User();

        // password = this.app.getSecurityService().generatePassword();
        password = "123";
        user.setPasswordPlain(password);

        return user;
    }

    @Override
    public List<User> getAllBeans() throws Exception
    {
        List<User> users;
        users = this.services.getUserService().findAll();

        return users;
    }

    @Override
    public User createBean(User user) throws Exception
    {
        User result = this.services.getUserService().create(user);

        return result;
    }

    @Override
    public void updateBean(User user) throws Exception
    {
        this.services.getUserService().update(user);
    }

    @Override
    public User readBean(User user) throws Exception
    {
        User result = this.services.getUserService().read(user.getUserName());

        return result;
    }

    @Override
    public void deleteBean(User user) throws Exception
    {
        this.services.getUserService().delete(user);
    }

    @Override
    public String getColumnName(String propertyId)
    {
        if (propertyId.equals(COL_USERNAME))
            return this.app.getMessage(ApplicationMessages.UserName);

        if (propertyId.equals(COL_TENANTID))
            return this.app.getMessage(ApplicationMessages.TenantId);

        return propertyId;
    }

    private static class UserFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public UserFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            // Identify the fields by their Property ID.
            Field result = null;

            String pid = (String) propertyId;

            if (COL_USERNAME.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.UserName));
            }
            else if (COL_PASSWORD.equals(pid))
            {
                result = new PasswordField(this.app.getMessage(ApplicationMessages.UserPassword));
            }
            else if (COL_TENANTID.equals(pid))
            {
                result = new TextField("tenantId");
            }

            return result;
        }

    }

}
