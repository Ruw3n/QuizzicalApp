package com.example.quizzicalapp.gui;

import com.example.quizzicalapp.application.PlayerFactory;
import com.example.quizzicalapp.entity.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogController implements Initializable {

    @FXML
    private  TextField newTeamText;

    @FXML
    private Button aw;

    @FXML
    private Button correct;

    @FXML
    private Button laughing;

    @FXML
    private Button thinking;

    @FXML
    private Button newTeam;

    @FXML
    private Button deleteTeam;

    @FXML
    private Button addPoints;

    @FXML
    private Button refresh;

    @FXML
    private Button applause;

    @FXML
    private Spinner<Integer> points;

    private final Media[] sound = new Media[5];
    private final MediaPlayer[] mediaPlayer = new MediaPlayer[5];

    private boolean isSound = false;




    @FXML
    private ListView<String> teamList;

    private PlayerFactory facade  = PlayerFactory.singleton();


    @FXML
    public void showTeams(){
        ObservableList<String> teams = FXCollections.observableArrayList();

        for (Player p : facade.getAllPlayers()
                ) {
            teams.add(p.getName());


        }
        teamList.setItems(teams);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-3,3,0);
        points.setValueFactory(valueFactory);
        points.setEditable(true);
        showTeams();
        newTeamText.setEditable(true);
        newTeamText.setPromptText("Enter Team name...");


        sound[0] = new Media(new File("/Users/ruwenlamm/Desktop/quizshow/QuizzicalEXE/QuizzicalApp/src/main/resources/applause.mp3").toURI().toString());
        sound[1] = new Media(new File("/Users/ruwenlamm/Desktop/quizshow/QuizzicalEXE/QuizzicalApp/src/main/resources/aw.mp3").toURI().toString());
        sound[2] = new Media(new File("/Users/ruwenlamm/Desktop/quizshow/QuizzicalEXE/QuizzicalApp/src/main/resources/correct.mp3").toURI().toString());
        sound[3] = new Media(new File("/Users/ruwenlamm/Desktop/quizshow/QuizzicalEXE/QuizzicalApp/src/main/resources/laughing.mp3").toURI().toString());
        sound[4] = new Media(new File("/Users/ruwenlamm/Desktop/quizshow/QuizzicalEXE/QuizzicalApp/src/main/resources/thinking.mp3").toURI().toString());
        for (int i=0;i<sound.length;i++){
            mediaPlayer[i] = new MediaPlayer(sound[i]);


        }




    }

    public void addPointsToTeam(ActionEvent actionEvent) {
        String selectedTeam = teamList.getFocusModel().getFocusedItem();

        if(selectedTeam!=null){
            facade.addPointsToPlayer(selectedTeam, points.getValue());

        }
    }

    public void deleteSelected(ActionEvent actionEvent) {
        String selectedTeam = teamList.getFocusModel().getFocusedItem();
        System.out.println(selectedTeam);
        if(selectedTeam!=null){
            facade.deletePlayer(selectedTeam);
            showTeams();
        }
    }

    public void createNewTeam(ActionEvent actionEvent) {
        String teamName = newTeamText.getText();
        newTeamText.setStyle("-fx-text-inner-color: black;");
        if(teamName!=null){
            if(!facade.newPlayer(teamName)){
                newTeamText.setText("TEAM ALREADY EXISTS");
                newTeamText.setStyle("-fx-text-inner-color: red;");
            } else {
                newTeamText.setText(null);
                showTeams();
            }
        }


    }

    public void playSound() {
        System.out.println("Test"+applause.isPressed());
        applause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isSound){
                    mediaPlayer[0].stop();
                    mediaPlayer[0].play();
                    isSound = true;
                } else {
                    mediaPlayer[0].stop();
                    isSound = false;

                }

            }
        });
        aw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isSound){
                    mediaPlayer[1].stop();
                    mediaPlayer[1].play();
                    isSound = true;
                } else {
                    mediaPlayer[1].stop();
                    isSound = false;

                }

            }
        });
        correct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isSound){
                    mediaPlayer[2].stop();
                    mediaPlayer[2].play();
                    isSound = true;
                } else {
                    mediaPlayer[2].stop();
                    isSound = false;

                }

            }
        });
        laughing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isSound){
                    mediaPlayer[3].stop();
                    mediaPlayer[3].play();
                    isSound = true;
                } else {
                    mediaPlayer[3].stop();
                    isSound = false;

                }

            }
        });
        thinking.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isSound){
                    mediaPlayer[4].play();
                    isSound = true;
                } else {
                    mediaPlayer[4].stop();
                    isSound = false;

                }

            }
        });
    }
}
