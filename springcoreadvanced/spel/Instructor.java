package com.ust1.spring.springcoreadvanced.spel;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("prototype")
@Component("ins")
public class Instructor {
	@Value("#{T(java.lang.Integer).MAX_VALUE}")
	int id=45;
	@Value("#{'Aryan Naik'.toLowerCase()}")
	String name;
	@Value("#{topics}")
	private List<String> topics;
	
	@Value("#{4+5>11?false:true}")
	private boolean active;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", name=" + name + ", topics=" + topics + ", active=" + active + "]";
	}

	
}
