package com.example.ai_project2;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class TicTacToeGame {
    private char[][] board = new char[3][3];
    private Button[][] buttons = new Button[3][3];
    private GridPane grid;
    private String currentPlayer;
    private int playerWins = 0;
    private int aiWins = 0;
    private Label playerScore;
    private Label aiScore;
    private Label statusLabel; // This is your status label
    private Button newGameButton;
    private Button startButton;
    private Label roundLabel;
    private int count=0;

//*******************************************************************************


//    button4 = new JButton(d);
//		button4.setPreferredSize(new Dimension(150,150));
//		button4.setFont(new Font("Arial",Font.PLAIN, 150));
//		button4.addActionListener(buttonListener);
//
//    button5 = new JButton(e);
//		button5.setPreferredSize(new Dimension(150,150));
//		button5.setFont(new Font("Arial", Font.PLAIN, 150));
//		button5.addActionListener(buttonListener);
//
//    button6 = new JButton(f);
//		button6.setPreferredSize(new Dimension(150,150));
//		button6.setFont(new Font("Arial", Font.PLAIN, 150));
//		button6.addActionListener(buttonListener);




//*************************************************************
    public TicTacToeGame(GridPane grid) {


            this.grid = grid;
            this.currentPlayer = "X"; // Assuming player starts with "X"
            this.playerScore = new Label("Player Wins: 0");
            this.aiScore = new Label("AI Wins: 0");
            // Initialize the board
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }

            newGameButton = new Button("New Game");
            newGameButton.setOnAction(e -> resetGame1());
            grid.add(newGameButton, 0, 3); // Adjust the position as needed in your layout

            statusLabel = new Label("Player's Turn");
            grid.add(statusLabel, 1, 3); // Change the position based on your layout

            grid.add(playerScore,0,4);
            grid.add(aiScore,0,5);

            startButton = new Button("Start Game");
            grid.add(startButton,2,3);

            roundLabel = new Label("Round 1");
            grid.add(roundLabel,0,6);

//            TiaLabel = new Label();
//            grid.add(TiaLabel,1,4);

            grid.setVgap(10);
            setupBoard();
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                board[i][j] = ' ';
            }
        }
        currentPlayer = "X"; // Or randomize the starting player
       // updateScore();
        roundLabel.setText("Round "+(count+2));

        updateGameStatus("Player's Turn");
        count++;
        if (count >= 5 && aiWins>=1) {
            // Show an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("The game is ended and the winner is the AI");
            alert.showAndWait();
            aiWins=0;
            count=0;
            aiScore.setText("Ai Wins: 0");
            roundLabel.setText("Round 1");
        }
        if (count>=5 && aiWins <1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("no winner");
            alert.showAndWait();
            aiWins=0;
            count=0;
            aiScore.setText("Ai Wins: 0");
            roundLabel.setText("Round 1");
        }
    }

    private void resetGame1(){
        resetBoard();
        aiWins=0;
        count=0;
        aiScore.setText("Ai Wins: 0");
    }

    public void setupBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                int finalI = i;
                int finalJ = j;
                button.setOnAction(e -> handlePlayerMove(finalI, finalJ));
                buttons[i][j] = button;
                grid.add(button, j, i);
            }
        }
        VBox scoreBoard = new VBox(10); // Vertical box with spacing
//        scoreBoard.getChildren().addAll(playerScore, aiScore);

        grid.add(scoreBoard, 3, 0, 1, 3); // Adjust position as needed
        grid.setAlignment(Pos.CENTER);
    }

    private void handlePlayerMove(int row, int col) {
        if (board[row][col] != ' ') {
            // Cell is already occupied; ignore the click
            return;
        }

        // Update the board state and GUI to reflect the player's move
        board[row][col] = 'X';  // Assuming the human player is 'X'
        buttons[row][col].setText("X");

        // Check for win or tie after the player's move
        if (checkWin()) {
            playerWins++;
            updateScore();
            updateGameStatus("Player wins!");
            resetBoard();
        } else if (checkTie()) {
            updateGameStatus("It's a tie!");
            resetBoard();
        } else {
            // It's AI's turn
            switchPlayer();
            handleAIMove();
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                board[i][j] = ' ';
            }
        }
        currentPlayer = "X"; // Resetting to player X or alternate if needed
        updateGameStatus("Player's Turn");
        resetGame();
    }

    //****************************************************************************

//    public static int minimax(int depth, boolean isMax) {
//        int score = evaluate();
//
//        if (score == 10) {
//            return score - depth;
//        }
//
//        if (score == -10) {
//            return score + depth;
//        }
//
//        if (!isMovesLeft()) {
//            return 0;
//        }
//
//        if (isMax) {
//            int best = Integer.MIN_VALUE;
//
//            for (int row = 0; row < BOARD_SIZE; row++) {
//                for (int col = 0; col < BOARD_SIZE; col++) {
//                    if (board[row][col] == EMPTY_SPACE) {
//                        board[row][col] = PLAYER_O;
//                        best = Math.max(best, minimax(depth + 1, !isMax));
//                        board[row][col] = EMPTY_SPACE;
//                    }
//                }
//            }
//
//            return best;
//        } else {
//            int best = Integer.MAX_VALUE;
//
//            for (int row = 0; row < BOARD_SIZE; row++) {
//                for (int col = 0; col < BOARD_SIZE; col++) {
//                    if (board[row][col] == EMPTY_SPACE) {
//                        board[row][col] = PLAYER_X;
//                        best = Math.min(best, minimax(depth + 1, !isMax));
//                        board[row][col] = EMPTY_SPACE;
//                    }
//                }
//            }
//
//            return best;
//        }
//    }

    //************************************************


    private void updateScore() {
        // Update the score display
        // Assume you have labels or text fields for playerScore and aiScore
        playerScore.setText("Player Wins: " + playerWins);
        aiScore.setText("AI Wins: " + aiWins);
        roundLabel.setText("Round "+(count+2));
        if (aiWins>=3){
            // Show an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("The game is ended and the winner is the AI");
            alert.showAndWait();
            aiWins=0;
            count=0;
            aiScore.setText("Ai Wins: 0");
            roundLabel.setText("Round 1");
        }
    }

    private void switchPlayer() {
        currentPlayer = String.valueOf((this.currentPlayer == "X") ? 'O' : 'X');
        updateGameStatus(this.currentPlayer == "X" ? "Player's Turn" : "AI's Turn");
    }



    private void updateGameStatus(String status) {
        statusLabel.setText(status);
    }



    private void handleAIMove() {
        int[] bestMove = minimax(board, true); // Assuming 'true' indicates the AI is the maximizing player
        int bestRow = bestMove[1];
        int bestCol = bestMove[2];

        // Perform the move on the board
        if (bestRow != -1 && bestCol != -1) {
            board[bestRow][bestCol] = 'O'; // Assuming 'O' is the AI's symbol
            buttons[bestRow][bestCol].setText("O");

            if (checkWin()) {
                aiWins++;
                updateScore();
                resetGame();
            } else if (checkTie()) {
                resetGame();
            } else {
                switchPlayer();
            }
        }
    }



    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (!buttons[i][0].getText().isEmpty() &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }

            // Check columns
            if (!buttons[0][i].getText().isEmpty() &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }

        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }


    private boolean checkTie() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                if (b.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;

    }


    private int evaluateBoard(char[][] board) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'O') {
                    return +10;
                } else if (board[row][0] == 'X') {
                    return -10;
                }
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 'O') {
                    return +10;
                } else if (board[0][col] == 'X') {
                    return -10;
                }
            }
        }

        // Checking for Diagonals for X or O victory.
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'O') {
                return +10;
            } else if (board[0][0] == 'X') {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'O') {
                return +10;
            } else if (board[0][2] == 'X') {
                return -10;
            }
        }

        // Else if none of them have won then return 0
        return 0;
    }


    private boolean isBoardFull(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


    private int[] minimax(char[][] board, boolean isMaximizing) {
        // Base case: check for terminal state (win, lose, draw)
        int score = evaluateBoard(board);
        if (score != 0) {
            return new int[]{score, -1, -1};
        }
        if (isBoardFull(board)) {
            return new int[]{0, -1, -1};
        }

        int bestRow = -1;
        int bestCol = -1;
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = isMaximizing ? 'O' : 'X'; // Place 'O' or 'X' based on the player
                     score = minimax(board, !isMaximizing)[0]; // Recursion
                    board[row][col] = ' '; // Undo the move

                    if (isMaximizing && score > bestScore || !isMaximizing && score < bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        return new int[]{bestScore, bestRow, bestCol};
    }


}


