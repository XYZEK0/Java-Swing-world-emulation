package PO;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Antylopa extends Zwierze
{
    public Antylopa( Point pos, Swiat world)
    {
        super(4, 4, pos, null, "Antylopa", world, Color.orange);
    }

  @Override
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
                   if (this.pos.getY() - 2 >= 0)
                   {
                       destination = new Point(this.getPos().getX(), this.getPos().getY() - 2);
                       hasMoved = true;
                   }
                   break;
               case 1:
                   if (this.pos.getY() + 2 < swiat.getSizeY())
                   {
                       destination = new Point(this.getPos().getX(), this.getPos().getY() + 2);
                       hasMoved = true;
                   }
                   break;
               case 2:
                   if (this.pos.getX() - 2 >= 0)
                   {
                       destination = new Point(this.getPos().getX() - 2, this.getPos().getY());
                       hasMoved = true;
                   }
                   break;
               case 3:
                   if (this.pos.getX() + 2 < swiat.getSizeX())
                   {
                       destination = new Point(this.getPos().getX() + 2, this.getPos().getY());
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
       if (org.getName().equals(this.getName())) super.kolizja(null);
       else
       {
           System.out.println("  Kolizja " + this.name + " z " + org.name);

           Random rand = new Random();
           int number = rand.nextInt(1000);
           if (number > 500)
           {
               org.setCanMove(false);
               System.out.println("  Antylopie udalo sie uciec przed:  " + org.getName());
               this.setCanMove(false);
               if (this.pos.getY() - 2 >= 0 && swiat.getOrganizmy()[this.pos.getY() - 2][this.pos.getX()] == null)
               {
                   this.setPos(new Point(this.getPos().getX(), this.getPos().getY() - 2));
               }
               else if (this.pos.getY() + 2 < swiat.getSizeY() && swiat.getOrganizmy()[this.pos.getY() + 2][this.pos.getX()] == null)
               {
                   this.setPos(new Point(this.getPos().getX(), this.getPos().getY() + 2));
               }
               else if (this.pos.getX() - 2 >= 0 && swiat.getOrganizmy()[this.pos.getY()][this.pos.getX() - 2] == null)
               {
                   this.setPos(new Point(this.getPos().getX() - 2, this.getPos().getY()));
               }
               else if (this.pos.getX() + 2 < swiat.getSizeX() && swiat.getOrganizmy()[this.pos.getY()][this.pos.getX() + 2] == null)
               {
                   this.setPos(new Point(this.getPos().getX() + 2, this.getPos().getY()));
               }
               else
               {
                   number = rand.nextInt(4);
                   switch (number) {
                       case 0:
                           if (this.pos.getY() - 2 >= 0) {
                               this.setPos(new Point(this.getPos().getX(), this.getPos().getY() - 2));
                           }
                           break;
                       case 1:
                           if (this.pos.getY() + 2 < swiat.getSizeY()) {
                               this.setPos(new Point(this.getPos().getX(), this.getPos().getY() + 2));
                           }
                           break;
                       case 2:
                           if (this.pos.getX() - 2 >= 0) {
                               this.setPos(new Point(this.getPos().getX() - 2, this.getPos().getY()));
                           }
                           break;
                       case 3:
                           if (this.pos.getX() + 2 < swiat.getSizeX()) {
                               this.setPos(new Point(this.getPos().getX() + 2, this.getPos().getY()));
                           }
                           break;
                   }
               }
           }
           else this.walka(org);
       }
   }
}
