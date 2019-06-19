import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Help extends JFrame implements ActionListener {
	
	JTextArea helpTextArea;
	
	public Help() {
		setTitle("도움말");
		setSize(460, 600);
		setResizable(false);
		setLocation(960, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JLabel guideLabel = new JLabel("하단 버튼 중 안내를 원하는 항목을 선택해주세요.");
		add(guideLabel, BorderLayout.NORTH);
		
		helpTextArea = new JTextArea(10,10);
		helpTextArea.setEditable(false);	
		helpTextArea.setLineWrap(true);
		JScrollPane helpScroll = new JScrollPane(helpTextArea);
		helpScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(helpScroll, BorderLayout.CENTER);
		
		JPanel selectGuide = new JPanel();
		selectGuide.setLayout(new BoxLayout(selectGuide, BoxLayout.X_AXIS));
		
		JButton systemIntroduction = new JButton("시스템 안내");
		systemIntroduction.addActionListener(this);
		selectGuide.add(systemIntroduction);
		
		JButton signUpAndIn = new JButton("가입과 로그인");
		signUpAndIn.addActionListener(this);
		selectGuide.add(signUpAndIn);
		
		JButton useProgram = new JButton("이용 안내");
		useProgram.addActionListener(this);
		selectGuide.add(useProgram);
		
		JButton advancedSetting = new JButton("고급 설정");
		advancedSetting.addActionListener(this);
		selectGuide.add(advancedSetting);
		
		JButton errorguide = new JButton("오류");
		errorguide.addActionListener(this);
		selectGuide.add(errorguide);
		
		add(selectGuide, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		if (event.equals("시스템 안내")) {
			helpTextArea.setText("시스템 안내\r\n" + 
					"\r\n" + 
					"가족 공동 자산 관리 시스템 입니다.\r\n" + 
					"가족 계좌와 개인 계좌로 나누어 자산을 관리할 수 있으며, 같은 가족은 함께 가족 계좌를 기록하고 수정할 수 있습니다.");
		} else if (event.equals("가입과 로그인")) {
			helpTextArea.setText("가입\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"1. FID (가족 고유 번호)\r\n" + 
					"- 가족의 식별은 FID를 통해 이루어집니다.\r\n" + 
					"- FID 생성은 필수적이며, 가족당 하나의 FID가 필요합니다.\r\n" + 
					"-  생성받은 FID 값은 따로 기록해두시길 바라며 FID 분실시 관리자에게 연락해주시길 바랍니다.\r\n" + 
					"- 가족이 아닌 타인과 FID를 공유하지 마십시오. 가족 공동계좌의 내용이 유출될 가능성이 있습니다.\r\n" + 
					"\r\n" + 
					"2. 회원가입\r\n" + 
					"- 가입을 위해서 가족 고유번호의 유효성 검사와 ID 중복검사를 필요로 합니다.\r\n" + 
					"- 두 가지 검사를 끝내지 못하면 가입을 하실 수 없습니다.\r\n" + 
					"- 회원 가입 이후 정보 수정이 필요할 시엔 관리자에게 문의해주세요.\r\n" + 
					"\r\n" + 
					"3. 로그인\r\n" + 
					"- 패스워드 입력 후 엔터를 치면 로그인 버튼을 누르지 않고도 로그인을 할 수 있습니다.");
		} else if (event.equals("이용 안내")) {
			helpTextArea.setText("이용 안내\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"1. 화면 소개\r\n" + 
					"- 왼쪽은 개인 계좌, 오른쪽은 가족 계좌입니다. 개인 계좌의 내용은 본인만 볼 수 있습니다.\r\n" + 
					"- 하단에는 추가, 수정, 삭제를 할 수 있는 메뉴가 있습니다.\r\n" + 
					"- 상단 메뉴에서 저장, 가족 보기, 도움말을 볼 수 있습니다.\r\n" + 
					"- 각 계좌 위에는 수입, 지출, 총합을 볼 수 있는 영역이 있습니다. 해당 영역은 추가, 수정, 삭제 마다 재계산됩니다.\r\n" + 
					"\r\n" + 
					"2. 데이터 추가\r\n" + 
					"- 날짜는 년 월 일 순으로 점이나 쉼표 없이 숫자만 입력해주세요. (ex.2000.01.01)\r\n" + 
					"- 대분류에는 수입 또는 지출 중 한 가지만 기입해주세요.\r\n" + 
					"- 소분류에는 원하는 값을 입력할 수 있습니다. (ex. 교통, 용돈, 식비)\r\n" + 
					"- 금액란에는 정수만 입력해주세요.\r\n" + 
					"- 가족 또는 개인을 선택해서 추가를 누르면 계좌에 추가가 됩니다.\r\n" + 
					"- 모든 칸에는 띄어쓰기를 해서는 안됩니다. 프로그램 에러를 유발하니 띄어쓰기를 절대 하지 마십시오.\r\n" + 
					"- 빈칸을 입력하지 마십시오. 이 또한 프로그램 에러의 원인이 됩니다.\r\n" + 
					"\r\n" + 
					"3. 데이터 삭제\r\n" + 
					"- 데이터 삭제를 할 열을 선택 후 삭제 버튼을 누르면 데이터가 삭제됩니다.\r\n" + 
					"\r\n" + 
					"4. 데이터 수정\r\n" + 
					"- 날짜, 대분류, 소분류, 금액 중 수정할 값을 먼저 입력하십시오.\r\n" + 
					"- 입력 후 가족 계좌인지, 개인 계좌인지 선택해주십시오.\r\n" + 
					"- 위 과정을 완료하고 수정할 셀을 선택하십시오, 그 후 바로 수정을 누르면 정상적으로 수정이 됩니다.\r\n" + 
					"- 셀을 더블클릭하여 수정할 시 합계 기능이 정상적으로 작동하지 않습니다,\r\n" + 
					"- 모든 칸에는 띄어쓰기를 해서는 안됩니다. 프로그램 에러를 유발하니 띄어쓰기를 절대 하지 마십시오.\r\n" + 
					"- 빈칸을 입력하지 마십시오. 이 또한 프로그램 에러의 원인이 됩니다.\r\n" + 
					"\r\n" + 
					"5. 데이터 저장\r\n" + 
					"- 상단 메뉴 - 저장을 클릭하면 저장이 됩니다.\r\n" + 
					"- 가족이 동시에 시스템을 사용할 시 가족 계좌의 추가, 수정, 삭제가 정상적으로 작동하지 않을 수 있습니다.\r\n" + 
					"- 저장 전 반드시 기입한 내용에 오류가 없는지 확인해주세요.\r\n" + 
					"\r\n" + 
					"6. 가족 보기\r\n" + 
					"- FID가 같은 회원의 목록을 보여줍니다.\r\n" + 
					"- 가족이 아닌 회원이 목록에 있다면 관리자에게 연락주세요.\r\n" + 
					"\r\n" + 
					"7. 도움말\r\n" + 
					"- 이용시 문제가 생기면 참고할 수 있습니다.\r\n" + 
					"- 그 외 궁금한 점이나 오류가 발견되면 관리자에게 연락주세요.");
		} else if (event.equals("고급 설정")) {
			helpTextArea.setText("고급 설정\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"1. 해당 프로그램의 FID.txt, ID.txt, JoinMembership.txt 파일은 첫 실행시에도 반드시 존재해야 합니다.\r\n" + 
					"\r\n" + 
					"2. FID ID JoinMembership 파일에 테스트 케이스 하나는 꼭 입력해야하며, ./Data/Account 내에도 가족, 개인 데이터 파일 하나씩 필수적으로 입력해야합니다.");
		} else if (event.equals("오류")) {
			helpTextArea.setText("오류\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"기타 오류 발생시 관리자에게 연락해주세요.\r\n" + 
					"\r\n" + 
					"연락처 : ramtk6726@naver.com");
		} else {
			JOptionPane.showMessageDialog(null, "예상치 못한 에러 발생. 관리자에게 문의하세요.");
		}		
	}
	
}
