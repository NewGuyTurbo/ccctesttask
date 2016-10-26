package com.ccc.testtask.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kirill Milinevskiy on 26.10.2016.
 */
public class ManageAccountController {

    @RequestMapping("/manageAccount")
    public String createAccount() {
        return "manageAccount";
    }
}
