package main;

import javax.sound.sampled.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

//Monster 클래스 선언
public class Monster {
	private String name; // 몬스터의 이름
	private int maxHp; // 몬스터의 최대 체력
	private int hp; // 몬스터의 현재 체력
	private int attackPower; // 몬스터의 공격력
	private int exp; // 몬스터를 잡았을 시 주는 경험치
	private int level;
	private Game game;
	private String imageUrl;  // 몬스터 이미지 URL
	public String deathSoundPath;
	
	private LootTable lootTable;

	// 생성자: 새로운 몬스터를 만들 때 호출됩니다.
	public Monster(String name, int maxHp, int attackPower, Game game, int exp, int level, String imageUrl, String deathSoundPath) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackPower = attackPower;
		this.game = game;
		this.exp = exp;
		this.level = level;
		this.imageUrl = imageUrl;  // 이미지 URL 저장
        this.lootTable = new LootTable();  // LootTable 초기화
        this.deathSoundPath = deathSoundPath;
	}
	
    public void playDeathSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(deathSoundPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
	public String getImageUrl() {
		return imageUrl;
	}
	
	public String getDeathSoundPath() {
		return deathSoundPath;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public Game getGame() {
		return game;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackPower() {
		return attackPower;
	}
	
	// Monster 클래스에 추가
	public void setLootTable(LootTable lootTable) {
	    this.lootTable = lootTable;
	}
	public LootTable getLootTable() {
		return this.lootTable;
	}
	
	 // 드롭 아이템 추가
    protected void addLootItem(ConsumableItem item, int chance) {
        this.lootTable.addLootItem(item, chance);
    }
    
    public void dropItem() {
        ConsumableItem item = this.lootTable.rollForLoot();
        if (item != null) {
            game.addItemToPlayerInventory(item, 1);
        } else {
        }
    }
    
	// 피해를 받는 메소드
	public void takeDamage(int damage) {
		// 입력받은 피해량만큼 체력이 감소
		this.hp -= damage;

		// 체력이 0 이하로 떨어지면 0으로 설정
		if (this.hp < 0) {
			this.hp = 0;
		}
		game.updateCommandInfoArea("<font color=#FF7F50>"+ damage + "</font> 만큼의 데미지를 주었습니다. (" + this.getName() + "의 <font color='red'>HP: "+ getHp() + "/"+ getMaxHp() +"</font>)<br>───────────────────────────");
		game.updateMonsterInfo();
	}

	// 몬스터의 생존 여부를 반환하는 메소드
	public boolean isAlive() {
		return hp > 0; // 체력이 0 이상이면 살아있는 것으로 간주
	}

	// 몬스터의 체력이 0인지 확인하는 메소드
	public boolean isDead() {
		return this.hp <= 0;
	}

	// 이 몬스터의 정보를 문자열로 반환하는 메소드
	@Override
	public String toString() {
		return String.format("&lt;%s&gt <font color='black'><br>Level: %d<br>HP:</font> <font color='red'>%d/%d</font>", name, level, hp, maxHp);
	}

	// 플레이어에게 이 몬스터의 공격력만큼의 피해
	public void attack(Player player) {
		player.takeDamage(attackPower);
	}

	public String getName() {
		return name;
	}

	public int getExp() {
		return this.exp;
	}

}

