package entity;

/**
 * 家具实体类
 */
public class Furniture {
    private int furnitureId;
    private String name;
    private String type; // 桌子、椅子、床、装饰品等
    private String color;
    private int price;
    private int x; // 房间中的位置
    private int y;
    private int rotation; // 旋转角度

    public Furniture() {}

    public Furniture(String name, String type, String color, int price) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.price = price;
    }

    // Getters and Setters
    public int getFurnitureId() { return furnitureId; }
    public void setFurnitureId(int furnitureId) { this.furnitureId = furnitureId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getRotation() { return rotation; }
    public void setRotation(int rotation) { this.rotation = rotation; }

    // 业务方法
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void rotate(int degrees) {
        this.rotation = (this.rotation + degrees) % 360;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", position=(" + x + "," + y + ")" +
                ", rotation=" + rotation +
                '}';
    }
}