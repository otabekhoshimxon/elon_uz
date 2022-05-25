package com.company.service;

import com.company.repository.CustomerRepo;
import com.company.util.ConsoleColors;
import com.company.util.Scans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ConsoleColors consoleColors;

    public void register() {
        getRegisterRequest();

    }

    public void getRegisterRequest() {
        consoleColors.print(ConsoleColors.GREEN, "ENTER  NAME ");
        String name = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(name)) {
            return;
        }
        consoleColors.print(ConsoleColors.GREEN, "ENTER  SURNAME ");
        String surname = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(surname)) {
            return;
        }
        consoleColors.print(ConsoleColors.GREEN, "ENTER  PHONE ");
        String phone = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(phone) || !customerRepo.hasNumber(phone)) {
            return;
        }
        consoleColors.print(ConsoleColors.GREEN, "ENTER  PASSWORD ");
        String password = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(password)) {
            return;
        }
        customerRepo.regist(name, surname, phone, password);
        consoleColors.print(ConsoleColors.YELLOW, "SUCCESFULLY REGISTERED ");


    }

    public boolean auth() {

        consoleColors.print(ConsoleColors.GREEN, "ENTER YOUR PHONE ");
        String phone = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(phone)) {
            return false;
        }
        consoleColors.print(ConsoleColors.GREEN, "ENTER YOUR PASSWORD ");
        String password = Scans.SCANNER_STR.nextLine().trim();
        if (Objects.isNull(password)) {
            return false;
        }

        if (checkPasswordAndPhone(phone, password)) {

            return customerRepo.authorization(phone, password);
        }

  return false;

    };


    private boolean checkPasswordAndPhone(String phone, String password) {
        boolean phoneMatches = Pattern.matches("998[0-9]{2}[0-9]{7}", phone);
        boolean passwordMatches = Pattern.matches("[a-zA-Z]*", password);

        consoleColors.print(ConsoleColors.RED, passwordMatches + " " + ConsoleColors.GREEN_BOLD + phoneMatches);
        return passwordMatches && phoneMatches;

    }
}
