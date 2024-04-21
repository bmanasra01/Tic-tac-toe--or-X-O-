package com.example.ai_project2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {
    private Button[][] buttons = new Button[3][3]; // 3x3 grid for Tic Tac Toe

    @Override
    public void start(Stage primaryStage) {

        VBox vBox = new VBox(10);
        GridPane grid = new GridPane();
        TicTacToeGame game = new TicTacToeGame(grid);
        game.setupBoard();

        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

