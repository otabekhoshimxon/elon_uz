package com.company;

import com.company.config.Config;
import com.company.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        MainController maincontroller = (MainController) context.getBean("mainController");
        maincontroller.start();
    }
}