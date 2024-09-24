package com.java;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Products
{
	private int pid;
	private String pbrand;
	private double pprice;
	private double discount;
	public Products(int pid, String pbrand, double pprice, double discount) {
		this.pid = pid;
		this.pbrand = pbrand;
		this.pprice = pprice;
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Product [id=" + pid + ", brand=" + pbrand + ", price=" + pprice + ", discount=" + discount + "]";
	}
	public int getId() {
		return pid;
	}
	public void setId(int pid) {
		this.pid = pid;
	}
	public String getBrand() {
		return pbrand;
	}
	public void setBrand(String pbrand) {
		this.pbrand = pbrand;
	}
	public double getPrice() {
		return pprice;
	}
	public void setPrice(double pprice) {
		this.pprice = pprice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
class Data
{
	public static Connection getcon() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb?user=root&password=root");
	}
	public int insertData(Products p1) throws ClassNotFoundException, SQLException
	{
		Connection con=getcon();
		PreparedStatement pst=con.prepareStatement("insert into Products(pid,pbrand,pprice,discount)values(?,?,?,?)");

		pst.setInt(1, p1.getId());
		pst.setString(2, p1.getBrand());
		pst.setDouble(3, p1.getPrice());
		pst.setDouble(4,p1.getDiscount());
		
		return pst.executeUpdate();
	}
public int deleteData(int pid) throws ClassNotFoundException, SQLException
{
	Connection con=getcon();
    PreparedStatement pst=con.prepareStatement("delete from products where pid=?");
	pst.setInt(1, pid);
	return pst.executeUpdate();
}
public Products fetchData(int pid) throws SQLException, ClassNotFoundException
{
	Products p=null;
	Connection con=getcon();
	PreparedStatement pst=con.prepareStatement("select * from products where pid=?");
	pst.setInt(1, pid);
	ResultSet rs=pst.executeQuery();
	while(rs.next())
	{
	p=new Products(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4));
	}	
	return p;
}

public List<Products> fetchAll() throws SQLException, ClassNotFoundException
{
	List<Products> list=new ArrayList<Products>();
	Connection con=getcon();
	PreparedStatement pst=con.prepareStatement("select * from products");
	ResultSet rs=pst.executeQuery();
	while(rs.next())
	{
		Products p=new Products(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
		list.add(p);
	}
	return list;
}
}
public class Orm 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Products p1=new Products(1, "lenovo", 890906.0, 1500.0);
		Products p2=new Products(2, "Dell", 987656.0, 2000.0);
		Products p3=new Products(3, "acer", 576789.0, 5000.0);
		Products p4=new Products(4, "mac", 3456767.0, 6567.0);
		Data data=new Data();
		int a=data.insertData(p1);
		    a=a+data.insertData(p2);
		    a=a+data.insertData(p3);
		    a=a+data.insertData(p4);
		    System.out.println("no of rows inserted "+a);
		    
		    int b=data.deleteData(p1.getId());
		    System.out.println("no of rows deleted "+b);
		    
		    System.out.println(data.fetchData(p2.getId()));
		    List<Products> list =data.fetchAll();
		    for(Products p: list)
		    {
		    	System.out.println(p);
		    }
	}
}
