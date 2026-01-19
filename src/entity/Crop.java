package entity;

import java.time.LocalDateTime;

/**
 * 农作物实体类
 */
public class Crop {
    private int cropId;
    private String name;
    private int growthTime; // 生长所需时间（分钟）
    private LocalDateTime plantedTime;
    private boolean isWatered;
    private boolean isFertilized;
    private int harvestYield; // 收获产量
    private int value; // 价值

    public Crop() {}

    public Crop(String name, int growthTime, int harvestYield, int value) {
        this.name = name;
        this.growthTime = growthTime;
        this.harvestYield = harvestYield;
        this.value = value;
        this.plantedTime = LocalDateTime.now();
        this.isWatered = false;
        this.isFertilized = false;
    }

    // Getters and Setters
    public int getCropId() { return cropId; }
    public void setCropId(int cropId) { this.cropId = cropId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getGrowthTime() { return growthTime; }
    public void setGrowthTime(int growthTime) { this.growthTime = growthTime; }

    public LocalDateTime getPlantedTime() { return plantedTime; }
    public void setPlantedTime(LocalDateTime plantedTime) { this.plantedTime = plantedTime; }

    public boolean isWatered() { return isWatered; }
    public void setWatered(boolean watered) { this.isWatered = watered; }

    public boolean isFertilized() { return isFertilized; }
    public void setFertilized(boolean fertilized) { this.isFertilized = fertilized; }

    public int getHarvestYield() { return harvestYield; }
    public void setHarvestYield(int harvestYield) { this.harvestYield = harvestYield; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    // 业务方法
    public boolean isMature() {
        if (plantedTime == null) return false;
        LocalDateTime now = LocalDateTime.now();
        long minutesPassed = java.time.Duration.between(plantedTime, now).toMinutes();
        return minutesPassed >= growthTime && isWatered;
    }

    public void water() {
        this.isWatered = true;
    }

    public void fertilize() {
        this.isFertilized = true;
        // 施肥可以加速生长
        this.growthTime = (int)(this.growthTime * 0.8);
    }

    public int harvest() {
        if (isMature()) {
            int bonus = isFertilized ? harvestYield / 2 : 0; // 施肥增加产量
            return harvestYield + bonus;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "name='" + name + '\'' +
                ", growthTime=" + growthTime +
                ", isWatered=" + isWatered +
                ", isFertilized=" + isFertilized +
                ", harvestYield=" + harvestYield +
                ", isMature=" + isMature() +
                '}';
    }
}