import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Join extends JFrame implements ActionListener {

	private JTextField tfid;
	private JTextField tid;
	private JTextField tpw;
	private JTextField tn;
	private JTextField tr;
	boolean isExist;
	boolean isDuplicated;

	public Join() {
		
		//FID 존재 확인과 아이디 중복을 확인하는 boolean 각자의 값으로 초기화. (해당 상태일 때는 가입 실패)
		isExist = false;
		isDuplicated = true;

		JPanel p = new JPanel();

		// 무슨 항목인지 출력
		JLabel lfid = new JLabel("고유번호");
		JLabel lid = new JLabel("아이디");
		JLabel lpw = new JLabel("비밀번호");
		JLabel ln = new JLabel("이름");
		JLabel lr = new JLabel("가족관계");

		// 중복확인 버튼
		JButton a1 = new JButton("FID 유효성 검사");
		JButton a2 = new JButton("아이디 중복체크");

		// 각 항목별로 텍스트창 만들기
		tfid = new JTextField();
		tid = new JTextField();
		tpw = new JTextField();
		tn = new JTextField();
		tr = new JTextField();

		// 화면에 표현
		add(lfid);
		add(tfid);
		add(a1);
		add(lid);
		add(tid);
		add(a2);
		add(lpw);
		add(tpw);
		add(ln);
		add(tn);
		add(lr);
		add(tr);

		// 버튼 생성 및 화면에 표현

		JButton j1 = new JButton("가입");
		JButton j2 = new JButton("취소");
		add(j1);
		add(j2);

		// 항목별로 위치 지정
		lfid.setBounds(40, 30, 80, 40);
		lid.setBounds(40, 70, 80, 40);
		lpw.setBounds(40, 110, 80, 40);
		ln.setBounds(40, 150, 80, 40);
		lr.setBounds(40, 190, 80, 40);

		tfid.setBounds(120, 30, 150, 40);
		tid.setBounds(120, 70, 150, 40);
		tpw.setBounds(120, 110, 150, 40);
		tn.setBounds(120, 150, 150, 40);
		tr.setBounds(120, 190, 150, 40);

		a1.setBounds(300, 30, 200, 40);
		a2.setBounds(300, 70, 200, 40);

		j1.setBounds(160, 330, 80, 30);
		j2.setBounds(290, 330, 80, 30);

		// 버튼 눌렀을때 실행되도록
		a1.addActionListener(this);
		a2.addActionListener(this);
		j1.addActionListener(this);
		j2.addActionListener(this);

		// 전체 모습 화면에 표현
		add(p);
		setSize(550, 500);
		setTitle("회원가입");
		setLocation(960, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			String buttonString = e.getActionCommand();			
			int number1;
			
			// 고유 번호 유효성 체크하기
			if (buttonString.equals("FID 유효성 검사")) {
				Scanner in = new Scanner(new FileInputStream("./Data/Login/FID.txt"));
				while (in.hasNextInt()) {
					number1 = in.nextInt();
					if (number1 == (stringToInt(tfid.getText()))) {
						isExist = true;
						break;
					}
				}
				in.close();
				if (isExist == true) {
					JOptionPane.showMessageDialog(null, "존재하는 FID입니다.");
					tfid.setEditable(false);					
				} else {
					JOptionPane.showMessageDialog(null, "FID가 존재하지 않습니다.");
				}
			}

			// 아이디 중복 체크하기
			else if (buttonString.equals("아이디 중복체크")) {
				Scanner input = new Scanner(new FileReader("./Data/Login/ID.txt"));				
				while (input.hasNext()) {
					String str = input.next();
					if (str.equals(tid.getText())) {
						isDuplicated = true;
						break;
					} else {
						isDuplicated = false;
					}
				}
				input.close();
				if (isDuplicated == true) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다");
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다");
					tid.setEditable(false);
				}
			}
			
			// 가입 버튼 누르면 텍스트파일에 입력
			else if (buttonString.equals("가입")) {
				if (isDuplicated == false && isExist == true) { // 고유번호가 있고 아이디 중복 확인을 통과했을 때
					PrintWriter createFIDNameFile = new PrintWriter(new FileOutputStream("./Data/Account/" + tfid.getText() + " " + tn.getText() + ".txt"));
					PrintWriter output = new PrintWriter(new FileWriter("./Data/Login/ID.txt", true));
					PrintWriter outputStream = new PrintWriter(new FileWriter("./Data/Login/JoinMembership.txt", true));
					//데이터 저장
					outputStream.println(tfid.getText() + " " + tid.getText() + " " + tpw.getText() + " " + tn.getText() + " " + tr.getText() + " ");
					output.println("" + tid.getText());
					outputStream.close();
					output.close();
					//개인 계좌 파일 생성
					createFIDNameFile.println("0");
					createFIDNameFile.close();
					//팝업창 띄우고 종료
					JOptionPane.showMessageDialog(null, "회원가입을 성공했습니다!");
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "FID 유효성 확인과 ID 중복체크를 확인 해주세요.");
				}
			}
			
			// 취소버튼 누르면 회원가입 취소
			else if (buttonString.equals("취소")) {
				this.dispose();
			}
			
			// 예외처리
			else
				JOptionPane.showMessageDialog(null, "예상치 못한 에러 발생. 관리자에게 문의하세요.");
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
		}
	}

	private static int stringToInt(String text) {
		return Integer.parseInt(text.trim());
	}
}
