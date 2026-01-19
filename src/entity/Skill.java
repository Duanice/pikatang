package entity;

/**
 * 技能实体类
 */
public class Skill {
    private int skillId;
    private String name;
    private String type; // 生产技能、生活技能、动作类、其他技能
    private int level;
    private String description;

    public Skill() {}

    public Skill(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.level = 1;
    }

    // Getters and Setters
    public int getSkillId() { return skillId; }
    public void setSkillId(int skillId) { this.skillId = skillId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public void levelUp() {
        this.level++;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Skill skill = (Skill) obj;
        return name != null ? name.equals(skill.name) : skill.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}