package conteoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TraineeVO;

public class TraineeDAO {
  //수강 신청
  public void setTraineeRegiste(TraineeVO tvo){
    String sql = "INSERT INTO TRAINEE VALUES(trainee_seq.nextval, ?, ?, ?, sysdate)";
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, tvo.getSd_num());
      pstmt.setString(2, tvo.getL_abbre());
      pstmt.setString(3, tvo.getT_section());

      int i = pstmt.executeUpdate();

      if(i == 1){
        System.out.println("수강신청 완료.");
        System.out.println("수강신청 성공!!!");
      }else{
        System.out.println("수강신청 실패!!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //수강 신청 삭제
  public void setTraineeDelete(int no){
    StringBuffer sql = new StringBuffer();
    sql.append("DELETE FROM TRAINEE WHERE NO = ?");
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, no);

      int i = pstmt.executeUpdate();

      if(i == 1){
        System.out.println("수강 신청 취소 완료.");
        System.out.println("수강 신청 취소 성공!!!");
      }else{
        System.out.println("수강 신청 취소 실패!!!");

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //개인 수강 신청 전체 목록
  public void getTraineeTotalList(String sd_num){
    String sql = "SELECT tr.no, tr.sd_num as sd_num, tr.l_abbre as l_abbre,le.l_name as l_name , st.sd_name as sd_name, t_section, t_date"
    + "FROM TRAINEE tr, lesson le, student st"
    + "WHERE tr.sd_num = ? and tr.l_abbre = le.l_abbre and tr.sd_num = st.sd_num ORDER BY T_DATE";
    
    
    try (Connection con = DBUtill.makeConnection()){
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      System.out.println("일련번호\t학생번호\t\t과목약어\t과목명\t학생이름\t과목구분\t등록일");

      while(rs.next()){
        TraineeVO tvo = new TraineeVO();
        tvo.setNo(rs.getInt("no"));
        tvo.setSd_num(rs.getString("sd_num"));
        tvo.setL_abbre(rs.getString("l_abbre"));
        tvo.setT_section(rs.getString("t_section"));
        tvo.setT_date(rs.getString("t_date"));

        System.out.println(tvo.toString());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //선택한 과목명의 과목 번호
  public String getLessonNum(String lessonName){
    String l_abbre = "";
    String sql = "SELECT L_ABBRE FROM LESSON WHERE L_NAME = ?";
    
    try (Connection con = DBUtill.makeConnection()){
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      if(rs.next()){
        l_abbre = rs.getString("L_ABBRE");
      }else{
        System.out.println("수강 과목의 과목 번호");
        System.out.println("선택한" + lessonName + "과목의 과목번호가 없습니다.");
        System.out.println("과목 검색 실패");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return l_abbre;
  }
}
