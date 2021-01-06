import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class Customer extends JPanel {
	private JTable table;
	private JScrollPane scroll;
	private JButton button1,button2,button3,button4,button;
	private SQL sql;
	private MyTableModel mtm;
	
	public Customer() {
		setPreferredSize(new Dimension(850, 550));
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,5);
		setLayout(fl);
		
		sql = new SQL();
		mtm=new MyTableModel();
		scroll = new JScrollPane(createtable());
		add(scroll);
		add(addbutton());
		add(deletebutton());
		add(modifybutton());
		add(salebutton());
	}
	public JTable createtable() {			
		table =new JTable(mtm);
		table.setPreferredScrollableViewportSize(new Dimension(830,380));
		table.setRowHeight(25);
        
		TableColumn column=table.getColumnModel().getColumn(3);
		column.setPreferredWidth(150);
		DefaultTableCellRenderer  renderer  =  new  DefaultTableCellRenderer();   
		renderer.setHorizontalAlignment(JTextField.CENTER);
		int y=table.getColumnCount();
		for(int i=0;i<y-1;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	     
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
		jp.setLayout(new GridLayout(2, 5, 10, 5));
		
		JLabel label = new JLabel("ID", SwingConstants.CENTER);
		JLabel label1 = new JLabel("性別", SwingConstants.CENTER);
		JLabel label2 = new JLabel("出生日期", SwingConstants.CENTER);
		JLabel label3 = new JLabel("地址", SwingConstants.CENTER);
		JLabel label4 = new JLabel("姓名", SwingConstants.CENTER);
		String s= ""+(table.getRowCount()+1);
		JLabel label0 = new JLabel(s, SwingConstants.CENTER);
		JTextField jt1 = new JTextField();
		JTextField jt2 = new JTextField();
		JTextField jt3 = new JTextField();
		JTextField jt4 = new JTextField();
		JButton jb = new JButton("確定");
		jb.setSize(new Dimension(300,30));
		jp.add(label);
		jp.add(label1);
		jp.add(label2);
		jp.add(label3);
		jp.add(label4);
		jp.add(label0);
		jp.add(jt1);
		jp.add(jt2);
		jp.add(jt3);
		jp.add(jt4);
		af.add(jp,BorderLayout.CENTER);
		af.add(jb,BorderLayout.SOUTH);
		class AddActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String s= ""+(table.getRowCount()+1);
				label0.setText(s);
				jt1.setText("");
				jt2.setText("");
				jt3.setText("");
				jt4.setText("");
				af.setVisible(true);
				jb.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {			
						try {
							sql.addcus(Integer.parseInt(label0.getText()), jt1.getText(),
									jt2.getText(), jt3.getText(), jt4.getText());
							mtm.update();
							af.dispose();
						} catch (Exception e1) {							
							JOptionPane.showMessageDialog(null,"輸入有誤","錯誤", JOptionPane.ERROR_MESSAGE);							
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
						if((boolean) table.getValueAt(i,5)==true) {
							sql.deletecus(Integer.parseInt(table.getValueAt(i,0).toString()));
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
					sql.modifycus(table.getValueAt(i,3).toString(), 
							table.getValueAt(i,4).toString(), 
							Integer.parseInt(table.getValueAt(i,0).toString()));					
				}
			}			
		}
		ModActionListener listener = new ModActionListener();
		button3.addActionListener(listener);
		return button3;
	}
	public JButton salebutton() {
		button4 = new JButton("個人數據");
		button4.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		class ModActionListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				Sale sale = new Sale();
				removeAll();
				add(sale);
				sale.setVisible(true);
				revalidate();
				repaint();			
				button = new JButton("返回");
				button.setFont(new Font("微軟正黑體",Font.PLAIN,15));
				button.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {		
						Customer cu = new Customer();
						removeAll();
						add(cu);
						cu.setVisible(true);
						revalidate();
						repaint();
					}
						
				});
				sale.add(button);
			}							
		}
		ModActionListener listener = new ModActionListener();
		button4.addActionListener(listener);
		return button4;
	}
	
	class MyTableModel extends AbstractTableModel{
		Object[][] data;
		String[] columns={"ID", "性別", "出生日期", "地址", "姓名", "選取"};
		public MyTableModel() {
			data=sql.selectcus();			
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
			data=sql.selectcus();
		}
	 }

}
