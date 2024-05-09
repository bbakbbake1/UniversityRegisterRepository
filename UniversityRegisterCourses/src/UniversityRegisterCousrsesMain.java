import conteoller.LessonRegisterManager;
import conteoller.StudentRegisterManager;
import conteoller.SubjectRegisterManager;
import conteoller.TraineeRegisterManager;
import view.LESSON_CHOICE;
import view.MENU_CHOICE;
import view.MenuViewer;
import view.STUDENT_CHOICE;
import view.SUBJECT_CHOICE;
import view.TRAINEE_CHOICE;

public class UniversityRegisterCousrsesMain {

	public static void main(String[] args) {
		mainMenu();
	}

	public static void mainMenu() {
		int choiceNum;

		while (true) {
			try {
				MenuViewer.mainMenuView();
				choiceNum = MenuViewer.choice.nextInt();
				MenuViewer.choice.nextLine();

				switch (choiceNum) {
				case MENU_CHOICE.SUBJECT:
					subjectMenu();
					break;
				case MENU_CHOICE.STUDENT:
					studentMenu();
					break;
				case MENU_CHOICE.LESSON:
					LessonMenu();
					break;
				case MENU_CHOICE.TRAINEE:
					traineeMenu();
					break;
				case MENU_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
				return;
			}

		}
	}
	//수강신청 메뉴
	private static void traineeMenu() {
		int choice;

		TraineeRegisterManager traineeManager = new TraineeRegisterManager();
		MenuViewer.traineeMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();

		switch (choice) {
		case TRAINEE_CHOICE.LIST:
			System.out.println("");
			traineeManager.traineeList();
			break;
		case TRAINEE_CHOICE.INSERT:
			System.out.println("");
			traineeManager.traineeRegister();
			break;
		case TRAINEE_CHOICE.UPDATE:
			System.out.println("");
			traineeManager.traineeDelete();
			break;
		case TRAINEE_CHOICE.MAIN:
			return;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	//학과 메뉴
	private static void LessonMenu() {
		int choice;
		
		SubjectRegisterManager subjectManager = new SubjectRegisterManager();
		MenuViewer.subjectMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		
		switch(choice) {
		case SUBJECT_CHOICE.LIST:
			subjectManager.subjectList();
			break;
		case SUBJECT_CHOICE.INSERT:
			subjectManager.subjectList();
			break;
		case SUBJECT_CHOICE.UPDATE:
			subjectManager.subjectList();
			break;
		case SUBJECT_CHOICE.DELETE:
			subjectManager.subjectList();
			break;
		case SUBJECT_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	//학생 메뉴
	private static void studentMenu() {
		int choice;
		
		StudentRegisterManager studentManager = new StudentRegisterManager();
		MenuViewer.studentMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		
		switch(choice) {
		case STUDENT_CHOICE.INSERT:
			System.out.println("");
			studentManager.studentRegister();
			break;
		case STUDENT_CHOICE.UPDATE:
			System.out.println("");
			studentManager.studentUpdate();
			break;
		case STUDENT_CHOICE.LIST:
			System.out.println("");
			studentManager.studentTotalList();
			break;
		case STUDENT_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	//과목 메뉴
	private static void subjectMenu() {
		int choice;
		
		LessonRegisterManager lessonManager = new LessonRegisterManager();
		MenuViewer.LessonMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		
		switch(choice) {
		case LESSON_CHOICE.LIST:
			System.out.println("");
			lessonManager.lessonList();
			break;
		case LESSON_CHOICE.INSERT:
			System.out.println("");
			lessonManager.lessonRegister();
			break;
		case LESSON_CHOICE.UPDATE:
			System.out.println("");
			lessonManager.lessonUpdate();
			break;
		case LESSON_CHOICE.DELETE:
			System.out.println("");
			lessonManager.lessonDelete();
			break;
		case LESSON_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}

}
