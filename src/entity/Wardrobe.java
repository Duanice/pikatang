package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 衣橱实体类
 * 存储角色的服装和配饰
 */
public class Wardrobe {
    private List<Clothing> clothes;
    private List<Accessory> accessories;

    public Wardrobe() {
        this.clothes = new ArrayList<>();
        this.accessories = new ArrayList<>();
    }

    // Getters and Setters
    public List<Clothing> getClothes() { return clothes; }
    public void setClothes(List<Clothing> clothes) { this.clothes = clothes; }

    public List<Accessory> getAccessories() { return accessories; }
    public void setAccessories(List<Accessory> accessories) { this.accessories = accessories; }

    // 业务方法
    public void addClothing(Clothing clothing) {
        clothes.add(clothing);
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
    }

    public boolean hasClothing(String clothingName) {
        return clothes.stream().anyMatch(c -> c.getName().equals(clothingName));
    }

    public boolean hasAccessory(String accessoryName) {
        return accessories.stream().anyMatch(a -> a.getName().equals(accessoryName));
    }
}