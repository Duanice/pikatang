import control.AccountManager;
import control.RoleManager;
import entity.Player;
import entity.Role;
import entity.Skill;
import persistence.DatabaseManager;

/**
 * 游戏功能演示类
 * 展示皮卡堂过家家游戏的核心功能
 */
public class GameDemo {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("      皮卡堂过家家游戏功能演示");
        System.out.println("=========================================");

        // 初始化数据库
        DatabaseManager.initializeDatabase();

        // 创建管理器实例
        AccountManager accountManager = new AccountManager();
        RoleManager roleManager = new RoleManager();

        System.out.println("\n--- 演示账号注册功能 ---");
        // 使用时间戳生成唯一用户名，避免重复
        String username = "testuser_" + System.currentTimeMillis();
        boolean registerSuccess = accountManager.registerAccount(username, "password123", "test@example.com");
        System.out.println("注册结果: " + (registerSuccess ? "成功" : "失败"));

        System.out.println("\n--- 演示账号登录功能 ---");
        Player player = accountManager.login(username, "password123");
        if (player != null) {
            System.out.println("登录成功，欢迎 " + player.getUsername());
            System.out.println("玩家信息: " + player.toString());

            System.out.println("\n--- 演示角色创建功能 ---");
            boolean roleCreateSuccess = roleManager.createRole(player, "小明", "男", "活泼开朗的小男孩");
            System.out.println("角色创建结果: " + (roleCreateSuccess ? "成功" : "失败"));

            System.out.println("\n--- 演示角色信息查看 ---");
            // 重新从数据库获取玩家信息，确保playerId正确
            Player freshPlayer = accountManager.login(username, "password123");
            if (freshPlayer != null) {
                player = freshPlayer; // 更新player对象
            }
            Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
            if (role != null) {
                System.out.println("角色信息: " + role.toString());

                System.out.println("\n--- 演示技能学习功能 ---");
                Skill miningSkill = new Skill("挖矿", "生产技能", "能够进行挖矿作业");
                boolean skillLearnSuccess = roleManager.learnSkill(role, miningSkill);
                System.out.println("技能学习结果: " + (skillLearnSuccess ? "成功" : "失败"));

                System.out.println("\n--- 演示经验提升功能 ---");
                boolean expGainSuccess = roleManager.gainExperience(role, 150);
                System.out.println("经验提升结果: " + (expGainSuccess ? "成功" : "失败"));

                System.out.println("更新后的角色信息: " + role.toString());
            }

            System.out.println("\n--- 演示虚拟货币功能 ---");
            System.out.println("初始货币: " + player.getVirtualCurrency().toString());
            player.getVirtualCurrency().addGold(100);
            player.getVirtualCurrency().addSilver(500);
            System.out.println("添加货币后: " + player.getVirtualCurrency().toString());
            boolean deductSuccess = player.getVirtualCurrency().deductGold(50);
            System.out.println("扣除50金币: " + (deductSuccess ? "成功" : "失败"));
            System.out.println("最终货币: " + player.getVirtualCurrency().toString());

        } else {
            System.out.println("登录失败");
        }

        System.out.println("\n--- 演示完成 ---");
        System.out.println("数据库文件已创建在项目根目录下");
        System.out.println("感谢体验皮卡堂过家家游戏！");

        // 关闭数据库连接
        DatabaseManager.closeConnection();
    }
}