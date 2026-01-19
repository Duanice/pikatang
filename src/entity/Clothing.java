package entity;

/**
 * 服装实体类
 */
public class Clothing {
    private int clothingId;
    private String name;
    private String type; // 上衣、下衣、鞋子等
    private String color;
    private int price;

    public Clothing() {}

    public Clothing(String name, String type, String color, int price) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.price = price;
    }

    // Getters and Setters
    public int getClothingId() { return clothingId; }
    public void setClothingId(int clothingId) { this.clothingId = clothingId; }

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
        return "Clothing{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}