package com.java;

import java.sql.*;

class User
{
	private String id;
	private String name;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId() throws Exception{
		this.id = id();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	private String id() throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///newdb","root","root");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from user");
			String lastdata=null;
			while(rs.next()) {
				lastdata=rs.getString(1);
			}
			if(lastdata==null) {
				return "us_0001";
			}
			else {
				String s=lastdata.substring(3);
				int index=Integer.parseInt(s)+1;
				String next="";
				for(int i=1;i<=4-count_val(index);i++) {
					next=next+'0';
				}
				next=next+index;
				return "us_"+next;
			}
	
		}
		
		
	private int count_val(int index) {
		int count=0;//120
		while(index!=0)
		{
			count++;
			index=index/10;
		}
		return count;
	}
}

