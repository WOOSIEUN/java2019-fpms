	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		
		private Mainprocess main;
		private TestFrm test;
		
		public static void main(String[] args) {
			//new LoginWindow();
		}

		public LoginWindow() {
			// setting
			setTitle("Property Management");
			setSize(400, 600);
			setResizable(false);
			setLocation(800, 450);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			// panel
			JPanel panel = new JPanel();
			placeLoginPanel(panel);
			
			
			// add
			add(panel);
			
			// visible
			setVisible(true);
		}
		
		public void placeLoginPanel(JPanel panel){
			panel.setLayout(null);
			JLabel title = new JLabel("Property Management System");
			title.setBounds(150,140,100,100);
			panel.add(title);
			
			JLabel userLabel1 = new JLabel("FID");
			userLabel1.setBounds(80, 250, 80, 25);
			panel.add(userLabel1);

			JLabel userLabel2 = new JLabel("ID");
			userLabel2.setBounds(80, 280, 80, 25);
			panel.add(userLabel2);
			
			JLabel passLabel = new JLabel("Pass");
			passLabel.setBounds(80, 310, 80, 25);
			panel.add(passLabel);
			
			userText1 = new JTextField(20);
			userText1.setBounds(170, 250, 160, 25);
			panel.add(userText1);
			
			userText2 = new JTextField(20);
			userText2.setBounds(170,280,160,25);
			panel.add(userText2);
			
			passText = new JPasswordField(20);
			passText.setBounds(170, 310, 160, 25);
			panel.add(passText);
			passText.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					isLoginCheck();			
				}
			});
			
			btnJoin = new JButton("Join");
			btnJoin.setBounds(100, 350, 100, 25);
			panel.add(btnJoin);
			btnJoin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					main.showFrameTest();//회원가입창 띄우기
				}
			});
			
			btnLogin = new JButton("Login");
			btnLogin.setBounds(220, 350, 100, 25);
			panel.add(btnLogin);
			btnLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isLoginCheck();
				}
			});
		}
		
		public void isLoginCheck(){
			BufferedReader inputdata = null;//입력데이터 값 읽어올 리더
			String line;
			
			String fid,id,pass;
			
			try {
				
				inputdata = new BufferedReader(new FileReader("Inputdata.txt"));
				line = inputdata.readLine();
				while(line !=null)
				{
					StringTokenizer token= new StringTokenizer(line," ");
					fid= token.nextToken();
					id=token.nextToken();
					pass=token.nextToken();
						if(userText1.getText().equals(fid)&&userText2.getText().equals(id) && new String(passText.getPassword()).equals(pass)){
						JOptionPane.showMessageDialog(null, "Success");
						bLoginCheck = true;
				
						if(isLogin()){
							main.showFrameTest();// 메인창 메소드를 이용해 창뛰우기
					
						}
				
				// 로그인 성공이라면 매니져창 뛰우기
						break;		
					}
						else{
							JOptionPane.showMessageDialog(null, "Fail");
							userText1.setText(" ");
							userText2.setText(" ");
							passText.setText(" ");
			
				
						}
						line = inputdata.readLine();
			
					}
				inputdata.close();
			
		}
			catch(FileNotFoundException e) {
			System.exit(0);
		}
			catch(IOException e) {
				System.exit(0);
			}
			
		
		}

		
		// mainProcess와 연동
		public void setMain(Mainprocess main) {
			this.main = main;
		}
		

		public boolean isLogin() {		
			return bLoginCheck;
		}

	}
	
	
	

