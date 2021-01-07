
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
		setSize(850, 550);
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
		
		menu1.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		menu2.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		menuitem1.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		menuitem2.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		menuitem3.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		menuitem4.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		menuitem5.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		class MenuActionListener implements ActionListener {
			int num;
			public MenuActionListener(int num) throws SQLException {
				this.num=num;								
			}
			public void actionPerformed(ActionEvent e) {				
				try {
					panel1.removeAll();
					switch(num) {
					case 1: 
						Warehouse wh = new Warehouse();
						panel1.add(wh);
						wh.setVisible(true);
						panel1.revalidate();
						panel1.repaint();
						break;
					case 2: 
						Supplier su = new Supplier();
						panel1.add(su);
						su.setVisible(true);
						panel1.revalidate();
						panel1.repaint();
						break;
					case 3:
						Customer cu = new Customer();
						panel1.add(cu);
						cu.setVisible(true);
						panel1.revalidate();
						panel1.repaint();
						break;
					case 4:
						Sell se = new Sell();
						panel1.add(se);
						se.setVisible(true);
						panel1.revalidate();
						panel1.repaint();
						break;
					case 5:
						Return re = new Return();
						panel1.add(re);
						re.setVisible(true);
						panel1.revalidate();
						panel1.repaint();
						break;
				}
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		}
		MenuActionListener listener1 = new MenuActionListener(1);
		menuitem1.addActionListener(listener1);
		MenuActionListener listener2 = new MenuActionListener(2);
		menuitem2.addActionListener(listener2);
		MenuActionListener listener3 = new MenuActionListener(3);
		menuitem3.addActionListener(listener3);
		MenuActionListener listener4 = new MenuActionListener(4);
		menuitem4.addActionListener(listener4);
		MenuActionListener listener5 = new MenuActionListener(5);
		menuitem5.addActionListener(listener5);
		
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
		img.setImage(img.getImage().getScaledInstance(600,350,Image.SCALE_DEFAULT));
		icon.setIcon(img);
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss");
		label1 = new JLabel("現在時間 : "+ ft.format(new Date()),JLabel.CENTER);
		label1.setFont(new Font("微軟正黑體",Font.PLAIN,20));
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