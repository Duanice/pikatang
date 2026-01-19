package control;

import entity.Account;
import entity.Player;
import persistence.PlayerDAO;

import java.sql.SQLException;

/**
 * 账号管理控制类
 * 负责账号相关的业务逻辑
 */
public class AccountManager {
    private PlayerDAO playerDAO;

    public AccountManager() {
        this.playerDAO = new PlayerDAO();
    }

    /**
     * 注册新账号
     */
    public boolean registerAccount(String username, String password, String email) {
        try {
            // 检查用户名是否已存在
            if (playerDAO.findByUsername(username) != null) {
                System.out.println("用户名已存在");
                return false;
            }

            // 创建新玩家
            Player newPlayer = new Player(username, password, email);
            playerDAO.save(newPlayer);

            System.out.println("账号注册成功");
            return true;
        } catch (SQLException e) {
            System.err.println("注册账号失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 登录账号
     */
    public Player login(String username, String password) {
        try {
            Player player = playerDAO.findByUsername(username);
            if (player != null && player.getPassword().equals(password)) {
                // 更新最后登录时间（这里简化了）
                System.out.println("登录成功");
                return player;
            } else {
                System.out.println("用户名或密码错误");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("登录失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * 修改密码
     */
    public boolean changePassword(Player player, String oldPassword, String newPassword) {
        if (!player.getPassword().equals(oldPassword)) {
            System.out.println("旧密码错误");
            return false;
        }

        try {
            player.setPassword(newPassword);
            playerDAO.save(player);
            System.out.println("密码修改成功");
            return true;
        } catch (SQLException e) {
            System.err.println("修改密码失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 修改邮箱
     */
    public boolean changeEmail(Player player, String newEmail) {
        try {
            player.setEmail(newEmail);
            playerDAO.save(player);
            System.out.println("邮箱修改成功");
            return true;
        } catch (SQLException e) {
            System.err.println("修改邮箱失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 注销账号
     */
    public boolean deleteAccount(Player player) {
        try {
            playerDAO.delete(player.getPlayerId());
            System.out.println("账号注销成功");
            return true;
        } catch (SQLException e) {
            System.err.println("注销账号失败: " + e.getMessage());
            return false;
        }
    }
}