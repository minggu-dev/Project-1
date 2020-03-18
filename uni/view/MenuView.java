package kosta.uni.view;

import java.util.Scanner;

import kosta.uni.controller.ComSubController;
import kosta.uni.controller.ProSubController;
import kosta.uni.controller.ProfessorController;
import kosta.uni.controller.StudentController;
import kosta.uni.controller.SubjectController;
import kosta.uni.session.Session;
import kosta.uni.session.SessionSet;

public class MenuView {
	private ProfessorController proController = ProfessorController.getInstance();
	private ProSubController proSubController = ProSubController.getInstance();
	private ComSubController comSubController = ComSubController.getInstance();
	private SubjectController subController = SubjectController.getInstance();
	private StudentController stuController = StudentController.getInstance();

	final static String THISTERM = "2020-1";

	Scanner sc = new Scanner(System.in);
	boolean outMenu = true;

	public void menu() {
		while (outMenu) {
			System.out.println("1. �α���  2. ȸ������  9. ����");
			System.out.print("�Է� >> ");
			switch (sc.nextLine()) {
			case "1":
				login();
				break;
			case "2":
				resister();
				break;
			case "9":
				outMenu = false;
				break;
			default:
				System.out.println("��ȣ�� �߸� �Է��ϼ̽��ϴ�.");
				break;
			}
		}
	}

	public void login() {
		System.out.println("==�α���==");
		System.out.println("1. �л� �α���  2. ���� �α���  9. ������");
		System.out.print("�Է� >> ");
		switch (sc.nextLine()) {
		case "1":
			stuLogin();
			break;
		case "2":
			proLogin();
			break;
		case "9":
			System.out.println("������");
			break;
		}
	}

	public void resister() {
		System.out.println("==ȸ������==");
		System.out.println("1. �л�  2. ����  9. ������");
		System.out.print("�Է� >> ");
		switch (sc.nextLine()) {
		case "1":
			stuResister();
			break;
		case "2":
			proResister();
			break;
		case "9":
			System.out.println("������");
			break;
		}
	}

	public void stuLogin() {
		System.out.println("==�л���� �α���==");
		System.out.print("ID : ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("Password : ");
		String pwd = sc.nextLine();

		stuController.login(id, pwd);
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);

		if (session == null)
			return;

		while (outMenu) {
			System.out.println("1. ������û �� ����");
			System.out.println("2. ���� ��ȸ");
			System.out.println("3. �Ż� ��ȸ");
			System.out.println("4. �ð�ǥ");
			System.out.println("5. ��й�ȣ ����");
			System.out.println("9. �α׾ƿ�");

			switch (sc.nextLine()) {
			case "1":
				applyChangeSubject(id);
				break;
			case "2":
				showGrade(id);
				break;
			case "3":
				showPersonalInfo(id);
				break;
			case "4":
				showStudentSchedule(id);
				break;
			case "5":
				System.out.println("==��й�ȣ ����==");
				System.out.println("���� ��й�ȣ�� �Է��ϼ���.");
				System.out.print("�Է� >> ");
				String newpwd = sc.nextLine();
				
				if (!pwd.equals(newpwd)) {
					System.out.println("��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
					break;
				}
				System.out.println("�����Ͻ� ��й�ȣ�� �Է��ϼ���.");
				System.out.println("�Է� >> ");
				pwd = sc.nextLine();
				stuController.changePwd(id, pwd);
				break;
			case "9":
				stuController.logout(id);
				outMenu = false;
				break;

			}
		}
		outMenu = true;
	}

	public void proLogin() {
		System.out.println("==������� �α���==");
		System.out.print("ID : ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("Password : ");
		String pwd = sc.nextLine();

		proController.login(id, pwd);

		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		if (session == null)
			return;

		while (outMenu) {
			System.out.println("1. �����ο�");
			System.out.println("2. ���� ���� �л��� ���� ��ȸ");
			System.out.println("3. �ð�ǥ");
			System.out.println("4. ��й�ȣ ����");
			System.out.println("9. �α׾ƿ�");
			System.out.print("�Է� >> ");
			switch (sc.nextLine()) {
			case "1":
				grantMySubjectStudent(id);
				break;
			case "2":
				System.out.println("���� Ȯ���� �����ڵ带 �Է��ϼ���.");
				System.out.print("�Է� >> ");

				proSubController.isMySubject(id, sc.nextLine());
				if (session.getAttribute("subject") == null) {
					System.out.println("�ش������ ��米���� �ƴմϴ�.");
					break;
				}
				comSubController.showMySubjectStudentGrade(session, THISTERM);
				break;
			case "3":
				proSubController.showSchedule(id);
				break;
			case "4":
				System.out.println("==��й�ȣ ����==");
				System.out.println("���� ��й�ȣ�� �Է��ϼ���.");
				System.out.print("�Է� >> ");
				String newpwd = sc.nextLine();
				
				if (!pwd.equals(newpwd)) {
					System.out.println("��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
					return;
				}
				System.out.println("�����Ͻ� ��й�ȣ�� �Է��ϼ���.");
				System.out.println("�Է� >> ");
				pwd = sc.nextLine();
				proController.changePwd(id, pwd);
				break;
			case "9":
				proController.logout(id);
				outMenu = false;
				break;
			default:
				System.out.println("�߸��� ��ȣ �Է�");
				break;
			}
		}
		outMenu = true;
	}

	public void stuResister() {
		System.out.println("==�л� ȸ������==");
		System.out.println("�л� ��ȣ�� �Է��ϼ���.");
		System.out.print("�Է� >> ");
		int pro_id = Integer.parseInt(sc.nextLine());
		System.out.println("��й�ȣ�� �Է��ϼ���.");
		System.out.print("�Է� >> ");
		String pwd = sc.nextLine();
		stuController.resister(pro_id, pwd);
	}

	public void proResister() {
		System.out.println("==���� ȸ������==");
		System.out.println("���� ��ȣ�� �Է��ϼ���.");
		System.out.print("�Է� >> ");
		int pro_id = Integer.parseInt(sc.nextLine());
		System.out.println("��й�ȣ�� �Է��ϼ���.");
		System.out.print("�Է� >> ");
		String pwd = sc.nextLine();
		proController.resister(pro_id, pwd);
	}

////////////////////////////////////////////////////
////////�л���� ���
	public void applyChangeSubject(int id) {
		while (outMenu) {
			System.out.println("================������û=================");
			System.out.println("===1.������ȸ 2.��û 3.��û ���� ���� 9.������====");
			System.out.print("�Է� >> ");
			switch (sc.nextLine()) {
			case "1":
				System.out.println("==���� ��ȸ==");
				System.out.println("1. ��ü ����  2. ���� ����  9. ������");
				System.out.print("�Է� >> ");
				switch (sc.nextLine()) {
				case "1":
					subController.showAllSubject();
					break;
				case "2":
					subController.showMajorSubject(id);
					break;
				case "9":
					break;
				}
				break;
			case "2":
				System.out.println("==������û==");
				System.out.println("��û�� �����ڵ� �Է�");
				System.out.print("�Է� >> ");
				comSubController.applySubject(sc.nextLine(), id);
				break;
			case "3":
				System.out.println("==�������� ����==");
				comSubController.shoMyApplyInfo(id, THISTERM);
				System.out.println("������ �����ȣ �Է�");
				System.out.print("�Է�");
				comSubController.deleteApply(sc.nextLine(), id);
				break;
			case "9":
				outMenu = false;
				break;
			default:
				System.out.println("�߸��� ��ȣ �Է�");
			}
		}
		outMenu = true;
	}

	public void showGrade(int id) {
		System.out.println("==���� ��ȸ==");
		System.out.println("1. ��ü ����  2. �б⺰ ����  9. ������");
		System.out.print("�Է� >> ");
		switch (sc.nextLine()) {
		case "1":
			comSubController.showAllGrade(id);
			break;
		case "2":
			System.out.println("��ȸ�� �б� �Է�(\"YYYY-S\" YYYY�� �⵵, S�� �б�)");
			System.out.print("�Է� >> ");
			String term = sc.nextLine();
			comSubController.showGradeByTerm(id, term);
			break;
		case "9":
			break;
		}
	}

	public void showPersonalInfo(int id) {
		System.out.println("==�������� ��ȸ==");
		stuController.showPersonalInfo(id);
	}

	public void showStudentSchedule(int id) {
		System.out.println("==�ð�ǥ ��ȸ==");
		comSubController.showSchedule(id, THISTERM);
	}

	/////////////////////////////////////////////////
	/// ������� ���
	public void grantMySubjectStudent(int id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		System.out.println("==���� �ο�==");

		while (outMenu) {
			System.out.println("1. ���� ���� ���  2. ���� �⼮��  3. �л� ���� �ο�  9. ������");
			System.out.print("�Է� >> ");
			switch (sc.nextLine()) {
			case "1":
				proSubController.showMySubject(id);
				break;
			case "2":
				System.out.println("�⼮�θ� Ȯ���� �����ڵ带 �Է��ϼ���.");
				System.out.print("�Է� >> ");

				proSubController.isMySubject(id, sc.nextLine());

				if (session.getAttribute("subject") == null) {
					System.out.println("�ش������ ��米���� �ƴմϴ�.");
					break;
				}
				comSubController.showMySubjectStudent(THISTERM);
				break;
			case "3":
				System.out.println("���� �ο��� ���� �Է��ϼ���.");
				System.out.print("�Է� >> ");
				proSubController.isMySubject(id, sc.nextLine());

				if (session.getAttribute("subject") == null) {
					System.out.println("�ش������ ��米���� �ƴմϴ�.");
					break;
				}

				System.out.println("������ �Է��� �л���ȣ �Է��ϼ���.");
				System.out.print("�Է� >> ");
				int student_id = Integer.parseInt(sc.nextLine());

				System.out.println("�ο��� ������ �Է��ϼ���.");
				System.out.print("�Է� >> ");
				String grade = sc.nextLine();

				comSubController.grantGrade(student_id, session.getAttribute("subject"), grade, THISTERM);
				break;
			case "9":
				outMenu = false;
				break;
			default:
				System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�.");
				break;
			}
		}
		outMenu = true;
	}
}