package com.tweet.service;

import java.util.List;
import java.util.Optional;

import com.tweet.dao.Tweetdao;
import com.tweet.dao.TweetdaoImpl;
import com.tweet.entity.Tweet;
import com.tweet.entity.User;

public class  ServiceImpl implements Service {

		Tweetdao dao=new TweetdaoImpl();
	@Override
	public String register(User user) {
		 return dao.register(user) ;
	}
	@Override
	public boolean login(User user) {
		
		return dao.login(user);
	}
	@Override
	public boolean postTweet(String post, User loginuser) {
		// TODO Auto-generated method stub
		return dao.postTweet(post,loginuser);
	}
	
	@Override
	public List<Tweet> viewMyTweets(User loginuser) {
		// TODO Auto-generated method stub
		return dao.viewMyTweets(loginuser);
	}
	@Override
	public List<Tweet> viewAllTweets() {
		// TODO Auto-generated method stub
		return dao.viewAllTweets();
	}
	@Override
	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		return dao.viewAllUsers();
	}
	@Override
	public boolean resetPassword(User user, String newpwd) {
		// TODO Auto-generated method stub
		return dao.resetPassword(user,newpwd);
	}
	@Override
	public boolean logout(User user) {
		// TODO Auto-generated method stub
		return dao.logout(user);
	}
	@Override
	public String forgetpassword(String email) {
		// TODO Auto-generated method stub
		return dao.forgetpassword(email);
	}
	

}
