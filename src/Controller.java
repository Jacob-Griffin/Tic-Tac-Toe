import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class Controller {
    private Game backend;
    public Controller(){
        JFrame frame = new JFrame("Tic Tac Toe Game");
        GameView view = new GameView(this);
        backend = new Game(view, this);

        frame.add(view);
        frame.setSize(500,550);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void play(GameButton b) {
        backend.addChecked(b.getPos()[0],b.getPos()[1],b.getChar());
        backend.process(b.getChar());
    }

    public void showEnd(String result){
        JFrame endFrame = new JFrame("You "+result);
        endFrame.add(new JLabel("You "+result));
        addMandatoryClose(endFrame);
        endFrame.setLayout(new BoxLayout(endFrame.getContentPane(),BoxLayout.Y_AXIS));
        endFrame.pack();
        endFrame.setVisible(true);
    }

    public void addMandatoryClose(JFrame f){
        JButton closeButton = new JButton("Close Game");
        f.add(closeButton);
        f.repaint();


        //When the victory dialog is closed, Either through the button or the window, the whole program closes
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        f.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if(e.getNewState() == WindowEvent.WINDOW_CLOSED){
                    System.exit(0);
                }
            }
        });
    }
}