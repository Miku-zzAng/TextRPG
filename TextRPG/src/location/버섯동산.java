package location;

import main.*;
import monster.*;

public class 버섯동산 extends Location {
	public 버섯동산(Game game) {
		super("버섯동산", game,"버섯동산.jpg", "wav/mapwav/헤사브금.wav");
		this.addMonster(new 주황버섯(game), 0.8);
		this.addMonster(new 스포아(game), 0.2);
	}
}