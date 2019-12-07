public class Game {
    private char[][] picked;
    private GameView view;
    private Controller c;

    public Game(GameView view, Controller c){
        this.view = view;
        this.c = c;
        picked = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                picked[i][j] = ' ';
            }
        }
    }

    public void process(char player){
        char winner = checkForVictory();
        if(winner == 'x'){
            c.showEnd("Win!");
            return;
        }
        if(winner == 'o'){
            c.showEnd("Lose :(");
            return;
        }
        if(winner == 't'){
            c.showEnd("Tied...");
            return;
        }
        if(player == 'x'){//Process() Happens after every move, but we only want the computer to make a move after we do
            computerPlay();
        }
    }

    public void addChecked(int x, int y, char xo){
        picked[x][y] = xo;
    }

    private char checkForVictory(){
        //Check Straight Lines
        String[] possibleVictories = new String[8];
        int currentIdx = 0;
        for(int i  = 0; i < 3; i++){
            possibleVictories[currentIdx] = (picked[i][0]+"")+(picked[i][1]+"")+(picked[i][2]+"");
            currentIdx += 1;
            possibleVictories[currentIdx] = (picked[0][i]+"")+(picked[1][i]+"")+(picked[2][i]+"");
            currentIdx += 1;
        }
        //Check Diagonals
        possibleVictories[currentIdx] = (picked[0][0]+"")+(picked[1][1]+"")+(picked[2][2]+"");
        currentIdx += 1;
        possibleVictories[currentIdx] = (picked[0][2]+"")+(picked[1][1]+"")+(picked[2][0]+"");

        //See if any line is 3 of the same character
        for(String str: possibleVictories){
            if(str.charAt(0) == str.charAt(1) && str.charAt(1) == str.charAt(2) && str.charAt(0)!=' '){
                return str.charAt(0); //return the char of the winning piece
            }
        }

        //Check the board. If any spaces are still available, this returns as such
        for(int i = 0; i<3; i++){
            for(int j = 0; j< 3; j++){
                if(picked[i][j] == ' '){
                    return ' ';
                }
            }
        }

        //If none were victories and there are no more spaces left, return a tie
        return 't';
    }

    private void computerPlay(){
        int x;
        int y;
        do{
            //Choose a random board position
            x = (int)Math.floor(Math.random()*3);
            y = (int)Math.floor(Math.random()*3);
        }while(!view.board[x][y].setChar('o'));//Keep choosing random spots until it is not bad
    }
}