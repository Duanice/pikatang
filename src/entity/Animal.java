package entity;

import java.time.LocalDateTime;

/**
 * 动物实体类
 */
public class Animal {
    private int animalId;
    private String name;
    private String type; // 鸡、牛、羊等
    private LocalDateTime lastFedTime;
    private int feedingInterval; // 喂食间隔（分钟）
    private int productionTime; // 生产周期（分钟）
    private LocalDateTime lastProductionTime;
    private int value; // 价值

    public Animal() {}

    public Animal(String name, String type, int feedingInterval, int productionTime, int value) {
        this.name = name;
        this.type = type;
        this.feedingInterval = feedingInterval;
        this.productionTime = productionTime;
        this.value = value;
        this.lastFedTime = LocalDateTime.now();
        this.lastProductionTime = LocalDateTime.now();
    }

    // Getters and Setters
    public int getAnimalId() { return animalId; }
    public void setAnimalId(int animalId) { this.animalId = animalId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getLastFedTime() { return lastFedTime; }
    public void setLastFedTime(LocalDateTime lastFedTime) { this.lastFedTime = lastFedTime; }

    public int getFeedingInterval() { return feedingInterval; }
    public void setFeedingInterval(int feedingInterval) { this.feedingInterval = feedingInterval; }

    public int getProductionTime() { return productionTime; }
    public void setProductionTime(int productionTime) { this.productionTime = productionTime; }

    public LocalDateTime getLastProductionTime() { return lastProductionTime; }
    public void setLastProductionTime(LocalDateTime lastProductionTime) { this.lastProductionTime = lastProductionTime; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    // 业务方法
    public boolean needsFeeding() {
        if (lastFedTime == null) return true;
        LocalDateTime now = LocalDateTime.now();
        long minutesPassed = java.time.Duration.between(lastFedTime, now).toMinutes();
        return minutesPassed >= feedingInterval;
    }

    public void feed() {
        this.lastFedTime = LocalDateTime.now();
    }

    public boolean isReadyForHarvest() {
        if (lastProductionTime == null) return false;
        LocalDateTime now = LocalDateTime.now();
        long minutesPassed = java.time.Duration.between(lastProductionTime, now).toMinutes();
        return minutesPassed >= productionTime;
    }

    public String harvest() {
        if (isReadyForHarvest()) {
            this.lastProductionTime = LocalDateTime.now();
            // 根据动物类型返回不同的产品
            switch (type.toLowerCase()) {
                case "chicken":
                    return "egg";
                case "cow":
                    return "milk";
                case "sheep":
                    return "wool";
                default:
                    return "product";
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", needsFeeding=" + needsFeeding() +
                ", isReadyForHarvest=" + isReadyForHarvest() +
                ", value=" + value +
                '}';
    }
}