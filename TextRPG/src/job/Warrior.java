package job;
import main.*;
import skill.*;

public class Warrior extends Job {
	public static final String WARRIOR_IMG = "https://upload3.inven.co.kr/upload/2022/02/21/bbs/i15493681709.png";
    
	public Warrior() {
        super();
        this.levelUpMaxHp = 50; // 전사는 레벨업당 HP가 50씩 증가
        this.levelUpMaxMp = 10; // 전사는 레벨업당 MP가 10씩 증가
        this.levelUpAttackPower = 10; // 전사는 레벨업당 공격력이 10씩 증가
        this.jobName = "전사";
        this.jobImageUrl = WARRIOR_IMG;
        this.addSkill(new PowerStrike()); // 전사의 고유 스킬 추가
    }

    @Override
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    @Override
    public void removeSkill(Skill skill) {
        skills.removeIf(s -> s.getName().equals(skill.getName()));
    }
    
    @Override
    public void updateStats() {
        // 레벨업시 증가하는 능력치를 업데이트하는 로직을 여기에 작성
    }
}
