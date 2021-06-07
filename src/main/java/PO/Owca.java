package PO;

import java.awt.*;

public class Owca extends Zwierze
{

    public Owca( Point pos, Swiat world)
    {
        super(4, 4, pos,null, "Owca", world, Color.cyan);
    }

    @Override
    public void akcja()
    {
        super.akcja();
    }

    public void kolizja(Organizm org)
    {
        if(org.getName().equals(this.getName())) super.kolizja(null);
        else
        {
            System.out.println("  Kolizja " + this.name + " z " + org.name);
            this.walka(org);
        }
    }
}
