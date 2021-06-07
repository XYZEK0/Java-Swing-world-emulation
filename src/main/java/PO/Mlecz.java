package PO;

import java.awt.*;
import java.util.Random;

public class Mlecz extends Roslina
{
    public Mlecz( Point pos, Swiat world)
    {
        super(0, 0, pos, null, "Mlecz", world, Color.lightGray);
    }

    @Override
    public void akcja()
    {
        Random rand = new Random();
        if(rand.nextInt(9) == 1)
            super.akcja();

        if(rand.nextInt(9) == 1)
            super.akcja();

        if(rand.nextInt(9) == 1)
            super.akcja();
    }

    public void kolizja(Organizm org)
    {
        System.out.println("  Kolizja " + this.name + " z " + org.name);
        this.walka(org);
    }
}
