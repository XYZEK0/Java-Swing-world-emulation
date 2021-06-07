package PO;
import java.awt.*;

public abstract class Roslina extends Organizm
{

    public Roslina(int sila, int inicjatywa, Point pos, Point oldPos, String name, Swiat world,Color color) {
        super(sila, inicjatywa, pos, oldPos, name, world,color);
    }

    public void akcja()
    {
        Point position = swiat.determineSpawnPos(this);

        if (position == null) return;

        Organizm zw = null;
        if (this instanceof Trawa)  zw = new Trawa(position,swiat);
        else if (this instanceof Mlecz) zw = new Mlecz(position, swiat);
        else if (this instanceof Guarana) zw = new Guarana(position, swiat);
        else if (this instanceof WilczaJagoda) zw = new WilczaJagoda(position, swiat);
        else if (this instanceof Barszcz) zw = new Barszcz(position, swiat);

        swiat.getZyweOrg().add(zw);

        System.out.println("  "+this.getName() + " rozmnaza sie");
    }


    public void kolizja(Organizm org)
    {

    }
}
