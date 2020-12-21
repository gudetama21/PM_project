package costco;
import java.awt.Dimension;  //封裝了一個構件的高度和寬度
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
	//在類中定義初始化介面的方法
	public void initUI() {		
		//設定窗體物件的屬性值
		setTitle("Login");//設定窗體標題
		setSize(350, 200);//設定窗體大小，只對頂層容器生效
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//設定窗體關閉操作，3表示關閉窗體退出程式
		setLocationRelativeTo(null);//設定窗體相對於另一組間的居中位置，引數null表示窗體相對於螢幕的中央位置
		setResizable(false);//禁止調整窗體大小
		setFont(new Font("微軟正黑體",Font.PLAIN,14));//設定字型，顯示格式正常，大小
		
		//例項化FlowLayout流式佈局類的物件，指定對齊方式為居中對齊元件之間的間隔為10個畫素
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
		//例項化流式佈局類的物件
		setLayout(fl);
		
		//例項化JLabel標籤物件，該物件顯示“賬號”
		JLabel labname = new JLabel("帳號：");
		labname.setFont(new Font("微軟正黑體",Font.PLAIN,14));
		add(labname);
		
		//例項化JTextField標籤物件化
		JTextField text_name = new JTextField();
		Dimension dim1 = new Dimension(250,30);
		text_name.setPreferredSize(dim1);//設定除頂級容器元件以外其他元件的大小
		//將textName標籤新增到窗體上
		add(text_name);
		
		//例項化JLabel標籤物件，該物件顯示“密碼”
		JLabel labpass = new JLabel("密碼：");
		labpass.setFont(new Font("微軟正黑體",Font.PLAIN,14));
		add(labpass);
		
		//例項化JPasswordField
		JPasswordField text_password = new JPasswordField();
		//設定大小
		text_password.setPreferredSize(dim1);
		add(text_password);
		
		//例項化JButton元件
		JButton button1 = new JButton();
		//設定按鍵的顯示內容
		Dimension dim2 = new Dimension(100,30);
		button1.setText("登入");
		button1.setFont(new Font("微軟正黑體",Font.PLAIN,14));
		//設定按鍵大小
		button1.setSize(dim2);
		add(button1);
		
		LoginListener ll = new LoginListener(this,text_name,text_password);
		button1.addActionListener(ll);
	}
}