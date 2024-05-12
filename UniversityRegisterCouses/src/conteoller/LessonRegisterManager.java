package conteoller;

import java.util.Scanner;

import model.LessonVO;

public class LessonRegisterManager {
	//과목 목록
	public void lessonList() {
		LessonDAO ld = new LessonDAO();
		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();
	}
	//과목 등록 관리
	public void lessonRegister() {
		Scanner input = new Scanner(System.in);

		LessonDAO ld = new LessonDAO();
		LessonVO lvo = new LessonVO();

		String l_abbre;
		String l_name;

		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();

		System.out.println("과목 정보 입력");
		System.out.println("과목 약어 : ");
		l_abbre = input.nextLine();
		System.out.println("과목명 : ");
		l_name = input.nextLine();

		lvo.setL_abbre(l_abbre);
		lvo.setL_name(l_name);

		ld.setLessonRegiste(lvo);

		System.out.println();
		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();
	}
	//과목 수정 관리
	public void lessonUpdate() {
		Scanner input = new Scanner(System.in);

		LessonDAO ld = new LessonDAO();
		LessonVO lvo = new LessonVO();


		System.out.println("과목 전체 리스트(사용중인 과목 변경 불가)");
		ld.getLessonTotalList();
		System.out.println();

		System.out.println("수정할 과목 일련번호 입력");
		System.out.println("일련번호 : ");
		int l_no = input.nextInt();
		input.nextLine();

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.println("과목 약어 : ");
		String l_abbre = input.nextLine();
		System.out.println("과목명 : ");
		String l_name = input.nextLine();

		lvo.setNo(l_no);
		lvo.setL_abbre(l_abbre);
		lvo.setL_name(l_name);

		ld.setLessonUpdate(lvo);

		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
	}
	//과목 삭제 관리
	public void lessonDelete() {
		Scanner input = new Scanner(System.in);

		LessonDAO ld = new LessonDAO();
		LessonVO lvo = new LessonVO();

		System.out.println("과목 전체 리스트(사용중인 과목 삭제 불가)");
		ld.getLessonTotalList();

		System.out.println("삭제할 과목 일련번호 입력");
		System.out.println("일련번호 : ");
		int l_no = input.nextInt();

		ld.setLessonDelete(l_no);

		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
	}

}
