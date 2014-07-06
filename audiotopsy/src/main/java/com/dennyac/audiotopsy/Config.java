package com.dennyac.audiotopsy;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;

public class Config implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
    	//Things to take care of at startup
    	// Will have to set up a connection to Hbase here, and set up all the handlers
    	String path = event.getServletContext().getRealPath("/");
    	System.out.println("This is the path" + path);
    	System.out.println(event.getClass().getName());
    			System.out.println(event.getServletContext().getServerInfo());
    			HBaseConnection.connectHbase();
    			HBaseConnection.initializeColQualMappings();
    	
    	
    }
    public void contextDestroyed(ServletContextEvent event) {
    	HBaseConnection.disconnectHbase();
        System.out.println("Closing Web App");
    }
}