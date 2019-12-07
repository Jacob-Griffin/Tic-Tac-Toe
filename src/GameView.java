import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    GameButton[][] board;

    public GameView(Controller game){
        this.setLayout(new GridLayout(3,3,25,25));
        board = new GameButton[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = new GameButton(i,j,game);
                board[i][j] = setButtonStyle(board[i][j]);
                this.add(board[i][j]);
            }
        }
        initializeLook();
    }

    private void initializeLook(){
        this.setBackground(new Color(79, 52, 73));
    }

    private GameButton setButtonStyle(GameButton b){
        b.setText(" ");
        b.setSize(100,100);
        b.setFont(new Font("Arial",Font.BOLD,25));
        return b;
    }
}