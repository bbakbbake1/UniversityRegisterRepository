package conteoller;

import java.util.Scanner;

import model.LessonVO;
import model.TraineeVO;

public class TraineeRegisterManager {
	//수강 신청 리스트
	public void traineeList() {
		Scanner input = new Scanner(System.in);

		TraineeDAO td = new TraineeDAO();
		StudentDAO sdao = new StudentDAO();

		String id;
		String pw;
		boolean success = false;

		System.out.println("수강 신청한 리스트");
		do{
			System.out.println("아이디 : ");
			id = input.nextLine();
			System.out.println("비밀번호 : ");
			pw = input.nextLine();
			success = sdao.getStudentLogin(id, pw);

			if(!success){
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력.");
				System.out.println("메인 메뉴로 이동 (y/n) : ");
				String mainMenu = input.nextLine();
				if(mainMenu.equals("y") || mainMenu.equals("Y")){
					return;
				}
				System.out.println();
			}

		}while(!success);
		String sd_num = sdao.getStudentNum(id, pw);
		System.out.println();
		System.out.println("수강 신청한 리스트");
		td.getTraineeTotalList(sd_num);
		System.out.println();
	}
	//수강 신청 관리
	public void traineeRegister() {
		Scanner input = new Scanner(System.in);

		TraineeDAO td = new TraineeDAO();
		TraineeVO tvo = new TraineeVO();

		LessonDAO ld = new LessonDAO();
		LessonVO lvo = new LessonVO();

		StudentDAO sdao = new StudentDAO();

		String id;
		String pw;
		
		boolean success = false;
		
		System.out.println("수강 가능 과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();

		System.out.println("수강 신청을 위한 정보 입력");
		do{
			System.out.println("아이디 : ");
			id = input.nextLine();
			System.out.println("비밀번호 : ");
			pw = input.nextLine();

			success = sdao.getStudentLogin(id, pw);
			if(!success){
				System.out.println("아이디 또는 비밀번호가 틀렸습니다. 다시 입력해주세요.");
				System.out.println("메인 메뉴로 이동(y/n) : ");
				String mainMenu = input.nextLine();
				if(mainMenu.equals("y") || mainMenu.equals("Y")){
					return;
				}
				System.out.println();
			}
		}while(!success);
		String sd_num = sdao.getStudentNum(id, pw);

		System.out.println("학번 : " + sd_num);
		System.out.println("과목약어(영어 대문자) : ");
		String l_abbre = input.nextLine();
		System.out.println("과목구분(교양,전공,부전공) : ");
		String t_section = input.nextLine();

		tvo.setSd_num(sd_num);
		tvo.setL_abbre(l_abbre);
		tvo.setT_section(t_section);

		//수강 신청 등록
		td.setTraineeRegiste(tvo);

		System.out.println();
		System.out.println("수강 신청한 리스트");
		td.getTraineeTotalList(sd_num);
		System.out.println();
	}
	//수강 취소 관리
	public void traineeDelete() {
		Scanner input = new Scanner(System.in);

		TraineeDAO td = new TraineeDAO();
		StudentDAO sdao = new StudentDAO();

		String id;
		String pw;

		boolean success = false;

		System.out.println("수강 취소를 위한 정보 입력");
		do{
			System.out.println("아이디 : ");
			id = input.nextLine();
			System.out.println("비밀번호 : ");
			pw = input.nextLine();

			success = sdao.getStudentLogin(id, pw);

			if(!success){
				System.out.println("아이디 또는 비밀번호가 틀렸습니다. 다시 입력해주세요.");
				System.out.println("메인 메뉴로 이동(y/n) : ");
				String mainMenu = input.nextLine();

				if(mainMenu.equals("y") || mainMenu.equals("Y")){
					return;
				}
				System.out.println();
			}
		}while(!success);

		String sd_num = sdao.getStudentNum(id, pw);

		System.out.println();
		System.out.println("수강 신청한 리스트");
		td.getTraineeTotalList(sd_num);
		System.out.println();

		System.out.println("취소할 수강 신청 일련번호 입력");
		System.out.println("일련번호 : ");
		int t_no = input.nextInt();
		input.nextLine();

		td.setTraineeDelete(t_no);

		System.out.println();
		System.out.println("수강 신청 취소 후 리스트");
		td.getTraineeTotalList(sd_num);
		System.out.println();
	}

}
