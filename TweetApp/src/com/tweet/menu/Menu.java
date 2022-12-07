package com.tweet.menu;

import java.util.List;
import java.util.Optional;

import com.tweet.dao.TweetdaoImpl;
import com.tweet.entity.Tweet;
import com.tweet.entity.User;
import com.tweet.service.Service;
import com.tweet.service.ServiceImpl;
import com.tweet.utility.Input;

public class Menu {
	
	

	public static void main(String[] args) {
		
		boolean flag=true;
		Service tweetService=new ServiceImpl();
		
		do {
	
		System.out.println("welcome to tweet");
		System.out.println("Enter Option");
		System.out.println("1 for Register");
		System.out.println("2 for login");
		System.out.println("3 for Forgotpassword");
		System.out.println("4 for Exit");
		System.out.println("");
		
		int choice=Input.getInteger();
		
		switch(choice) {
		
		case 1:{
		System.out.println("Enter name");
		String name=Input.getString().trim();
		System.out.println("Enter email");
		String email=Input.getString().trim();
		System.out.println("Enter password");
		String password=Input.getString().trim();
		
		User user=new User(name,email,password);
		String status=tweetService.register(user);
		System.out.println(status);
		break;
		}
		
		case 2:{
			System.out.println("Enter email");
			String email=Input.getString().trim();
			System.out.println("Enter password");
			String password=Input.getString().trim();
			
			User user =new User(email,password);
			boolean status=tweetService.login(user);
			
			if(status) {
				System.out.println("successfuly login");
				
				User loginuser=TweetdaoImpl.getUser(user.getEmail());
				
				do {
					
					System.out.println("enter options");
					System.out.println("1 for post a tweet");
					System.out.println(" 2 for view my tweet");
					System.out.println("3 for view all tweet");
					System.out.println("4 for view all user");
					System.out.println("5 for reset password");
					System.out.println("6 for logout");
					
					int option=Input.getInteger();
					
					switch (option) {
					
					case 1:{
						System.out.println("enter a tweet");
						String post=Input.getString();
						boolean twt=tweetService.postTweet(post,loginuser);
						
						if(twt) {
							System.out.println("successfully posted");
							
							
						}else {
							System.out.println("not posted");
						
						}
						break;
					}
					case 2:{
						System.out.println("View My tweets");
						List<Tweet> tweets=tweetService.viewMyTweets(loginuser);
						tweets.forEach(tweet->{
						System.out.println("post:"+ tweet.getMessage());	
						System.out.println("Date:"+ tweet.getDate());
						System.out.println(" ");
						
					});
						break;
						
					}
					
					case 3:{
						System.out.println("View all tweets");
						List<Tweet> tweets1=tweetService.viewAllTweets();
						tweets1.forEach(tweet->{
						System.out.println("post:"+ tweet.getMessage());	
						System.out.println("Date:"+ tweet.getDate());
						System.out.println(" ");
						
					});
						break;
						
					}
						
					case 4: {
						List<User> users=tweetService.viewAllUsers();
						for(int i=0;i<users.size();i++) {
							
							User u=users.get(i);
							System.out.println("name: "+u.getName());
							System.out.println("email: "+u.getEmail());
							
							//List<Tweet> tweets=u.getTweets();
							//for(int j=0;j<tweets.size();j++) {
								//Tweet t=tweets.get(i);
							System.out.println("Post: "+u.getTweets());

							
								//System.out.println("Post: "+t.getMessage());
								//System.out.println("Date: "+t.getDate());
								System.out.println("");
							//}
							}
						break;
							
						}
					case 5:{
						
						System.out.println("Eneter new password");
	
						 String newpwd=Input.getString().trim();
						 boolean isSet=tweetService.resetPassword(user, newpwd);
						 if(isSet) {
							 System.out.println("password changed susccessfully");
							 
						 }
						 else {
							 System.out.println("something went wrong");
						 }
					break;
					}
					case 6:{
						status=false;
						boolean islogout=tweetService.logout(user);
						
						if(islogout) {
							System.out.println("log out successfully");
							
							
						}
						else {
							System.out.println("log out not successful");
						}break;
					}
					default:
						System.out.println("invalid option enter correct option"
								);
						break;
					}
					
					
					}while(status);
			}
					else {
					System.out.println("enter valid credentials");
					}
			break;
			
		
			
		}
		case 3:{
			System.out.println("eneter email to get password");
			String email=Input.getString().trim();
			String pwd=tweetService.forgetpassword(email);
			System.out.println("your password is:" +pwd);
			break;
		}
		case 4:{
			flag=false;
			System.out.println("thank you");
			break;
		}
		default:
			System.out.println("invalid option");
			break;
		
				}
	}while(flag);
}
}