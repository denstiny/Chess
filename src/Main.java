import com.jxrj.aero.Frame.GameChess;

public class Main {
    public static void main(String[] args) {
        GameChess gameChess = new GameChess(true);
        GameChess gameChess1 = new GameChess(false);

        if(!gameChess.isread) {
            gameChess.ismove = true;
        }else {
            gameChess1.ismove = true;
        }

        while (true) {
            if(!gameChess.getIsmove()) {
                gameChess1.setIsmove(true);
                //System.out.println("红棋移动");
                gameChess1.send(gameChess);
            }
            if(!gameChess1.getIsmove()){
                gameChess.setIsmove(true);
                //System.out.println("黑棋移动");
                gameChess.send(gameChess1);

            }
        }
    }
}
