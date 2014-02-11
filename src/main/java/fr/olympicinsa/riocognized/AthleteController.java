package fr.olympicinsa.riocognized;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */

@Controller
@RequestMapping("/athlete")
public class AthleteController {

	private static final String template = "I am %s %s!";
	private final AtomicLong counter = new AtomicLong();

    
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Athlete athlete(
			@RequestParam(value="name", required=false, defaultValue="bolt") String name, @RequestParam(value="surname", required=false, defaultValue="usain") String surname){
		return new Athlete(counter.incrementAndGet(),
				name, surname, String.format(template, surname, name));
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAthlete(@PathVariable long id) {

	}

	@RequestMapping(value="{id}",method = RequestMethod.PUT)
	@ResponseBody
	public void putComputer(@PathVariable long id, @RequestBody Athlete athlete) {
	  athlete.setId(id);

	}
}
