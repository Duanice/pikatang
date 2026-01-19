package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接管理器
 * 负责管理数据库连接的创建和关闭
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:pikachu_game.db";
    private static Connection connection = null;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found");
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Error closing database connection");
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化数据库表
     */
    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            // 创建玩家表
            String createPlayerTable = """
                CREATE TABLE IF NOT EXISTS players (
                    player_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    email TEXT,
                    level INTEGER DEFAULT 1,
                    experience INTEGER DEFAULT 0,
                    gold_coins INTEGER DEFAULT 0,
                    silver_coins INTEGER DEFAULT 0,
                    created_time DATETIME DEFAULT CURRENT_TIMESTAMP
                )
                """;

            // 创建角色表
            String createRoleTable = """
                CREATE TABLE IF NOT EXISTS roles (
                    role_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    player_id INTEGER,
                    name TEXT NOT NULL,
                    gender TEXT,
                    appearance TEXT,
                    level INTEGER DEFAULT 1,
                    experience INTEGER DEFAULT 0,
                    FOREIGN KEY (player_id) REFERENCES players(player_id)
                )
                """;

            // 创建技能表
            String createSkillTable = """
                CREATE TABLE IF NOT EXISTS skills (
                    skill_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    type TEXT,
                    level INTEGER DEFAULT 1,
                    description TEXT
                )
                """;

            // 创建角色技能关联表
            String createRoleSkillTable = """
                CREATE TABLE IF NOT EXISTS role_skills (
                    role_id INTEGER,
                    skill_id INTEGER,
                    FOREIGN KEY (role_id) REFERENCES roles(role_id),
                    FOREIGN KEY (skill_id) REFERENCES skills(skill_id),
                    PRIMARY KEY (role_id, skill_id)
                )
                """;

            // 创建衣橱表
            String createWardrobeTable = """
                CREATE TABLE IF NOT EXISTS wardrobes (
                    wardrobe_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    role_id INTEGER,
                    FOREIGN KEY (role_id) REFERENCES roles(role_id)
                )
                """;

            // 创建服装表
            String createClothingTable = """
                CREATE TABLE IF NOT EXISTS clothings (
                    clothing_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    type TEXT,
                    color TEXT,
                    price INTEGER
                )
                """;

            // 创建配饰表
            String createAccessoryTable = """
                CREATE TABLE IF NOT EXISTS accessories (
                    accessory_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    type TEXT,
                    color TEXT,
                    price INTEGER
                )
                """;

            // 创建衣橱服装关联表
            String createWardrobeClothingTable = """
                CREATE TABLE IF NOT EXISTS wardrobe_clothings (
                    wardrobe_id INTEGER,
                    clothing_id INTEGER,
                    FOREIGN KEY (wardrobe_id) REFERENCES wardrobes(wardrobe_id),
                    FOREIGN KEY (clothing_id) REFERENCES clothings(clothing_id),
                    PRIMARY KEY (wardrobe_id, clothing_id)
                )
                """;

            // 创建衣橱配饰关联表
            String createWardrobeAccessoryTable = """
                CREATE TABLE IF NOT EXISTS wardrobe_accessories (
                    wardrobe_id INTEGER,
                    accessory_id INTEGER,
                    FOREIGN KEY (wardrobe_id) REFERENCES wardrobes(wardrobe_id),
                    FOREIGN KEY (accessory_id) REFERENCES accessories(accessory_id),
                    PRIMARY KEY (wardrobe_id, accessory_id)
                )
                """;

            // 执行创建表语句
            conn.createStatement().execute(createPlayerTable);
            conn.createStatement().execute(createRoleTable);
            conn.createStatement().execute(createSkillTable);
            conn.createStatement().execute(createRoleSkillTable);
            conn.createStatement().execute(createWardrobeTable);
            conn.createStatement().execute(createClothingTable);
            conn.createStatement().execute(createAccessoryTable);
            conn.createStatement().execute(createWardrobeClothingTable);
            conn.createStatement().execute(createWardrobeAccessoryTable);

            System.out.println("Database initialized successfully");

        } catch (SQLException e) {
            System.err.println("Error initializing database");
            e.printStackTrace();
        }
    }
}