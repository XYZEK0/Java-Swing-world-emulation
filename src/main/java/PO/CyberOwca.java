package PO;

import java.awt.*;
import java.math.*;

public class CyberOwca extends Zwierze
{
    public CyberOwca( Point pos, Swiat world)
    {
        super(11, 4, pos, null, "CyberOwca", world, Color.pink);
    }

    @Override
    public void akcja()
    {
        this.setCanMove(true);
        Point destination = findClosestBarszcz();
        Point move = null;

        if(destination == null) super.akcja();
        else
        {
            if ((destination.getX() - this.getPos().getX()) > 0)
                move = new Point(this.getPos().getX() + 1, this.getPos().getY());

		    else if ((destination.getX() - this.getPos().getX()) < 0)
                move = new Point(this.getPos().getX() - 1, this.getPos().getY());
		    else
                {
                    if ((destination.getY() - this.getPos().getY()) > 0)
                    move = new Point(this.getPos().getX(), this.getPos().getY() + 1);
		    	else if ((destination.getY() - this.getPos().getY()) < 0)
                    move = new Point(this.getPos().getX(), this.getPos().getY() - 1);
                }
        }

        if(move!=null)
        {
            if (swiat.getOrganizmy()[move.getY()][move.getX()] != null)
            {
                swiat.getOrganizmy()[move.getY()][move.getX()].kolizja(this);
            }
        }
        if(move!=null && this.getCanMove())
        this.setPos(move);
    }

    public void kolizja(Organizm org)
    {
        if (org.getName().equals(this.getName())) super.kolizja(null);
        else
        {
            System.out.println("  Kolizja " + this.name + " z " + org.name);
            this.walka(org);
        }

    }

    private Point findClosestBarszcz()
    {
        Point destination = null;
        for(Organizm org:swiat.getZyweOrg())
        {
            if(org.getName().equals("Barszcz"))
            {
                if(destination == null || calculateVectorMagnitude(destination)>calculateVectorMagnitude(org.getPos()))
                destination = org.getPos();
            }
        }
        return destination;
    }

    private int calculateVectorMagnitude(Point vect)
    {
        return (int)Math.sqrt(Math.pow(vect.getX(),2) + Math.pow(vect.getY(),2));
    }

}
