package com.tweet.service;

import java.util.List;
import java.util.Optional;

import com.tweet.entity.Tweet;
import com.tweet.entity.User;


public interface Service {
	
	String register (User user);

	boolean login(User user);

	boolean postTweet(String post, User loginuser);
	
	

	List<Tweet> viewMyTweets(User loginuser);
	List<Tweet> viewAllTweets();

List<User> viewAllUsers();

boolean resetPassword(User user, String newpwd);

boolean logout(User user);

String forgetpassword(String email);	

	

	

}
