package PO;

import java.awt.*;
import java.util.Vector;

public abstract class Organizm
{

    protected Color orgColor;
    protected int sila;
    protected int inicjatywa;
    protected int wiek;
    protected Point pos;
    protected Point oldPos;
    protected String name;
    protected boolean canMove;
    protected Swiat swiat;

    public Organizm(int sila,int inicjatywa, Point pos, Point oldPos, String name, Swiat world, Color color)
    {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.pos = pos;
        this.oldPos = oldPos;
        this.name = name;
        this.orgColor = color;
        this.wiek = 0;
        swiat = world;
    }

    public void akcja()
    {

    }
    public void kolizja(Organizm org)
    {

    }

    public void walka(Organizm org)
    {

        System.out.println("  Doszlo do walki pomiedzy " + this.getName() + " i " + org.getName());
        if (swiat.compareStrength(this,org) == 1)
        {
            System.out.println("  " + this.getName() + " zabija " + org.getName());
            swiat.getOrgTab().remove(org);
            swiat.getZyweOrg().remove(org);
        }
        else
        {
            System.out.println("  " + org.getName() + " zabija " + this.getName());
            swiat.getOrgTab().remove(this);
            swiat.getZyweOrg().remove(this);
        }
    }

    //<editor-fold desc="Getters and setters>
    public int getSila() {
        return sila;
    }
    public void setSila(int sila) {
        this.sila = sila;
    }
    public int getInicjatywa() {
        return inicjatywa;
    }
    public void setInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }
    public int getWiek() {
        return wiek;
    }
    public void setWiek(int wiek) {
        this.wiek = wiek;
    }
    public Point getPos() {
        return pos;
    }
    public void setPos(Point pos) {
        this.pos = pos;
    }
    public Point getOldPos() {
        return oldPos;
    }
    public void setOldPos(Point oldPos) {
        this.oldPos = oldPos;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getCanMove() {
        return canMove;
    }
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    public Swiat getSwiat() {
        return swiat;
    }
    public void setSwiat(Swiat world) {
        swiat = world;
    }
    public Color getOrgColor() {
        return orgColor;
    }
    public void setOrgColor(Color orgColor) {
        this.orgColor = orgColor;
    }


    //</editor-fold>
}
