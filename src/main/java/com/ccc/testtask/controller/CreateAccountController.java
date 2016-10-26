package com.ccc.testtask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kirill Milinevskiy on 26.10.2016.
 */
@Controller
public class CreateAccountController {

    @RequestMapping("/createAccount")
    public String createAccount() {
        return "createAccount";
    }
}
