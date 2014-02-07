package fr.olympicinsa.riocognized.webservice.athlete;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AthleteInfoController {

	private static final String template = "I am %s %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/athlete")
	public @ResponseBody Athlete athlete(
			@RequestParam(value="name", required=false, defaultValue="bolt") String name, @RequestParam(value="surname", required=false, defaultValue="usain") String surname){
		return new Athlete(counter.incrementAndGet(),
				name, surname, String.format(template, surname, name));
	}
}