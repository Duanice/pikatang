package entity;

/**
 * 虚拟货币实体类
 * 管理玩家的金币和银币
 */
public class VirtualCurrency {
    private int goldCoins;  // 金币
    private int silverCoins; // 银币

    public VirtualCurrency() {
        this.goldCoins = 0;
        this.silverCoins = 0;
    }

    public VirtualCurrency(int goldCoins, int silverCoins) {
        this.goldCoins = goldCoins;
        this.silverCoins = silverCoins;
    }

    // Getters and Setters
    public int getGoldCoins() { return goldCoins; }
    public void setGoldCoins(int goldCoins) { this.goldCoins = goldCoins; }

    public int getSilverCoins() { return silverCoins; }
    public void setSilverCoins(int silverCoins) { this.silverCoins = silverCoins; }

    // 业务方法
    public boolean hasEnoughGold(int amount) {
        return this.goldCoins >= amount;
    }

    public boolean hasEnoughSilver(int amount) {
        return this.silverCoins >= amount;
    }

    public void addGold(int amount) {
        this.goldCoins += amount;
    }

    public void addSilver(int amount) {
        this.silverCoins += amount;
    }

    public boolean deductGold(int amount) {
        if (hasEnoughGold(amount)) {
            this.goldCoins -= amount;
            return true;
        }
        return false;
    }

    public boolean deductSilver(int amount) {
        if (hasEnoughSilver(amount)) {
            this.silverCoins -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "VirtualCurrency{" +
                "goldCoins=" + goldCoins +
                ", silverCoins=" + silverCoins +
                '}';
    }
}