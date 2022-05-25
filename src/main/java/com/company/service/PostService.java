package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.PostDTO;
import com.company.repository.CustomerRepo;
import com.company.repository.PostRepo;
import com.company.util.ConsoleColors;
import com.company.util.Scans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ConsoleColors consoleColors;

    public void getAllPosts() {

        List<PostDTO> allPost = postRepo.getAllPost();
         for (PostDTO postDTO : allPost) {
            consoleColors.print(ConsoleColors.BLUE,postDTO.getId()+
                    ConsoleColors.RED+" "+postDTO.getTitle()+" "+
                    ConsoleColors.GREEN_BOLD+" "+postDTO.getPrice()+" "+
                    ConsoleColors.YELLOW_BOLD+" "+postDTO.getDescription()+" "+
                    ConsoleColors.YELLOW_BRIGHT+" "+postDTO.getCreatedDate()+" "+
                    ConsoleColors.BLUE_BOLD+" "+postDTO.getStatusPost()+" "+
                    ConsoleColors.YELLOW_BRIGHT+" "+postDTO.getCustomerDTO().getName()+" "+
                    ConsoleColors.GREEN_BOLD+" "+postDTO.getCustomerDTO().getPhone()
            );
        }

    }

    public void create() {

        consoleColors.print(ConsoleColors.GREEN, "ENTER  POST TITLE ");
        String title = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(title)) {
            return;
        }
        consoleColors.print(ConsoleColors.GREEN, "ENTER  PRICE PRODUCT ");
        String price = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(price)) {
            return;
        }
         consoleColors.print(ConsoleColors.GREEN, "ENTER  POST DESCRIPTION ");
        String description = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(description)) {
            return;
        }
        postRepo.createPost(title,Double.parseDouble(price),description, ComponentContainer.currentCustomer.getId());

    }

    public boolean getOwnPosts() {
        List<PostDTO> ownPost = postRepo.getOwnPost();
        if (ownPost==null)
        {
            consoleColors.print(ConsoleColors.RED,"NO POSTS");
            return false;
        }
        for (PostDTO postDTO : ownPost) {
            consoleColors.print(ConsoleColors.BLUE,postDTO.getId()+
                    ConsoleColors.RED+" "+postDTO.getTitle()+" "+
                    ConsoleColors.GREEN_BOLD+" "+postDTO.getPrice()+" "+
                    ConsoleColors.YELLOW_BOLD+" "+postDTO.getDescription()+" "+
                    ConsoleColors.YELLOW_BRIGHT+" "+postDTO.getCreatedDate()+" "+
                    ConsoleColors.BLUE_BOLD+" "+postDTO.getStatusPost()
                    );
        }
        return true;


    }

    public void deletePost() {
        boolean ownPosts = getOwnPosts();
        if (!ownPosts)
        {
            consoleColors.print(ConsoleColors.GREEN, "ENTER  POST ID ");
            int i = Scans.SCANNER_INT.nextInt();
            boolean b = postRepo.delPost(i);
            if (b)
            {
                consoleColors.print(ConsoleColors.YELLOW,"SUCCESSFULLY DELETED");
            }else {
                consoleColors.print(ConsoleColors.RED,"NOT  DELETED");
            }
        }else {
            consoleColors.print(ConsoleColors.RED,"NO POSTS");
        }

    }

    public void update() {
        boolean ownPosts = getOwnPosts();
        if (!ownPosts)
        {
            consoleColors.print(ConsoleColors.RED,"NO POSTS");
            return;
        }else {
            consoleColors.print(ConsoleColors.GREEN, "ENTER  POST ID ");
            int i = Scans.SCANNER_INT.nextInt();
            boolean availablePost = postRepo.isAvailablePost(i);
            if (availablePost)
            {
                consoleColors.print(ConsoleColors.GREEN, "ENTER  POST TITLE ");
                String title = Scans.SCANNER_STR.nextLine().trim();
                if (Objects.isNull(title)) {
                    return;
                }
                consoleColors.print(ConsoleColors.GREEN, "ENTER  PRICE PRODUCT ");
                String price = Scans.SCANNER_STR.nextLine().trim();
                if (Objects.isNull(price)) {
                    return;
                }
                consoleColors.print(ConsoleColors.GREEN, "ENTER  POST DESCRIPTION ");
                String description = Scans.SCANNER_STR.nextLine().trim();
                if (Objects.isNull(description)) {
                    return;
                }
                postRepo.updateFullPost(i,title,Double.parseDouble(price),description);
            }
        }




    }
}
