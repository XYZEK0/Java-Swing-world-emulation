package PO;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

public class Czlowiek extends Zwierze
{
    public enum Directions
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Point moveDestination;
    private Directions playerMoveDir;
    private int moveDistance;
    private boolean isPowerOn;
    private boolean isPowerOnCooldown;
    private boolean isAlive;
    private int cooldownTurns;
    private int powerTurns;


    public Czlowiek( Point pos, Swiat world)
    {
        super(5, 5, pos,null, "Czlowiek", world, Color.blue);
        this.moveDistance = 1;
        this.isPowerOn = false;
        this.isPowerOnCooldown = false;
        this.cooldownTurns = 0;
        this.powerTurns = 0;
        this.playerMoveDir = null;
        this.isAlive = true;
    }

    @Override
    public void akcja()
    {
        if(!isAlive) return;

        System.out.println("  Sila gracza: " + this.sila);
        //<editor-fold desc="<-Power handling->">
        Random rand = new Random();
        if (powerTurns > 2) moveDistance = 2;
        else if (powerTurns != 0 && (rand.nextInt(4)) > 1)
        {
            moveDistance = 2;
        }
        else moveDistance = 1;


        String status = null;
        if(this.isPowerOn)
            status = "ON";
        else status = "OFF";
        System.out.println("  Power status: " + status);
        if (this.cooldownTurns > 0)
        {
            this.cooldownTurns--;
        }
	else
        {
            isPowerOnCooldown = false;
            if (this.powerTurns > 0)
            {
                this.powerTurns--;
                if (this.powerTurns == 0)
                {
                    this.cooldownTurns = 5;
                    this.isPowerOn = false;
                    this.isPowerOnCooldown = true;
                }
            }
        }
        //</editor-fold>


        if(playerMoveDir == Directions.UP)
            moveDestination = new Point(this.pos.getX(), this.pos.getY() - moveDistance);
        else if(playerMoveDir == Directions.DOWN)
            moveDestination = new Point(this.pos.getX(), this.pos.getY() + moveDistance);
        else if(playerMoveDir == Directions.LEFT)
            moveDestination = new Point(this.pos.getX() - moveDistance, this.pos.getY() );
        else if(playerMoveDir == Directions.RIGHT)
            moveDestination = new Point(this.pos.getX() + moveDistance, this.pos.getY() );

        this.setCanMove(true);


        if (moveDestination != null)
        {
            if (swiat.getOrganizmy()[moveDestination.getY()][moveDestination.getX()] != null)
            {
                swiat.getOrganizmy()[moveDestination.getY()][moveDestination.getX()].kolizja( this);
            }
            if (this.getCanMove())
            this.setPos(moveDestination);
        }

         moveDestination = null;
    }

    public void kolizja(Organizm org)
    {
        System.out.println("  Kolizja " + this.name + " z " + org.name);
        this.walka(org);
    }

    //<editor-fold desc="Getters and setters">

    public Point getMoveDestination() {
        return moveDestination;
    }

    public void setMoveDestination(Point moveDestination) {
        this.moveDestination = moveDestination;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getMoveDistance() {
        return moveDistance;
    }

    public void setMoveDistance(int moveDistance) {
        this.moveDistance = moveDistance;
    }

    public boolean isPowerOn() {
        return isPowerOn;
    }

    public void setPowerOn(boolean powerOn) {
        isPowerOn = powerOn;
    }

    public boolean isPowerOnCooldown() {
        return isPowerOnCooldown;
    }

    public void setPowerOnCooldown(boolean powerOnCooldown) {
        isPowerOnCooldown = powerOnCooldown;
    }

    public int getCooldownTurns() {
        return cooldownTurns;
    }

    public void setCooldownTurns(int cooldownTurns) {
        this.cooldownTurns = cooldownTurns;
    }

    public int getPowerTurns() {
        return powerTurns;
    }

    public void setPowerTurns(int powerTurns) {
        this.powerTurns = powerTurns;
    }

    public Directions getPlayerMoveDir() {
        return playerMoveDir;
    }

    public void setPlayerMoveDir(Directions playerMoveDir) {
        this.playerMoveDir = playerMoveDir;
    }
    //</editor-fold>
}
