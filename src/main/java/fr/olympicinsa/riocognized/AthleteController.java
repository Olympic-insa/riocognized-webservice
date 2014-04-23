package fr.olympicinsa.riocognized;

import fr.olympicinsa.riocognized.exception.MyExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONException;

import java.io.IOException;

import fr.olympicinsa.riocognized.model.*;
import fr.olympicinsa.riocognized.repository.*;
import fr.olympicinsa.riocognized.service.AthleteService;
import fr.olympicinsa.riocognized.service.CountryService;

import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
@ComponentScan("fr.olympicinsa.riocognized.repository")
public class AthleteController extends MyExceptionHandler {

    @Autowired
    private AthleteService athleteService;
    @Autowired
    private CountryService countryService;
    /* API GET Method */

    @RequestMapping(value = "/api/athletes/full", method = RequestMethod.GET)
    public @ResponseBody
    List<Athlete> listAthleteJson(ModelMap model, @RequestParam(value = "full", required = false, defaultValue = "0") int full) throws JSONException {
        if (full == 1) {
            return athleteService.findAllOrderByName();
        }

        return athleteService.findAllOrderByName();
    }

    @RequestMapping(value = "/api/athletes", method = RequestMethod.GET)
    public @ResponseBody
    String listDetailAthleteJson(ModelMap model, @RequestParam Map<String,String> param) throws JSONException {
        JSONArray athleteArray = new JSONArray();
        List<Athlete> athleteList = param.isEmpty() ? athleteService.findAllOrderByName() : athleteService.findByDescription(param);
        for (Athlete athlete : athleteList) {
            JSONObject countryJSON = new JSONObject();
            countryJSON.put("id",athlete.getCountry().getId());
            countryJSON.put("name",athlete.getCountry().getName());
            JSONObject athleteJSON = new JSONObject();
            athleteJSON.put("id", athlete.getId());
            athleteJSON.put("name", athlete.getName());
            athleteJSON.put("surname", athlete.getSurname());
            athleteJSON.put("country", countryJSON);
            athleteJSON.put("sport", athlete.getSport().getId());
            athleteJSON.put("image_url", athlete.getURL());
            athleteArray.put(athleteJSON);
        }
        return athleteArray.toString();
    }

    @RequestMapping(value = "/api/athletes/name={name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Athlete> listAthleteByNameJson(ModelMap model, @PathVariable("name") String name) throws JSONException {
        return athleteService.findByNameStartingWith(name.toLowerCase());
    }
    
    @RequestMapping(value = "/api/athletes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Athlete athleteByIdJson(ModelMap model, @PathVariable("id") long id) throws JSONException {
        return athleteService.findOne(id);
    }

    @RequestMapping(value = "/api/athletes/sport={sport}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Athlete> listAthleteBySportJson(ModelMap model, @PathVariable("sport") String sport) throws JSONException {
        return athleteService.findBySportStartingWith(sport.toLowerCase());
    }


    @RequestMapping(value = "/api/sports", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<String> listSportJson(ModelMap model) throws JSONException {
        return athleteService.findBySport();
    }

    @RequestMapping(value = "/api/countries", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Country> listCountryJson(ModelMap model) throws JSONException {
        List<Country> id = athleteService.findByCountry();
        return countryService.findById(id);
    }

    @RequestMapping(value = "/api/athletes/country={country}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Athlete> listAthleteByCountryJson(ModelMap model, @PathVariable("country") String country) throws JSONException {
        return athleteService.findByCountryStartingWith(country.toLowerCase());
    }

    /* Web GET Method */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("athlete", new Athlete());
        model.addAttribute("athletes", athleteService.findAllOrderByName());
        return "athlete";
    }

    /* Web POST Method */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("athlete") Athlete athlete,
        @RequestParam("file") MultipartFile file, BindingResult result) {

        Image image = new Image();
        try {
            byte[] blob = IOUtils.toByteArray(file.getInputStream());
            image.setFilename(file.getOriginalFilename());
            image.setContent(blob);
            image.setName(athlete.getName());
            image.setDescription(athlete.getContent());
            image.setContentType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        athlete.setImage(image);
        athleteService.save(athlete);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") Long athleteId) {
        athleteService.delete(athleteService.findOne(athleteId));
        return "redirect:/";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage() {
		return "logout";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
}
