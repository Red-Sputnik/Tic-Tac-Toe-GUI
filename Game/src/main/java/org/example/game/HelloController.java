package org.example.game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    private char nowSym = 'X';
    private char[][] gameBoard = new char[3][3];
    private boolean isGame = true;
    private Button btn;

    @FXML
    void btnClick(ActionEvent event) {
        btn = (Button)event.getSource();

        if(!isGame || !btn.getText().isEmpty()) return;

        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
        gameBoard[rowIndex][columnIndex] = nowSym;
        btn.setText(String.valueOf(nowSym));

        if (checkWin()) {
            finalWindow();
            isGame = false;
        } else if (isDraw()) {
            drawWindow();
            isGame = false;
        } else {
            nowSym = nowSym == 'X' ? '0' : 'X';
        }
    }

    private boolean checkWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][0] == nowSym && gameBoard[i][1] == nowSym && gameBoard[i][2] == nowSym) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[0][i] == nowSym && gameBoard[1][i] == nowSym && gameBoard[2][i] == nowSym) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (gameBoard[0][0] == nowSym && gameBoard[1][1] == nowSym && gameBoard[2][2] == nowSym) ||
                (gameBoard[0][2] == nowSym && gameBoard[1][1] == nowSym && gameBoard[2][0] == nowSym);
    }

    private boolean isDraw() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == '\u0000') return false;
            }
        }
        return true;
    }

    void drawWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ничья!", ButtonType.OK);
        alert.showAndWait();
    }

    void finalWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Победитель: <" + nowSym + ">", ButtonType.OK);
        alert.showAndWait();
    }
}
