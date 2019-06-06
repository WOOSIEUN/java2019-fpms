
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* 가족번호 생성 프로그램  */
public class FID extends JFrame implements ActionListener{ 

	
	private JTextField id;
	
	
	public FID() { //창 띄우기
		
		setSize(600,150);
		setTitle("가족 고유 번호 생성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel j = new JPanel();
		setLayout(new FlowLayout());
		
		
		//버튼, 가족 고유번호 창 생성
		JLabel label = new JLabel("가족 고유번호");
		add(label);
		
		id = new JTextField(20);
		add(id);
		
		JButton FidButton =new JButton("고유번호 생성");
		add(FidButton);
		
		//'완료'누르면 창 꺼짐
		JButton Closed = new JButton("완료");
		Ending End = new Ending();
		Closed.addActionListener(End);
		add(Closed);
	  
	    //버튼 실행
		FidButton.addActionListener(this);
		
		//전체 출력
		add(j);
		
	}
	
	public static void main(String[] args) {
		
		   FID f=new FID();
		   f.setVisible(true);
		   
	}
		


	public static String ID() {
		
		//가족 번호 6자리 랜덤 생성
		Random rand = new Random();
		String num = "";
			
		for(int i=0;i<6;i++) {
					
			String ran = Integer.toString(rand.nextInt(9)+1);
					
			if(num.contains(ran)) {
				i-=1;
			}			
			else
				num += ran;
			}
				
		return num;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String buttonString = e.getActionCommand();
				
			if (buttonString.equals("고유번호 생성")) { //'고유번호 생성'버튼 눌렀을 때 실행 내용
				
				// 고유 번호 파일에 저장(누적해서)
				PrintWriter outputStream=null;
				try {
					outputStream = new PrintWriter(new FileOutputStream("FID.txt",true));
				} 
				catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}
			
				// 창에 고유번호 생성
				id.setText(ID());
				//텍스트 파일에 고유번호 저장
				outputStream.println(id.getText());
				outputStream.close();
				
				JOptionPane.showMessageDialog(null, "고유번호가 생성되었습니다.");
				//JOptionPane.CLOSED_OPTION;
			}
				
		else
			//오류 
		     id.setText(getWarningString());
		
		
			
	}
	
}
 

