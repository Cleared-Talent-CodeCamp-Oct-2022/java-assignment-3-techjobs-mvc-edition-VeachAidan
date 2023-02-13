package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController{

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType","all");
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "results", method = {RequestMethod.GET, RequestMethod.POST})
    public String displaySearchResults(@RequestParam String searchType, @RequestParam String searchTerm, Model model ) {

        ArrayList<Job> jobs = new ArrayList<Job>();

        if (searchTerm.strip().toLowerCase().equals("all") || searchTerm.strip().equals("")){
            jobs = JobData.findAll();
        }else {
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
        }

        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("jobs",jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}
