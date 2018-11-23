

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
    private Game game;
    private Map<Ship, Slave> slaves;
    public Master(Game g){
        int mw = g.gameMap.width;
        int mh = g.gameMap.height;
        this.game = g;
        this.ph = new int[mw][mh];
        slaves = new Hashtable();
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

        int ships;
        for (final Ship ship : game.me.ships.values()) {
            if(slaves.get(ship) == null){
                slaves.put(ship, new Slave(ship));
            }
            Slave s = slaves.get(ship);
            s.runAI(commands);
            ships++;
        }
        if(ships == 0){
            game.me.spawn();
        }

        
        
        return commands;

    }
}

class Slave{
    Ship s;
    public Slave(Ship s){
        this.s = s;
    }
    public void runAI(LinkedList<Command> Commandq){

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


