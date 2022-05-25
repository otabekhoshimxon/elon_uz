package com.company.controller;

import com.company.service.CustomerService;
import com.company.service.PostService;
import com.company.util.ConsoleColors;
import com.company.util.Scans;
import org.postgresql.replication.PGReplicationConnectionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController  extends Thread{
    @Autowired
    private ConsoleColors consoleColors;
    @Autowired
    private CustomerService customerService;
 @Autowired
    private PostService postService;


    @Override
    public void run() {
        while (true)
        {
            showBasicMenu();
            int i = Scans.SCANNER_INT.nextInt();
            basicActionMenu(i);
        }



    }



    private void showBasicMenu()
    {
        consoleColors.print(ConsoleColors.BLUE,"1. REGISTRATION");
        consoleColors.print(ConsoleColors.BLUE,"2. AUTORIZATION");
        consoleColors.print(ConsoleColors.BLUE,"3. POST LIST");

    }

    private void basicActionMenu(int i) {

        switch (i)
        {
            case 1 -> {
              getRegister();
                break;
            }
            case 2 -> {
                getAuthCustomer();
                break;
            }
            case 3 -> {
                postService.getAllPosts();
                break;
            }
        }
    }

    private void getAuthCustomer() {
        boolean auth = customerService.auth();
        if (auth)
        {
        menu();
        }
    }

    private void getRegister() {
        customerService.register();
    }

    private void menu()
    {
        while (true)
        {
            consoleColors.print(ConsoleColors.BLUE,"1. CREATE POST");
            consoleColors.print(ConsoleColors.BLUE,"2. UPDATE POST");
            consoleColors.print(ConsoleColors.BLUE,"3. DELETE POST");
            consoleColors.print(ConsoleColors.BLUE,"4. MY POST");
            int i = Scans.SCANNER_INT.nextInt();
            getActionMenu(i);
        }

    }

    private void  getActionMenu(int i)
    {
        switch (i)
        {
            case 1 -> {
                getCreatePost();
                break;
            }
            case 2 -> {
                getUpdatePost();
                break;
            }
            case 3 -> {
                getDeletePost();
                break;
            }
            case 4 -> {
                getOwnPost();
                break;
            }



    }
}

    private void getDeletePost() {
     postService.deletePost();

    }

    private void getUpdatePost() {
        postService.update();
    }

    private void getCreatePost() {
        postService.create();

    }

    private void getOwnPost()
    {
     postService.getOwnPosts();
    }
    }
