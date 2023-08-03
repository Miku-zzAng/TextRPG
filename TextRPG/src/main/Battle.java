package main;

import java.util.*;

public class Battle {
	private Player player;
	private Monster monster;
	private boolean playerTurn = true; // 플레이어가 먼저 공격하는 것으로 초기값 설정
	private Game game;
	private Random rand = new Random();
    public boolean isCritical;

	// 생성자에서 플레이어와 몬스터를 받아서 필드에 저장
	public Battle(Player player, Monster monster, Game game) {
		this.player = player;
		this.monster = monster;
		this.game = game;
	}

	// 공격 차례를 결정하는 메소드
	// 현재 플레이어의 차례인지 확인하고 차례가 끝나면 공격 차례를 상대방에게 넘김
	public void changeTurn() {
		playerTurn = !playerTurn;
	}

	// 명중률 계산
	public double calculateHitRate() {
		double hitRate = 1 - (monster.getLevel() - player.getLevel()) * 0.1;
		return hitRate > 0 ? hitRate : 0;
	}

	// 데미지 계산
	public int calculateDamage() {
		double hitRate = calculateHitRate();
        double critRate = 0.2;
        
		double baseDamage = player.getAttackPower() * hitRate * (0.8 + rand.nextDouble() * 0.4);
        
	    this.isCritical = false;
		double critDamage = 0;
        if(rand.nextDouble() < critRate) {  // 크리티컬 확률에 따른 크리티컬 데미지 계산
        	this.isCritical = true;
            critDamage = baseDamage * (0.5 + rand.nextDouble() * 0.5);
        }
        
        double finalDamage = baseDamage + critDamage;
        return (int)finalDamage;
	}

	// 플레이어와 몬스터가 공격하는 메소드
	// 현재 공격 차례인 쪽이 상대방을 공격하고, 공격 차례를 바꿈
	public void attack() {
		if (playerTurn) {
			player.attack(monster, this);
		} else {
			monster.attack(player);
		}
		changeTurn();
	}

	public void useSkill(Skill skill) {
		if (playerTurn) {
			player.useSkill(skill);
			skill.applyEffect(player, monster);
		} else {
			monster.attack(player);
		}
		changeTurn();
	}

	// 전투가 끝났는지 확인하는 메소드
	// 플레이어나 몬스터 중 하나라도 체력이 0 이하면 전투가 끝난 것으로 판단
	public boolean isBattleOver() {
		return player.isDead() || monster.isDead();
	}

	public Monster getMonster() {
		return this.monster;
	}
}
