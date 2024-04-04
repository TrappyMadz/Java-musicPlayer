import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 *
 *@author Charrier Simon
 *@version 1.0 
 */
public class LecteurAudio extends Application{
	private boolean isPlaying = false;

	public void start(Stage primaryStage) {
		// On créer les différents boutons
		Button play = new Button("Play");
		play.setPrefWidth(100);
		Button debut = new Button("<<");
		
		// Le label volume :
		Label volume = new Label("Volume");
		
		// On créer le slider pour modifier le volume
		Slider volumeS = new Slider(0,100,50);
		
		// On utilisera un HBox pour afficher les choses alignées à l'horizontal et on ajoute les éléments
		HBox panneau = new HBox(4);
		panneau.getChildren().add(play);
		panneau.getChildren().add(debut);
		panneau.getChildren().add(volume);
		panneau.getChildren().add(volumeS);
		panneau.setAlignment(Pos.CENTER);
		
		// On peut choisir notre musqiue
		FileChooser fichier = new FileChooser();
		File f = fichier.showOpenDialog(null);
		
		// On cherche le media
		Media Based = new Media(f.toURI().toString());
		MediaPlayer joue = new MediaPlayer(Based);
		
		// On ajoute les fonctions aux différents boutons
		play.setOnAction(e -> {
			if (isPlaying) {
				joue.pause();
				play.setText("Play");
				isPlaying = false;
			}
			else {
				joue.play();
				play.setText("Pause");
				isPlaying = true;
			}
		});
		
		debut.setOnAction(e -> {	
		joue.seek(Duration.ZERO);	
		});
		
		joue.volumeProperty().bind(volumeS.valueProperty().divide(100));
		
		// On créer la scène
		Scene scene = new Scene(panneau);
		
		// Affichage de la scène
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
