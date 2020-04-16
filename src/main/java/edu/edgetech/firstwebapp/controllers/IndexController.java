package edu.edgetech.firstwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 *      IndexController
 *          We will access all endpoints listed in this class with the prefix /home
 */
@Controller
@RequestMapping("/home")            //  this will add home to the start of all URL endpoints
public class IndexController {
    /**
     *      index
     *          endpoint will be: /home/
     *
     *          Our basic home page, in which we provide a few interesting pieces of data
     *              to add to the web page
     *
     * @param model     model will hold the data that goes to the next page.
     *                          name, num, date and an image string
     * @return          returns the name of the page to merge with the data in the model object
     *                  this will be the index page
     *
     */
    @RequestMapping("/")         //  so that means this code will be reached by /home/
    public String index(Model model) {
        model.addAttribute("name", "April");            //  create a value to be passed to the web page
        model.addAttribute("num", 42);                          //  we can create numbers
        model.addAttribute("date", new Date());                 //  we can add in dates
        model.addAttribute("image", "/images/EdgeTech.png");    //  we can even send over the address of an image
        return "index";
    }

    @RequestMapping("/tester")         //  so that means this code will be reached by /home/
    public String indexnew(Model model) {
        model.addAttribute("name", "Hello Friends");            //  create a value to be passed to the web page
        model.addAttribute("num", 42);                          //  we can create numbers
        model.addAttribute("date", new Date());                 //  we can add in dates
        model.addAttribute("image", "/images/EdgeTech.png");    //  we can even send over the address of an image
        return "tester";
    }

    /**
     *      showPage2
     *          endpoint will be: /home/page2
     *
     *          We have created a dummy page just to show how simple an endpoint can be
     *
     * @param model     model will hold the data that goes to the next page. Which we are not taking advantage of
     * @return          returns the name of the page to merge with the data in the model object
     *                  this will be the page2 page
     *
     */
    @RequestMapping("/page2")                   // this code will be reached by /home/page2
    public String showPage2(Model model) {
        return "page2";                                 //  not much to see here. Just display the page no data
    }

    /**
     *      edgeTechStudent
     *          endpoint will be: /home/me
     *
     *          We have created a personal page for the Edge Tech student
     *          There are several pieces of data we will pass to the web page through the model object
     *                  student name, home town and state and today's date
     *
     * @param model     model will hold the data that goes to the next page
     * @return          returns the name of the page to merge with the data in the model object
     *                  this will be the whoAmI page
     *
     */
    @RequestMapping("/me")                      //  this code will be reached by /home/me
    public String edgeTechStudent(Model model) {
        model.addAttribute("name", "Ima Java Nut");     //  let's end over our name
        model.addAttribute("homeTown", "Arlington");    //  where we are from
        model.addAttribute("state", "Texas");
        model.addAttribute("date", new Date());         //  what is today's date
        return "whoAmI";
    }
}