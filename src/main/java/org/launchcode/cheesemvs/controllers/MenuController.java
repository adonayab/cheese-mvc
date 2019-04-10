package org.launchcode.cheesemvs.controllers;

import org.launchcode.cheesemvs.models.Cheese;
import org.launchcode.cheesemvs.models.Menu;
import org.launchcode.cheesemvs.models.data.CheeseDao;
import org.launchcode.cheesemvs.models.data.MenuDao;
import org.launchcode.cheesemvs.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");
        return "menu/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a Menu");
        model.addAttribute("menu", new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenuForm(Model model, @ModelAttribute @Valid Menu menu, Errors errors){

        model.addAttribute("title", "Add Menu");
        if (errors.hasErrors()) {
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int id) {

        Menu menu = menuDao.findOne(id);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());
        return "menu/view";
    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int id) {

        Menu menu = menuDao.findOne(id);
        model.addAttribute("title", "Add item to menu: " + menu.getName());
        Iterable<Cheese> cheeses = cheeseDao.findAll();
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeses);
        model.addAttribute("form", form);

        return "menu/add-item";
    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.POST)
    public String processAddItem(@ModelAttribute @Valid AddMenuItemForm form, Errors errors,
                          @RequestParam int cheeseId,
                          @RequestParam int menuId, Model model, @PathVariable int id) {

        if (errors.hasErrors()) {
            Menu menu = menuDao.findOne(menuId);
            model.addAttribute("title", "Add item to menu: " + menu.getName());
            return "menu/add-item";
        }

        Menu menu = menuDao.findOne(menuId);
        menu.addItem(cheeseDao.findOne(cheeseId));
        menuDao.save(menu);

        return "redirect:/menu/view/" + menu.getId();
    }

}
