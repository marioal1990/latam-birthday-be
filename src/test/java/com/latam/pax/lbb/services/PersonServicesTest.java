package com.latam.pax.lbb.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.latam.pax.lbb.domains.Person;
import com.latam.pax.lbb.domains.apis.Poem;
import com.latam.pax.lbb.helpers.Helper;
import com.latam.pax.lbb.repositories.PersonRepository;

public class PersonServicesTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private Helper helper;
	
	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonServices services;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void addPersonServicesNotSameDayTest() {
		Person person = new Person();
    	person.setName("Mario");
    	person.setLastName("Araya");
    	person.setBirthday("20/03/1990");
		when(this.repository.save(person)).thenReturn(this.getPersonOutput(person));
		assertThat(this.services.savePerson(person)).isNotNull();
	}

	@Test
	public void addPersonServicesSameDayTest() {
		Person person = new Person();
    	person.setName("Mario");
    	person.setLastName("Araya");
    	person.setBirthday("29/05/1990");
    	when(this.helper.isToday(person.getBirthday())).thenReturn(true);
    	when(this.helper.getPoem()).thenReturn(ArgumentMatchers.anyString());
    	when(this.repository.save(person)).thenReturn(this.getPersonOutput(person));
		assertThat(this.services.savePerson(person)).isNull();
	}
	
	@Test
	public void getAllPersonsTest() {
		when(this.repository.findAll()).thenReturn(this.getAllPersons());
		assertThat(this.services.getAllPersons()).isNotNull();
	}
	
	private List<Person> getAllPersons(){
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person();
    	person.setName("Mario");
    	person.setLastName("Araya");
    	person.setBirthday("29/05/1990");
		persons.add(this.getPersonOutput(person));
		return persons;
	}
	
	private Person getPersonOutput(Person person) {
		person.setId(1L);
		person.setAge(30);
		person.setDaysLeft("365");
		person.setPoem(this.getPoems()[0].getContent());
		return person;
	}
	
	private Poem[] getPoems() {
		Poem poem = new Poem();
		poem.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard "
    			+ "dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a "
    			+ "type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, "
    			+ "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing "
    			+ "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		Poem[] poems = { poem };
		return poems;
	}
}
