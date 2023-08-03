//Game 클래스는 Main 클래스에서 정의한 라벨들을 받아와서 게임을 실행하며, 라벨의 텍스트를 갱신
package main;

import java.net.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.sound.sampled.*;
import java.io.*;

import job.*;
import location.*;
import item.*;

public class Game {
	private static final int MAX_TEXT_LENGTH = 5000; // 최대 텍스트창 제한

	private Player player; // 플레이어 정보
	private Monster monster; // 현재 전투 중인 몬스터 정보
	private Location location; // 플레이어의 현재 위치
	private Battle battle; // 현재 전투 상황 정보

	private JProgressBar hpBar;
	private JProgressBar mpBar;
	private JProgressBar expBar;
	private JLabel playerInfoLabel;
	private JLabel locationInfoLabel;
	private JTextPane commandInfoArea;
	private JLabel monsterInfoLabel;
	JTextField userCommandField;
	private JButton enterButton;
	private JLabel monsterImageLabel;
	private JLabel playerJobImgLabel;
	private JLabel backgroundLabel;
	private String textcolor;
	private JProgressBar monsterhpBar;

	private int commandState = 0;
	// 게임 상태를 다단계 경우의 수로 구현하기 위해 사용

	private String currentBgmUrl;
	private int selectedItemIndex; // 새로 추가한 필드

	private ArrayList<Location> locations; // 모든 위치를 저장하는 ArrayList
	private ArrayList<Location> connectedLocations;

	private Clip clip = null;
	private AudioInputStream inputStream = null;

	// 생성
	public Game() {
		this.locations = new ArrayList<>(); // ArrayList 초기화

		Job warrior = new Warrior();

		player = new Player(this, "지존궁수v", warrior, Warrior.WARRIOR_IMG);

		// player.setJob(warrior);

		// MapManager를 사용하여 맵을 생성하고 추가
		MapManager mapManager = new MapManager(this);
		mapManager.createMaps();

		// 위치 객체를 locations 리스트에서 가져와 초기화
		location = this.locations.get(0);
		player.setLocation(location);

		// 초기에는 몬스터와 전투 상황이 없으므로 null로 초기화
		monster = null;
		battle = null;
	}

	// 게임 시작 메소드
	public void start(JLabel playerInfoLabel, JLabel locationInfoLabel, JTextPane commandInfoArea,
			JLabel monsterInfoLabel, JTextField userCommandField, JLabel monsterImageLabel, JLabel playerJobImgLabel,
			JButton enterButton, JLabel backgroundLabel, JProgressBar hpBar, JProgressBar mpBar, JProgressBar expBar, JProgressBar monsterhpBar) {

		// 라벨 객체들을 멤버에 저장
		this.playerInfoLabel = playerInfoLabel;
		this.locationInfoLabel = locationInfoLabel;
		this.commandInfoArea = commandInfoArea;
		this.monsterInfoLabel = monsterInfoLabel;
		this.userCommandField = userCommandField;
		this.monsterImageLabel = monsterImageLabel;
		this.playerJobImgLabel = playerJobImgLabel;
		this.enterButton = enterButton;
		this.backgroundLabel = backgroundLabel;
		this.hpBar = hpBar;
		this.mpBar = mpBar;
		this.expBar = expBar;
		this.monsterhpBar = monsterhpBar;
		changeBGM(player.getCurrentLocation().getBgmUrl());

		updatePlayerInfo();
		updateLocationInfo();
		updateMonsterInfo();
		updateCommandInfoArea(
				"<b>무엇을 하시겠습니까?</b><br><br>1. 이동<br>2. 탐색<br>3. 인벤토리 열기<br>4. 설정<br>0. 게임 종료<br>───────────────────────────");

	}

	public void updateCommandInfoArea(String newText) {
		HTMLEditorKit editorKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) this.commandInfoArea.getStyledDocument();
		String formattedText = "<div style='font-family:Malgun Gothic; font-size:10px;'>" + newText + "</div>";

		try {
			editorKit.insertHTML(htmlDoc, htmlDoc.getLength(), formattedText, 0, 0, null);
		} catch (BadLocationException | IOException e) {
			e.printStackTrace();
		}
	}

	public void updatePlayerInfo() {
		// 플레이어의 정보를 라벨에 출력
		playerInfoLabel.setText("<html>" + player.toString() + "</html>");
		updatePlayerJobImg(player, playerJobImgLabel);
		updateBars();
	}

	private void updatePlayerJobImg(Player player, JLabel playerJobImgLabel) {
		String playerJobImgUrl = player.getImageUrl();
		ImageIcon playerJobImgIcon = createImageIcon(playerJobImgUrl);
		playerJobImgLabel.setIcon(playerJobImgIcon);
	}

	private void updateBars() {
		int currentHp = getPlayer().getHp();
		int maxHp = getPlayer().getMaxHp();
	    hpBar.setMaximum(maxHp);
		hpBar.setValue(currentHp);
		hpBar.setString("HP   " + currentHp + " / " + maxHp);

		int currentMp = getPlayer().getMp();
		int maxMp = getPlayer().getMaxMp();
		mpBar.setMaximum(maxMp);
		mpBar.setValue(currentMp);
		mpBar.setString("MP   " + currentMp + " / " + maxMp);

		int currentExp = getPlayer().getCurrentExp();
		int maxExp = getPlayer().getMaxExp();
		expBar.setMaximum(maxExp);
		expBar.setValue(currentExp);
		expBar.setString("EXP   " + currentExp + " / " + maxExp);
		
		// 만약 전투가 발생하였다면
		if(getMonster() != null) {
			int currentMonsterHp = getMonster().getHp();
			int mosterMaxHp = getMonster().getMaxHp();
			monsterhpBar.setMaximum(mosterMaxHp);
			monsterhpBar.setValue(currentMonsterHp);
			monsterhpBar.setString("HP   " + currentMonsterHp + " / " + mosterMaxHp);
			monsterhpBar.setVisible(true); // 전투가 발생했으므로 체력바를 표시
		} else {
			monsterhpBar.setVisible(false);
		}
	}

	public void updateMonsterInfo() {
		// 몬스터의 정보를 라벨에 출력
		if (getMonster() == null) {
			// 전투 중이 아니면 몬스터 정보 라벨은 비움
			monsterImageLabel.setIcon(null);
			monsterInfoLabel.setText(
					"<html><div style='text-align: left;'><font color='black'>전투 대상 몬스터<br>없음</font></div></html>");
		} else {
			// 전투 중이면 현재 몬스터의 정보를 출력
			updateMonsterImage(getMonster(), monsterImageLabel);
			int levelDifference = player.getLevel() - getMonster().getLevel();
			setTextColor("black");

			if (levelDifference <= -5) {
				setTextColor("maroon");
			} else if (levelDifference <= 1 && levelDifference >= -1) {
				setTextColor("black");
			} else if (levelDifference >= 2 && 4 >= levelDifference) {
				setTextColor("green");
			} else if (levelDifference >= 5) {
				setTextColor("gray");
			}

			monsterInfoLabel.setText("<html><font color='black'>전투 대상 몬스터</font><br><font color='" + getTextColor()
					+ "'>" + getMonster().toString() + "</font></div></html>");
		}
	}

	private void updateMonsterImage(Monster monster, JLabel monsterImageLabel) {
		if (monster == null) {
			monsterImageLabel.setIcon(null);
			return;
		}
		String monsterImageUrl = monster.getImageUrl();
		ImageIcon monsterImageIcon = createImageIcon(monsterImageUrl);
		monsterImageLabel.setIcon(monsterImageIcon);
	}

	public void updateLocationInfo() {
		// 위치의 정보를 라벨에 출력
		locationInfoLabel.setText("<html>" + player.getLocation().toString() + "</html>");
	}

	private ImageIcon createImageIcon(String imageUrl) {
		try {
			if (imageUrl == null || imageUrl.trim().isEmpty()) {
				throw new IllegalArgumentException("imageUrl is null or empty");
			}
			return new ImageIcon(new URL(imageUrl));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateBackgroundImage() {
		String imageUrl = player.getCurrentLocation().getImageUrl();
		ImageIcon backgroundImage = createBackgroundImage(imageUrl);
		backgroundLabel.setIcon(backgroundImage);

		// 배경 음악 업데이트를 추가
		String bgmUrl = player.getCurrentLocation().getBgmUrl();
		playBackgroundMusic(bgmUrl);
	}

	// 배경 이미지를 생성하는 메소드
	public ImageIcon createBackgroundImage(String fileName) {
		String imagePath = "file:///C:/Users/user/Desktop/20201123김선혁 미니프로젝트 TextRPG/TextRPG/images/" + fileName;
		URL imageUrl = null;
		try {
			imageUrl = new URL(imagePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (imageUrl != null) {
			return new ImageIcon(imageUrl);
		} else {
			return null;
		}
	}

	private void playBackgroundMusic(String newBgmUrl) {
		if (newBgmUrl.equals(currentBgmUrl)) {
			return;
		}
		currentBgmUrl = newBgmUrl;
		try {
			if (clip != null) {
				if (clip.isRunning()) {
					clip.stop();
				}
				clip.close();
				clip = null;
			}

			if (inputStream != null) {
				inputStream.close();
				inputStream = null;
			}

			inputStream = AudioSystem.getAudioInputStream(new File(currentBgmUrl));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void changeBGM(String newBgmUrl) {
		playBackgroundMusic(newBgmUrl);
	}

	public void stopBGM() {
		try {
			if (clip != null && clip.isRunning()) {
				clip.stop();
				clip.close();
				clip = null;
			}

			if (inputStream != null) {
				inputStream.close();
				inputStream = null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	// 맵을 추가하는 메소드
	public void addLocation(Location location) {
		this.locations.add(location);
	}

	// commandState를 1로 설정하고 있는 moveList() 메소드에서는 연결된 위치들을 보여주는 것으로 충분함
	// 실제로 이동하는 로직은 ActionListener에서 처리
	void moveList() {
		Location currentLocation = this.player.getLocation();
		connectedLocations = currentLocation.getConnectedLocation();

		if (connectedLocations.isEmpty()) {
			updateCommandInfoArea("이동할 수 있는 맵이 없습니다.<br>───────────────────────────");
			return;
		}
		currentLocation.showConnectedLocations();
	}

	void moveTo(int destinationIndex) {
		if (destinationIndex < 0 || destinationIndex >= connectedLocations.size()) {
			// 입력한 숫자가 연결된 위치의 범위를 벗어나는 경우
			updateCommandInfoArea("잘못된 맵 번호입니다. 다시 입력하세요.<br>───────────────────────────");
		} else {
			Location newLocation = connectedLocations.get(destinationIndex);
			player.setLocation(newLocation);
			location = newLocation;
			updateLocationInfo();
			updateBackgroundImage();
			updateCommandInfoArea("<b><font color='rgb(255,0,255)'>" + location.getName()
					+ "</font>(으)로 이동하였습니다.</b><br>───────────────────────────");
			changeBGM(player.getCurrentLocation().getBgmUrl());
			displayMainOptions();
		}
	}

	void explore() {
		updateCommandInfoArea("<b><font color='rgb(255,0,255)'>" + location.getName()
				+ "</font>(을)를 탐색중입니다...</b><br>───────────────────────────");
		setMonster(location.explore());

		if (getMonster() != null) {
			battle = new Battle(player, getMonster(), this);
			updateMonsterInfo();
			updateCommandInfoArea("<b>앗! <font color='" + getTextColor() + "'>&lt" + getMonster().getName()
					+ "&gt</font>(을)를 만났다!<br>무엇을 하시겠습니까?</b><br><br>1. 일반 공격<br>" + player.listSkills()
					+ "9. 소비아이템 인벤토리 열기<br>0. 도망치기<br>───────────────────────────");
			setCommandState(21);
		} else { // 몬스터를 만나지 못한 경우
			updateCommandInfoArea("아무것도 찾지 못했다.<br>───────────────────────────");
			setCommandState(0);
		}
	}

	void battleAttack() {
		updateCommandInfoArea("<b>일반 공격을 가합니다.</b>");
		battle.attack();
		checkBattleMosterAlive();
	}

	void battleUseSkill(int skillIndex) {
		Skill chosenSkill = player.getJob().getSkills().get(skillIndex);

		// 만약 mp가 부족할 경우,
		if (player.getMp() < chosenSkill.getMpCost()) {
			updateCommandInfoArea("MP가 부족합니다.<br>───────────────────────────");
			return; // 스킬을 사용하지 않음
		}
		// 만약 mp가 충분하면 스킬 사용
		performSkill(chosenSkill);
	}

	private void performSkill(Skill chosenSkill) {
		updateCommandInfoArea("<b><p style='font-size:13px;'>" + chosenSkill.getName() + "!</p></b>");
		battle.useSkill(chosenSkill);
		checkBattleMosterAlive();
	}

	private void checkBattleMosterAlive() {
		// 몬스터가 살아 있다면, 몬스터의 반격
		if (!getMonster().isDead()) {
			monsterCounterAttack();
		} else { // 몬스터가 살아있는게 아니라면, 전투에서 승리
			handleVictory();
		}
	}

	private void monsterCounterAttack() {
		updateCommandInfoArea("<b>"+getMonster().getName() + "의 반격!</b>");
		battle.attack(); // 몬스터가 공격

		if (battle.isBattleOver()) { // 전투가 끝났는지 확인
			handleBattleOver();
		} else { // 전투가 끝나지 않았다면
			displayBattleOptions();
		}

		updatePlayerInfo();
	}

	private void handleBattleOver() {
		if (player.isDead()) { // 플레이어가 죽었을 경우 (전투에서 패배)
			player.loseExp();
			displayMainOptions();
		}
	}

	private void handleVictory() {
		updateCommandInfoArea(getMonster().getName() + "(을)를 처치했습니다.");
		getMonster().dropItem(); // 전투에서 이겼을경우, 몬스터가 아이템을 드랍함
		getMonster().playDeathSound(); // 몬스터 죽는소리 재생
		player.gainExp(getMonster().getExp()); // 몬스터에 설정된 경험치만큼 플레이어가 먹음
		displayMainOptions();
	}

	void battleRun() {
	    // 무작위 실수를 생성
	    Random random = new Random();
	    double chance = random.nextDouble(); // 0~1 이하의 무작위 실수를 change로 반환

	    // chance가 0.33 이하이면 도망치기에 성공
	    if (chance <= 0.33) {
	        setMonster(null); // monster 객체를 null로 설정하여 삭제
	        setBattle(null); // battle 객체를 null로 설정하여 전투 종료
	        updateMonsterInfo();
	        updateCommandInfoArea("<b>전투에서 도망쳤습니다.</b><br>───────────────────────────");
	        displayMainOptions();
	    } else {
	        // chance가 0.33보다 크면 도망치기에 실패
	        updateCommandInfoArea("<b>도망치기 실패했습니다.....</b><br>───────────────────────────");
	        // 도망치기에 실패하면 몬스터의 턴이 돌아옴
	        if (battle != null) {
	            battle.changeTurn();
	            monsterCounterAttack();
	        }
	    }
	}

	void setting(int command, String userCommand) {
		if (getCommandState() == 4) {
			if (command == 0) {
				displayMainOptions();
			} else if (command == 1) {
				setCommandState(10);
				updateCommandInfoArea("<b>새로운 닉네임을 커맨드창에 입력해주세요.</b><br>───────────────────────────");
			}
		} else if (getCommandState() == 10) {
			getPlayer().setName(userCommand);
			updatePlayerInfo();
			updateCommandInfoArea(
					"<b>&lt;" + getPlayer().getName() + "&gt;(으)로 닉네임이 변경되었습니다.</b><br>───────────────────────────");
			displayMainOptions();
		}
	}

	public void showConsumableInventory() {
		// 먼저 플레이어의 소바이이템 인벤토리를 가져옴
		Inventory inventory = player.getInventory();
		// 가져온 인벤토리의 소비아이템 맵이 비어있는지 확인
		if (inventory.isConsumableInventoryEmpty()) {
			updateCommandInfoArea("소비 아이템 인벤토리가 비어있습니다.<br>───────────────────────────");

			if (getBattle() == null) {
				setCommandState(3);
			} else if (getBattle() != null) {
				setCommandState(21);
			}

		} else { // 소비아이템 맵이 비어있는게 아니라면,
			StringBuilder sb = new StringBuilder();
			sb.append("<b>현재 보유한 소비아이템 목록 -</b><br><br>");
			// 인벤토리에 있는 소비 아이템을 순서대로 출력
			int index = 1;
			for (ConsumableItem item : inventory.getConsumableItems().keySet()) {
				sb.append(index).append(". ").append(item.getName()).append(" - 수량: ")
						.append(inventory.getConsumableItems().get(item)).append("개<br>");
				index++;
			}
			// 돌아가기 선택지도 함께 표시
			sb.append("0. 돌아가기<br>───────────────────────────");
			updateCommandInfoArea(sb.toString());
			return;
		}
	}

	public void showConsumableItemDetail(int itemIndex) {
		Inventory inventory = player.getInventory();
		List<ConsumableItem> consumableItemList = new ArrayList<>(inventory.getConsumableItems().keySet());
		if (itemIndex >= consumableItemList.size() || itemIndex < 0) {
			updateCommandInfoArea("소비 아이템 목록: 잘못된 입력입니다.<br>───────────────────────────");
			return;
		} else {
			ConsumableItem selectedItem = consumableItemList.get(itemIndex);
			StringBuilder sb = new StringBuilder();
			sb.append("<b>&lt" + selectedItem.getName() + "&gt</b>").append("<br>설명 : ")
					.append(selectedItem.getDescription()).append("<br><br>").append("1. 사용하기<br>").append("2. 버리기<br>")
					.append("0. 돌아가기<br>───────────────────────────");

			updateCommandInfoArea(sb.toString());
		}
	}

	public void selectItem(int itemIndex) {
		// 인벤토리에서 해당 인덱스의 아이템 찾음
		Inventory inventory = player.getInventory();
		List<ConsumableItem> consumableItemList = new ArrayList<>(inventory.getConsumableItems().keySet());

		if (itemIndex - 1 >= consumableItemList.size() || itemIndex < 1) {
			updateCommandInfoArea("아이템 선택: 잘못된 입력입니다.<br>───────────────────────────");
			return;
		} else {
			// 아이템 인덱스 저장
			setSelectedItemIndex(itemIndex - 1);
			// 아이템 상세 정보와 사용/버리기 선택지를 출력
			showConsumableItemDetail(itemIndex - 1);
			// 아이템 선택 상태로 전환
			if (getCommandState() == 92) {
				setCommandState(921);
			} else if (getCommandState() == 32) {
				setCommandState(321);
			}
		}
	}

	public void useItem(int command) {
		// 사용 또는 버리려는 아이템 가져오기
		Inventory inventory = player.getInventory();
		List<ConsumableItem> consumableItemList = new ArrayList<>(inventory.getConsumableItems().keySet());
		ConsumableItem itemToUse = consumableItemList.get(getSelectedItemIndex());

		if (command == 1) { // 사용하기
			// 아이템의 효과를 플레이어에게 적용
			itemToUse.applyEffect(player);
			updateCommandInfoArea("<b>" + itemToUse.getName() + "</b>(을)를 사용하였습니다.<br>───────────────────────────");

			// 아이템 사용 후 인벤토리에서 아이템을 제거
			if (!inventory.removeItem(itemToUse, 1)) { // 아이템 제거에 실패한 경우
				updateCommandInfoArea("<b>아이템을 사용할 수 없습니다.</b><br>───────────────────────────");
				return;
			}
		} else if (command == 2) { // 버리기
			// 아이템을 인벤토리에서 제거
			if (!inventory.removeItem(itemToUse, 1)) { // 아이템 제거에 실패한 경우
				updateCommandInfoArea("<b>아이템을 버릴 수 없습니다.</b><br>───────────────────────────");
				return;
			}
			updateCommandInfoArea("<b>" + itemToUse.getName() + "</b>을(를) 버렸습니다.<br>───────────────────────────");

		} else { // 잘못된 입력
			updateCommandInfoArea("아이템 사용: 잘못된 입력입니다.<br>───────────────────────────");
			return;
		}

		if (battle != null) { // 전투상태에서 호출한 경우, 턴 체인지
			battle.changeTurn();
			monsterCounterAttack();
		} else {
			displayMainOptions();
		}
	}

	public void addItemToPlayerInventory(ConsumableItem item, int quantity) {
		player.getInventory().addItem(item, quantity);
		updateCommandInfoArea("<b>" + item.getName() + "</b>(을)를 획득했습니다.");
	}

	public boolean removeItemFromPlayerInventory(ConsumableItem item, int quantity) {
		return player.getInventory().removeConsumableItem(item, quantity);
	}
	
	void displayMainOptions() {
		setCommandState(0);
		updateCommandInfoArea(
				"<b>무엇을 하시겠습니까?</b><br><br>1. 이동<br>2. 탐색<br>3. 인벤토리 열기<br>4. 설정<br>0. 게임 종료<br>───────────────────────────");
		userCommandField.requestFocus();
	}

	void displayBattleOptions() {
		setCommandState(21);
		updateCommandInfoArea("<b>무엇을 하시겠습니까?</b><br><br>1. 일반 공격<br>" + player.listSkills()
				+ "9. 소비아이템 인벤토리 열기<br>0. 도망치기<br>───────────────────────────");
		userCommandField.requestFocus();
	}
	
	public Player getPlayer() {
		return this.player;
	}

	// 모든 위치를 반환하는 메소드
	public ArrayList<Location> getLocations() {
		return this.locations;
	}

	public Monster getMonster() {
		return this.monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Battle getBattle() {
		return this.battle;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public int getCommandState() {
		return this.commandState;
	}

	public void setCommandState(int commandState) {
		this.commandState = commandState;
	}

	public String getTextColor() {
		return this.textcolor;
	}

	public void setTextColor(String textcolor) {
		this.textcolor = textcolor;
	}

	public int getSelectedItemIndex() {
		return selectedItemIndex;
	}

	public void setSelectedItemIndex(int selectedItemIndex) {
		this.selectedItemIndex = selectedItemIndex;
	}
}