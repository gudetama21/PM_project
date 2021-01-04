package costco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
	private String server = "jdbc:mysql://localhost/";
	private String database = "costco?characterEncoding=utf-8";
	private String url = server + database;
	private String username = "root";
	private String password = "";
	private Connection con = null;
	private PreparedStatement ps;
	private ArrayList<Object> list;

	public SQL() {		
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		list = new ArrayList<Object>();
	}
	
	public Object[][] selectproduct() {
		try{
			list.clear();
			String query = "SELECT * FROM product";
			ps = con.prepareStatement(query);		
			ResultSet result = ps.executeQuery();
			
			int l = 0;
			while(result.next()) {
				list.add(result.getInt("product_ID"));						
				list.add(result.getString("product_name"));	
				list.add(result.getInt("product_price"));
				list.add(result.getInt("quantity"));
				l++;
			}
			Object[][] info = new Object[l][5]; 
			for(int i=0;i<l;i++) {			
				for(int j=0;j<4;j++) {
					info[i][j]=list.get(i*4+j);
				}	
				info[i][4]=(boolean)false;
			}
								
			result.close();
			ps.close(); 
			return info;
		}
		catch( Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void modifyproduct(String n,int p,int q,int id) {
		String query = "UPDATE product SET product_name=?, product_price=?, quantity=? WHERE product_ID =?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, n);
			ps.setInt(2, p);
			ps.setInt(3, q);
			ps.setInt(4, id);			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	public void addproduct(int id,String n,int p,int q) {
		String query = "INSERT INTO product (product_ID,product_name,product_price,quantity) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, n);
			ps.setInt(3, p);
			ps.setInt(4, q);				
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
	}
	public void deleteproduct(int id) {
		String query = "DELETE FROM product WHERE product_ID = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}				
	}
	
	public Object[][] selectsupply() {
		try{
			list.clear();
			String query = "SELECT supply.*, supplier_name, product_name FROM supplier, product, supply WHERE supply.supplier_ID=supplier.supplier_ID AND supply.product_ID=product.product_ID";
			ps = con.prepareStatement(query);		
			ResultSet result = ps.executeQuery();
			
			int l = 0;
			while(result.next()) {
				list.add(result.getInt("transaction_ID"));
				list.add(result.getString("time"));
				list.add(result.getString("supplier_name"));
				list.add(result.getString("product_name"));	
				list.add(result.getInt("quantity"));				
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
		catch( Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void modifysupply(String t,int q,int id) {
		String query = "UPDATE supply SET time=?, quantity=? WHERE transaction_ID =?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, t);
			ps.setInt(2, q);
			ps.setInt(3, id);			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	public void addsupply(String t,int q,int id) {
		String sql = "UPDATE product SET quantity=quantity+? WHERE product_ID=?";
		String query = "INSERT INTO supply (time,quantity,supplier_ID,product_ID) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, q);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
			sql = "SELECT supplier_ID FROM supply WHERE product_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			result.next();
			int sid=result.getInt("supplier_ID");
			ps = con.prepareStatement(query);
			ps.setString(1, t);
			ps.setInt(2, q);
			ps.setInt(3, sid);
			ps.setInt(4, id);				
			ps.executeUpdate();
			result.close();
			ps.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
	}
	public void deletesupply(int id) {
		String query = "DELETE FROM supply WHERE transaction_ID = ?";
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
