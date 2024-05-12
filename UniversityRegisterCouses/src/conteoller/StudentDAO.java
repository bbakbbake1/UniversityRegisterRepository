package conteoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.StudentVO;

public class StudentDAO {
  //학생등록
  public void setStudentRegiste(StudentVO svo){
    String sql = "INSERT INTO STUDENT VALUES(STUDENT_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, svo.getSd_num());
      pstmt.setString(2, svo.getSd_name());
      pstmt.setString(3, svo.getSd_id());
      pstmt.setString(4, svo.getSd_passwd());
      pstmt.setString(5, svo.getS_num());
      pstmt.setString(6, svo.getSd_birthday());
      pstmt.setString(7, svo.getSd_phone());
      pstmt.setString(8, svo.getSd_address());
      pstmt.setString(9, svo.getSd_email());
      
      int i = pstmt.executeUpdate();
      
      if(i == 1){
        System.out.println(svo.getSd_name() + " 학생 등록 완료.");
        System.out.println("학생등록 성공!!");
      }else{
        System.out.println("학생등록 실패!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //학생 정보 수정
  public void setStudentUpdate(StudentVO svo){
    String sql = "UPDATE STUDENT SET SD_PASSWD = ?, SD_PHONE = ?, SD_ADDRESS = ?, SD_EMAIL = ? WHERE SD_NUM = ?";
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, svo.getSd_passwd());
      pstmt.setString(2, svo.getSd_phone());
      pstmt.setString(3, svo.getSd_address());
      pstmt.setString(4, svo.getSd_email());
      pstmt.setString(5, svo.getSd_num());

      int i = pstmt.executeUpdate();
      if(i == 1){
        System.out.println(svo.getSd_name() + "학생정보 수정완료.");
        System.out.println("학생정보 수정 성공!!");
      }else{
        System.out.println("학생정보 수정 실패!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //동일 학과 학생 일련번호
  public String getStudentCount(String subjectNum){
    String sql = "SELECT LPAD(count(*)+1, 4, '0') AS STUDENTCOUNT FROM STUDENT WHERE S_NUM = ?";
    String serialNumber = "";
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, subjectNum);
      ResultSet rs = pstmt.executeQuery();
      if(rs.next()){
        serialNumber = rs.getString("STUDENTCOUNT");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return serialNumber;
  }
  //학생 아이디 중복 체크
  public boolean getStudentIdOverlap(String idOverlap){
    String sql = "SELECT * FROM STUDENT WHERE SD_ID = ?";
    boolean idOverlapResult = false;
    
    try (Connection con = DBUtill.makeConnection()){
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, idOverlap);
      ResultSet rs = pstmt.executeQuery();
      if(rs.next()){
        idOverlapResult = true;     //중복된 아이디가 있다.
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return idOverlapResult;
  }
  //학생 로그인
  public boolean getStudentLogin(String id, String pw){
    String sql = "SELECT * FROM STUDENT WHERE SD_ID = ? AND SD_PASSWD = ?";
    boolean loginSuccess = false;
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      
      if(rs.next()){
        loginSuccess = true;    //로그인 성공
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return loginSuccess;
  }
  
  //학생번호
  public String getStudentNum(String id, String pw){
    String sql = "SELECT SD_NUM FROM STUDENT WHERE SD_ID = ? AND SD_PASSWD = ?";
    String sd_num = "";

    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
    
      if(rs.next()){
        sd_num = rs.getString("SD_NUM");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sd_num;
  }
  //학생 정보
  public void getStudent(String id, String pw){
    String sql = "SELECT * FROM STUDENT WHERE SD_ID = ? AND SD_PASSWD = ?";
    StudentVO svo = null;
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, pw);
      ResultSet rs = pstmt.executeQuery();
      

      System.out.println("일련번호\t학생번호\t\t성명\t아디디\t\t비밀번호\t\t학과\t\t생년월일\t\t전화번호\t\t주소\t\t\t이메일\t\t\t등록일자");
      
      if(rs.next()){
          svo = new StudentVO();
          svo.setNo(rs.getInt("no"));
          svo.setSd_num(rs.getString("sd_num"));
          svo.setSd_name(rs.getString("sd_name"));
          svo.setSd_id(rs.getString("sd_id"));
          svo.setSd_passwd(rs.getString("sd_passwd"));
          svo.setS_num(rs.getString("s_num"));
          svo.setSd_birthday(rs.getString("sd_birthday"));
          svo.setSd_phone(rs.getString("sd_phone"));
          svo.setSd_address(rs.getString("sd_address"));
          svo.setSd_email(rs.getString("sd_email"));
          svo.setSd_date(rs.getDate("sd_date") + "");
          System.out.println(svo.toString());
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //학생 전체 목록
  public void getStudentTotalList(){
    String sql = "SELECT ST.NO AS NO, SD_NUM, SD_NAME, SD_ID, SD_PASSWD, SU.S_NAME AS S_NUM, SD_BIRTHDAY, SD_PHONE, SD_ADDRESS, SD_EMAIL, SD_DATE FROM STUDENT ST JOIN  SUBJECT SU ON  ST.S_NUM = SU.S_NUM ORDER BY NO";
    StudentVO svo = null;
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      System.out.println("일련번호\t학생번호\t\t성명\t아이디\t\t비밀번호\t\t학과\t\t생년월일\t\t전화번호\t\t주소\t\t\t이메일\t\t\t등록일자");

      while(rs.next()){
        svo = new StudentVO();
        svo.setNo(rs.getInt("no"));
        svo.setSd_num(rs.getString("sd_num"));
        svo.setSd_name(rs.getString("sd_name"));
        svo.setSd_id(rs.getString("sd_id"));
        svo.setSd_passwd(rs.getString("sd_passwd"));
        svo.setS_num(rs.getString("s_num"));
        svo.setSd_birthday(rs.getString("sd_birthday"));
        svo.setSd_phone(rs.getString("sd_phone"));
        svo.setSd_address(rs.getString("sd_address"));
        svo.setSd_email(rs.getString("sd_email"));
        svo.setSd_date(rs.getString("sd_date") + "");
        System.out.println(svo.toString());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
