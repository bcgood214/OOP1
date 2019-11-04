package CS222;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Entry extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		ScrollPane sp = new ScrollPane();
		VBox root = new VBox();
		
		Label l1 = new Label("Pick your favorite color");
		l1.setFont(new Font(24));
		l1.setTextFill(Color.LIMEGREEN);
		
		ToggleGroup tg = new ToggleGroup();
		
		RadioButton rb1 = new RadioButton("red");
		rb1.setToggleGroup(tg);
		rb1.setSelected(true);
		
		RadioButton rb2 = new RadioButton("orange");
		rb2.setToggleGroup(tg);
		
		RadioButton rb3 = new RadioButton("blue");
		rb3.setToggleGroup(tg);
		
		RadioButton rb4 = new RadioButton("green");
		rb4.setToggleGroup(tg);
		
		root.getChildren().addAll(l1, rb1, rb2, rb3, rb4);
		root.getStylesheets().add("style.css");
		
		Spinner<Integer> ageSpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 18);
		
		ageSpinner.setValueFactory(valueFactory);
		
		Label l2 = new Label("Enter your age");
		l2.setFont(new Font(24));
		l2.setTextFill(Color.LIMEGREEN);
		
		root.getChildren().add(l2);
		root.getChildren().add(ageSpinner);
		
		Label l3 = new Label("Choose your favorite programming language.");
		l3.setFont(new Font(24));
		l3.setTextFill(Color.LIMEGREEN);
		
		String[] languages = { "C++", "Python", "C#", "Java" };
		
		ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(languages));
		
		
		root.getChildren().add(l3);
		root.getChildren().add(cb);
		root.setSpacing(20);
		
		Button submitBtn = new Button();
		submitBtn.setId("sb");
		submitBtn.setText("Submit");
		submitBtn.setFont(new Font(24));
		submitBtn.setTextFill(Color.WHITE);
		
		Label l4 = new Label();
		l4.setFont(new Font(24));
		l4.setTextFill(Color.LIMEGREEN);
		
		submitBtn.setOnAction(b -> {
			String s = "Your color is ";
			RadioButton sr = (RadioButton) tg.getSelectedToggle();
			s += sr.getText() + ", ";
			s += "and you are " + valueFactory.getValue() + " years old.";
			s += " \nLastly, your favorite programming language is "  + cb.getSelectionModel().getSelectedItem() + ".";
			l4.setText(s);
		});
		
		root.getChildren().add(submitBtn);
		root.getChildren().add(l4);
		sp.setContent(root);
		Scene scene = new Scene(sp, 800, 600);
		
		arg0.setTitle("Survey GUI");
		arg0.setScene(scene);
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
