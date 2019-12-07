import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButton extends JButton { //Also an observable
    //Each button holds its own position, observer (The Model), and character
    private int x;
    private int y;
    private char xo;
    private Controller observer;

    public GameButton(int x,int y, Controller observer){
        this.x = x;
        this.y = y;
        this.xo = ' ';
        setText(" ");
        this.observer = observer;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChar('x'); //I'm defaulting the player to x, and the player is the only one clicking on the buttons
            }
        });
    }

    public boolean setChar(char xo){
        if(this.xo == ' '){ //If xo is still default
            this.xo = xo;   //Set it to the new type
            setText(""+xo);
            notifyGame();   //and tell the game what happened
            return true;
            //This means that the game will only change and be notified if the button was still default
        }
        else{
            return false;
            //The computer randomly chooses spaces, and needs to know if it was invalid
        }
    }

    public char getChar(){
        return xo;
    }

    //No setter for position, position is constant and determined when making the board

    public int[] getPos(){
        return new int[]{x,y};
    }

    private void notifyGame(){
        observer.play(this);
    }
}
