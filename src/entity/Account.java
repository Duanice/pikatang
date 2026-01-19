package entity;

import java.time.LocalDateTime;

/**
 * 账号实体类
 */
public class Account {
    private int accountId;
    private String username;
    private String password;
    private String email;
    private String authenticationInfo; // 认证信息
    private LocalDateTime createTime;
    private LocalDateTime lastLoginTime;
    private boolean isActive;

    public Account() {
        this.createTime = LocalDateTime.now();
        this.isActive = true;
    }

    public Account(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAuthenticationInfo() { return authenticationInfo; }
    public void setAuthenticationInfo(String authenticationInfo) { this.authenticationInfo = authenticationInfo; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public LocalDateTime getLastLoginTime() { return lastLoginTime; }
    public void setLastLoginTime(LocalDateTime lastLoginTime) { this.lastLoginTime = lastLoginTime; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }

    // 业务方法
    public void updateLastLoginTime() {
        this.lastLoginTime = LocalDateTime.now();
    }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", isActive=" + isActive +
                '}';
    }
}