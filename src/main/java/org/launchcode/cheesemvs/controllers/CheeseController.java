package org.launchcode.cheesemvs.controllers;


import org.launchcode.cheesemvs.models.Cheese;
import org.launchcode.cheesemvs.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("cheese")
public class CheeseController {


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model, @ModelAttribute Cheese newCheese){


        CheeseData.add(newCheese);
        return "redirect:";
//        model.addAttribute("title", "Add Cheese");
//        String errorMessage = "Invalid Cheese Name or Description !";
//        model.addAttribute("errorMessage", errorMessage);
//        return "cheese/add";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model){
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        if (cheeseIds.length == 0) {
            return "redirect:";
        }
        for (int aCheeseId: cheeseIds) {
            CheeseData.remove(aCheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("editCheeses", CheeseData.getById(cheeseId));
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int cheeseId, @RequestParam String name, @RequestParam String description) {
        Cheese cheeseToBeEdited = CheeseData.getById(cheeseId);
        cheeseToBeEdited.setCheeseName(name);
        cheeseToBeEdited.setCheeseDescription(description);
        return "redirect:";
    }



}
