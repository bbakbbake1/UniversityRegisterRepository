package conteoller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.StudentVO;

public class StudentRegisterManager {

	//학생 정보 등록
	public void studentRegister() {
		Scanner input = new Scanner(System.in);

		SubjectDAO sdao = new SubjectDAO();
		StudentDAO sd = new StudentDAO();
		StudentVO svo = new StudentVO();

		String sd_num;
		String sd_name;
		String sd_id;
		String sd_passwd;
		String s_num;
		String sd_birthday;
		String sd_phone;
		String sd_address;
		String sd_email;

		boolean id_check;
		String year;

		System.out.println("학생 정보 입력");
		System.out.println("성명 : ");
		sd_name = input.nextLine();
		do{
			System.out.println("아이디(8자 이상 12자 이내) : ");
			sd_id = input.nextLine();
			id_check = sd.getStudentIdOverlap(sd_id);
			if(id_check){
				System.out.println("중복된 아이디입니다. 다시 입력하세요.");
			}
		}while(id_check);

		System.out.println("비밀번호(12자 이내) : ");
		sd_passwd = input.nextLine();

		sdao.getSubjectTotalList();

		System.out.println("학과번호 : ");
		s_num = input.nextLine();

		//학생 번호는 8자리로 생성한다. (연도2자리+학과2자리+일련번호  _ 예로06010001)
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		year = sdf.format(new Date());
		sd_num = year + s_num + sd.getStudentCount(s_num);

		System.out.println("생년월일(8자리) : ");
		sd_birthday = input.nextLine();
		System.out.println("전화번호 : ");
		sd_phone = input.nextLine();
		System.out.println("도로명 주소 : ");
		sd_address = input.nextLine();
		System.out.println("이메일 : ");
		sd_email = input.nextLine();

		svo.setSd_num(sd_num);
		svo.setSd_name(sd_name);
		svo.setSd_id(sd_id);
		svo.setSd_passwd(sd_passwd);
		svo.setS_num(s_num);
		svo.setSd_birthday(sd_birthday);
		svo.setSd_phone(sd_phone);
		svo.setSd_address(sd_address);
		svo.setSd_email(sd_email);

		sd.setStudentRegiste(svo);

		System.out.println();
		System.out.println("등록 학생 정보");
		sd.getStudent(svo.getSd_id(), svo.getSd_passwd());
		System.out.println();
	}
	//학생 정보 수정
	public void studentUpdate() {
		Scanner input = new Scanner(System.in);
		StudentDAO sdao = new StudentDAO();
		StudentVO svo = new StudentVO();

		String id;
		String pw;
		String sd_num;
		String sd_passwd;
		String sd_phone;
		String sd_address;
		String sd_email;

		boolean success = false;

		System.out.println("학생 정보 수정");
		do{
			System.out.println("아이디 : ");
			id = input.nextLine();
			System.out.println("비밀번호 : ");
			pw = input.nextLine();
			success = sdao.getStudentLogin(id, pw);
			if(!success){
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
			}
		}while(!success);

		sd_num = sdao.getStudentNum(id, pw);
		System.out.println();
		System.out.println("수정할 학생");
		System.out.println("학생번호 : " + sd_num);

		System.out.println("비밀번호(12자 이내) : ");
		sd_passwd = input.nextLine();
		System.out.println("전화번호 : ");
		sd_phone = input.nextLine();
		System.out.println("도로명 주소 : ");
		sd_address = input.nextLine();
		System.out.println("이메일 : ");
		sd_email = input.nextLine();

		svo.setSd_num(sd_num);
		svo.setSd_passwd(sd_passwd);
		svo.setSd_phone(sd_phone);
		svo.setSd_address(sd_address);
		svo.setSd_email(sd_email);

		sdao.getStudent(id, svo.getSd_passwd());
		System.out.println();
	}
	//학생 전체 목록
	public void studentTotalList() {
		Scanner input = new Scanner(System.in);

		StudentDAO sdao = new StudentDAO();

		String pw;

		System.out.println("학생 정보 전체 목록");
		System.out.println("관리자 비밀번호 : ");
		pw = input.nextLine();

		if(pw.equals("admin1234")){
			sdao.getStudentTotalList();
		}else{
			System.out.println("관리자 비밀번호가 틀립니다.");
		}
	}
	
	
}
