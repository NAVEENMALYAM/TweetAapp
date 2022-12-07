package com.tweet.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tweet.dao.Tweetdao;
import com.tweet.dao.TweetdaoImpl;
import com.tweet.entity.User;

import junit.framework.Assert;

class UserTest {
	User user;
	Tweetdao dao;	
	@Test
	void usertest() {
		user=new User();
		user.setEmail("ravi@gmail.com");
		user.setName("ravi");
		user.setPassword("ravi@123");
		Assert.assertEquals( "ravi@gmail.com",user.getEmail());
		
	}
	@Test
	void logintest() {
		dao=new TweetdaoImpl();
		user=new User("er","rtrt");
		boolean login=dao.login(user);
		Assert.assertEquals(true,login);
	}
}
