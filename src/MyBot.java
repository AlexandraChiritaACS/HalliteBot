

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

class Master{
    private int[][]ph;
    int mw,mh;
    private Game game;
    private Map<Ship, Slave> slaves;
    public Master(Game g){
        this.mw = g.gameMap.width;
        this.mh = g.gameMap.height;
        this.game = g;
        this.ph = new int[mw][mh];
        this.slaves = new Hashtable();
    }

    public LinkedList<Command> update(Game game) {
        LinkedList<Command> cq = new LinkedList<>();
        for (int i = 0; i < ph.length; i++) {
            for (int j = 0; j < ph[0].length; j++) {
                if (ph[i][j] > 0) {
                    ph[i][j]--;
                }
            }
        }

        int ships = 0;
        for (final Ship ship : game.me.ships.values()) {
            if(slaves.get(ship) == null){
                slaves.put(ship, new Slave(ship));
            }
            Slave s = slaves.get(ship);
            s.runAI(cq);
            ships++;
        }
        if(ships == 0){
            spawn(cq);
        }
        return cq;
    }
    public void spawn(Collection<Command>cq){
        cq.add(game.me.shipyard.spawn());   
    }
}
class Slave{
    Ship s;
    public Slave(Ship s){
        this.s = s;
    }
    public void runAI(LinkedList<Command> cq){
        moveN(cq);
    }
    public void moveN(LinkedList<Command> cq){
        cq.push(s.move(Direction.NORTH)); 
    }
    public void moveS(LinkedList<Command> cq){
        cq.push(s.move(Direction.SOUTH));
    }
    public void moveE(LinkedList<Command> cq){
        cq.push(s.move(Direction.EAST));
    }
    public void moveW(LinkedList<Command> cq){
        cq.push(s.move(Direction.WEST));
    }
}

class Pair{
    int a,b;
    public Pair(int a, int b){
        this.a = a;
        this.b = b; 
    }
}


