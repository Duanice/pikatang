package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 农场实体类
 */
public class Farm {
    private int farmId;
    private List<Crop> crops;
    private int size; // 农场大小

    public Farm() {
        this.crops = new ArrayList<>();
        this.size = 10; // 默认大小
    }

    public Farm(int size) {
        this();
        this.size = size;
    }

    // Getters and Setters
    public int getFarmId() { return farmId; }
    public void setFarmId(int farmId) { this.farmId = farmId; }

    public List<Crop> getCrops() { return crops; }
    public void setCrops(List<Crop> crops) { this.crops = crops; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    // 业务方法
    public void plantCrop(Crop crop) {
        if (crops.size() < size) {
            crops.add(crop);
        }
    }

    public void harvestCrop(Crop crop) {
        crops.remove(crop);
    }

    public List<Crop> getMatureCrops() {
        return crops.stream()
                .filter(Crop::isMature)
                .toList();
    }

    public int getAvailableSpace() {
        return size - crops.size();
    }

    @Override
    public String toString() {
        return "Farm{" +
                "farmId=" + farmId +
                ", cropCount=" + crops.size() +
                ", size=" + size +
                ", availableSpace=" + getAvailableSpace() +
                '}';
    }
}