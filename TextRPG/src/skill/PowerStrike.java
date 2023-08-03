package skill;
import main.*;

public class PowerStrike extends Skill {
    public PowerStrike() {
        this.name = "파워 스트라이크"; // 스킬 이름
        this.mpCost = 10;  // 스킬 사용시 mp 10을 소모
        this.skillSoundPath = "wav/skillwav/powerstrike.wav";
        this.type = "공격형"; // 스킬 타입
        this.effect = "MP를 소비하여 적에게 일격을 가한다."; // 스킬 효과
    }

    // 스킬이 적용되는 메서드
    @Override
    public void applyEffect(Player player, Monster monster) {
        int damage = (int)(player.getAttackPower() * 1.3); // 공격력의 1.3배의 피해를 계산
        monster.takeDamage(damage); // 계산된 피해를 몬스터에게 적용
    }
}