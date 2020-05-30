package com.latam.pax.lbb.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.latam.pax.lbb.domains.Person;
import com.latam.pax.lbb.services.PersonServices;

public class PersonControllerTest {
	
	@Mock
	private PersonServices services;

	@InjectMocks
	private PersonController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void addPersonaNullTest() {
    	when(this.services.savePerson(new Person())).thenReturn(new Person());
    	this.controller.addPersona(new Person());
    	when(this.services.getAllPersons()).thenReturn(new ArrayList<Person>());
    	assertThat(this.controller.addPersona(new Person())).isNotNull();
    }
    
    @Test
    public void addPersonaDataTest() {
    	Person person = new Person();
    	person.setName("Mario");
    	person.setLastName("Araya");
    	person.setBirthday("20/03/1990");
    	when(this.services.savePerson(person)).thenReturn(new Person());
    	this.controller.addPersona(person);
    	List<Person> list = new ArrayList<Person>();
    	person.setAge(30);
    	person.setDaysLeft("389");
    	person.setId(1L);
    	person.setPoem("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard "
    			+ "dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a "
    			+ "type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, "
    			+ "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing "
    			+ "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    	list.add(person);
    	when(this.services.getAllPersons()).thenReturn(list);
    	assertThat(this.controller.addPersona(person)).isNotNull();
    }
    
    @Test
    public void getAllPersonsTest() {
    	when(this.services.getAllPersons()).thenReturn(new ArrayList<Person>());
    	assertThat(this.controller.getAllPersons()).isNotEmpty();
    }
}
