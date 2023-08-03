package main;

import location.*;

public class MapManager {
    private Game game;

    public MapManager(Game game) {
        this.game = game;
    }

    public void createMaps() {
    	버섯동산 버섯동산 = new 버섯동산(game);
    	헤네시스 헤네시스 = new 헤네시스(game);
        헤네시스사냥터1 헤네시스사냥터1 = new 헤네시스사냥터1(game);
        헤네시스사냥터2 헤네시스사냥터2 = new 헤네시스사냥터2(game);
    	헤네시스동쪽풀숲 헤네시스동쪽풀숲 = new 헤네시스동쪽풀숲(game);
    	파란버섯의숲 파란버섯의숲 = new 파란버섯의숲(game);
    	리스항구 리스항구 = new 리스항구(game);
    	리스항구외곽 리스항구외곽 = new 리스항구외곽(game);
    	해안가풀숲 해안가풀숲 = new 해안가풀숲(game);
    	세갈래길 세갈래길 = new 세갈래길(game);
    	남쪽숲나무던전 남쪽숲나무던전 = new 남쪽숲나무던전(game);
    	지혜의숲 지혜의숲 = new 지혜의숲(game);
    	엘리니아 엘리니아 = new 엘리니아(game);
    	솟아오른나무 솟아오른나무 = new 솟아오른나무(game);
    	북쪽숲나무던전1 북쪽숲나무던전1 = new 북쪽숲나무던전1(game);
    	북쪽숲나무던전2 북쪽숲나무던전2 = new 북쪽숲나무던전2(game);
    	
        game.addLocation(리스항구);
        game.addLocation(리스항구외곽);
        game.addLocation(해안가풀숲);
        game.addLocation(세갈래길);
    	game.addLocation(헤네시스);
        game.addLocation(헤네시스사냥터1);
        game.addLocation(헤네시스동쪽풀숲);
        game.addLocation(헤네시스사냥터2);
        game.addLocation(파란버섯의숲);
        game.addLocation(남쪽숲나무던전);
        game.addLocation(지혜의숲);
        game.addLocation(엘리니아);
        game.addLocation(솟아오른나무);
        game.addLocation(북쪽숲나무던전1);
        game.addLocation(북쪽숲나무던전2);
        game.addLocation(버섯동산);
        
        //리스항구
        리스항구.addConnectedLocation(리스항구외곽);
        리스항구.addConnectedLocation(헤네시스);
        리스항구.addConnectedLocation(엘리니아);
        
        리스항구외곽.addConnectedLocation(리스항구);
        리스항구외곽.addConnectedLocation(해안가풀숲);
        
        해안가풀숲.addConnectedLocation(리스항구외곽);
        해안가풀숲.addConnectedLocation(세갈래길);

        세갈래길.addConnectedLocation(해안가풀숲);
        세갈래길.addConnectedLocation(버섯동산);
        
        버섯동산.addConnectedLocation(세갈래길);
        버섯동산.addConnectedLocation(헤네시스사냥터1);
        
        헤네시스사냥터1.addConnectedLocation(버섯동산);
        헤네시스사냥터1.addConnectedLocation(헤네시스사냥터2);
        헤네시스사냥터1.addConnectedLocation(헤네시스);
        
        헤네시스사냥터2.addConnectedLocation(헤네시스사냥터1);
        
        //헤네시스
        헤네시스.addConnectedLocation(헤네시스사냥터1);
        헤네시스.addConnectedLocation(헤네시스동쪽풀숲);
        헤네시스.addConnectedLocation(리스항구);
        헤네시스.addConnectedLocation(엘리니아);
        
        헤네시스동쪽풀숲.addConnectedLocation(헤네시스);
        헤네시스동쪽풀숲.addConnectedLocation(파란버섯의숲);
        헤네시스동쪽풀숲.addConnectedLocation(남쪽숲나무던전);
        
        파란버섯의숲.addConnectedLocation(헤네시스동쪽풀숲);
        
        남쪽숲나무던전.addConnectedLocation(헤네시스동쪽풀숲);
        남쪽숲나무던전.addConnectedLocation(지혜의숲);
        
        지혜의숲.addConnectedLocation(남쪽숲나무던전);
        지혜의숲.addConnectedLocation(엘리니아);
        
        //엘리니아
        엘리니아.addConnectedLocation(지혜의숲);
        엘리니아.addConnectedLocation(솟아오른나무);
        엘리니아.addConnectedLocation(리스항구);
        엘리니아.addConnectedLocation(헤네시스);
        
        솟아오른나무.addConnectedLocation(엘리니아);
        솟아오른나무.addConnectedLocation(북쪽숲나무던전1);
        
        북쪽숲나무던전1.addConnectedLocation(솟아오른나무);
        북쪽숲나무던전1.addConnectedLocation(북쪽숲나무던전2);
        
        북쪽숲나무던전2.addConnectedLocation(북쪽숲나무던전1);
        
    }
}