package entity;

/**
 * 穿搭实体类
 * 代表一套完整的服装搭配
 */
public class Outfit {
    private int outfitId;
    private String name;
    private Clothing top;      // 上衣
    private Clothing bottom;   // 下衣
    private Clothing shoes;    // 鞋子
    private Accessory hat;     // 帽子
    private Accessory glasses; // 眼镜
    private Accessory necklace; // 项链

    public Outfit() {}

    public Outfit(String name) {
        this.name = name;
    }

    // Getters and Setters
    public int getOutfitId() { return outfitId; }
    public void setOutfitId(int outfitId) { this.outfitId = outfitId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Clothing getTop() { return top; }
    public void setTop(Clothing top) { this.top = top; }

    public Clothing getBottom() { return bottom; }
    public void setBottom(Clothing bottom) { this.bottom = bottom; }

    public Clothing getShoes() { return shoes; }
    public void setShoes(Clothing shoes) { this.shoes = shoes; }

    public Accessory getHat() { return hat; }
    public void setHat(Accessory hat) { this.hat = hat; }

    public Accessory getGlasses() { return glasses; }
    public void setGlasses(Accessory glasses) { this.glasses = glasses; }

    public Accessory getNecklace() { return necklace; }
    public void setNecklace(Accessory necklace) { this.necklace = necklace; }

    @Override
    public String toString() {
        return "Outfit{" +
                "name='" + name + '\'' +
                ", top=" + (top != null ? top.getName() : "none") +
                ", bottom=" + (bottom != null ? bottom.getName() : "none") +
                ", shoes=" + (shoes != null ? shoes.getName() : "none") +
                ", hat=" + (hat != null ? hat.getName() : "none") +
                ", glasses=" + (glasses != null ? glasses.getName() : "none") +
                ", necklace=" + (necklace != null ? necklace.getName() : "none") +
                '}';
    }
}