package com.latam.pax.lbb.domains.apis;

import java.io.Serializable;

import lombok.Data;

/**
 * OBJECT Poem JSON for API https://www.poemist.com/api/v1/randompoems
 * @author NB-MARAYA
 */
@Data
public class Poem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
    private String content;
    private String url;
    private Poet poet;
}
