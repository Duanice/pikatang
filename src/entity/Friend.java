package entity;

import java.time.LocalDateTime;

/**
 * 好友实体类
 */
public class Friend {
    private int friendshipId;
    private Player player1;
    private Player player2;
    private String status; // pending, accepted, blocked
    private LocalDateTime createdTime;
    private LocalDateTime acceptedTime;

    public Friend() {
        this.createdTime = LocalDateTime.now();
    }

    public Friend(Player player1, Player player2) {
        this();
        this.player1 = player1;
        this.player2 = player2;
        this.status = "pending";
    }

    // Getters and Setters
    public int getFriendshipId() { return friendshipId; }
    public void setFriendshipId(int friendshipId) { this.friendshipId = friendshipId; }

    public Player getPlayer1() { return player1; }
    public void setPlayer1(Player player1) { this.player1 = player1; }

    public Player getPlayer2() { return player2; }
    public void setPlayer2(Player player2) { this.player2 = player2; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getAcceptedTime() { return acceptedTime; }
    public void setAcceptedTime(LocalDateTime acceptedTime) { this.acceptedTime = acceptedTime; }

    // 业务方法
    public void accept() {
        this.status = "accepted";
        this.acceptedTime = LocalDateTime.now();
    }

    public void block() {
        this.status = "blocked";
    }

    public boolean isAccepted() {
        return "accepted".equals(this.status);
    }

    public boolean isPending() {
        return "pending".equals(this.status);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendshipId=" + friendshipId +
                ", player1=" + (player1 != null ? player1.getUsername() : "null") +
                ", player2=" + (player2 != null ? player2.getUsername() : "null") +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}