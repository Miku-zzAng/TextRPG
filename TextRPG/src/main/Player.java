package main;

import java.io.*;
import javax.sound.sampled.*;

public class Player {
	private String name;
	private int hp; // 플레이어의 현재 체력
	private int maxHp; // 플레이어의 최대 체력
	private int mp; // 플레이어의 현재 마나
	private int maxMp; // 플레이어의 최대 마나
	private int currentexp; // 플레이어의 현재 경험치
	private int maxexp; // 플레이어의 현재 레벨 요구 경험치
	private int level; // 플레이어의 현재 레벨
	private int attackPower; // 플레이어의 공격력
	private Location currentLocation; // 플레이어의 현재 위치, Location은 임의의 위치 클래스
	private String imageUrl; // 플레이어 이미지 URL
	private Game game;
	private Job job;
	private Inventory inventory; // Inventory 클래스를 멤버 변수로 가짐
	//protected String soundPath;

	public Player(Game game, String name, Job job, String imageUrl) {
		this.name = name;
		this.maxHp = 50;
		this.hp = 50;
		this.attackPower = 5;
		this.currentexp = 0;
		this.maxexp = 5;
		this.level = 1;
		this.game = game;
		this.imageUrl = imageUrl;
		this.job = job;
		this.mp = 10;
		this.maxMp = 10;
		this.inventory = new Inventory(); // 새로운 Inventory 인스턴스를 생성하여 할당
	}

	public void addConsumableItem(ConsumableItem item, int quantity) {
		this.inventory.addItem(item, quantity);
	}

	public boolean removeConsumableItem(ConsumableItem item, int quantity) {
		return this.inventory.removeConsumableItem(item, quantity);
	}

	// 인벤토리를 확인하는 메소드
	public void showInventory() {
		this.inventory.showInventory();
	}

	public String listSkills() {
		StringBuilder skillsList = new StringBuilder();
		for (int i = 0; i < job.getSkills().size(); i++) {
			skillsList.append((i + 2) + ". " + job.getSkills().get(i).getName() + " (MP "+ job.getSkills().get(i).getMpCost() + " 소모)<br>");
		}
		return skillsList.toString();
	}

	// 플레이어가 공격하는 메소드
	public void attack(Monster monster, Battle battle) {
		int damage = battle.calculateDamage();
		if ( battle.isCritical == true) {
			game.updateCommandInfoArea("<font color='red'><b>크리티컬 데미지!</b></font>");
		}
		monster.takeDamage(damage); // 몬스터에게 플레이어의 공격력만큼의 피해 
	}
	
	public void useSkill(Skill skill) {
		this.mp -= skill.getMpCost(); // mp를 소비
		skill.playSkillSound();
		game.updatePlayerInfo();
	}
	
	// 플레이어가 몬스터에게 공격받을 때 호출되는 메소드
	public void takeDamage(int damage) {
		this.hp -= damage;
		if (this.hp < 0) {
			this.hp = 0;
		}
		game.updateCommandInfoArea(
				"<font color='Purple'>" + damage + "</font> 만큼의 피해를 입었습니다. (현재 나의 <font color='red'>HP: " + getHp()
						+ "/" + getMaxHp() + "</font>)<br>───────────────────────────");
		game.updatePlayerInfo();
	}

	// 플레이어가 몬스터를 죽일 때 경험치를 획득하는 메소드
	public void gainExp(int exp) {
	    if (this.currentexp + exp >= this.maxexp) {
	        this.currentexp = this.maxexp;
	    } else {
	        this.currentexp = this.currentexp + exp;
	    }
	    game.updateCommandInfoArea("<font color='#3CB371'>EXP " + exp + "</font> 획득! (현재 <font color='#3CB371'>EXP: "
	            + getCurrentExp() + "/" + getMaxExp() + "</font>)<br>───────────────────────────");
	    game.updatePlayerInfo();
	    game.setBattle(null); // 전투 종료
	    game.setMonster(null); // monster 객체를 null로 설정하여 삭제
	    game.updateMonsterInfo();
	     // 레벨업이 필요한지 체크
	    if (this.currentexp >= this.maxexp) {
	        levelUp();
	    }
	}

	// 플레이어가 레벨업을 하는 메소드
	private void levelUp() {
		int oldMaxHp = this.maxHp;
		int oldAttackPower = this.attackPower;
		this.level += 1;
		this.maxHp = this.maxHp + getJob().getLevelUpMaxHp();
		this.maxMp = this.maxMp + getJob().getLevelUpMaxMp();
		this.attackPower = this.attackPower + getJob().getLevelUpAttackPower();
		this.hp = this.maxHp; // 레벨업 시 체력을 전부 회복
		this.mp = this.maxMp;
		this.currentexp = 0; // 레벨업 후 경험치 초기화
		this.maxexp += level * 7;
		game.updateCommandInfoArea("<p style='font-size:18px;'><b><font color='rgb(0,255,255)'>　　　　　　레벨 업!</font></b></p>" + "<p style='font-size:11px;'><b>　　　　　　　　　HP: " + oldMaxHp
				+ " -> " + this.maxHp + "<br>　　　　　　　　　공격력: " + oldAttackPower + " -> " + this.attackPower
				+ "</b></p>───────────────────────────");
		game.updatePlayerInfo();
		playSound("wav/levelup.wav");
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
		this.imageUrl = this.getJob().getJobImageUrl();
		//game.updateCommandInfoArea("<h3><font color='red'>"+ job.getJobName() + "</font><font color='black'>로 전직하였습니다!</font><br>───────────────────────────");
	}

	// 플레이어의 생존 여부를 반환하는 메소드
	public boolean isAlive() {
		return hp > 0; // 체력이 0 이상이면 살아있는 것으로 간주
	}

	// 플레이어의 체력이 0인지 확인하는 메소드
	public boolean isDead() {
		return this.hp <= 0;
	}

	// 플레이어가 전투에서 패배하고 일정량의 경험치를 감소하는 메소드
	public void loseExp() {
		this.currentexp = (int) (this.currentexp * 0.9); // 경험치 10% 감소

		// HP가 0이면 죽음을 표시하고, HP를 최대 HP까지 회복
		if (this.hp <= 0) {
			this.hp = this.maxHp;
			game.setMonster(null); // monster 객체를 null로 설정하여 삭제
			game.setBattle(null); // 전투 종료
			game.updateMonsterInfo();
			game.updateCommandInfoArea(
					"<b><font color='gray'>전투에서 패배하였습니다. HP를 모두 회복하고 경험치 10%를 잃습니다.</font></b><br>───────────────────────────");
			game.updatePlayerInfo();
			playSound("wav/stone.wav");
		}

		// 경험치가 0보다 작아지면 0으로
		if (this.currentexp < 0) {
			this.currentexp = 0;
			game.updatePlayerInfo();
		}
	}
	
	public void heal(int healAmount) {
		if (this.hp + healAmount >= this.maxHp) {
			this.hp = this.maxHp;
		} else {
			this.hp += healAmount;
		}
		game.updateCommandInfoArea("HP가 <font color='red'>"+ healAmount + "</font> 회복되었습니다.<br>───────────────────────────");
		game.updatePlayerInfo();
	}
	
	public void mana(int manaAmount) {
		if (this.mp + manaAmount >= this.maxMp) {
			this.mp = this.maxMp;
		} else {
			this.mp += manaAmount;
		}
		game.updateCommandInfoArea("MP가 <font color='blue'>"+ manaAmount + "</font> 회복되었습니다.<br>───────────────────────────");
		game.updatePlayerInfo();
	}
	
	@Override
	public String toString() {
		return String.format("<font color='purple'>&lt;%s&gt;<font color='black'><br>Level: %d<br>공격력: %d<br>직업: %s",
				this.name, this.level, this.attackPower, this.getJob().getJobName());
	}
	
    public void playSound(String soundPath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
	public Location getCurrentLocation() {
		return this.currentLocation;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public int getAttackPower() {
		return this.attackPower;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return this.mp;
	}

	public int getMaxMp() {
		return this.maxMp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getCurrentExp() {
		return this.currentexp;
	}

	public int getMaxExp() {
		return this.maxexp;
	}

	public Location getLocation() {
		return this.currentLocation;
	}

	public void setLocation(Location location) {
		this.currentLocation = location;
	}

}