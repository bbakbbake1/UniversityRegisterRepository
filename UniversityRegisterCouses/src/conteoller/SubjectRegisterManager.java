package conteoller;

import java.util.Scanner;

import model.SubjectVO;

public class SubjectRegisterManager {

	public void subjectList() {
		SubjectDAO sd = new SubjectDAO();
		System.out.println("학과 전체 리스트");
		sd.getSubjectTotalList();
		System.out.println();
	}

	// 학과 등록 관리
	public void subjectRegister() {
		Scanner input = new Scanner(System.in);
		SubjectDAO sd = new SubjectDAO();
		System.out.println("학과정보 리스트");
		sd.getSubjectTotalList();

		System.out.println("학과정보 입력");
		System.out.println("학과번호 >>");
		String s_num = input.nextLine();
		System.out.println("학과이름 >>");
		String s_name = input.nextLine();
		SubjectVO svo = new SubjectVO(s_num, s_name);
		sd.setSubjectRegister(svo);
	}

	// 학과 수정 관리
	public void subjectUpdate() {
		Scanner input = new Scanner(System.in);
		SubjectDAO sd = new SubjectDAO();
		System.out.println("학과정보 리스트");
		sd.getSubjectTotalList();

		System.out.println("수정할 학과 일련번호 입력");
		System.out.println("일련번호 >>");
		int no = input.nextInt();
		input.nextLine();
		System.out.println("새로운 정보 입력");
		System.out.println("학과번호 >>");
		String s_num = input.nextLine();
		System.out.println("학과명 >>");
		String s_name = input.nextLine();

		SubjectVO svo = new SubjectVO(no, s_num, s_name);
		sd.setSubjectRegister(svo);
	}
	// 학과 삭제 관리
}
