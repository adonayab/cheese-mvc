package org.launchcode.cheesemvs.controllers;


import org.launchcode.cheesemvs.models.Category;
import org.launchcode.cheesemvs.models.Cheese;
import org.launchcode.cheesemvs.models.Menu;
import org.launchcode.cheesemvs.models.data.CategoryDao;
import org.launchcode.cheesemvs.models.data.CheeseDao;
import org.launchcode.cheesemvs.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model, Cheese cheese){
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors, @RequestParam int categoryId){

        model.addAttribute("title", "Add Cheese");

        if (errors.hasErrors()) {
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model){
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeseDao.findAll());
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId: cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(@PathVariable int cheeseId, Cheese cheese, Model model) {

        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("editCheese", cheeseDao.findOne(cheeseId));

        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId,
                                  @Valid Cheese editCheese, Errors errors, Model model,
                                  @RequestParam int cheeseRating,
                                  @RequestParam int categoryId ) {


        if (errors.hasErrors()) {
            model.addAttribute("editCheese", cheeseDao.findOne(cheeseId));
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/edit";
        }

        Cheese workCheese = cheeseDao.findOne(cheeseId);
        workCheese.setCheeseName(editCheese.getCheeseName());
        workCheese.setCheeseDescription(editCheese.getCheeseDescription());
        workCheese.setRating(cheeseRating);
        workCheese.setCategory(categoryDao.findOne(categoryId));

        cheeseDao.save(workCheese);

        return "redirect:/cheese";
    }

    @RequestMapping(value = "/index/{id}")
    public String category(Model model, @PathVariable int id) {

        Category category = categoryDao.findOne(id);

        model.addAttribute("title", "Cheeses By Category");
        model.addAttribute("cheesesByCategory", category.getCheeses());

        return "cheese/index";
    }

}
