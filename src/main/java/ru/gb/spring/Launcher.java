package ru.gb.spring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class Launcher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8189);

        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/app");

        // http://localhost:8189/app/hello
        // System.out.println("test -> " + location.toExternalForm());
        // ToDo research issue with location.toExternalForm()
        webAppContext.setWar("/Users/gainanov/IdeaProjects/untitled/spring-lesson3-mvc/target/spring-mvc.war");

        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}
