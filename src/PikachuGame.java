import boundary.AccountUI;
import boundary.RoleUI;
import entity.Player;
import persistence.DatabaseManager;

import java.util.Scanner;

/**
 * 皮卡堂过家家游戏主应用程序
 */
public class PikachuGame {
    private static Scanner scanner = new Scanner(System.in);
    private static Player currentPlayer = null;

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("        欢迎来到皮卡堂过家家游戏");
        System.out.println("=========================================");

        // 初始化数据库
        DatabaseManager.initializeDatabase();

        // 显示主菜单
        showMainMenu();
    }

    /**
     * 显示主菜单
     */
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== 主菜单 ===");
            if (currentPlayer == null) {
                System.out.println("1. 账号管理");
                System.out.println("2. 退出游戏");
            } else {
                System.out.println("当前玩家: " + currentPlayer.getUsername());
                System.out.println("1. 账号管理");
                System.out.println("2. 角色管理");
                System.out.println("3. 家园建设");
                System.out.println("4. 虚拟交易");
                System.out.println("5. 社区交友");
                System.out.println("6. 充值系统");
                System.out.println("7. 注销登录");
                System.out.println("8. 退出游戏");
            }
            System.out.print("请选择: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            if (currentPlayer == null) {
                switch (choice) {
                    case 1:
                        handleAccountManagement();
                        break;
                    case 2:
                        exitGame();
                        return;
                    default:
                        System.out.println("无效选择，请重新输入");
                }
            } else {
                switch (choice) {
                    case 1:
                        handleAccountManagement();
                        break;
                    case 2:
                        handleRoleManagement();
                        break;
                    case 3:
                        handleHomeConstruction();
                        break;
                    case 4:
                        handleVirtualTrading();
                        break;
                    case 5:
                        handleCommunityFriends();
                        break;
                    case 6:
                        handleRechargeSystem();
                        break;
                    case 7:
                        logout();
                        break;
                    case 8:
                        exitGame();
                        return;
                    default:
                        System.out.println("无效选择，请重新输入");
                }
            }
        }
    }

    /**
     * 处理账号管理
     */
    private static void handleAccountManagement() {
        AccountUI accountUI = new AccountUI();
        accountUI.showMenu();

        // 检查是否登录成功
        if (currentPlayer == null) {
            // 这里可以添加登录逻辑，但暂时保持简单
        }
    }

    /**
     * 处理角色管理
     */
    private static void handleRoleManagement() {
        if (currentPlayer == null) {
            System.out.println("请先登录账号");
            return;
        }

        RoleUI roleUI = new RoleUI();
        roleUI.showMenu(currentPlayer);
    }

    /**
     * 处理家园建设
     */
    private static void handleHomeConstruction() {
        System.out.println("家园建设功能开发中...");
        // TODO: 实现家园建设UI
    }

    /**
     * 处理虚拟交易
     */
    private static void handleVirtualTrading() {
        System.out.println("虚拟交易功能开发中...");
        // TODO: 实现虚拟交易UI
    }

    /**
     * 处理社区交友
     */
    private static void handleCommunityFriends() {
        System.out.println("社区交友功能开发中...");
        // TODO: 实现社区交友UI
    }

    /**
     * 处理充值系统
     */
    private static void handleRechargeSystem() {
        System.out.println("充值系统功能开发中...");
        // TODO: 实现充值系统UI
    }

    /**
     * 注销登录
     */
    private static void logout() {
        currentPlayer = null;
        System.out.println("已注销登录");
    }

    /**
     * 退出游戏
     */
    private static void exitGame() {
        System.out.println("感谢您游玩皮卡堂过家家游戏，再见！");
        DatabaseManager.closeConnection();
        scanner.close();
    }

    /**
     * 设置当前玩家（用于登录后的状态管理）
     */
    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    /**
     * 获取当前玩家
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
}