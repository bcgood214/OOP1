package gerrymandering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		BorderPane base = new BorderPane();
		VBox panel = new VBox();
		ScrollPane scroller = new ScrollPane();
		FileChooser fc = new FileChooser();
		VBox vb = new VBox();
		
		Button fileBtn = new Button();
		fileBtn.setText("Choose File");
		
		panel.getChildren().add(fileBtn);
		
		fileBtn.setOnAction( e -> {
			File selectedFile = fc.showOpenDialog(arg0);
			try {
				BufferedReader bReader = new BufferedReader(new FileReader(selectedFile));
				String s1;
				Label l1 = new Label();
				l1.setText("Alaska Gerrymander Results");
				vb.getChildren().add(l1);
				int winningTotal = 0;
				int districtTotal;
				int rTotal = 0;
				int dTotal = 0;
				int wd = 0;
				int wr = 0;
				int d = 0;
				int r = 0;
				while((s1 = bReader.readLine()) != null) {
					String[] data = s1.split(",");
					ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( 
							   new PieChart.Data("Democratic Votes", Integer.parseInt(data[1])), 
							   new PieChart.Data("Republican Votes", Integer.parseInt(data[2])));
					PieChart pieChart = new PieChart(pieChartData);
					pieChart.setTitle(data[0]);
					pieChart.setClockwise(true);
					pieChart.setLabelsVisible(false);
					pieChart.setStartAngle(180);
					vb.getChildren().add(pieChart);
					d = Integer.parseInt(data[1]);
					r = Integer.parseInt(data[2]);
					dTotal += d;
					rTotal += r;
					districtTotal = d + r;
					if (d > r) {
						winningTotal = d - r;
						wd += winningTotal / 2 + 1;
						wr += r;
					}
					if (r > d) {
						winningTotal = r - d;
						wr += winningTotal / 2 + 1;
						wd += d;
					}
				}
				double dp = (double)wd/dTotal;
				double rp = (double)wr/rTotal;
				int dPer = (int)(dp * 100);
				int rPer = (int)(rp * 100);
				String overview = "Wasted democratic votes: " + Integer.toString(wd) + ", " + Double.toString(dp) +
						"\n" +
				"Wasted republican votes: " + Integer.toString(wr) +  ", " + Double.toString(rp) +
				"\n";
				if (dPer >= rPer + 7) {
					overview += "The state was gerrymandered in favor of the Republican party.";
				} else if  (rPer >= dPer + 7) {
					overview += "The sate was gerrymandered in favor of the Democratic party.";
				} else {
					overview += "No gerrymandering was found.";
				}
				Label l2 = new Label();
				l2.setText(overview);
				panel.getChildren().add(l2);
				bReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		scroller.setContent(vb);
		base.setLeft(panel);
		base.setCenter(scroller);
		Scene scene = new Scene(base, 800, 600);
		
		
		arg0.setTitle("Gerrymandering");
		arg0.setScene(scene);
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
