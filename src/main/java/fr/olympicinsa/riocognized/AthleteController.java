package fr.olympicinsa.riocognized;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import fr.olympicinsa.riocognized.model.*;
import fr.olympicinsa.riocognized.repository.*;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;

/**
 * Handles requests for the application home page.
 */
@Controller
@ComponentScan("fr.olympicinsa.riocognized.repository")
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @RequestMapping(value = "/api/athletes", method = RequestMethod.GET)
    public @ResponseBody
    List <Athlete> listAthleteJson(ModelMap model) throws JSONException {
        return athleteRepository.findAll();  
    }

    @RequestMapping(value = "/api/athletes/name/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Athlete> listAthleteByNameJson(ModelMap model, @PathVariable("name") String name) throws JSONException {
        return athleteRepository.findByNameStartingWith(name.toLowerCase());
    }
    
    @RequestMapping(value = "/api/athletes/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Athlete athleteByIdJson(ModelMap model, @PathVariable("id") long id) throws JSONException {
        return athleteRepository.find(id);
    }

    @RequestMapping(value = "/api/athletes/sport/{sport}", method = RequestMethod.GET)
    public @ResponseBody
    List<Athlete> listAthleteBySportJson(ModelMap model, @PathVariable("sport") String sport) throws JSONException {
        return athleteRepository.findBySportStartingWith(sport.toLowerCase());
    }
    @RequestMapping(value = "/api/athletes/country/{country}", method = RequestMethod.GET)
    public @ResponseBody
    List<Athlete> listAthleteByCountryJson(ModelMap model, @PathVariable("country") String country) throws JSONException {
        return athleteRepository.findByCountryStartingWith(country.toLowerCase());
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("athlete", new Athlete());
        model.addAttribute("athletes", athleteRepository.findAll());
        return "athlete";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("athlete") Athlete athlete, BindingResult result) {
        athleteRepository.save(athlete);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") Long athleteId) {
        athleteRepository.delete(athleteRepository.findOne(athleteId));
        return "redirect:/";
    }
}
