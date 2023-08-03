package main;

import java.util.*;

public abstract class Job {
    protected int levelUpMaxHp;
    protected int levelUpMaxMp;
    protected int levelUpAttackPower;
    protected String jobName;
    protected List<Skill> skills;
    protected String jobImageUrl;
    
    public Job() {
        skills = new ArrayList<>();
    }

    public abstract void addSkill(Skill skill);

    public abstract void removeSkill(Skill skill);

    public abstract void updateStats();
    
    public String getJobName() {
        return this.jobName;
    }
    
    public List<Skill> getSkills() {
        return skills;
    }
    
    public int getLevelUpMaxHp() {
        return this.levelUpMaxHp;
    }
    
    public String getJobImageUrl() {
        return this.jobImageUrl;
    }
    
    public int getLevelUpMaxMp() {
        return this.levelUpMaxMp;
    }
    
    public int getLevelUpAttackPower() {
        return this.levelUpAttackPower;
    }
    
}