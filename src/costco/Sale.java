import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Sale extends JPanel{
	private SQL sql;
	private JComboBox jc;
	private ArrayList<Object> a;
	private JTextArea area;
	
	public Sale() {
		setPreferredSize(new Dimension(850, 550));
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,5);
		setLayout(fl);
		
		sql = new SQL();
		add(createjc(), BorderLayout.NORTH);
		add(createarea(), BorderLayout.CENTER);
		
	}
	public JComboBox createjc() {
		jc = new JComboBox();
		jc.addItem("請選擇");
		for(int i=1; i<7; i++) {
			jc.addItem(i);
		}
		
		class BoxActionListener implements ActionListener {			
			public BoxActionListener() {												
			}
			public void actionPerformed(ActionEvent e) {
				int sum=0;
				int stime=0;
				String slast="";
				int back=0;
				int btime=0;
				String blast="無";
				a = sql.selectsale(Integer.parseInt(jc.getSelectedItem().toString()));
				sum+=Integer.parseInt(a.get(0).toString());
				stime+=Integer.parseInt(a.get(1).toString());
				if(a.get(2)!=null)slast=a.get(2).toString();
				back+=Integer.parseInt(a.get(3).toString());
				btime+=Integer.parseInt(a.get(4).toString());
				if(a.get(5)!=null)blast=a.get(5).toString();
				area.setText("總購買金額:  "+sum+"\n購買次數: "+stime+"\n最近購買日期: "+slast
						+"\n總退貨金額: "+back+"\n退貨次數: "+btime+"\n最近退貨日期: "+blast+"\n退貨率: "+(back/sum));
			}
		}
		BoxActionListener l = new BoxActionListener();
		jc.addActionListener(l);
		
		return jc;		
	}
	public JTextArea createarea() {
		area = new JTextArea();
		area.setPreferredSize(new Dimension(500, 350));
		area.setEditable(false);
		area.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		return area;
	}
}
