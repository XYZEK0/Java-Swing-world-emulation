package PO;

import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.util.Vector;

public abstract class Zwierze extends Organizm
{

    public Zwierze(int sila, int inicjatywa, Point pos, Point oldPos, String name, Swiat world, Color color)
    {
        super(sila, inicjatywa, pos, oldPos, name, world,color);
    }


    public void akcja()
    {
        this.setCanMove(true);

        Random rand = new Random();
        boolean hasMoved = false;

        Point destination = null;
        while (!hasMoved)
        {

            switch (rand.nextInt(4))
            {
                case 0:
                    if (this.pos.getY() - 1 >= 0)
                {
                    destination = new Point(this.getPos().getX(), this.getPos().getY() - 1);
                    hasMoved = true;
                }
                break;
                case 1:
                    if (this.pos.getY() + 1 < swiat.getSizeY())
                {
                    destination = new Point(this.getPos().getX(), this.getPos().getY() + 1);
                    hasMoved = true;
                }
                break;
                case 2:
                    if (this.pos.getX() - 1 >= 0)
                {
                    destination = new Point(this.getPos().getX() - 1, this.getPos().getY());
                    hasMoved = true;
                }
                break;
                case 3:
                    if (this.pos.getX() + 1 < swiat.getSizeX())
                {
                    destination = new Point(this.getPos().getX() + 1, this.getPos().getY());
                    hasMoved = true;
                }
                break;
                default:  break;
            }
        }

        if (swiat.getOrganizmy()[destination.getY()][destination.getX()] != null)
        {
            swiat.getOrganizmy()[destination.getY()][destination.getX()].kolizja( this);
        }

        if (this.getCanMove())
        this.setPos(destination);
    }

    public void kolizja(Organizm org)
    {
       Point position = swiat.determineSpawnPos(this);

        if (position == null) return;

        Organizm zw = null;

        if (this instanceof Owca)  zw = new Owca(position,swiat);
        else if (this instanceof Antylopa) zw = new Antylopa(position, swiat);
        else if (this instanceof Lis) zw = new Lis(position, swiat);
        else if (this instanceof Zolw) zw = new Zolw(position, swiat);
        else if (this instanceof Wilk) zw = new Wilk(position, swiat);
        else if(this instanceof CyberOwca) zw = new CyberOwca(position,swiat);

        swiat.getZyweOrg().add(zw);

        System.out.println("  "+this.getName() + " rozmnaza sie");
    }


}
