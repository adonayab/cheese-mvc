package org.launchcode.cheesemvs.controllers;


import org.launchcode.cheesemvs.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Sign Up");
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, @RequestParam String verify) {
        model.addAttribute("title", "Sign Up");
        if (!user.getPassword().equals(verify)) {
            model.addAttribute("passError", "Passwords Don't match.");
            return "/user/add";
        }
        String name = user.getUserName();
        return String.format("redirect:/user/index/%s", name);
    }

    @RequestMapping(value = "index/{name}")
    public String index(Model model, @PathVariable String name) {
        model.addAttribute("title", name);
        model.addAttribute("name", name);

        return "/user/index";
    }

}
