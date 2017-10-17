package com.caveofprogramming.spring.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.caveofprogramming.spring.web.model.Offer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author 227761
 *
 */
@Controller
public class OfferController {
	Logger logger = LoggerFactory.getLogger(OfferController.class);

	@LoadBalanced
	@Bean
	private RestTemplate restTemplate(RestTemplateBuilder builder) {

		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/createoffer", method = RequestMethod.GET)
	public String createOffer(Model model) {
		logger.debug("inside createOffer>>>>");
		model.addAttribute("offerForm", new Offer());

		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(@ModelAttribute("offerForm") Offer offerForm, Model model, BindingResult bindingResult) {
		logger.debug("inside new Offer>>>>");
		restTemplate.postForLocation(env.getProperty("offerservice.endpoint"), offerForm);

		if (bindingResult.hasErrors()) {
			return "createoffer";
		}

		return "redirect:/offercreated";
	}

	@RequestMapping(value = "/offercreated", method = RequestMethod.GET)
	public String offerCreated(Model model) {
		logger.debug("Inside offercraeted>>>>>");
		return "offercreated";

	}

	@HystrixCommand(fallbackMethod = "notShowingOffers")
	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	public String showOffers(Model model) {
		logger.debug("Instance-restTemplate>>>>>" + restTemplate);
		Offer[] offers = restTemplate.getForObject(env.getProperty("offerservice.endpoint"), Offer[].class);

		model.addAttribute("offers", offers);

		return "offers";

	}

	public String notShowingOffers(Model model) {
		logger.debug("inside fallback notShowingOffers>>>>");

		return "servicedown";
	}

}
