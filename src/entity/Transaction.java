package entity;

import java.time.LocalDateTime;

/**
 * 交易实体类
 */
public class Transaction {
    private int transactionId;
    private Player seller;
    private Player buyer;
    private String type; // mall_purchase, personal_trade, recharge
    private String itemName;
    private int amount;
    private int price;
    private String currencyType; // gold, silver
    private String status; // pending, completed, cancelled
    private LocalDateTime createdTime;
    private LocalDateTime completedTime;

    public Transaction() {
        this.createdTime = LocalDateTime.now();
        this.status = "pending";
    }

    public Transaction(Player seller, Player buyer, String type, String itemName, int amount, int price, String currencyType) {
        this();
        this.seller = seller;
        this.buyer = buyer;
        this.type = type;
        this.itemName = itemName;
        this.amount = amount;
        this.price = price;
        this.currencyType = currencyType;
    }

    // Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public Player getSeller() { return seller; }
    public void setSeller(Player seller) { this.seller = seller; }

    public Player getBuyer() { return buyer; }
    public void setBuyer(Player buyer) { this.buyer = buyer; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCurrencyType() { return currencyType; }
    public void setCurrencyType(String currencyType) { this.currencyType = currencyType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getCompletedTime() { return completedTime; }
    public void setCompletedTime(LocalDateTime completedTime) { this.completedTime = completedTime; }

    // 业务方法
    public void complete() {
        this.status = "completed";
        this.completedTime = LocalDateTime.now();
    }

    public void cancel() {
        this.status = "cancelled";
    }

    public boolean isCompleted() {
        return "completed".equals(this.status);
    }

    public boolean isPending() {
        return "pending".equals(this.status);
    }

    public int getTotalPrice() {
        return this.amount * this.price;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", type='" + type + '\'' +
                ", itemName='" + itemName + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", currencyType='" + currencyType + '\'' +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}