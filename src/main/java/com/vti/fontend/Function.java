package com.vti.fontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.vti.backend.common.StringConfig;
import com.vti.backend.presentationlayer.UserController;
import com.vti.entity.Admin;
import com.vti.entity.Employee;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.utils.properties.JdbcUtils;
import com.vti.utils.properties.ScannerUtils;

public class Function {
	private UserController userController;

	public Function() throws IOException {
		userController = new UserController();
	}

	public static void showUser() throws IOException, ClassNotFoundException, SQLException {
		UserController controller = new UserController();
		JdbcUtils jdbcUtils = new JdbcUtils();
		List<User> users = controller.getListUser();

		System.out.printf("%-10s %-25s %-25s \n", "ID", "Email", "Fullname");
		for (User user : users) {
			System.out.printf("%-10s %-25s %-25s \n", user.getId(), user.getEmail(), user.getFullName());
		}
	}

	public static void showAdmin() throws IOException, ClassNotFoundException, SQLException {
		UserController controller = new UserController();
		JdbcUtils jdbcUtils = new JdbcUtils();

		List<Admin> admins = controller.getAdmin();

//		for (Admin admin : admins) {
//			System.out.println(admin);
//		}
		System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s\n", "ID", "Email", "Fullname", "Password", "Exp In Year",
				"Role");
		for (Admin admin : admins) {
			System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s\n", admin.getId(), admin.getEmail(),
					admin.getFullName(), "*********", admin.getExpInyear(), admin.getRole());
		}
	}

	public void getUserById() throws ClassNotFoundException, SQLException {
		System.out.print("Mời bạn nhập User ID: ");
		while (true) {
			int userId = ScannerUtils.inputPositiveInt("Bạn phải nhập vào số nguyên dương! Mời nhập lại");

			User user = userController.getUserById(userId);

			if (user != null) {
				System.out.println(user);
				return;
			} else {
				System.err.println("Không tìm thấy user có id = " + userId + "! Mời nhập lại");
			}
		}
	}

	public void deleteUserById() throws ClassNotFoundException, SQLException {
		System.out.println("Mời bạn nhập User ID muốn xóa: ");
		while (true) {
			int userId = ScannerUtils.inputPositiveInt("Bạn phải nhập vào số nguyên dương! Mời nhập lại");
			boolean result = userController.deleteUserById(userId);
			if (result) {
				System.out.println("Đã xóa thành công");
				return;
			} else {
				System.err.println("Không tìm thấy user có id = " + userId + "! Mời nhập lại");
			}
		}
	}

	public void login() {
		// nên cho người ta lựa chọn, login tiếp hoặc out, e lam tiep doan nay nhe ^^
		System.out.println("Pls enter your email: ");
		String email = ScannerUtils.inputEmail("Email chưa đúng định dạng");
		System.out.println("Pls enter your pass: ");
		String password = ScannerUtils.inputString("Password chưa đúng định dạng");
		String name = userController.login(email, password);
		if(StringUtils.isEmpty(name))
			System.out.println("Pls login again!");
		else
			System.out.println("Welcome " + name + "!"); //vay la ho biet thanh cong roi
	}

	public void createUser() throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("1: Tạo Admin. 2: Tạo Employee.");
			System.out.println("Bạn chọn: ");
//			Scanner sc = new Scanner(System.in); //remove if not use
			int choose = ScannerUtils.inputInt("Kí tự vừa nhập chưa đúng. Vui lòng nhập lại!");
			switch (choose) { // recommend user if else role - user admin
			case 1:
				User userAdmin = getBasicInfo(StringConfig.admin); // a không chắc chỗ này, thường để Role là 1 class
																	// luôn, k dùng enum
				// xíu chạy test xem oki không
				System.out.println("Mời bạn nhập vào Exp in Year của account: ");
				int expInYear = ScannerUtils.inputInt("ExpInYear chưa đúng định dạng");

				Admin admin = new Admin(userAdmin, expInYear);
				userController.createAdmin(admin);
				System.out.println("Tạo thành công Admin");
				return;
			case 2:
				User userEmployeeUser = getBasicInfo(StringConfig.employee);
				System.out.println("Mời bạn nhập vào proSkill của account: ");
				String proSkill = ScannerUtils.inputString("ProSkill chưa đúng định dạng");
				Employee employee = new Employee(userEmployeeUser, proSkill);
				userController.createEmployee(employee);
				System.out.println("Tạo thành công Employee");
				return;
			default:
				break;// ??
			}
		}
	}

	private User getBasicInfo(String role) { // tên function bắt đầu bằng 1 động từ!
		System.out.println("Nhập thông tin tạo Admin!");
		System.out.println("Mời bạn nhập vào Id của account:");
		int id = ScannerUtils.inputInt("Id có dạng số. Vui lòng nhập lại");
		System.out.print("Mời bạn nhập vào FullName của account:");
		String fullName = ScannerUtils.inputName("Fullname chưa đúng định dạng");
		System.out.println("Mời bạn nhập vào Email của account: ");
		String email = ScannerUtils.inputEmail("Email chưa đúng định dạng");
		String password = StringConfig.defaultPasswordString;
		return new User(id, fullName, email, password, role);
	}

}
