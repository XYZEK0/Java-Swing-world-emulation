package PO;

import java.awt.*;

public class Wilk extends Zwierze
{
    public Wilk( Point pos, Swiat world)
    {
        super(9, 5, pos,null, "Wilk", world, Color.darkGray);
    }

    @Override
    public void akcja()
    {
        super.akcja();
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
}
