package costco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


public class GUI extends JFrame {
	private JPanel panel1;
	private JMenuItem menuitem1,menuitem2;
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
		
	}
	public JPanel createPanel() {
		panel1 = new JPanel();	
		panel1.setLayout(new BorderLayout());
		panel1.setPreferredSize(new Dimension(750, 450));
		createLabel();
		createmenu();
		
		return panel1;
	}
	public void createmenu() {		
		menu1 = new JMenu("顧客管理");
		menu2 = new JMenu("作業管理");
		menuitem1 = new JMenuItem("供應商");
		menuitem2 = new JMenuItem("庫存");
		
		class MenuActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		MenuActionListener listener = new MenuActionListener();
		menuitem1.addActionListener(listener);
		menuitem2.addActionListener(listener);
		menu2.add(menuitem1);
		menu2.add(menuitem2);
		bar = new JMenuBar();
		bar.add(menu1);
		bar.add(menu2);
		setJMenuBar(bar);		
	}
	public void createLabel() {
		icon = new JLabel("",JLabel.CENTER);
		img.setImage(img.getImage().getScaledInstance(500,250,Image.SCALE_DEFAULT));
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
        
        panel1.add(label1,BorderLayout.NORTH);
        panel1.add(icon,BorderLayout.CENTER);     
		
	}
}
