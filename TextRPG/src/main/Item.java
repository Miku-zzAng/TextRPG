package main;

import java.util.*;

public class Item {
    public String name;
    private String type;
    private String description; // 아이템에 대한 설명을 위한 속성
    
    public Item(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
    
    @Override
    public boolean equals(Object o) {
    	if (this == o) return true;
    	if (o == null || getClass() != o.getClass()) return false;
    	Item item = (Item) o;
    	return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
    	return Objects.hash(name);
    }
    
    // 아이템 효과 적용 메소드
    public void applyEffect(Player player) {
        // 기본 Item 클래스에는 효과 없음. 
        // 상속받은 클래스에서 오버라이드하여 효과 정의
    }
}