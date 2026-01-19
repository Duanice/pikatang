package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色实体类
 * 代表玩家在游戏中的角色
 */
public class Role {
    private int roleId;
    private int playerId; // 关联的玩家ID
    private String name;
    private String gender;
    private String appearance; // 外观描述
    private int level;
    private int experience;
    private List<Skill> skills;
    private Wardrobe wardrobe;
    private Outfit currentOutfit;

    public Role() {
        this.skills = new ArrayList<>();
        this.wardrobe = new Wardrobe();
        this.level = 1;
        this.experience = 0;
    }

    public Role(String name, String gender, String appearance) {
        this();
        this.name = name;
        this.gender = gender;
        this.appearance = appearance;
    }

    // Getters and Setters
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAppearance() { return appearance; }
    public void setAppearance(String appearance) { this.appearance = appearance; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }

    public Wardrobe getWardrobe() { return wardrobe; }
    public void setWardrobe(Wardrobe wardrobe) { this.wardrobe = wardrobe; }

    public Outfit getCurrentOutfit() { return currentOutfit; }
    public void setCurrentOutfit(Outfit currentOutfit) { this.currentOutfit = currentOutfit; }

    // 业务方法
    public void gainExperience(int exp) {
        this.experience += exp;
        checkLevelUp();
    }

    private void checkLevelUp() {
        while (this.experience >= this.level * 100) {
            this.level++;
        }
    }

    public void learnSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    public boolean hasSkill(String skillName) {
        return skills.stream().anyMatch(skill -> skill.getName().equals(skillName));
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", skills=" + skills +
                '}';
    }
}