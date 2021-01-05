

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class Warehouse extends JPanel {
	private JTable table;
	private JScrollPane scroll;
	private JButton button1,button2,button3,button4;
	private SQL sql;
	private MyTableModel mtm;
	
	public Warehouse() throws SQLException {
		setPreferredSize(new Dimension(850, 550));
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,5);
		setLayout(fl);
		
		sql = new SQL();
		mtm=new MyTableModel();
		scroll = new JScrollPane(createtable());
		add(scroll);
		add(addbutton(), BorderLayout.CENTER);
		add(deletebutton(), BorderLayout.CENTER);
		add(modifybutton(), BorderLayout.CENTER);
		add(orderbutton(), BorderLayout.CENTER);
		
	}
	public JTable createtable() {			
		table =new JTable(mtm);
		table.setPreferredScrollableViewportSize(new Dimension(830,380));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
		DefaultTableCellRenderer  renderer  =  new  DefaultTableCellRenderer();   
		renderer.setHorizontalAlignment(JTextField.CENTER);
		int y=table.getColumnCount();
		for(int i=0;i<y-1;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {   
	        public void setValue(Object value) { 
	            int a = (value instanceof Integer) ? ((Integer) value).intValue() : 0;   
	            setForeground((a < 100) ? Color.red : Color.black);   
	            setText((value == null) ? "" : value.toString()); 
	            setHorizontalAlignment(JTextField.CENTER);
	        }   
	     }; 
	     TableColumn quantity = table.getColumn("數量"); 
	     quantity.setCellRenderer(fontColor);
	     
		return table;
	}
	
	public JButton addbutton() {
		button1 = new JButton("新增");
		button1.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		JFrame af = new JFrame();		
		af.setSize(450,250);
		af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		af.setLocationRelativeTo(null);
		af.setTitle("新增資料");
		af.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(420, 55));
		jp.setLayout(new GridLayout(2, 4, 10, 5));
		
		JLabel label = new JLabel("ID", SwingConstants.CENTER);
		JLabel label1 = new JLabel("商品名", SwingConstants.CENTER);
		JLabel label2 = new JLabel("價錢", SwingConstants.CENTER);
		JLabel label3 = new JLabel("數量", SwingConstants.CENTER);		
		String s= ""+(table.getRowCount()+1);
		JLabel label0 = new JLabel(s, SwingConstants.CENTER);
		JTextField jt1 = new JTextField();
		JTextField jt2 = new JTextField();
		JTextField jt3 = new JTextField();
		JButton jb = new JButton("確定");
		jb.setSize(new Dimension(300,30));
		jp.add(label);
		jp.add(label1);
		jp.add(label2);
		jp.add(label3);
		jp.add(label0);
		jp.add(jt1);
		jp.add(jt2);
		jp.add(jt3);
		af.add(jp,BorderLayout.CENTER);
		af.add(jb,BorderLayout.SOUTH);
		class AddActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String s= ""+(table.getRowCount()+1);
				label0.setText(s);
				jt1.setText("");
				jt2.setText("");
				jt3.setText("");
				af.setVisible(true);
				jb.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {			
						try {
							sql.addproduct((table.getRowCount()+1), jt1.getText(),
									Integer.parseInt(jt2.getText()), Integer.parseInt(jt3.getText()));
							mtm.update();
							af.dispose();
						} catch (Exception e1) {							
							JOptionPane.showMessageDialog(null,"輸入有誤", "錯誤", JOptionPane.ERROR_MESSAGE);							
						}
						table.validate();
						table.updateUI();											
					}					
				});
			}			
		}
		AddActionListener l = new AddActionListener();
		button1.addActionListener(l);
		return button1;
	}
	
	public JButton deletebutton() {
		button2 = new JButton("刪除");
		button2.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		class DelActionListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				int x = table.getRowCount();	
				try {
					for(int i=0;i<x;i++) {
						if((boolean) table.getValueAt(i,4)==true) {
							sql.deleteproduct(Integer.parseInt(table.getValueAt(i,0).toString()));
						}						
					}
					mtm.update();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.validate();
				table.updateUI();
			}			
		}
		DelActionListener l = new DelActionListener();
		button2.addActionListener(l);
		return button2;
	}
	
	public JButton modifybutton() {
		button3 = new JButton("儲存修改");
		button3.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		class ModActionListener implements ActionListener{
			int x = table.getRowCount();
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<x;i++) {					
					sql.modifyproduct(table.getValueAt(i,1).toString(), 
							Integer.parseInt(table.getValueAt(i,2).toString()), 
							Integer.parseInt(table.getValueAt(i,3).toString()),
							Integer.parseInt(table.getValueAt(i,0).toString()));					
				}
			}			
		}
		ModActionListener listener = new ModActionListener();
		button3.addActionListener(listener);
		return button3;
	}
	
	public JButton orderbutton() {
		button4 = new JButton("訂購");
		button4.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		JFrame af = new JFrame();		
		af.setSize(450,250);
		af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		af.setLocationRelativeTo(null);
		af.setTitle("訂購");
		af.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(420, 55));
		jp.setLayout(new GridLayout(2, 2, 15, 5));
		
		JLabel label = new JLabel("時間", SwingConstants.CENTER);
		JLabel label1 = new JLabel("數量", SwingConstants.CENTER);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
		JLabel labelt = new JLabel(ft.format(new Date()),JLabel.CENTER);
		JTextField jt1 = new JTextField();

		JButton jb = new JButton("確定");
		jb.setSize(new Dimension(300,30));
		jp.add(label);
		jp.add(label1);
		jp.add(labelt);
		jp.add(jt1);

		af.add(jp,BorderLayout.CENTER);
		af.add(jb,BorderLayout.SOUTH);
		class AddActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int x = table.getRowCount();	
				for(int i=0;i<x;i++) {
					if((boolean)table.getValueAt(i,4)==true) {
						int a=2*sql.selectsum(Integer.parseInt(table.getValueAt(i,0).toString()))+70-Integer.parseInt(table.getValueAt(i,3).toString());
						jt1.setText(""+a);
						af.setVisible(true);
						int n=i;
						jb.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {			
								try {
									sql.addsupply(labelt.getText(),Integer.parseInt(jt1.getText()),Integer.parseInt(table.getValueAt(n,0).toString()));
									mtm.update();
									af.dispose();
								} catch (Exception e1) {							
									JOptionPane.showMessageDialog(null,"輸入有誤",	"錯誤", JOptionPane.ERROR_MESSAGE);							
								}
								table.validate();
								table.updateUI();											
							}					
						});
					break;}
				}
																					
			}			
		}
		AddActionListener l = new AddActionListener();
		button4.addActionListener(l);
		return button4;
	}
	class MyTableModel extends AbstractTableModel{
		Object[][] data;
		String[] columns={"ID", "商品名", "價錢", "數量", "選取"};
		public MyTableModel() {
			data=sql.selectproduct();			
		}		
		public int getColumnCount() {return columns.length;}
		public int getRowCount() {return data.length;}
		public Object getValueAt(int row, int col) {return data[row][col];}
		public String getColumnName(int col) {return columns[col];}
		public Class getColumnClass(int col) {
			return getValueAt(0,col).getClass();
		}
		public boolean isCellEditable(int row,int col) {return true;}
		public void setValueAt(Object value,int row,int col) {
			data[row][col]=value;
			fireTableCellUpdated(row,col);
	    }
		public void update() throws SQLException {
			data=sql.selectproduct();
		}
	 }

}
