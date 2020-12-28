package costco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginListener implements ActionListener {
	private JTextField text_name;
	private JPasswordField text_password;
	private JFrame login;
	
	public LoginListener(JFrame login,JTextField text_name,JPasswordField text_password){
		this.login=login;
		this.text_name=text_name;
		this.text_password=text_password;
	}
		
	public void actionPerformed(ActionEvent e) {
		Dimension dim3 = new Dimension(300,30);
		
		//生成新介面
		JFrame login2 = new JFrame();
		login2.setSize(400,200);
		login2.setDefaultCloseOperation(3);
		login2.setLocationRelativeTo(null);
		login2.setFont(new Font("微軟正黑體",Font.PLAIN,14));  

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		char[] password = {'1','2','3','4','5','6'};
			if(text_name.getText().equals("123") && (Arrays.equals(text_password.getPassword(),password))){					
				//通過我們獲取的登入介面物件，用dispose方法關閉它
				login.dispose();
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					JFrame gui = new GUI();
					gui.setVisible(true);
				} 
				catch (Exception e1) {					
					
				}
			}
			
			else {
				JLabel message = new JLabel("帳號或密碼錯誤");
				message.setFont(new Font("微軟正黑體",Font.PLAIN,14));  
				message.setPreferredSize(dim3);
				jp1.add(message);
				login2.add(jp1,BorderLayout.CENTER);
				
				JButton close = new JButton("確定");
				close.setFont(new Font("微軟正黑體",Font.PLAIN,14));
				//設定按鍵大小
				close.setSize(dim3);
				jp2.add(close);
				login2.add(jp2,BorderLayout.SOUTH);
								
				close.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login2.dispose();
					}
				});
				
				login2.setResizable(false);
				login2.setVisible(true);
			}										
	}
}
