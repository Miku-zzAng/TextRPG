package main;

import java.io.*;
import javax.sound.sampled.*;

public abstract class Skill {
    protected String name;
    protected String type;
    protected String effect;
    protected int mpCost;
	public String skillSoundPath;
	
    public abstract void applyEffect(Player player, Monster monster);
    
    public void playSkillSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(skillSoundPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // 스킬 이름을 반환하는 메서드
    public int getMpcost() {
        return this.mpCost;
    }
    
    // 스킬 이름을 반환하는 메서드
    public String getName() {
        return this.name;
    }

    // 스킬 타입을 반환하는 메서드
    public String getType() {
        return this.type;
    }

    // 스킬 효과를 반환하는 메서드
    public String getEffect() {
        return this.effect;
    }
    
    // 스킬 이름을 반환하는 메서드
    public int getMpCost() {
        return this.mpCost;
    }
}