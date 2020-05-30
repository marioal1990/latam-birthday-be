package com.latam.pax.lbb.controllers.helpers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.latam.pax.lbb.helpers.Helper;

@TestPropertySource(properties = {
	    "api.poems=https://www.poemist.com/api/v1/randompoems",
	})
public class HelperTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private Helper helper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isTodayFalseTest() {
    	assertThat(this.helper.isToday("28/05/2020")).isFalse();
    }
    
    @Test
    public void isTodayTrueTest() {
    	assertThat(this.helper.isToday("29/05/2020")).isTrue();
    }
    
    @Test
    public void daysLeftBirthdayBTest() {
    	assertThat(this.helper.daysLeftBirthday("28/05/2020")).isNotNull();
    }
    
    @Test
    public void daysLeftBirthdayCTest() {
    	assertThat(this.helper.daysLeftBirthday("29/05/2020")).isNotNull();
    }
    
    @Test
    public void daysLeftBirthdayDTest() {
    	assertThat(this.helper.daysLeftBirthday("30/05/2020")).isNotNull();
    }
    
    @Test
    public void getAgePersonTest() {
    	assertThat(this.helper.getAgePerson("28/05/2020")).isEqualTo(0);
    }
    
    @Test
    public void getPoemTest() {
    	assertThat(this.helper.getPoem()).isNotNull();
    }
}
