package entity;

/**
 * 玩家实体类
 * 存储玩家的基本信息和游戏状态
 */
public class Player {
    private int playerId;
    private String username;
    private String password;
    private String email;
    private int level;
    private int experience;
    private VirtualCurrency virtualCurrency;
    private Role currentRole;
    private Account account;

    // 构造函数
    public Player() {
        this.virtualCurrency = new VirtualCurrency();
    }

    public Player(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.level = 1;
        this.experience = 0;
        this.virtualCurrency = new VirtualCurrency();
    }

    // Getters and Setters
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public VirtualCurrency getVirtualCurrency() { return virtualCurrency; }
    public void setVirtualCurrency(VirtualCurrency virtualCurrency) { this.virtualCurrency = virtualCurrency; }

    public Role getCurrentRole() { return currentRole; }
    public void setCurrentRole(Role currentRole) { this.currentRole = currentRole; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    // 业务方法
    public void gainExperience(int exp) {
        this.experience += exp;
        checkLevelUp();
    }

    private void checkLevelUp() {
        // 简单的升级逻辑，每100经验升一级
        while (this.experience >= this.level * 100) {
            this.level++;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", username='" + username + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", virtualCurrency=" + virtualCurrency +
                '}';
    }
}