package com.tweet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tweet.entity.Tweet;
import com.tweet.entity.User;
import com.tweet.utility.Constant;

public class TweetdaoImpl implements Tweetdao {

	@Override
	public String register(User user) {
		
		String status=null;
		String query="insert into user (name,email,password) values(?,?,?)";
		
		try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
				PreparedStatement ps=con.prepareStatement(query);)
		{

			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			
			
			if(ps.executeUpdate()>0) {
				status="registered succesfully";
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static User getUser(String email) {
	User user=new User();
	String query3="select * from user where email=?";
	
	
	try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
			PreparedStatement ps=con.prepareStatement(query3);
			){
		
		ps.setString(1, email);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {user.setName(rs.getString("name"));
		
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
			
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return user;
}
	@Override
	public boolean login(User user) {
	
		boolean status=false;
		String query1="update user set status=? where email=? and password=?";
		
		try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
				PreparedStatement ps=con.prepareStatement(query1);
				){
			
			ps.setBoolean(1, true);
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			int a=ps.executeUpdate();
			
			if(a>0) {status=true;
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean postTweet(String post, User loginuser) {
	boolean status=false;
	String query2="insert into tweet(name,email,post,date) values (?,?,?,?)"; 
		
	try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
			PreparedStatement ps=con.prepareStatement(query2);
			){
		
		ps.setString(1, loginuser.getName());
		ps.setString(2, loginuser.getEmail());
		ps.setString(3, post);
		ps.setString(4,LocalDateTime.now().toString());
		int a=ps.executeUpdate();
		
		if(a>0) {status=true;
		
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return status;
}

	@Override
	public List<Tweet> viewMyTweets(User loginuser) {
		List<Tweet> tweets=new ArrayList<Tweet>();
		String queryt="select post,date from tweet where email=?";
		
		try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
				PreparedStatement ps=con.prepareStatement(queryt);
				){
			Tweet tweet=null;
			ps.setString(1, loginuser.getEmail());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				tweet=new Tweet();
				tweet.setMessage(rs.getString("post"));
				tweet.setDate(rs.getString("date"));
				tweet.setEmail(loginuser.getEmail());
				
				tweets.add(tweet);
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tweets;
	}

	@Override
	public List<Tweet> viewAllTweets() {
		List<Tweet> tweets=new ArrayList<Tweet>();
		String query4="select post,date from tweet";
		
		try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
				PreparedStatement ps=con.prepareStatement(query4);
				){
			
				ResultSet rs=ps.executeQuery();
				Tweet t=null;
				while(rs.next()) {
					t=new Tweet();
					t.setMessage(rs.getString("post"));
					t.setDate(rs.getString("date"));
					tweets.add(t);
				}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tweets;
	}
	@Override
	public List<User> viewAllUsers() {
	List<User> users=new ArrayList<User>();
	String queryu="select * from tweet";
	try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
			PreparedStatement ps=con.prepareStatement(queryu);
			){
		ResultSet rs=ps.executeQuery();
		
		User user=null;
		while(rs.next()){
		user=new User();
		
		user.setEmail( rs.getString("email"));
		user.setName(rs.getString("name"));
		user.setTweets(viewMyTweets(user));
		users.add(user);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return users;
	}

	@Override
	public boolean resetPassword(User user, String newpwd) {
		boolean status=false;
		String queryr="update user set password=? where email=? and password=?";
			
		try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
				PreparedStatement ps=con.prepareStatement(queryr);
				){
			
			ps.setString(1, newpwd);
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			int a=ps.executeUpdate();
			if(a>0) {status=true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return status;
	}

	@Override
	public boolean logout(User user) {
		boolean status=false;
		String queryl="update user set status=? where email=? and password=?";
		
		try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
				PreparedStatement ps=con.prepareStatement(queryl);
				){
			
			ps.setBoolean(1, false);
			ps.setString(2, user.getEmail());
			ps.setString(3,user.getPassword());
			 int a=ps.executeUpdate();
			 
			 if(a>0) {status=true;
			 }
	
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return status;
	}

	@Override
	public String forgetpassword(String email) {
		
		String password=null;
		String queryp="select password from user where email=?";
		try(Connection con=DriverManager.getConnection(Constant.dburl, Constant.user, Constant.pass);
				PreparedStatement ps=con.prepareStatement(queryp);
				){
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {password=rs.getString("password");
			
			}
			
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return password;
	}

}
