package boundary;

import control.RoleManager;
import entity.Player;
import entity.Role;
import entity.Skill;

import java.util.Scanner;

/**
 * 角色管理用户界面
 */
public class RoleUI {
    private RoleManager roleManager;
    private Scanner scanner;

    public RoleUI() {
        this.roleManager = new RoleManager();
        this.scanner = new Scanner(System.in);
    }

    /**
     * 显示角色管理菜单
     */
    public void showMenu(Player player) {
        while (true) {
            System.out.println("\n=== 角色管理 ===");
            System.out.println("1. 创建角色");
            System.out.println("2. 查看角色信息");
            System.out.println("3. 修改角色信息");
            System.out.println("4. 学习技能");
            System.out.println("5. 提升经验");
            System.out.println("0. 返回上级菜单");
            System.out.print("请选择: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    createRole(player);
                    break;
                case 2:
                    viewRoleInfo(player);
                    break;
                case 3:
                    updateRoleInfo(player);
                    break;
                case 4:
                    learnSkill(player);
                    break;
                case 5:
                    gainExperience(player);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }

    /**
     * 创建角色
     */
    private void createRole(Player player) {
        System.out.println("\n--- 创建角色 ---");
        System.out.print("请输入角色名: ");
        String name = scanner.nextLine();
        System.out.print("请输入性别: ");
        String gender = scanner.nextLine();
        System.out.print("请输入外观描述: ");
        String appearance = scanner.nextLine();

        boolean success = roleManager.createRole(player, name, gender, appearance);
        if (success) {
            System.out.println("角色创建成功！");
        } else {
            System.out.println("角色创建失败！");
        }
    }

    /**
     * 查看角色信息
     */
    private void viewRoleInfo(Player player) {
        Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        if (role == null) {
            System.out.println("您还没有创建角色，请先创建角色");
            return;
        }

        System.out.println("\n--- 角色信息 ---");
        System.out.println("角色名: " + role.getName());
        System.out.println("性别: " + role.getGender());
        System.out.println("外观: " + role.getAppearance());
        System.out.println("等级: " + role.getLevel());
        System.out.println("经验: " + role.getExperience());
        System.out.println("技能数量: " + role.getSkills().size());

        if (!role.getSkills().isEmpty()) {
            System.out.println("技能列表:");
            for (Skill skill : role.getSkills()) {
                System.out.println("  - " + skill.getName() + " (等级: " + skill.getLevel() + ")");
            }
        }
    }

    /**
     * 修改角色信息
     */
    private void updateRoleInfo(Player player) {
        Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        if (role == null) {
            System.out.println("您还没有创建角色，请先创建角色");
            return;
        }

        System.out.println("\n--- 修改角色信息 ---");
        System.out.print("请输入新角色名 (当前: " + role.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("请输入新性别 (当前: " + role.getGender() + "): ");
        String gender = scanner.nextLine();
        System.out.print("请输入新外观描述 (当前: " + role.getAppearance() + "): ");
        String appearance = scanner.nextLine();

        boolean success = roleManager.updateRole(role, name, gender, appearance);
        if (success) {
            System.out.println("角色信息修改成功！");
        } else {
            System.out.println("角色信息修改失败！");
        }
    }

    /**
     * 学习技能
     */
    private void learnSkill(Player player) {
        Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        if (role == null) {
            System.out.println("您还没有创建角色，请先创建角色");
            return;
        }

        System.out.println("\n--- 学习技能 ---");
        System.out.println("可学习的技能:");
        System.out.println("1. 挖矿");
        System.out.println("2. 钓鱼");
        System.out.println("3. 种植");
        System.out.println("4. 烹饪");
        System.out.print("请选择要学习的技能: ");

        int skillChoice = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        Skill skill = null;
        switch (skillChoice) {
            case 1:
                skill = new Skill("挖矿", "生产技能", "能够进行挖矿作业");
                break;
            case 2:
                skill = new Skill("钓鱼", "生产技能", "能够进行钓鱼作业");
                break;
            case 3:
                skill = new Skill("种植", "生产技能", "能够进行种植作业");
                break;
            case 4:
                skill = new Skill("烹饪", "生活技能", "能够进行烹饪作业");
                break;
            default:
                System.out.println("无效选择");
                return;
        }

        boolean success = roleManager.learnSkill(role, skill);
        if (success) {
            System.out.println("技能学习成功！");
        } else {
            System.out.println("技能学习失败！");
        }
    }

    /**
     * 提升经验
     */
    private void gainExperience(Player player) {
        Role role = roleManager.getRoleByPlayerId(player.getPlayerId());
        if (role == null) {
            System.out.println("您还没有创建角色，请先创建角色");
            return;
        }

        System.out.println("\n--- 提升经验 ---");
        System.out.print("请输入要提升的经验值: ");
        int experience = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        boolean success = roleManager.gainExperience(role, experience);
        if (success) {
            System.out.println("经验提升成功！");
        } else {
            System.out.println("经验提升失败！");
        }
    }
}