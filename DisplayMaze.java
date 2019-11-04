package CS222;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class main extends Application {
	
	public boolean colorSwitch;
	private float x;
	private float y;
	private int w;
	private int h;

	@Override
	public void start(Stage arg0) throws Exception {
		GridPane gp = new GridPane();
		Maze m = new Maze();
		m.digTunnel(1, 1);
		
		char[][] c = new char[25][25];
		c = m.getMaze();
		
		Rectangle[][] rArray = new Rectangle[25][25];
		
		
		x = 0;
		y = 0;
		w = 10;
		h = 10;
		for (int i = 0; i < 25; i++, y += 10) {
			for (int j = 0; j < 25; j++, x += 10) {
				
				rArray[i][j] = new Rectangle(x, y, w, h);
				if (c[i][j] == 'X') {
					rArray[i][j].setFill(Color.BLACK);
					
				} else {
					rArray[i][j].setFill(Color.AQUA);
				}
				
				GridPane.setRowIndex(rArray[i][j], j);
				GridPane.setColumnIndex(rArray[i][j], i);
				gp.getChildren().addAll(rArray[i][j]);
			}
		}
		
		Scene scene = new Scene(gp, 500, 500);
		
		arg0.setTitle("Maze");
		arg0.setScene(scene);
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}