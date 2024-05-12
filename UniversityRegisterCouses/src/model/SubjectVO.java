package model;

public class SubjectVO {
	private int no; // 학과 일련 번호
	private String s_num; // 학과 번호
	private String s_name; // 학과명

	public SubjectVO() {
		super();
	}

	public SubjectVO(int no, String s_num, String s_name) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.s_name = s_name;
	}

	public SubjectVO(String s_num, String s_name) {
		super();
		this.s_num = s_num;
		this.s_name = s_name;
	}
	public SubjectVO(int no) {
		super();
		this.no = no;
		
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	@Override
	public String toString() {
		return "일련번호 : " + no + ", 학과 번호 : " + s_num + ", 학과명 : " + s_name + "]";
	}

}
