package PO;

import java.awt.*;
import java.util.Random;

public class Guarana extends Roslina
{
    public Guarana( Point pos, Swiat world)
    {
        super(0, 0, pos, null, "Guarana", world, Color.red);
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
        //System.out.println("  Kolizja " + this.name + " z " + org.name);
        org.setSila(org.getSila() + 3);
        System.out.println("  " + org.getName() + " zjada Guarane (sila + 3)");
        swiat.getOrgTab().remove(this);
        swiat.getZyweOrg().remove(this);
    }
}
