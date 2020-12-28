package costco;

import javax.swing.*;

public class Main {
	public static void main(String[] args) throws Exception {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		JFrame login = new Login();
		login.setVisible(true);
		
	}
}
