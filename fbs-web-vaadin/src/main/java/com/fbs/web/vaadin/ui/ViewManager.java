package com.fbs.web.vaadin.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Stack;

import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

public class ViewManager implements Serializable
{
    private static final long serialVersionUID = 1L;
    private HashMap<String, Layout> views = new HashMap<String, Layout>();
    private Stack<Layout> screenStack = new Stack<Layout>();
    private Panel window;


    public ViewManager(Panel window)
    {
        this.window = window;
    }


    public void init()
    {

    }


    /**
     * Switches current screen to the given screen.
     * 
     * @param screenType
     *            The screen to switch to.
     * @param newScreen
     *            If not null, the existing screen is replaced with the given screen.
     */
    public void switchScreen(String viewName, Layout newView)
    {
        Layout view;

        if (newView != null)
        {
            view = newView;
            
            views.put(viewName, newView);
        }
        else
        {
            view = views.get("viewname");
        }
        
        window.setContent(view);
    }


    /**
     * Switches to the given screen and pushes the current screen to stack. The pushed screen can be switched back to by
     * calling popScreen().
     * 
     * @param screenType
     *            Screen to switch to.
     */
    public void pushScreen(String viewName, Layout newView)
    {
        screenStack.push((Layout) window.getContent());
        
        switchScreen(viewName, newView);
    }


    /**
     * Switches back to the topmost screen in the screen stack.
     */
    public void popScreen()
    {
        window.setContent(screenStack.pop());
    }


    public Panel getWindow()
    {
        return window;
    }
}