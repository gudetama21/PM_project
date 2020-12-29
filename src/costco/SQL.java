package costco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
	private String server = "jdbc:mysql://127.0.0.1/";
	private String database = "pms?characterEncoding=utf-8";
	private String url = server + database;
	private String username = "root";
	private String password = "";
	private Connection con = null;
	private PreparedStatement ps;
	private ArrayList<Object> list;

	public SQL() throws SQLException {		
		con = DriverManager.getConnection(url, username, password);
		
		list = new ArrayList<Object>();
	}
	
	public Object[][] selectall(String db) throws SQLException {
		list.clear();
		String query = "SELECT * FROM "+db;
		ps = con.prepareStatement(query);		
		ResultSet result = ps.executeQuery();
		
		int l = 0;
		while(result.next()) {
			list.add(result.getInt("Id"));
			list.add(result.getString("name"));			
			list.add(result.getInt("price"));
			list.add(result.getString("unit"));
			list.add(result.getString("country"));
			l++;
		}
		Object[][] info = new Object[l][6]; 
		for(int i=0;i<l;i++) {			
			for(int j=0;j<5;j++) {
				info[i][j]=list.get(i*5+j);
			}	
			info[i][5]=(boolean)false;
		}
							
		result.close();
		ps.close(); 
		return info;
	}
	public void modify(String db,String n,int p,String u,String c,int id) {
		String query = "UPDATE "+db+" SET name=?, price=?, unit=?, country=? WHERE Id =?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, n);
			ps.setInt(2, p);
			ps.setString(3, u);
			ps.setString(4, c);
			ps.setInt(5, id);			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	public void add(String db,int id,String n,int p,String u,String c) {
		String query = "INSERT INTO "+db+" (Id,name,price,unit,country) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, n);
			ps.setInt(3, p);
			ps.setString(4, u);
			ps.setString(5, c);			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
	}
	public void delete(String db,int id) {
		String query = "DELETE FROM "+db+" WHERE Id = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}				
	}
	public void conclose() throws SQLException {
		con.close();
	}
}
