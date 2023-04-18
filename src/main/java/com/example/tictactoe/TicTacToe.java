package com.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private Label playerXScoreLable, PlayerOScoreLable ; // two lables that used to show score at bottom side
    private  int playerXScore=0, PlayerOScore=0;
    Button button2D[][] =new Button[3][3];
    private boolean playerXtrun=true;

    // we take BorderPane because of it has 5 part top bottom right left and center
    // at top part we set out title
    // at bottom we display score
    private BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));



        //  Lable title
        Label titleLable = new Label("Tic Tac Toe");
        titleLable.setStyle("-fx-font-size: 24pt;   -fx-font-weight: bold; ");
        root.setTop(titleLable);
        BorderPane.setAlignment(titleLable, Pos.CENTER);


        // board where where we put X or O through buttons
        // GridPane Layout pane allows us to add the multiple nodes in multiple rows and columns.
        // It is seen as a flexible grid of rows and columns where nodes can be placed in any cell of the grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
       // gridPane.setStyle("-fx-background-color: yellow");


        //we take 2D array of buttons that will be set on gridPAne
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Button button = new Button();
                button.setPrefSize(120, 120);
                button.setStyle("-fx-font-size: 35pt;   -fx-font-weight: bold; ");
                button.setOnAction(event -> buttonClicked(button));
                button2D[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        root.setCenter(gridPane); // we set gridpane to the center
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        // score Board where we players can see how many matches they win
        HBox ScoreBoard = new HBox(20);
        ScoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLable = new Label("Player X: 0");
        playerXScoreLable.setStyle("-fx-font-size: 18pt; -fx-font-weight: 18pt ;");
        PlayerOScoreLable = new Label("Player O: 0");
        PlayerOScoreLable.setStyle("-fx-font-size: 18pt; -fx-font-weight: 18pt");
        ScoreBoard.getChildren().addAll(playerXScoreLable, PlayerOScoreLable);


        root.setBottom(ScoreBoard);

        return root;
    }



    // set action on pressing buttons either it is X or O
        private void buttonClicked(Button button){
           if(button.getText().equals("")) {

               if(playerXtrun){
                   button.setText("X");
               }
               else{
                   button.setText("O");
               }
               playerXtrun=!playerXtrun;
               checkWinner();
           }
            return ;
        }

    // checking winning condition
    // after a player won, game must be reset
        private void checkWinner(){
            for (int row = 0; row <3 ; row++) {
                // row wise winning condition
                if (button2D[row][0].getText().equals(button2D[row][1].getText()) &&
                        button2D[row][1].getText().equals(button2D[row][2].getText())
                        && !button2D[row][0].getText().isEmpty())
                {
                    // we have a winner

                    String winner = button2D[row][0].getText();
                    showWinnnerDialog(winner);
                    updateScore(winner);
                    ResetBoard();
                    return;
                }
            }

                //  coloum wise winning condition
                for (int col = 0; col <3 ; col++) {
                    if (button2D[0][col].getText().equals(button2D[1][col].getText()) &&
                            button2D[1][col].getText().equals(button2D[2][col].getText())
                            && !button2D[0][col].getText().isEmpty())
                        // we have a winner
                    {
                        String winner = button2D[0][col].getText();
                        showWinnnerDialog(winner);
                        updateScore(winner);
                        ResetBoard();
                        return;
                    }
                }

                // diagonal1 wise winning condition
                        if ( button2D[0][0].getText().equals( button2D[1][1].getText()) &&
                                button2D[1][1].getText().equals( button2D[2][2].getText() )
                                && !button2D[0][0].getText().isEmpty() ){
                            // we have a winner

                            String winner=button2D[0][0].getText();
                            showWinnnerDialog(winner);
                            updateScore(winner);
                            ResetBoard();
                            return;
                        }
                      // diagonal1 wise winning condition

                        if ( button2D[2][0].getText().equals( button2D[1][1].getText()) &&
                                button2D[1][1].getText().equals( button2D[0][2].getText() )
                                && !button2D[2][0].getText().isEmpty() ){
                            // we have a winner

                            String winner=button2D[2][0].getText();
                            showWinnnerDialog(winner);
                            updateScore(winner);
                            ResetBoard();
                            return;
                        }
// also checking tie condition when none player win
                    boolean tie =true;

                    for( Button row[]: button2D){
                        for(Button button: row ){
                            if(button.getText().isEmpty()){
                                tie=false;
                                break;
                            }
                        }
                    }
                    if(tie){
                        ResetBoard();
                        showTieDialog();
                    }
        }

    // if any player win then a dialog box appear that telling congratulation...to winning player
        private void showWinnnerDialog(String winner){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("Winner !");
            alert.setContentText("Congratulation "+ winner+ "...! you won the game");
                    alert.showAndWait();
        }
// if a game is tie then it will showing there is tie
    private void showTieDialog(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("");
        alert.setTitle("Tie");
        alert.setContentText("Game Over ...its a tie game");
        alert.showAndWait();
    }


    // score update  when players start winning
    private void updateScore(String winner){

        if(winner.equals("X")){
            playerXScore++;
            playerXScoreLable.setText("Player X : "+ playerXScore);
             }
         else{
            PlayerOScore++;
            PlayerOScoreLable.setText("Player O : "+ PlayerOScore);
             }
        }


        // after winning or tie  a game must be reset
        private void ResetBoard(){
        for( Button row[]: button2D){
            for(Button button: row ){
                button.setText("");
            }
        }

        }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}