package com.university.web.util;

import com.opensymphony.xwork2.ActionContext;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class Initializer implements ServletContextListener,
        HttpSessionListener {

    private  static final Logger logger = Logger.getLogger(Initializer.class.getSimpleName());

    public Initializer() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("-- Context Initialized -- ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
