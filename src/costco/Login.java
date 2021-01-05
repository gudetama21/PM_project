
import java.awt.Dimension;  
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame {

	public Login() throws Exception {
		initUI();
	}
	public void initUI() {		
		setTitle("Login");
		setSize(350, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setFont(new Font("微軟正黑體",Font.PLAIN,15));
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,15);
		setLayout(fl);
		
		JLabel labname = new JLabel("帳號：");
		labname.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		add(labname);
		
		JTextField text_name = new JTextField();
		Dimension dim1 = new Dimension(250,30);
		text_name.setPreferredSize(dim1);
		add(text_name);
		
		JLabel labpass = new JLabel("密碼：");
		labpass.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		add(labpass);
		
		JPasswordField text_password = new JPasswordField();
		text_password.setPreferredSize(dim1);
		add(text_password);
		
		JButton button1 = new JButton();
		Dimension dim2 = new Dimension(100,30);
		button1.setText("登入");
		button1.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		button1.setSize(dim2);
		add(button1);
		
		LoginListener ll = new LoginListener(this,text_name,text_password);
		button1.addActionListener(ll);
	}
}