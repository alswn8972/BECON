package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserConcert extends BaseEntity {
	@ManyToOne(fetch =FetchType.LAZY)
	Concert concert;
	@ManyToOne(fetch =FetchType.LAZY)
	User user;
	
	public static UserConcert of (Concert concert, User user) {
		UserConcert userConcert = new UserConcert();
		userConcert.setConcert(concert);
		userConcert.setUser(user);
		return userConcert;	
	}
}