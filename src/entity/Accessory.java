package entity;

/**
 * 配饰实体类
 */
public class Accessory {
    private int accessoryId;
    private String name;
    private String type; // 帽子、眼镜、项链等
    private String color;
    private int price;

    public Accessory() {}

    public Accessory(String name, String type, String color, int price) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.price = price;
    }

    // Getters and Setters
    public int getAccessoryId() { return accessoryId; }
    public void setAccessoryId(int accessoryId) { this.accessoryId = accessoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        return "Accessory{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}