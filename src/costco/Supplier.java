package costco;

import javax.swing.JPanel;

public class Supplier extends JPanel {
	private JTable table;
	private JScrollPane scroll;
	private JButton button1,button2,button3;
	private SQL sql;
	private STableModel smtm;
	
	public Supplier() {
		setPreferredSize(new Dimension(850, 550));
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,5);
		setLayout(fl);
		
		sql = new SQL();
		smtm=new STableModel();
		scroll = new JScrollPane(createtable());
		add(scroll);
		add(deletebutton(), BorderLayout.CENTER);
		add(modifybutton(), BorderLayout.CENTER);
	}
	public JTable createtable() {			
		table =new JTable(smtm);
		table.setPreferredScrollableViewportSize(new Dimension(830,380));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
		DefaultTableCellRenderer  renderer  =  new  DefaultTableCellRenderer();   
		renderer.setHorizontalAlignment(JTextField.CENTER);
		int y=table.getColumnCount();
		for(int i=0;i<y-1;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	     
		return table;
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
							sql.deletesupply(Integer.parseInt(table.getValueAt(i,0).toString()));
						}						
					}
					smtm.update();
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
				try{
					for(int i=0;i<x;i++) {	
						sql.modifysupply(table.getValueAt(i,1).toString(), 
								Integer.parseInt(table.getValueAt(i,4).toString()), 					
								Integer.parseInt(table.getValueAt(i,0).toString()));										
					}
				}					
				catch (Exception e1) {							
					JOptionPane.showMessageDialog(null,"輸入有誤",
                              "錯誤", JOptionPane.ERROR_MESSAGE);							
				}
			}			
		}
		ModActionListener listener = new ModActionListener();
		button3.addActionListener(listener);
		return button3;
	}
	class STableModel extends AbstractTableModel{
		Object[][] data;
		String[] columns={"ID", "時間", "供應商", "商品名", "數量", "選取"};
		public STableModel() {
			data=sql.selectsupply();			
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
			data=sql.selectsupply();
		}
	 }

}
