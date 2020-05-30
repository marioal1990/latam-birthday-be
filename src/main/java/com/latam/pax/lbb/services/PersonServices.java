package com.latam.pax.lbb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latam.pax.lbb.domains.Person;
import com.latam.pax.lbb.helpers.ConstantsHelper;
import com.latam.pax.lbb.helpers.Helper;
import com.latam.pax.lbb.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private PersonRepository repository;

	public Person savePerson(Person person) {
		//OBTENER EL PRIMER NOMBRE DE LA PERSONA
		person.setName(this.getFirstPerson(person.getName()));
		//OBTENER EL PRIMER APELLIDO DE LA PERSONA
		person.setLastName(this.getFirstPerson(person.getLastName()));
		//OBTENER LA EDAD 
		person.setAge(this.helper.getAgePerson(person.getBirthday()));
		//OBTENER CUANTOS DÍAS LE FALTA PARA SU
		//CUMPLEAÑOS O POEMA SI HOY ESTÁ DE CUMPLEAÑOS
		person = this.getDaysLeftOrPoem(person);
		return this.repository.save(person);
	}
	
	public List<Person> getAllPersons(){
		return this.repository.findAll();
	}
	
	private Person getDaysLeftOrPoem(Person person) {
		if (this.helper.isToday(person.getBirthday())) {
			person.setPoem(this.helper.getPoem());
		} else {
			person.setDaysLeft(this.helper.daysLeftBirthday(person.getBirthday()).toString());
		}
		return person;
	}

	private String getFirstPerson(String name) {
		String[] splitName = name.split(ConstantsHelper.WHITE_SPACE);
		return splitName[0];
	}
}
