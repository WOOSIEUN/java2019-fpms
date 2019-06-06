import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame implements ActionListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	public static final int ROW = 1000;
	public static final int COLUMN = 4;
	
	public MainFrame(int FID, String Name) {
		//프레임 설정
		super("Property Management System");
		setSize(400, 400);
		setResizable(false);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//입출력에 필요한 변수 선언
		int date, amount, cnt = 0;
		String majorClassification, minorClassification;
		String fileName = (intToString(FID) + " " + Name +".txt");
		
		//테이블 만들기 - 헤더와 데이터 불러올 배열 만들기
		String tableHeader[] = {"날짜","대분류","소분류","금액"};
		String contents[][] = new String[ROW][COLUMN];
		
		//데이터 파일에서 데이터 불러와서 contents 배열에 저장하기.		
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(fileName));			
		} catch (FileNotFoundException e) {
			System.out.println(fileName + "파일이 존재하지 않습니다. 프로그램 에러로 프로그램을 종료합니다.");
			System.exit(0);
		}
		while (inputStream.hasNext()) {
			try {
				contents[cnt][0] = intToString(inputStream.nextInt());
				contents[cnt][1] = inputStream.next();
				contents[cnt][2] = inputStream.next();
				contents[cnt++][3] = intToString(inputStream.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("파일 로딩에 에러가 발생했습니다. 프로그램을 종료합니다.");
				System.exit(0);
			}
		}
		DefaultTableModel tableModel = new DefaultTableModel(contents,tableHeader);
		JTable accountTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(accountTable);
		
		add(scrollPane);
		inputStream.close();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int stringToInt(String string) {
		return Integer.parseInt(string);
	}
	public String intToString(int integer) {
		return String.valueOf(integer);
	}
}
