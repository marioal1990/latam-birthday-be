package com.latam.pax.lbb.domains.apis;

import java.io.Serializable;

import lombok.Data;

/**
 * OBJECT Poet JSON for API https://www.poemist.com/api/v1/randompoems
 * @author NB-MARAYA
 */
@Data
public class Poet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
    private String url;
}
