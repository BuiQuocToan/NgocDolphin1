package com.vti.fontend;

import com.vti.utils.properties.ScannerUtils;

import java.io.IOException;
import java.sql.SQLException;

// ctrl + alt + l -- format code
// ctrl + alt + o -- xoá thư viện thừa
public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Function function = new Function();
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