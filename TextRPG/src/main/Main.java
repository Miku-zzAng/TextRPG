package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*; // 캐럿

public class Main {
	

	public static void main(String[] args) {
		installGUI();
	}

	private static void installGUI() {
		JFrame frame = new JFrame("2008 MapleStory in Text RPG");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850, 670);
		frame.setLayout(null);

		Game gamer = new Game();

		Font font = new Font("AppleGothic", Font.BOLD, 14);

		JPanel playerInfoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) { // Swing의 paintComponent 재정의
				g.setColor(getBackground()); // 객체의 색상을 컴포넌트의 배경색으로 설정
				g.fillRect(0, 0, getWidth(), getHeight()); // 설정된 색상으로 컴포넌트를 채우는 사각형 그림 (좌,상,우,하)
				super.paintComponent(g); // 재정의 이전 메소드 호출하여 기본 컴포넌트 그리기 작업 수행
			}
		};
		JLabel playerJobImgLabel = new JLabel();
		playerJobImgLabel.setBounds(475, 40, 150, 100);
		JLabel playerInfoLabel = new JLabel();
		playerInfoLabel.setFont(font);
		playerInfoLabel.setOpaque(false);
		playerInfoPanel.setOpaque(false); // 배경에 투명도 적용될수있게 설정
		playerInfoPanel.setBackground(new Color(255, 255, 255, 180));
		playerInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 설정
		playerInfoPanel.setBounds(500, 151, 130, 110);
		playerInfoPanel.add(playerInfoLabel);

		JPanel playerBarPanel = new JPanel();
		playerBarPanel.setOpaque(false);
		playerBarPanel.setBounds(640, 153, 250, 110);
		playerBarPanel.setLayout(null);
		JProgressBar hpBar = new JProgressBar(0, gamer.getPlayer().getMaxHp());
		hpBar.setStringPainted(true);
		hpBar.setBackground(new Color(170, 170, 170));
		hpBar.setForeground(new Color(255, 0, 0));
		hpBar.setBounds(0, 0, 180, 25);
		JProgressBar mpBar = new JProgressBar(0, gamer.getPlayer().getMaxMp());
		mpBar.setStringPainted(true);
		mpBar.setBackground(new Color(170, 170, 170));
		mpBar.setForeground(new Color(0, 0, 255));
		mpBar.setBounds(0, 40, 180, 25);
		JProgressBar expBar = new JProgressBar(0, gamer.getPlayer().getMaxExp());
		expBar.setStringPainted(true);
		expBar.setBackground(new Color(170, 170, 170));
		expBar.setForeground(new Color(0, 255, 0));
		expBar.setBounds(0, 80, 180, 25);
		playerBarPanel.add(hpBar);
		playerBarPanel.add(mpBar);
		playerBarPanel.add(expBar);

		JPanel locationInfoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		JLabel locationInfoLabel = new JLabel();
		locationInfoLabel.setFont(font);
		locationInfoLabel.setOpaque(false);
		locationInfoPanel.setOpaque(false);
		locationInfoPanel.setBackground(new Color(255, 255, 255));
		locationInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		locationInfoPanel.setBounds(10, 15, 220, 30);
		locationInfoPanel.add(locationInfoLabel);

		JPanel monsterInfoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		JLabel monsterImageLabel = new JLabel();
		monsterImageLabel.setBounds(540, 280, 200, 100);
		JLabel monsterInfoLabel = new JLabel();
		monsterInfoLabel.setOpaque(false);
		monsterInfoLabel.setForeground(new Color(251, 115, 15));
		monsterInfoLabel.setFont(font);
		monsterInfoLabel.setHorizontalAlignment(SwingConstants.LEFT); // 컴포넌트의 내부 컨텐츠를 왼쪽으로 정렬
		monsterInfoPanel.setOpaque(false);
		monsterInfoPanel.setBackground(new Color(255, 255, 255, 180));
		monsterInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		monsterInfoPanel.setBounds(500, 385, 135, 90);
		monsterInfoPanel.add(monsterInfoLabel);
		JProgressBar monsterhpBar = new JProgressBar(0, 1);
		monsterhpBar.setStringPainted(true);
		monsterhpBar.setBackground(new Color(170, 170, 170));
		monsterhpBar.setForeground(new Color(255, 0, 0));
		monsterhpBar.setBounds(646, 387, 172, 25);
		
		// JTextPane을 사용한 이유: html을 지원함.
		class CustomTextPane extends JTextPane { // (지역 클래스) 텍스트페인 상속받아서 별도로 커스텀화시킴.
			public CustomTextPane() { // 생성자
				setOpaque(false); // 배경 투명하게 설정
				setContentType("text/html"); // html 요소를 삽입할수 있게 함
			}

			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(255, 255, 255, 180));
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		}
		CustomTextPane commandInfoArea = new CustomTextPane();
		commandInfoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		commandInfoArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(commandInfoArea); // 커스텀 텍스트페인 창을 스크롤화함.
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false); // JScrollPane의 세가지 요소중 하나인 사용자가 스크롤 영역내의 볼수있는 영역, viewport를 투명하게 함
		scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0)); // 뷰포트 배경 투명하게 설정
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // 수직 스크롤 바가 항상 보이게 설정
		scrollPane.setBounds(10, 50, 472, 550);

		// Caret: 사용자가 텍스트를 입력하거나 텍스트 필드 내에서 이동하는 현재 위치를 표시하는 데 사용되는 컴포넌트
		DefaultCaret caret = (DefaultCaret) commandInfoArea.getCaret(); // 새로운 텍스트가 추가될때마다 오토스크롤화 함(Caret 객체를 가져와서
																		// DefaultCaret로 강제변환)
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); // Caret의 업데이트 정책을 항상으로 바꿈

		JTextField userCommandField = new JTextField();
		userCommandField.setBounds(10, 603, 380, 20);

		JButton enterButton = new JButton("입력");
		enterButton.setBounds(393, 603, 90, 20);

		ImageIcon backgroundImage = gamer.createBackgroundImage(gamer.getPlayer().getCurrentLocation().getImageUrl());

		ImageIcon logo = new ImageIcon("logo.png");
	    JLabel logolabel = new JLabel("1");
	    logolabel.setIcon(logo);
		logolabel.setBounds(515,380,500,300);
		
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(backgroundImage);
		backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		backgroundLabel.setOpaque(true);
		backgroundLabel.setDoubleBuffered(true); // 배경이미지 이중버퍼링 활성화(컴포넌트 부드럽게 다시 그릴수있게 하기 위함)
		backgroundLabel.add(locationInfoPanel); // 배경이미지 구현을 위해 백그라운드 라벨을 만들고 라벨 속에 하위 컴포넌트를 삽입)
		backgroundLabel.add(userCommandField);
		backgroundLabel.add(scrollPane);
		backgroundLabel.add(enterButton);
		backgroundLabel.add(playerJobImgLabel);
		backgroundLabel.add(playerInfoPanel);
		backgroundLabel.add(playerBarPanel);
		backgroundLabel.add(monsterInfoPanel);
		backgroundLabel.add(monsterImageLabel);
		backgroundLabel.add(monsterhpBar);
		backgroundLabel.add(logolabel);
		
		frame.getContentPane().add(backgroundLabel);
		frame.setVisible(true);

		// 메인 클래스에서 게임의 시작 메소드를 호출. 게임 진행 상태에 따라 라벨의 상태를 바꾸기 위해 시작 메소드의 매개값으로 컴포넌트를 보냄
		gamer.start(playerInfoLabel, locationInfoLabel, commandInfoArea, monsterInfoLabel, userCommandField,
				monsterImageLabel, playerJobImgLabel, enterButton, backgroundLabel, hpBar, mpBar, expBar, monsterhpBar);

		// 액션 리스너를 지역 클래스로 구현. 액션 리스너의 모든 이벤트는 핸들유저커맨드 정적 메소드로 관리한다.
		class MyActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				handleUserCommand(gamer, playerInfoLabel, locationInfoLabel, userCommandField, monsterInfoLabel);
			}
		}

		// 액션리스너를 입력버튼에도 추가(입력 버튼을 누를시, actionPerformed 메소드 호출)
		enterButton.addActionListener(new MyActionListener());

		// 사용자 입력창에 키리스너 추가(엔터키를 누를시, keyPressed 메소드 호출)
		userCommandField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleUserCommand(gamer, playerInfoLabel, locationInfoLabel, userCommandField, monsterInfoLabel);
				}
			}
		});
	}

	// 버튼클릭 액션리스너, 엔터키 키리스너 이벤트 발생시 핸들유저커맨드 메소드가 실행됨
	// 게임의 모든 상태를 commandInfoArea 및 각종 라벨에 최신화 하면서 관리함
	private static void handleUserCommand(Game game, JLabel playerInfoLabel, JLabel locationInfoLabel,
			JTextField userCommandField, JLabel monsterInfoLabel) {

		String userCommand = userCommandField.getText(); // 닉네임 변경 구현을 위해 유저입력창에 입력된 텍스트를 get
		int command = 0; // 초기 커맨드는 0

		// 게임 커맨드 상태가 10일때, 즉 닉네임 변경 상태일때를 제외하고는 모두 입력받은 모든값을 정수로 변환하여 command로 넘김
		// 해당 if문에서 command 변수값이 바뀜으로써 -> Game클래스에서 바꾸는 CommandState의 상태값 + command 입력값에
		// 따른
		// switch 문으로 여러가지 게임 상태 변경 수행
		if (game.getCommandState() != 10) {
			try {
				command = Integer.parseInt(userCommand); // getText()한 userCommand를 정수로 변환하여 command로 넘김
			} catch (NumberFormatException e) { // 예외 처리(닉네임 변경 상태가 아닐때 정수가 아닌 다른값을 입력받을떄)
				game.updateCommandInfoArea("잘못된 입력입니다. 올바른 입력값을 입력해주세요.<br>───────────────────────────");
				return;
			}
		}

		if (game.getBattle() != null) {
			handleBattleCommand(game, command);
		} else {
			handleNonBattleCommand(game, userCommand, command);
		}
		userCommandField.setText("");
		userCommandField.requestFocus();
	}

	private static void handleBattleCommand(Game game, int command) {
		switch (game.getCommandState()) {
		case 21:
			if (command == 0) {
				game.battleRun();
			} else if (command == 1) {
				game.battleAttack();
			} else if (command > 1 && command < (2 + game.getPlayer().getJob().getSkills().size()) && command < 9) {
				game.battleUseSkill(command - 2);
			} else if (command == 9) {
				game.setCommandState(92);
				game.showConsumableInventory();
			} else {
				game.updateCommandInfoArea("전투 상태: 잘못된 입력입니다.<br>───────────────────────────");
			}
			break;
		case 92: // 인벤토리 관리 - 소비아이템 관리를 선택할 시
			if (command == 0) {
				game.displayBattleOptions();
			} else if (command > 0) {
				game.selectItem(command);
			}
			break;

		case 921: // 인벤토리 관리 - 소비아이템 관리를 선택 - 원하는 아이템 관리를 선택할 시9
			if (command == 0) {
				game.displayBattleOptions();
			} else {
				game.useItem(command);
			}
			break;

		default:
			game.updateCommandInfoArea("잘못된 입력입니다. 올바른 입력값을 입력해주세요.<br>───────────────────────────");
			break;

		} // 스위치문
		game.updatePlayerInfo();
		game.userCommandField.requestFocus();
	} // 메소드

	// 전투상태가 아닐때 명령 처리
	private static void handleNonBattleCommand(Game game, String userCommand, int command) {
		switch (game.getCommandState()) {
		case 0:
			switch (command) {
			case 1:
				game.moveList();
				game.setCommandState(1);
				break;
			case 2:
				game.setCommandState(2);
				game.explore();
				break;
			case 3:
				game.updateCommandInfoArea(
						"<b>어떤 인벤토리를 열어보시겠습니까?</b><br><br>1. 장비 아이템<br>2. 소비 아이템<br>3. 기타 아이템<br>0. 돌아가기<br>───────────────────────────");
				game.setCommandState(3);
				break;
			case 4:
				game.updateCommandInfoArea(
						"<b>어떤 설정을 하시겠습니까?</b><br><br>1. 닉네임 변경<br>0. 돌아가기<br>───────────────────────────");
				game.setCommandState(4);
				break;
			case 0:
				game.stopBGM();
				System.exit(0);
				break;
			}
			break;

		case 1: // 이동을 선택할 시
			if (command == 0) {
				game.displayMainOptions();
			} else {
				game.moveTo(command - 1);
				break;
			}

		case 3: // 인벤토리 관리를 선택할 시
			switch (command) {
			case 2:
				game.setCommandState(32);
				game.showConsumableInventory();
				break;
			case 1:
			case 3:
				game.updateCommandInfoArea("미구현 상태입니다.<br>───────────────────────────");
				break;
			case 0:
				game.displayMainOptions();
				break;
			}
			break;

		case 4: // 게임 설정을 선택할 시
		case 10:
			game.setting(command, userCommand);
			break;

		case 32: // 인벤토리 관리 - 소비아이템 관리를 선택할 시
			if (command == 0) {
				game.displayMainOptions();
			} else if (command > 0) {
				game.selectItem(command);
			}
			break;

		case 321: // 인벤토리 관리 - 소비아이템 관리를 선택 - 원하는 아이템 관리를 선택할 시9
			if (command == 0) {
				game.displayMainOptions();
			} else {
				game.useItem(command);
			}
			break;

		default:
			game.updateCommandInfoArea("잘못된 입력입니다. 올바른 입력값을 입력해주세요.<br>───────────────────────────");
			break;

		} // 스위치문
		game.updatePlayerInfo();
		game.userCommandField.requestFocus();
	} // 메소드
}
