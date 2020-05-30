package com.latam.pax.lbb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.latam.pax.lbb.domains.Person;
import com.latam.pax.lbb.services.PersonServices;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private PersonServices services;

	@RequestMapping(value="/birthdays", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addPersona(@Valid @RequestBody Person person) {
		Person personOutput = this.services.savePerson(person);
		String response = new Gson().toJson(personOutput);
		return response;
	}
	
	@RequestMapping(value="/persons", method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllPersons() {
		List<Person> persons = this.services.getAllPersons();
		String response = new Gson().toJson(persons);
		return response;
	}
}
