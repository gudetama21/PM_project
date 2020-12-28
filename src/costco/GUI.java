package costco;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class GUI extends JFrame {
	private JPanel panel1;
	private JMenuItem menuitem1,menuitem2,menuitem3,menuitem4,menuitem5;
	private JMenu menu1,menu2;
	private JMenuBar bar;
	private JLabel label1,icon;
	private ImageIcon img = new ImageIcon("src\\costco圖片.jpg");
	
	public GUI() throws Exception {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Costco");
		setIconImage(img.getImage());
		setSize(750, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		
		add(createPanel());		
		panel1.setVisible(true);
		addWindowListener(new WindowHandler());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	public JPanel createPanel() throws SQLException {
		panel1 = new JPanel();	
		panel1.setLayout(new BorderLayout());
		panel1.setPreferredSize(new Dimension(750, 450));
		createLabel();
		createmenu();
		
		return panel1;
	}
	public void createmenu() throws SQLException {		
		menu1 = new JMenu("顧客管理");
		menu2 = new JMenu("作業管理");
		menuitem1 = new JMenuItem("庫存");
		menuitem2 = new JMenuItem("供應商");
		menuitem3 = new JMenuItem("顧客");
		menuitem4 = new JMenuItem("銷售");
		menuitem5 = new JMenuItem("退貨");
		
		class MenuActionListener implements ActionListener {
			int num;
			public MenuActionListener(int num) throws SQLException {
				this.num=num;								
			}
			public void actionPerformed(ActionEvent e) {				
				try {
					panel1.setVisible(false);
					Warehouse wh = new Warehouse();
					add(wh);
					wh.setVisible(false);
					
					switch(num) {
					case 1: wh.setVisible(true);
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
				}
				} catch (SQLException e1) {

				}				
			}
		}
		MenuActionListener listener1 = new MenuActionListener(1);
		menuitem1.addActionListener(listener1);
		menuitem2.addActionListener(listener1);
		
		menu1.add(menuitem3);
		menu1.add(menuitem4);
		menu1.add(menuitem5);
		menu2.add(menuitem1);
		menu2.add(menuitem2);
		bar = new JMenuBar();
		bar.add(menu1);
		bar.add(menu2);
		setJMenuBar(bar);		
	}
	public void createLabel() {
		icon = new JLabel("",JLabel.CENTER);
		img.setImage(img.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT));
		icon.setIcon(img);
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss");
		label1 = new JLabel("現在時間 : "+ ft.format(new Date()),JLabel.CENTER);
		label1.setFont(new Font("微軟正黑體",Font.PLAIN,18));
		Timer timeAction = new Timer(1000, new ActionListener() {          			  
            public void actionPerformed(ActionEvent e) {       
                long time = System.currentTimeMillis();                        
                label1.setText("現在時間 : "+ ft.format(new Date(time)));                
            }      
        });            
        timeAction.start();
        
        add(label1,BorderLayout.SOUTH);
        panel1.add(icon,BorderLayout.CENTER);     
		
	}
	class WindowHandler extends WindowAdapter {				
		public void windowClosing(WindowEvent e) {
			int result=JOptionPane.showConfirmDialog((Component) e.getSource(),"確定要結束程式嗎?","確認訊息",
	               JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if (result==JOptionPane.YES_OPTION) {System.exit(0);}
	    }   
	 }
}
