package entity;

/**
 * 任务实体类
 */
public class Task {
    private int taskId;
    private String name;
    private String description;
    private String type; // 日常任务、活动任务等
    private int requiredLevel;
    private int rewardExperience;
    private int rewardGold;
    private int rewardSilver;
    private boolean isCompleted;
    private boolean isAccepted;

    public Task() {}

    public Task(String name, String description, String type, int requiredLevel) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.requiredLevel = requiredLevel;
        this.isCompleted = false;
        this.isAccepted = false;
    }

    // Getters and Setters
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getRequiredLevel() { return requiredLevel; }
    public void setRequiredLevel(int requiredLevel) { this.requiredLevel = requiredLevel; }

    public int getRewardExperience() { return rewardExperience; }
    public void setRewardExperience(int rewardExperience) { this.rewardExperience = rewardExperience; }

    public int getRewardGold() { return rewardGold; }
    public void setRewardGold(int rewardGold) { this.rewardGold = rewardGold; }

    public int getRewardSilver() { return rewardSilver; }
    public void setRewardSilver(int rewardSilver) { this.rewardSilver = rewardSilver; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { this.isCompleted = completed; }

    public boolean isAccepted() { return isAccepted; }
    public void setAccepted(boolean accepted) { this.isAccepted = accepted; }

    // 业务方法
    public boolean canAccept(int playerLevel) {
        return playerLevel >= requiredLevel;
    }

    public void complete() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", rewardExperience=" + rewardExperience +
                ", isCompleted=" + isCompleted +
                '}';
    }
}