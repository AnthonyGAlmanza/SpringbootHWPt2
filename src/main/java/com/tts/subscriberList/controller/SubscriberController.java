package com.tts.subscriberList.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.subscriberList.model.Subscriber;
import com.tts.subscriberList.repository.SubscriberRepository;

@Controller
public class SubscriberController {
	
	@Autowired
	private SubscriberRepository subscriberRepository;

	@GetMapping(value = "/")
	public String index(Subscriber subscriber) {
		
		return "subscriber/index";
	}

//	@GetMapping(value = "/subscriber")
//	List<Subscriber> getAllSubscribers(Model model) {	
//		subList = (List<Subscriber>) subscriberRepository.findAll();
//	}
	public List<Subscriber> findAll(){
		return (List<Subscriber>) subscriberRepository.findAll();
	}
	
	@GetMapping(value = "/subscribers")
	public String getAllSubscribers(Subscriber subscriber, Model model) {
		ArrayList<Subscriber> subList = new ArrayList<>();
		subList = (ArrayList<Subscriber>) subscriberRepository.findAll();
		model.addAttribute("subList", subList);
		return "subscriber/subscribers";
	}
	
	@PostMapping(value = "/")
	public String addNewSubsriber(Subscriber subscriber, Model model) {
		Subscriber subscriberToAdd = new Subscriber(subscriber.getFirstName(), subscriber.getLastName(), subscriber.getUsername());
		subscriberRepository.save(subscriberToAdd);
		model.addAttribute("firstName", subscriberToAdd.getFirstName());
		model.addAttribute("lastName", subscriberToAdd.getLastName());
		model.addAttribute("username", subscriberToAdd.getUsername());
		return "subscriber/result";		
	}
	
	
	
	
	
}
