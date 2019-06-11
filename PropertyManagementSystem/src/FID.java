import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* 가족번호 생성 프로그램  */
public class FID extends JFrame implements ActionListener {

	private JTextField id;

	public FID() { // 창 띄우기

		setSize(600, 150);
		setLocation(960, 500);
		setTitle("FID 생성");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel j = new JPanel();
		setLayout(new FlowLayout());

		// 버튼, 가족 고유번호 창 생성
		JLabel label = new JLabel("FID");
		add(label);

		id = new JTextField(20);
		add(id);

		JButton FidButton = new JButton("FID 생성");
		add(FidButton);

		// '완료'누르면 창 꺼짐
		JButton Closed = new JButton("완료");
		Closed.addActionListener(this);
		add(Closed);

		// 버튼 실행
		FidButton.addActionListener(this);

		// 전체 출력
		add(j);

	}

	public static String ID() {
		// 가족 번호 6자리 랜덤 생성
		Random rand = new Random();
		String num = "";
		boolean isDuplicated = true; //FID 파일에서 중복이 있는지 확인		
		String ran = null;
		while (isDuplicated == true) {			
			for (int i = 0; i < 6; i++) {
				ran = Integer.toString(rand.nextInt(10) + 0);
				if(num.contains(ran)) {
					i-=1;
				} else {
					num += ran;
				}
			}
			//중복확인
			Scanner inputStream = null;
			try {
				inputStream = new Scanner(new FileInputStream("./Data/Login/FID.txt"));
				while (inputStream.hasNextInt()) {
					int checknumber = inputStream.nextInt();
					if (checknumber == stringToInt(ran)) {
						isDuplicated = true;
					} else {
						isDuplicated = false;
					}
				}
				inputStream.close();	
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "예상치 못한 에러 발생. 관리자에게 문의하세요.");
			}
		}
		return num;
	}

	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		
		if (buttonString.equals("FID 생성")) { // '고유번호 생성'버튼 눌렀을 때 실행 내용
			// 창에 고유번호 생성
			id.setText(ID());
			id.setEditable(false);
			JOptionPane.showMessageDialog(null, "고유번호가 생성되었습니다.");
			
		} else if (buttonString.equals("완료")) {			
			// 고유 번호 파일에 저장
			PrintWriter createFIDFile = null;
			PrintWriter outputStream = null;
			try {
				createFIDFile = new PrintWriter(new FileOutputStream("./Data/Account/" + id.getText() + ".txt"));
				outputStream = new PrintWriter(new FileOutputStream("./Data/Login/FID.txt", true));
				// 가족 계좌 파일 생성
				createFIDFile.println("0");
				createFIDFile.close();
				// 텍스트 파일에 FID 저장
				outputStream.println("\n" + id.getText());
				outputStream.close();

			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "예상치 못한 에러 발생. 관리자에게 문의하세요.");
			}
			this.dispose();
			
		} else // 예외처리
			JOptionPane.showMessageDialog(null, "예상치 못한 에러 발생. 관리자에게 문의하세요.");
	}

	public static int stringToInt(String string) {
		return Integer.parseInt(string);
	}

	public String intToString(int integer) {
		return String.valueOf(integer);
	}

}