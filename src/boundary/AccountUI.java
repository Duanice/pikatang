package boundary;

import control.AccountManager;
import entity.Player;

import java.util.Scanner;

/**
 * 账号管理用户界面
 */

/**
 * 账号管理用户界面
 */

/**
 * 账号管理用户界面
 */
public class AccountUI {
    private AccountManager accountManager;
    private Scanner scanner;

    public AccountUI() {
        this.accountManager = new AccountManager();
        this.scanner = new Scanner(System.in);
    }

    /**
     * 显示账号管理菜单
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n=== 账号管理 ===");
            System.out.println("1. 注册账号");
            System.out.println("2. 登录账号");
            System.out.println("3. 修改密码");
            System.out.println("4. 修改邮箱");
            System.out.println("5. 注销账号");
            System.out.println("0. 返回上级菜单");
            System.out.print("请选择: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    registerAccount();
                    break;
                case 2:
                    loginAccount();
                    break;
                case 3:
                    changePassword();
                    break;
                case 4:
                    changeEmail();
                    break;
                case 5:
                    deleteAccount();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }

    /**
     * 注册账号
     */
    private void registerAccount() {
        System.out.println("\n--- 注册账号 ---");
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();
        System.out.print("请输入邮箱: ");
        String email = scanner.nextLine();

        boolean success = accountManager.registerAccount(username, password, email);
        if (success) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
    }

    /**
     * 登录账号
     */
    private Player loginAccount() {
        System.out.println("\n--- 登录账号 ---");
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        Player player = accountManager.login(username, password);
        if (player != null) {
            System.out.println("登录成功！欢迎 " + player.getUsername());
            return player;
        } else {
            System.out.println("登录失败！");
            return null;
        }
    }

    /**
     * 修改密码
     */
    private void changePassword() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return;

        System.out.println("\n--- 修改密码 ---");
        System.out.print("请输入旧密码: ");
        String oldPassword = scanner.nextLine();
        System.out.print("请输入新密码: ");
        String newPassword = scanner.nextLine();

        boolean success = accountManager.changePassword(currentPlayer, oldPassword, newPassword);
        if (success) {
            System.out.println("密码修改成功！");
        } else {
            System.out.println("密码修改失败！");
        }
    }

    /**
     * 修改邮箱
     */
    private void changeEmail() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return;

        System.out.println("\n--- 修改邮箱 ---");
        System.out.print("请输入新邮箱: ");
        String newEmail = scanner.nextLine();

        boolean success = accountManager.changeEmail(currentPlayer, newEmail);
        if (success) {
            System.out.println("邮箱修改成功！");
        } else {
            System.out.println("邮箱修改失败！");
        }
    }

    /**
     * 注销账号
     */
    private void deleteAccount() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return;

        System.out.println("\n--- 注销账号 ---");
        System.out.print("确定要注销账号吗？(y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.toLowerCase().equals("y")) {
            boolean success = accountManager.deleteAccount(currentPlayer);
            if (success) {
                System.out.println("账号注销成功！");
            } else {
                System.out.println("账号注销失败！");
            }
        } else {
            System.out.println("操作已取消");
        }
    }

    /**
     * 获取当前玩家（简化版本，总是要求重新登录）
     */
    private Player getCurrentPlayer() {
        System.out.println("请先登录账号");
        return loginAccount();
    }
}