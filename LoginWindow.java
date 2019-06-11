import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {

	private JButton btnLogin;
	private JButton btnJoin;
	private JPasswordField passText;
	private JTextField userText1;
	private JTextField userText2;
	private boolean bLoginCheck;

	private PropertyManagementSystemDemo PMSDemo;
	private MainFrame mainFrame;

	public LoginWindow() {
		// setting
		setTitle("Property Management");
		setSize(400, 600);
		setResizable(false);
		setLocation(960, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();
		placeLoginPanel(panel);

		// add
		add(panel);

		// visible
		setVisible(true);
		
		bLoginCheck = false;
	}

	public void placeLoginPanel(JPanel panel) {
		panel.setLayout(null);
		JLabel title = new JLabel("Property Management System");
		title.setBounds(115, 140, 180, 50);
		panel.add(title);

		JLabel userLabel1 = new JLabel("FID");
		userLabel1.setBounds(60, 250, 50, 25);
		panel.add(userLabel1);

		JLabel userLabel2 = new JLabel("ID");
		userLabel2.setBounds(60, 280, 50, 25);
		panel.add(userLabel2);

		JLabel passLabel = new JLabel("PW");
		passLabel.setBounds(60, 310, 50, 25);
		panel.add(passLabel);

		userText1 = new JTextField(20);
		userText1.setBounds(110, 250, 160, 25);
		panel.add(userText1);

		userText2 = new JTextField(20);
		userText2.setBounds(110, 280, 160, 25);
		panel.add(userText2);

		passText = new JPasswordField(20);
		passText.setBounds(110, 310, 160, 25);
		panel.add(passText);
		passText.addActionListener(new ActionListener() { //패스워드 입력 후 엔터 치면 로그인 됨.
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});

		btnJoin = new JButton("회원가입");
		btnJoin.setBounds(90, 360, 90, 30);
		panel.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PMSDemo.showJoinFrame();// 회원가입창 띄우기
			}
		});

		btnJoin = new JButton("FID 생성");
		btnJoin.setBounds(210, 360, 90, 30);
		panel.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PMSDemo.showFIDFrame();// FID 생성창 생성
			}
		});

		btnLogin = new JButton("Login");
		btnLogin.setBounds(280, 250, 70, 85);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
	}

	public void isLoginCheck() {
		Scanner inputdata = null;// 입력데이터 값 읽어올 리더
		String line;
		String FID = null, ID = null, PW, Name = null, Relation = null;

		try {
			inputdata = new Scanner(new FileReader("./Data/Login/JoinMembership.txt"));
			while (inputdata.hasNext()) {
				FID = inputdata.next();
				ID = inputdata.next();
				PW = inputdata.next();
				Name = inputdata.next();
				Relation = inputdata.next();
				if (userText1.getText().equals(FID) == true && userText2.getText().equals(ID) == true
						&& new String(passText.getPassword()).equals(PW) == true) {
					bLoginCheck = true;
					break;
				} 
			}			
			inputdata.close();
			if (isLogin()) { //bLoginCheck가 true일 때만 실행
				PMSDemo.showMainFrame(stringToInt(FID), ID, Name, Relation);
			} else {
				JOptionPane.showMessageDialog(null, "존재하지 않는 회원정보 입니다.");
				userText1.setText("");
				userText2.setText("");
				passText.setText("");
			}			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "예상치 못한 에러 발생. 관리자에게 문의하세요.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "예상치 못한 에러 발생. 관리자에게 문의하세요.");
		}
	}

	// PropertyManagementSystemDemo와 연동
	public void setMain(PropertyManagementSystemDemo PMSDemo) {
		this.PMSDemo = PMSDemo;
	}

	public boolean isLogin() {
		return bLoginCheck;
	}

	public int stringToInt(String string) {
		return Integer.parseInt(string);
	}

	public String intToString(int integer) {
		return String.valueOf(integer);
	}
}