package com.vti.fontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.vti.backend.datalayer.UserRepository;
import com.vti.backend.presentationlayer.UserController;
import com.vti.entity.Admin;
import com.vti.entity.User;
import com.vti.utils.properties.JdbcUtils;
import com.vti.utils.properties.ScannerUtils;

public class Program {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
//		UserController controller = new UserController();
//		
//		JdbcUtils jdbcUtils = new JdbcUtils();
//		
//		jdbcUtils.Connect();
//		List<Admin> admins = controller.getAdmin();
//		
//		for (Admin admin : admins) {
//			System.out.println(admin);
//		}
//		remove if not use
		Function function = new Function();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("========= Menu =========");
			System.out.println("1. Đăng nhập");
			System.out.println("2. Đăng ký");
			System.out.println("3. In ra danh sách các User");
			System.out.println("4. Tìm kiếm User theo Id");
			System.out.println("5. Xóa User theo Id");
			System.out.println("0. Thoát chương trình");
			int choose = ScannerUtils.inputInt("Kí tự vừa nhập chưa đúng định dạng. Vui lòng nhập lại!");
			switch (choose) {
			case 1:
				function.login();
				break;
			case 2:
				function.createUser();
				break;
			case 3:
				Function.showUser();
				break;
			case 4:
				function.getUserById();
				break;
			case 5:
				function.deleteUserById();
				break;
			case 0:
				System.out.println("Chương trình kết thúc!!!");
				return;
			default:
				System.out.println("Nhập sai !");
				break;
			}
		}
	}
}