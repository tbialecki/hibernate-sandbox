package com.training.jpa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Jakub Kubrynski
 */
@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@GeneratedValue
	private Integer id;

	public Integer getId() {
		return id;
	}

}
