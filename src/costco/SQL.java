
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

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
				list.add(result.getInt("transaction_number"));
				list.add(result.getString("time"));
				list.add(result.getString("supplier_name"));
				list.add(result.getString("product_name"));	
				list.add(result.getInt("quantity"));				
				l++;
			}
			Object[][] info = new Object[l][5]; 
			for(int i=0;i<l;i++) {			
				for(int j=0;j<5;j++) {
					info[i][j]=list.get(i*5+j);
				}	
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

	public void addsupply(String t,int q,int id) {
		String sql = "UPDATE product SET quantity=quantity+? WHERE product_ID=?";
		String query = "INSERT INTO supply (time,quantity,supplier_ID,product_ID) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, q);
			ps.setInt(2, id);
			ps.executeUpdate();
			sql = "SELECT supplier_ID FROM supply WHERE product_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			result.next();
			int sid=result.getInt("supplier_ID");
			ps = con.prepareStatement(query);
			Date date = Date.valueOf(t);
			ps.setDate(1, date);
			ps.setInt(2, q);
			ps.setInt(3, sid);
			ps.setInt(4, id);				
			ps.executeUpdate();
			result.close();
			ps.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}

	public Object[][] selectcus() {
		try{
			list.clear();
			String query = "SELECT * FROM customer";
			ps = con.prepareStatement(query);		
			ResultSet result = ps.executeQuery();
			
			int l = 0;
			while(result.next()) {
				list.add(result.getInt("customer_ID"));						
				list.add(result.getString("sex"));
				list.add(result.getString("birth_day"));
				list.add(result.getString("address"));
				list.add(result.getString("name"));
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
	public void deletecus(int id) {
		String query = "DELETE FROM customer WHERE customer_ID = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}				
	}
	public void addcus(int id,String s,String b,String a,String n) {
		String query = "INSERT INTO  customer (customer_ID,sex,birth_day,address,name) VALUES (?, ?, ?, ?,?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, s);
			Date date = Date.valueOf(b);
			ps.setDate(3, date);
			ps.setString(4, a);	
			ps.setString(5, n);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}
	public void modifycus(String a,String n,int id) {
		String query = "UPDATE customer SET address=?, name=? WHERE customer_ID =?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, a);
			ps.setString(2, n);
			ps.setInt(3, id);			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	public Object[][] selectreturns() {
		try{
			list.clear();
			String query = "SELECT returns.*, name, product_name FROM customer, product, returns WHERE returns.customer_ID=customer.customer_ID AND returns.product_ID=product.product_ID ORDER BY transaction_number ASC";
			ps = con.prepareStatement(query);		
			ResultSet result = ps.executeQuery();
			
			int l = 0;
			while(result.next()) {
				list.add(result.getInt("transaction_number"));
				list.add(result.getString("time"));
				list.add(result.getString("name"));
				list.add(result.getString("product_name"));	
				list.add(result.getInt("quantity"));				
				l++;
			}
			Object[][] info = new Object[l][5]; 
			for(int i=0;i<l;i++) {			
				for(int j=0;j<5;j++) {
					info[i][j]=list.get(i*5+j);
				}	
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
	public Object[][] selectsell() {
		try{
			list.clear();
			String query = "SELECT buy.*, name, product_name FROM customer, product, buy WHERE buy.customer_ID=customer.customer_ID AND buy.product_ID=product.product_ID ORDER BY transaction_number ASC";
			ps = con.prepareStatement(query);		
			ResultSet result = ps.executeQuery();
			
			int l = 0;
			while(result.next()) {
				list.add(result.getInt("transaction_number"));
				list.add(result.getString("time"));
				list.add(result.getString("name"));
				list.add(result.getString("product_name"));	
				list.add(result.getInt("quantity"));				
				l++;
			}
			Object[][] info = new Object[l][5]; 
			for(int i=0;i<l;i++) {			
				for(int j=0;j<5;j++) {
					info[i][j]=list.get(i*5+j);
				}	
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
	public void addsell(int id,String t,int q,int cid,int pid) {
		String query = "INSERT INTO buy VALUES (?, ?, ?, ?,?)";
		String sql = "UPDATE product SET quantity=quantity-? WHERE product_ID=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);			
			Date date = Date.valueOf(t);
			ps.setDate(2, date);
			ps.setInt(3, q);	
			ps.setInt(4, cid);
			ps.setInt(5, pid);
			ps.executeUpdate();
			ps = con.prepareStatement(sql);
			ps.setInt(1, q);
			ps.setInt(2, pid);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}
	public void addreturns(int id,String t,int q,int cid,int pid) {
		String query = "INSERT INTO returns VALUES (?, ?, ?, ?,?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);			
			Date date = Date.valueOf(t);
			ps.setDate(2, date);
			ps.setInt(3, q);	
			ps.setInt(4, cid);
			ps.setInt(5, pid);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}
	public ArrayList<Object> selectsale(int id) {
		try{
			list.clear();
			String query = "SELECT SUM(product_price*buy.quantity),COUNT(DISTINCT transaction_number),Max(time) FROM product, buy WHERE buy.customer_ID=? AND buy.product_ID=product.product_ID";
			ps = con.prepareStatement(query);	
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();						
			result.next(); 
			list.add(result.getInt(1));
			list.add(result.getInt(2));
			list.add(result.getString(3));				
			
			query = "SELECT SUM(product_price*returns.quantity),COUNT(DISTINCT transaction_number),Max(time) FROM product, returns WHERE returns.customer_ID=? AND returns.product_ID=product.product_ID";
			ps = con.prepareStatement(query);	
			ps.setInt(1, id);
			result = ps.executeQuery();						
			result.next();
			list.add(result.getInt(1));
			list.add(result.getInt(2));
			list.add(result.getString(3));
								
			result.close();
			ps.close(); 
			return list;
		}
		catch( Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int selectsum(int id) {
		try{
			String query = "SELECT SUM(quantity) FROM buy WHERE product_ID=?";
			ps = con.prepareStatement(query);	
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			
			result.next();
			int n=result.getInt(1);								
											
			result.close();
			ps.close(); 
			return n;
		}
		catch( Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public void conclose() throws SQLException {
		con.close();
	}
}