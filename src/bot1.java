

import hlt.*;
import java.util.*;

public class MyBot {
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

//manages ships and large scale operations
class Master{
    private int[][]ph;
    
    //maps ships to slaves
    private Map<Ship, Slave> slaves;
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


        for (final Ship ship : game.me.ships.values()) {
            if(slaves.get(ship) == null){
                slaves.put(ship, new Slave(ship));
            }
            Slave s = slaves.get(ship);
            s.update();
        }
        
        
        return commands;

    }
}

//wrapper for ship stores a directive location and an ai
class Slave{
    Ship s;
    
    public Slave(Ship s){
        this.s = s;
    }
    

}

//regular pair class like really whytf would i not be able to get this

class Pair{
    private int a,b;
    public Pair(int a, int b){
        this.a = a;
        this.b = b;
    }


}
