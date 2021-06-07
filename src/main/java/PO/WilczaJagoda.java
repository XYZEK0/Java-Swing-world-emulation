package PO;

import java.awt.*;
import java.util.Random;

public class WilczaJagoda extends Roslina
{
    public WilczaJagoda( Point pos, Swiat world)
    {
        super(99, 0, pos, null, "WilczaJagoda", world, Color.magenta);
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
        System.out.println(" " + org.getName() + " zjada Wilcza Jagoda i ginie");
        swiat.getOrgTab().remove(org);
        swiat.getZyweOrg().remove(org);
    }
}
