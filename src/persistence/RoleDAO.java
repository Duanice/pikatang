package persistence;

import entity.Player;
import entity.Role;
import entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色数据访问对象
 */
public class RoleDAO {

    /**
     * 保存角色信息
     */
    public void save(Role role) throws SQLException {
        String sql;
        boolean isUpdate = role.getRoleId() > 0;

        if (isUpdate) {
            // 更新现有角色
            sql = """
                UPDATE roles SET
                player_id = ?, name = ?, gender = ?, appearance = ?, level = ?, experience = ?
                WHERE role_id = ?
                """;

            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, role.getPlayerId());
                stmt.setString(2, role.getName());
                stmt.setString(3, role.getGender());
                stmt.setString(4, role.getAppearance());
                stmt.setInt(5, role.getLevel());
                stmt.setInt(6, role.getExperience());
                stmt.setInt(7, role.getRoleId());

                stmt.executeUpdate();
            }
        } else {
            // 插入新角色
            sql = """
                INSERT INTO roles
                (player_id, name, gender, appearance, level, experience)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, role.getPlayerId());
                stmt.setString(2, role.getName());
                stmt.setString(3, role.getGender());
                stmt.setString(4, role.getAppearance());
                stmt.setInt(5, role.getLevel());
                stmt.setInt(6, role.getExperience());

                stmt.executeUpdate();

                // 获取生成的ID (SQLite方式)
                try (Statement idStmt = conn.createStatement();
                     ResultSet rs = idStmt.executeQuery("SELECT last_insert_rowid()")) {
                    if (rs.next()) {
                        role.setRoleId(rs.getInt(1));
                    }
                }
            }
        }

        // 保存角色技能关联
        saveRoleSkills(role);
    }

    /**
     * 根据ID查找角色
     */
    public Role findById(int roleId) throws SQLException {
        String sql = "SELECT * FROM roles WHERE role_id = ?";
        Role role = null;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = mapResultSetToRole(rs);
                }
            }
        }

        // 在ResultSet关闭后加载技能
        if (role != null) {
            role.setSkills(findSkillsByRoleId(roleId));
        }

        return role;
    }

    /**
     * 根据玩家ID查找角色
     */
    public List<Role> findByPlayerId(int playerId) throws SQLException {
        String sql = "SELECT * FROM roles WHERE player_id = ? ORDER BY role_id";
        List<Role> roles = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Role role = mapResultSetToRole(rs);
                    roles.add(role);
                }
            }
        }

        // 在ResultSet关闭后加载技能
        for (Role role : roles) {
            role.setSkills(findSkillsByRoleId(role.getRoleId()));
        }

        return roles;
    }

    /**
     * 删除角色
     */
    public void delete(int roleId) throws SQLException {
        // 先删除角色技能关联
        deleteRoleSkills(roleId);

        String sql = "DELETE FROM roles WHERE role_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            stmt.executeUpdate();
        }
    }

    /**
     * 保存角色技能关联
     */
    private void saveRoleSkills(Role role) throws SQLException {
        // 先删除现有的技能关联
        deleteRoleSkills(role.getRoleId());

        String sql = "INSERT INTO role_skills (role_id, skill_id) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Skill skill : role.getSkills()) {
                stmt.setInt(1, role.getRoleId());
                stmt.setInt(2, skill.getSkillId());
                stmt.executeUpdate();
            }
        }
    }

    /**
     * 删除角色技能关联
     */
    private void deleteRoleSkills(int roleId) throws SQLException {
        String sql = "DELETE FROM role_skills WHERE role_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            stmt.executeUpdate();
        }
    }

    /**
     * 根据角色ID查找技能
     */
    private List<Skill> findSkillsByRoleId(int roleId) throws SQLException {
        String sql = """
            SELECT s.* FROM skills s
            INNER JOIN role_skills rs ON s.skill_id = rs.skill_id
            WHERE rs.role_id = ?
            """;

        List<Skill> skills = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    skills.add(mapResultSetToSkill(rs));
                }
            }
        }
        return skills;
    }

    /**
     * 将ResultSet映射为Role对象
     */
    private Role mapResultSetToRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        role.setPlayerId(rs.getInt("player_id"));
        role.setName(rs.getString("name"));
        role.setGender(rs.getString("gender"));
        role.setAppearance(rs.getString("appearance"));
        role.setLevel(rs.getInt("level"));
        role.setExperience(rs.getInt("experience"));
        return role;
    }

    /**
     * 将ResultSet映射为Skill对象
     */
    private Skill mapResultSetToSkill(ResultSet rs) throws SQLException {
        Skill skill = new Skill();
        skill.setSkillId(rs.getInt("skill_id"));
        skill.setName(rs.getString("name"));
        skill.setType(rs.getString("type"));
        skill.setLevel(rs.getInt("level"));
        skill.setDescription(rs.getString("description"));
        return skill;
    }
}