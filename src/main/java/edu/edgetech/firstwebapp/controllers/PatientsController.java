package edu.edgetech.firstwebapp.controllers;

import edu.edgetech.firstwebapp.models.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patients")            //  this will add patients to the start of all URL endpoints
public class PatientsController {

    //  this is going to be our database.
    //  the constructor for our controller will load this List on startup
    private List<Patient> patients;

    //  Constructor for our patients page Controller
    //  this Constructor is called by Spring and will read our 'Database' to have ready for us as needed
    public PatientsController() {
        patients = Patient.loadRecords("src/patients.csv");
    }

    /**
     *      index
     *          endpoint will be: /patients/
     *
     *          This is the home page for the patient web pages
     *          initially it will display all patients in our roster
     *
     * @param model     model will hold the data that goes to the next page
     * @return          returns the name of the page to merge with the data in the model object
     *                  this will be the patientList page
     */
    @RequestMapping("/")                                    //  this code will be reached by /patients/
    public String index(Model model) {
        //  this means as long as we provide a list of patients
        //        //  to the "patients" attribute
        model.addAttribute("patients", patients);

        //  the the patientList page will be happy to display it
        return "patientList";
    }

    /**
     *      search
     *          endpoint will be: /patients/search
     *          This method is a POST method. We only get here from the user pressing the search button
     *
     *          this endpoint will receive the fields in the search fields.
     *          we can use them to identify matching patients
     *
     * @param name      we get the name from the search field
     * @param model     model will hold the data that goes to the next page
     * @return          returns the name of the page to merge with the data in the model object
     *                  this will be the patientList page
     *
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)     //  this code will be reached by /patients/search
    public String search(@RequestParam String name, @RequestParam String temp, Model model) {
        List<Patient> sublist = patients;

        if (name != null && name.length() > 0) {
            sublist = findByName(sublist, name);
        }
        if (temp != null && temp.length() > 0) {
            if (Integer.parseInt(temp) > 0) {
                sublist = findByTemp(sublist, Integer.parseInt(temp));
            }
        }

        //  notice we are adding our list of found patients to the "patients" attribute
        model.addAttribute("patients", sublist);

        //  and now our patientList page will faithfully display this sub list of our patients
        return "patientList";
    }

    /**
     *      findByName
     *          look for patient names (first or last) that contain the string passed to the Controller
     *
     * @param list      list of patients to search through
     * @param name      the name from the search field
     *
     */
    public List<Patient> findByName(List<Patient> list, String name) {

        //  shortList will contain the patients whose names were matched from the web page
        List<Patient> shortList = new ArrayList<>();

        name = name.toLowerCase();          //  lower case the name to standardize the format (all lowercase)
        //  look at all patients. One at a time
        for (Patient patient : list) {
            //  check to see if the first or last name contains the name given
            //  we will convert the first and last names to lowercase since contains does a case sensitive compare
            if (patient.getLastName().toLowerCase().contains(name) ||
                patient.getFirstName().toLowerCase().contains(name)) {
                shortList.add(patient);
            }
        }
        //  return the list of patients we found matching the name provided
        return shortList;
    }

    public List<Patient> findByTemp(List<Patient> list, int temp) {

        //  shortList will contain the patients whose names were matched from the web page
        List<Patient> shortList = new ArrayList<>();

        //  look at all patients. One at a time
        for (Patient patient : list) {
            //  check to see if the first or last name contains the name given
            //  we will convert the first and last names to lowercase since contains does a case sensitive compare
            if (patient.getTemperature() >= temp) {
                shortList.add(patient);
            }
        }
        //  return the list of patients we found matching the name provided
        return shortList;
    }
}
