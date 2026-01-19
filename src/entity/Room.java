package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间实体类
 * 代表玩家的虚拟家园房间
 */
public class Room {
    private int roomId;
    private Player owner;
    private String roomName;
    private List<Furniture> furnitureList;
    private Farm farm;
    private Pasture pasture;

    public Room() {
        this.furnitureList = new ArrayList<>();
    }

    public Room(Player owner, String roomName) {
        this();
        this.owner = owner;
        this.roomName = roomName;
    }

    // Getters and Setters
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public Player getOwner() { return owner; }
    public void setOwner(Player owner) { this.owner = owner; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public List<Furniture> getFurnitureList() { return furnitureList; }
    public void setFurnitureList(List<Furniture> furnitureList) { this.furnitureList = furnitureList; }

    public Farm getFarm() { return farm; }
    public void setFarm(Farm farm) { this.farm = farm; }

    public Pasture getPasture() { return pasture; }
    public void setPasture(Pasture pasture) { this.pasture = pasture; }

    // 业务方法
    public void addFurniture(Furniture furniture) {
        furnitureList.add(furniture);
    }

    public void removeFurniture(Furniture furniture) {
        furnitureList.remove(furniture);
    }

    public boolean hasFurniture(String furnitureName) {
        return furnitureList.stream().anyMatch(f -> f.getName().equals(furnitureName));
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", furnitureCount=" + furnitureList.size() +
                ", hasFarm=" + (farm != null) +
                ", hasPasture=" + (pasture != null) +
                '}';
    }
}