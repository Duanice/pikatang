package persistence;

import entity.Player;
import entity.VirtualCurrency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家数据访问对象
 */
public class PlayerDAO {

    /**
     * 保存玩家信息
     */
    public void save(Player player) throws SQLException {
        String sql;
        boolean isUpdate = player.getPlayerId() > 0;

        if (isUpdate) {
            // 更新现有玩家
            sql = """
                UPDATE players SET
                username = ?, password = ?, email = ?, level = ?, experience = ?,
                gold_coins = ?, silver_coins = ?
                WHERE player_id = ?
                """;

            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, player.getUsername());
                stmt.setString(2, player.getPassword());
                stmt.setString(3, player.getEmail());
                stmt.setInt(4, player.getLevel());
                stmt.setInt(5, player.getExperience());
                stmt.setInt(6, player.getVirtualCurrency().getGoldCoins());
                stmt.setInt(7, player.getVirtualCurrency().getSilverCoins());
                stmt.setInt(8, player.getPlayerId());

                stmt.executeUpdate();
            }
        } else {
            // 插入新玩家
            sql = """
                INSERT INTO players
                (username, password, email, level, experience, gold_coins, silver_coins)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, player.getUsername());
                stmt.setString(2, player.getPassword());
                stmt.setString(3, player.getEmail());
                stmt.setInt(4, player.getLevel());
                stmt.setInt(5, player.getExperience());
                stmt.setInt(6, player.getVirtualCurrency().getGoldCoins());
                stmt.setInt(7, player.getVirtualCurrency().getSilverCoins());

                stmt.executeUpdate();

                // 获取生成的ID (SQLite方式)
                try (Statement idStmt = conn.createStatement();
                     ResultSet rs = idStmt.executeQuery("SELECT last_insert_rowid()")) {
                    if (rs.next()) {
                        player.setPlayerId(rs.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * 根据ID查找玩家
     */
    public Player findById(int playerId) throws SQLException {
        String sql = "SELECT * FROM players WHERE player_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPlayer(rs);
                }
            }
        }
        return null;
    }

    /**
     * 根据用户名查找玩家
     */
    public Player findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM players WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPlayer(rs);
                }
            }
        }
        return null;
    }

    /**
     * 获取所有玩家
     */
    public List<Player> findAll() throws SQLException {
        String sql = "SELECT * FROM players ORDER BY player_id";
        List<Player> players = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                players.add(mapResultSetToPlayer(rs));
            }
        }
        return players;
    }

    /**
     * 删除玩家
     */
    public void delete(int playerId) throws SQLException {
        String sql = "DELETE FROM players WHERE player_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            stmt.executeUpdate();
        }
    }

    /**
     * 更新玩家虚拟货币
     */
    public void updateCurrency(int playerId, VirtualCurrency currency) throws SQLException {
        String sql = "UPDATE players SET gold_coins = ?, silver_coins = ? WHERE player_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, currency.getGoldCoins());
            stmt.setInt(2, currency.getSilverCoins());
            stmt.setInt(3, playerId);
            stmt.executeUpdate();
        }
    }

    /**
     * 将ResultSet映射为Player对象
     */
    private Player mapResultSetToPlayer(ResultSet rs) throws SQLException {
        Player player = new Player();
        player.setPlayerId(rs.getInt("player_id"));
        player.setUsername(rs.getString("username"));
        player.setPassword(rs.getString("password"));
        player.setEmail(rs.getString("email"));
        player.setLevel(rs.getInt("level"));
        player.setExperience(rs.getInt("experience"));

        VirtualCurrency currency = new VirtualCurrency();
        currency.setGoldCoins(rs.getInt("gold_coins"));
        currency.setSilverCoins(rs.getInt("silver_coins"));
        player.setVirtualCurrency(currency);

        return player;
    }
}