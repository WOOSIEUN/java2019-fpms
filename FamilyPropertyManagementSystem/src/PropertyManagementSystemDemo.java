
public class PropertyManagementSystemDemo {
	MainFrame mainFrame; //메인프레임
	LoginWindow loginView; //로그인화면
	Join JoinFrame; //회원가입 화면
	FID FIDFrame; //FID 생성화면

	public static void main(String[] args) {
		//PropertyManagementSystemDemo 클래스 실행
		PropertyManagementSystemDemo PMSDemo = new PropertyManagementSystemDemo();
		PMSDemo.loginView = new LoginWindow(); // 로그인창 보이기
		PMSDemo.loginView.setMain(PMSDemo); // 로그인창에게 데모 클래스보내기		
	}
	
	//회원가입창 띄우는 메소드
	public void showJoinFrame(){
		this.JoinFrame = new Join(); //회원가입창
		JoinFrame.setVisible(true);
	}
	
	public void showFIDFrame(){
		this.FIDFrame =new FID(); //가족 고유번호 생성
		FIDFrame.setVisible(true);
	}
	
	//메인프레임 띄우는 메소드
	public void showMainFrame(int FID, String ID, String Name, String Relation){
		loginView.dispose(); // 로그인창닫기
		this.mainFrame = new MainFrame(FID, ID, Name, Relation); //메인프레임 생성
	}

}