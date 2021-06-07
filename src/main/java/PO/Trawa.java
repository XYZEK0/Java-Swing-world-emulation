package PO;

import java.awt.*;
import java.util.Random;

public class Trawa extends Roslina
{
    public Trawa( Point pos, Swiat world)
    {
        super(0, 0, pos, null, "Trawa", world, Color.green);
    }

    @Override
    public void akcja()
    {
        Random rand = new Random();
        if(rand.nextInt(5) == 1)
            super.akcja();

    }

    public void kolizja(Organizm org)
    {
        System.out.println("  Kolizja " + this.name + " z " + org.name);
        this.walka(org);
    }
}
