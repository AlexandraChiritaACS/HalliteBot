

import hlt.*;
import java.util.*;

public class bot1 {
    static Game game;
    static boolean shouldClose = false;
    static Master master;
    public static void main(String[]args){
        game = new Game();
        game.ready("yea boi");
        master = new Master(game);
        while(!shouldClose) {
            game.updateFrame();
            game.endTurn(master.update(game));
        }

    }
}

class Master{
    private int[][]ph;

    public Master(Game g){
        int mw = g.gameMap.width;
        int mh = g.gameMap.height;

        ph = new int[mw][mh];
    }

    public LinkedList<Command> update(Game game) {
        LinkedList<Command> commands = new LinkedList<>();
        for (int i = 0; i < ph.length; i++) {
            for (int j = 0; j < ph[0].length; j++) {
                if (ph[i][j] > 0) {
                    ph[i][j]--;
                }
            }
        }


        LinkedList<Ship> myShips = new LinkedList<Ship>();
        for (final Ship ship : game.me.ships.values()) {
            myShips.push(ship);
        }
        return commands;

    }

}




