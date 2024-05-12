package conteoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LessonVO;

public class LessonDAO {
  //과목 목록
  public void getLessonTotalList(){
    String sql = "SELECT * FROM LESSON ORDER BY NO";
    LessonVO lvo = null;
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      System.out.println("일련번호\t과목약어\t과목명");
      while(rs.next()){
        lvo = new LessonVO();
        lvo.setNo(rs.getInt("no"));
        lvo.setL_abbre(rs.getString("l_abbre"));
        lvo.setL_name(rs.getString("l_name"));

        System.out.println(lvo.getNo() + "\t" + lvo.getL_abbre() + "\t" + lvo.getL_name());

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //과목 등록
  public void setLessonRegiste(LessonVO lvo){
    String sql = "INSERT INTO LESSON VALUES(lesson_seq.nextval, ?, ?)";
    
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, lvo.getL_abbre());
      pstmt.setString(2, lvo.getL_name());

      int i = pstmt.executeUpdate();
      if(i == 1){
        System.out.println(lvo.getL_name() + " 과목 등록 완료");
        System.out.println("과목 등록 성공!!");
      }else{
        System.out.println("과목 등록 실패!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //과목 수정
  public boolean setLessonUpdate(LessonVO lvo){
    String sql = "UPDATE LESSON SET L_ABBRE = ?, L_NAME = ? WHERE NO = ?";
    boolean lessonUpdateSuccess = false;
    try(Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, lvo.getL_abbre());
      pstmt.setString(2, lvo.getL_name());
      pstmt.setInt(3, lvo.getNo());

      int i = pstmt.executeUpdate();
      if(i == 1){
        System.out.println(lvo.getL_name() + " 과목 수정 완료");
        System.out.println("과목 수정 성공!!");
      }else{
        System.out.println("과목 수정 실패!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lessonUpdateSuccess;
  }
  //과목 삭제
  public void setLessonDelete(int no){
    StringBuffer sql = new StringBuffer();
    sql.append("DELETE FROM LESSON WHERE NO = ?");

    
    try (Connection con = DBUtill.makeConnection()){
        PreparedStatement pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, no);

        int i = pstmt.executeUpdate();

        if(i == 1){
          System.out.println("과목 삭제 완료.");
          System.out.println("과목 삭제 성공!!");
        }else{
          System.out.println("과목 삭제 실패!!");
        }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
