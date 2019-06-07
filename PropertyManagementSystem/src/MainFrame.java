import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame implements ActionListener{
   private static final int WIDTH = 1000;
   private static final int HEIGHT = 500;
   private int ROW;
   private static final int COLUMN = 4;

   //파일 이름
   private String personalFileName;
   private String familyFileName;
   //개인과 가족별 ROW count
   private int cnt;
   //테이블 관련
   DefaultTableModel personalAccountTableModel;
   JTable personalAccountTable;
   JScrollPane personalScrollPane;
   DefaultTableModel familyAccountTableModel;
   JTable familyAccountTable;
   JScrollPane familyScrollPane;
   //개인과 가족별 수입 지출 총합 텍스트필드
   JTextField personalIncome;
   JTextField personalconsumption;
   JTextField personalTotal;
   JTextField familyIncome;
   JTextField familyconsumption;
   JTextField familyTotal;
   //데이터 삽입에 필요한 텍스트필드
   JTextField dateField;
   JTextField majorClassificationField;
   JTextField minorClassificationField;
   JTextField amountField;
   //콤보박스 생성
   DefaultComboBoxModel selectComboModel;
   JComboBox selectCombo;

   public MainFrame(int FID, String Name) {
      //프레임 설정
      super("Property Management System");
      setSize(400, 400);
      setResizable(false);      
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      //입출력에 필요한 변수 초기화
      cnt = 0;

      //파일 이름 설정
      familyFileName = (intToString(FID) + ".txt");
      personalFileName = (intToString(FID) + " " + Name +".txt");

      //테이블을 붙일 panel 생성
      JPanel tablePanel = new JPanel();
      tablePanel.setLayout(new GridLayout(1,2));

      //테이블 만들기 - 헤더와 데이터 불러올 배열 만들기
      String tableHeader[] = {"날짜","대분류","소분류","금액"};
      String personalContents[][] = null;
      String familyContents[][] = null;

      //개인 계좌 데이터 로드
      Scanner inputStream = null;
      try {
         inputStream = new Scanner(new FileInputStream(personalFileName));         
      } catch (FileNotFoundException e) {
         System.out.println(personalFileName + "파일이 존재하지 않습니다. 프로그램 에러로 프로그램을 종료합니다.");
         System.exit(0);
      }
      //배열의 행의 개수 읽어오기.
      try {
         ROW = inputStream.nextInt();
         personalContents = new String[ROW][COLUMN];
      } catch (InputMismatchException e) {
         System.out.println("파일 로딩에 에러가 발생했습니다. 프로그램을 종료합니다.");
         System.exit(0);
      }
      inputStream.nextLine();
      //배열에 값 저장하기
      while (inputStream.hasNext()) {
         try {
            personalContents[cnt][0] = intToString(inputStream.nextInt());
            personalContents[cnt][1] = inputStream.next();
            personalContents[cnt][2] = inputStream.next();
            personalContents[cnt++][3] = intToString(inputStream.nextInt());
         } catch (InputMismatchException e) {
            System.out.println("파일 로딩에 에러가 발생했습니다. 프로그램을 종료합니다.");
            System.exit(0);
         }
      }
      inputStream.close();
      //테이블 만들기
      personalAccountTableModel = new DefaultTableModel(personalContents,tableHeader);
      personalAccountTable = new JTable(personalAccountTableModel);
      personalScrollPane = new JScrollPane(personalAccountTable);
      tablePanel.add(personalScrollPane);
      
      cnt=0;
      //가족 계좌 데이터 로드
      try {
         inputStream = new Scanner(new FileInputStream(familyFileName));         
      } catch (FileNotFoundException e) {
         System.out.println(familyFileName + "파일이 존재하지 않습니다. 프로그램 에러로 프로그램을 종료합니다.");
         System.exit(0);
      }
      //배열의 행의 개수 읽어오기.
      try {
         ROW = inputStream.nextInt();
         familyContents = new String[ROW][COLUMN];
      } catch (InputMismatchException e) {
         System.out.println("파일 로딩에 에러가 발생했습니다. 프로그램을 종료합니다.");
         System.exit(0);
      }
      inputStream.nextLine();
      //배열에 값 저장하기
      while (inputStream.hasNext()) {
         try {
            familyContents[cnt][0] = intToString(inputStream.nextInt());
            familyContents[cnt][1] = inputStream.next();
            familyContents[cnt][2] = inputStream.next();
            familyContents[cnt++][3] = intToString(inputStream.nextInt());
         } catch (InputMismatchException e) {
            System.out.println("파일 로딩에 에러가 발생했습니다. 프로그램을 종료합니다.");
            System.exit(0);
         }
      }
      inputStream.close();
      //테이블 만들기
      familyAccountTableModel = new DefaultTableModel(familyContents,tableHeader);
      familyAccountTable = new JTable(familyAccountTableModel);
      familyScrollPane = new JScrollPane(familyAccountTable);
      tablePanel.add(familyScrollPane);
      add(tablePanel, BorderLayout.CENTER);

      //수입 지출 총합을 출력할 때 쓸 패널 생성.
      JPanel sumPanel = new JPanel();
      sumPanel.setLayout(new GridLayout(2,2));
      JPanel personalSumPanel = new JPanel();
      personalSumPanel.setLayout(new BoxLayout(personalSumPanel, BoxLayout.X_AXIS));
      JPanel familySumPanel = new JPanel();
      familySumPanel.setLayout(new BoxLayout(familySumPanel, BoxLayout.X_AXIS));
      JPanel personalSumLabelPanel = new JPanel();
      personalSumLabelPanel.setLayout(new BoxLayout(personalSumLabelPanel, BoxLayout.X_AXIS));
      JPanel familySumLabelPanel = new JPanel();
      familySumLabelPanel.setLayout(new BoxLayout(familySumLabelPanel, BoxLayout.X_AXIS));

      //라벨 생성 및 sumPanel에 붙이기
      JLabel personalIncomeLabel = new JLabel("  개인소비  ");
      personalSumLabelPanel.add(personalIncomeLabel);
      JLabel personalconsumptionLabel = new JLabel("  개인지출  ");
      personalSumLabelPanel.add(personalconsumptionLabel);
      JLabel personalTotalLabel = new JLabel("  개인총합  ");
      personalSumLabelPanel.add(personalTotalLabel);
      JLabel familyIncomeLabel = new JLabel("  가족소비  ");
      familySumLabelPanel.add(familyIncomeLabel);
      JLabel familyconsumptionLabel = new JLabel("  가족지출  ");
      familySumLabelPanel.add(familyconsumptionLabel);
      JLabel familyTotalLabel = new JLabel("  가족총합  ");
      familySumLabelPanel.add(familyTotalLabel);
      sumPanel.add(personalSumLabelPanel);
      sumPanel.add(familySumLabelPanel);

      //각 텍스트필드 생성과 수정 불가하게 초기화, 각 패널에 붙이기
      personalIncome = new JTextField("");
      personalIncome.setEditable(false);
      personalSumPanel.add(personalIncome);
      personalconsumption = new JTextField("");
      personalconsumption.setEditable(false);
      personalSumPanel.add(personalconsumption);
      personalTotal = new JTextField("");
      personalTotal.setEditable(false);
      personalSumPanel.add(personalTotal);
      familyIncome = new JTextField("");
      familyIncome.setEditable(false);
      familySumPanel.add(familyIncome);
      familyconsumption = new JTextField("");
      familyconsumption.setEditable(false);
      familySumPanel.add(familyconsumption);
      familyTotal = new JTextField("");
      familyTotal.setEditable(false);
      familySumPanel.add(familyTotal);
      sumPanel.add(personalSumPanel);
      sumPanel.add(familySumPanel);
      add(sumPanel, BorderLayout.NORTH);

      //각 총합 계산하는 메소드 필요 -> 해당 메소드는 값 수정 삭제 입력마다 필요할 것으로 예상.

      //데이터 삽입 삭제 저장
      //패널 생성
      JPanel dataManagementPanel = new JPanel();
      dataManagementPanel.setLayout(new GridLayout(3,4));

      //데이터 삽입에 필요한 Text Field와 안내문구를 넣을 라벨 생성.      
      JLabel dateLabel = new JLabel("날짜");
      dataManagementPanel.add(dateLabel);
      dateField = new JTextField("(yyyymmdd)");
      dataManagementPanel.add(dateField);
      JLabel majorClassificationLabel = new JLabel("대분류");
      dataManagementPanel.add(majorClassificationLabel);
      majorClassificationField = new JTextField("(수입/지출)");
      dataManagementPanel.add(majorClassificationField);
      JLabel minorClassificationLabel = new JLabel("소분류");
      dataManagementPanel.add(minorClassificationLabel);
      minorClassificationField = new JTextField("(띄어쓰기 금지)");
      dataManagementPanel.add(minorClassificationField);
      JLabel amountLabel = new JLabel("금액");
      dataManagementPanel.add(amountLabel);
      JTextField amountField = new JTextField("정수입력");
      dataManagementPanel.add(amountField);
      
      
      
      //콤보박스 추가
      String []selectPORF = new String[]{"가족", "개인"};
      selectCombo = new JComboBox();       
      selectComboModel = new DefaultComboBoxModel(selectPORF);
      selectCombo.setModel(selectComboModel);
      dataManagementPanel.add(selectCombo);
      
      //버튼 추가 - 추가 삭제
      
      JButton insert = new JButton("추가");
      insert.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();
             if(event == "추가") {
                //필드에 입력한 값 받기
                String inputString[] = new String[4];
                inputString[0] = dateField.getText();
                inputString[1] = majorClassificationField.getText();
                inputString[2] = minorClassificationField.getText();
                inputString[3] = amountField.getText(); 
                String select = (String)selectComboModel.getSelectedItem();
                //필드 값 입력
                if(select == "가족") {
                   familyAccountTableModel.addRow(inputString);
                } else if (select == "개인" ) {
                   personalAccountTableModel.addRow(inputString);
                } else {
                   System.out.println("예상치 못한 에러가 발생했습니다. 관리자에게 문의해주세요.");
                }          
                //필드 초기화
                dateField.setText("");
                majorClassificationField.setText("");
                minorClassificationField.setText("");
                amountField.setText(""); 
             } else {
                System.out.println("예상치 못한 에러가 발생했습니다. 관리자에게 문의해주세요.");
             }
         }
      });
      dataManagementPanel.add(insert);
      JButton delete = new JButton("삭제");
      delete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();
            if (event == "삭제") {
                if(personalAccountTable.getSelectedRow() != -1) {
                   personalAccountTableModel.removeRow(personalAccountTable.getSelectedRow());
                } else if (familyAccountTable.getSelectedRow() != -1){
                   familyAccountTableModel.removeRow(familyAccountTable.getSelectedRow());
                } else {
                   return;
                }   
             } else {
                System.out.println("예상치 못한 에러가 발생했습니다. 관리자에게 문의해주세요.");
             }
         }
      });
      dataManagementPanel.add(delete);
      JButton modify = new JButton("수정");
      modify.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();            
            if (event == "수정") {
               String select = (String)selectComboModel.getSelectedItem();
               String modifyString = null;
               if(select == "개인" && personalAccountTable.getSelectedRow() != -1 && personalAccountTable.getSelectedColumn() != -1) {
                  //선택한 열에 따라 수정할 값을 입력한 필드의 값을 modifyString 변수에 넣음
                  if(personalAccountTable.getSelectedColumn() == 0) {
                     modifyString = dateField.getText();
                  } else if(personalAccountTable.getSelectedColumn() == 1) {
                     modifyString = majorClassificationField.getText();
                  } else if(personalAccountTable.getSelectedColumn() == 2) {
                     modifyString = minorClassificationField.getText();
                  } else if(personalAccountTable.getSelectedColumn() == 3) {
                     modifyString = amountField.getText();
                  } else {
                     return;
                  }
                  //선택한 행과 열의 데이터 수정
                  personalAccountTableModel.setValueAt(modifyString,personalAccountTable.getSelectedRow(),personalAccountTable.getSelectedColumn());             
                } else if (select == "가족" && familyAccountTable.getSelectedRow() != -1 && familyAccountTable.getSelectedColumn() != -1){
                   // 가족 부분 수정코드. 위와 동일
                   if(familyAccountTable.getSelectedColumn() == 0) {
                     modifyString = dateField.getText();
                  } else if(familyAccountTable.getSelectedColumn() == 1) {
                     modifyString = majorClassificationField.getText();
                  } else if(familyAccountTable.getSelectedColumn() == 2) {
                     modifyString = minorClassificationField.getText();
                  } else if(familyAccountTable.getSelectedColumn() == 3) {
                     modifyString = amountField.getText();
                  } else {
                     return;
                  }
                   familyAccountTable.setValueAt(modifyString,familyAccountTable.getSelectedRow(),familyAccountTable.getSelectedColumn());
                } else {
                   return;
                }
                if(personalAccountTable.getSelectedColumn() == 0 || familyAccountTable.getSelectedColumn() == 0) {
//                   //날짜순 정렬 필요
                }
             } else {
                System.out.println("예상치 못한 에러가 발생했습니다. 관리자에게 문의해주세요.");
             }
         }
      });
      dataManagementPanel.add(modify);
            
      //dataMangementPanel 메인프레임 SOUTH에 추가
      add(dataManagementPanel, BorderLayout.SOUTH);
      

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String event = e.getActionCommand();
       if (event == "삭제") {
          if(personalAccountTable.getSelectedRow() != -1) {
             personalAccountTableModel.removeRow(personalAccountTable.getSelectedRow());
          } else if (familyAccountTable.getSelectedRow() != -1){
             familyAccountTableModel.removeRow(familyAccountTable.getSelectedRow());
          } else {
             return;
          }   
       } else {
         System.out.println("예상치 못한 에러가 발생했습니다. 관리자에게 문의해주세요.");
      }  
   }

   public int stringToInt(String string) {
      return Integer.parseInt(string);
   }
   public String intToString(int integer) {
      return String.valueOf(integer);
   }
}