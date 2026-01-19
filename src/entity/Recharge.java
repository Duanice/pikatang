package entity;

import java.time.LocalDateTime;

/**
 * 充值实体类
 */
public class Recharge {
    private int rechargeId;
    private Player player;
    private int amount; // 充值金额（人民币）
    private String paymentMethod; // 支付宝、微信、一卡通等
    private String status; // pending, completed, failed
    private LocalDateTime createdTime;
    private LocalDateTime completedTime;
    private int goldReward; // 获得的游戏金币

    public Recharge() {
        this.createdTime = LocalDateTime.now();
        this.status = "pending";
    }

    public Recharge(Player player, int amount, String paymentMethod) {
        this();
        this.player = player;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.goldReward = calculateGoldReward(amount);
    }

    // Getters and Setters
    public int getRechargeId() { return rechargeId; }
    public void setRechargeId(int rechargeId) { this.rechargeId = rechargeId; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getCompletedTime() { return completedTime; }
    public void setCompletedTime(LocalDateTime completedTime) { this.completedTime = completedTime; }

    public int getGoldReward() { return goldReward; }
    public void setGoldReward(int goldReward) { this.goldReward = goldReward; }

    // 业务方法
    private int calculateGoldReward(int amount) {
        // 简单的兑换比例：1元人民币 = 10金币
        return amount * 10;
    }

    public void complete() {
        this.status = "completed";
        this.completedTime = LocalDateTime.now();
    }

    public void fail() {
        this.status = "failed";
    }

    public boolean isCompleted() {
        return "completed".equals(this.status);
    }

    public boolean isPending() {
        return "pending".equals(this.status);
    }

    @Override
    public String toString() {
        return "Recharge{" +
                "rechargeId=" + rechargeId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", goldReward=" + goldReward +
                ", createdTime=" + createdTime +
                '}';
    }
}