package PO;

import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze
{
    public Zolw( Point pos, Swiat world)
    {
        super(2, 1, pos,null, "Zolw", world, new Color(152,102,0));
    }

    @Override
    public void akcja()
    {
        this.setCanMove(true);
        Random rand = new Random();

        if(rand.nextInt(4) == 3)
        {
            super.akcja();
        }
    }

    public void kolizja(Organizm org)
    {
        if (org.getName().equals(this.getName())) super.kolizja(null);
        else
        {
            System.out.println("  Kolizja " + this.name + " z " + org.name);
            if (org.getSila() < 5) {
                System.out.println("  Zolw odpiera atak " + org.getName());
                org.setCanMove(false);
            } else this.walka(org);
        }
    }
}
