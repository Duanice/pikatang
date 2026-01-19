package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 牧场实体类
 */
public class Pasture {
    private int pastureId;
    private List<Animal> animals;
    private int size; // 牧场大小

    public Pasture() {
        this.animals = new ArrayList<>();
        this.size = 8; // 默认大小
    }

    public Pasture(int size) {
        this();
        this.size = size;
    }

    // Getters and Setters
    public int getPastureId() { return pastureId; }
    public void setPastureId(int pastureId) { this.pastureId = pastureId; }

    public List<Animal> getAnimals() { return animals; }
    public void setAnimals(List<Animal> animals) { this.animals = animals; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    // 业务方法
    public void addAnimal(Animal animal) {
        if (animals.size() < size) {
            animals.add(animal);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimalsNeedingFood() {
        return animals.stream()
                .filter(animal -> animal.needsFeeding())
                .toList();
    }

    public List<Animal> getAnimalsReadyForHarvest() {
        return animals.stream()
                .filter(Animal::isReadyForHarvest)
                .toList();
    }

    public int getAvailableSpace() {
        return size - animals.size();
    }

    @Override
    public String toString() {
        return "Pasture{" +
                "pastureId=" + pastureId +
                ", animalCount=" + animals.size() +
                ", size=" + size +
                ", availableSpace=" + getAvailableSpace() +
                '}';
    }
}