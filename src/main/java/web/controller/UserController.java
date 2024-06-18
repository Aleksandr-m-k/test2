package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;



@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDao userDAO;

    @Autowired
    public UserController(UserDao userDAO) {
        this.userDAO = userDAO;
    }


    @GetMapping()
// получим всех людей из DAO и перададим на отображение в представленние
    public String index(Model model) {
        model.addAttribute("users", userDAO.getUsers());
        return "users";
    }


    @GetMapping("/{id}")
    // получим одного человека по id из DAO и перададим на отображение в представленние
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userById", userDAO.getUserById(id));
        return "userById";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        System.out.println("sdelano");
        return "new";

    }

    @PostMapping()
    public String create(@ModelAttribute("newUser") User user) {
        System.out.println("sozdan user");
        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.getUserById(id));
        System.out.println("izmenenie user");
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAO.update(id, user);
        System.out.println("izmenen user");
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userDAO.deleteUser(id);
        return "redirect:/users";
    }
}

