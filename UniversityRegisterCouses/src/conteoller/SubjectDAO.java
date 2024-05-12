package conteoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.SubjectVO;

public class SubjectDAO {
  // 학과 목록
  public void getSubjectTotalList() {
    String sql = "SELECT * FROM subject order by no";
    try (Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      SubjectVO sVo = null;
      while (rs.next()) {
        sVo = new SubjectVO();
        sVo.setNo(rs.getInt("no"));
        sVo.setS_num(rs.getString("s_num"));
        sVo.setS_name(rs.getString("s_name"));

        System.out.println(sVo.toString());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 학과 등록
  public void setSubjectRegister(SubjectVO svo) {
    String sql = "INSERT INTO subject VALUES(subject_seq.nextval, ?, ?)";
    try (Connection con = DBUtill.makeConnection()) {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, svo.getS_num());
      pstmt.setString(2, svo.getS_name());
      int value = pstmt.executeUpdate();
      if (value == 1) {
        System.out.println(svo.getS_name() + "학과등록 성공");
      } else {
        System.out.println(svo.getS_name() + "학과등록 실패");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 학과 수정
  public void setSubjectUpdate(SubjectVO svo) {
    String sql = "update subject set s_num = ?, s_name = ? where no = ?";
    Connection con = null;
    PreparedStatement pstmt = null;

    try {
    con = DBUtill.makeConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, svo.getS_num());
      pstmt.setString(2, svo.getS_name());
      pstmt.setInt(3, svo.getNo());

      int i = pstmt.executeUpdate();
      if(i == 1){
        System.out.println(svo.getS_name() + " 학과 수정 완료.");
        System.out.println(" 학과 수정 성공!!");
      }else{
        System.out.println("학과 수정 실패!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally{
      try {
        //데이터베이스와의 연결에 사용되었던 오브젝트를 해제
      if(pstmt != null){
          pstmt.close();
        }
        if(con != null){
          con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  //학과 삭제
  public void setSubjectDelete(int no){
    StringBuffer sql = new StringBuffer();
    sql.append("delete from subject where no = ?");

    Connection con = null;
    PreparedStatement pstmt = null;

    try {
      con = DBUtill.makeConnection();
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, no);

      int i = pstmt.executeUpdate();

      if(i == 1){
        System.out.println("학과 삭제 완료.");
        System.out.println("학과 삭제 성공!!");
      }else{
        System.out.println("학과 삭제 실패!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      try {
        //데이터베이스와의 연결에 사용되었던 오브젝트를 해제
      if(pstmt != null){
          pstmt.close();
        }
      if(con != null){
        con.close();
      }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
