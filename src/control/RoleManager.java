package control;

import entity.Player;
import entity.Role;
import entity.Skill;
import persistence.RoleDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * 角色管理控制类
 * 负责角色相关的业务逻辑
 */
public class RoleManager {
    private RoleDAO roleDAO;

    public RoleManager() {
        this.roleDAO = new RoleDAO();
    }

    /**
     * 创建新角色
     */
    public boolean createRole(Player player, String name, String gender, String appearance) {
        try {
            // 检查玩家是否已有角色（简化版，每个玩家只能有一个角色）
            List<Role> existingRoles = roleDAO.findByPlayerId(player.getPlayerId());
            if (!existingRoles.isEmpty()) {
                System.out.println("玩家已拥有角色");
                return false;
            }

            // 创建新角色
            Role newRole = new Role(name, gender, appearance);
            newRole.setPlayerId(player.getPlayerId());
            player.setCurrentRole(newRole);

            roleDAO.save(newRole);
            System.out.println("角色创建成功");
            return true;
        } catch (SQLException e) {
            System.err.println("创建角色失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 修改角色信息
     */
    public boolean updateRole(Role role, String name, String gender, String appearance) {
        try {
            role.setName(name);
            role.setGender(gender);
            role.setAppearance(appearance);
            roleDAO.save(role);
            System.out.println("角色信息修改成功");
            return true;
        } catch (SQLException e) {
            System.err.println("修改角色信息失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 学习技能
     */
    public boolean learnSkill(Role role, Skill skill) {
        if (role.hasSkill(skill.getName())) {
            System.out.println("角色已拥有此技能");
            return false;
        }

        try {
            role.learnSkill(skill);
            roleDAO.save(role);
            System.out.println("技能学习成功");
            return true;
        } catch (SQLException e) {
            System.err.println("学习技能失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 提升角色经验
     */
    public boolean gainExperience(Role role, int experience) {
        try {
            role.gainExperience(experience);
            roleDAO.save(role);
            System.out.println("经验提升成功，当前等级: " + role.getLevel());
            return true;
        } catch (SQLException e) {
            System.err.println("提升经验失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 删除角色
     */
    public boolean deleteRole(Role role) {
        try {
            roleDAO.delete(role.getRoleId());
            System.out.println("角色删除成功");
            return true;
        } catch (SQLException e) {
            System.err.println("删除角色失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 根据玩家ID获取角色
     */
    public Role getRoleByPlayerId(int playerId) {
        try {
            List<Role> roles = roleDAO.findByPlayerId(playerId);
            return roles.isEmpty() ? null : roles.get(0);
        } catch (SQLException e) {
            System.err.println("获取角色失败: " + e.getMessage());
            return null;
        }
    }
}