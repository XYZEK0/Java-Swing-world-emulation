package PO;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Barszcz extends Roslina
{
    public Barszcz(Point pos, Swiat world)
    {
        super(10,0,pos,null,"Barszcz",world, Color.black);
    }

    @Override
    public void akcja()
    {
        exterminate();
        Random rand = new Random();
        if(rand.nextInt(7) == 1)
            super.akcja();
    }
    public void kolizja(Organizm org)
    {
       // System.out.println("  Kolizja " + this.name + " z " + org.name);
        if(!org.getName().equals("CyberOwca"))
        {
            System.out.println(" " + org.getName() + " zjada Barszcz i ginie");
            swiat.getOrgTab().remove(org);
            swiat.getZyweOrg().remove(org);
        }
        else this.walka(org);

    }

    public void exterminate()
    {

        Organizm org = null;
        if(this.getPos().getY() + 1 < swiat.getSizeY())
        org = swiat.getOrganizmy()[this.getPos().getY() + 1][this.getPos().getX()];
        if(org!=null)
        {
            if(!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
                //swiat.getOrganizmy()[this.getPos().getY() + 1][this.getPos().getX()] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());

            }
            org = null;
        }
        if(this.getPos().getY() - 1 > 0)
        org = swiat.getOrganizmy()[this.getPos().getY() - 1][this.getPos().getX()];
        if (org != null)
        {
            if (!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
                //swiat.getOrganizmy()[this.getPos().getY() - 1][this.getPos().getX()] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());
            }
            org = null;
        }
        if(this.getPos().getX() - 1 > 0 && this.getPos().getY() + 1 < swiat.getSizeY())
        org = swiat.getOrganizmy()[this.getPos().getY() + 1][this.getPos().getX() - 1];
        if (org != null)
        {
            if (!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
                //swiat.getOrganizmy()[this.getPos().getY() + 1][this.getPos().getX() - 1] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());
            }
            org = null;
        }
        if(this.getPos().getY() + 1 < swiat.getSizeY() && this.getPos().getX() + 1 <swiat.getSizeX())
        org = swiat.getOrganizmy()[this.getPos().getY() + 1][this.getPos().getX() + 1];
        if (org != null)
        {
            if (!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
               // swiat.getOrganizmy()[this.getPos().getY() + 1][this.getPos().getX() + 1] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());
            }
            org = null;
        }
        if(this.getPos().getX() + 1 < swiat.getSizeX())
        org = swiat.getOrganizmy()[this.getPos().getY()][this.getPos().getX() + 1];
        if (org != null)
        {
            if (!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
                //swiat.getOrganizmy()[this.getPos().getY()][this.getPos().getX() + 1] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());
            }
            org = null;
        }
        if(this.getPos().getX() - 1 > 0)
        org = swiat.getOrganizmy()[this.getPos().getY()][this.getPos().getX() - 1];
        if (org != null)
        {
            if (!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
               //swiat.getOrganizmy()[this.getPos().getY()][this.getPos().getX() - 1] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());
            }
            org = null;
        }
        if(this.getPos().getY() - 1 > 0 && this.getPos().getX() + 1 < swiat.getSizeX())
        org = swiat.getOrganizmy()[this.getPos().getY() - 1][this.getPos().getX() + 1];
        if (org != null)
        {
            if (!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
                //swiat.getOrganizmy()[this.getPos().getY() - 1][this.getPos().getX() + 1] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());
            }
            org = null;
        }
        if(this.getPos().getY() - 1 > 0 && this.getPos().getX() - 1 > 0)
        org = swiat.getOrganizmy()[this.getPos().getY() - 1][this.getPos().getX() - 1];
        if (org != null)
        {
            if (!org.getName().equals("CyberOwca") && !org.getName().equals("Barszcz"))
            {
                //swiat.getOrganizmy()[this.getPos().getY() - 1][this.getPos().getX() - 1] = null;
                swiat.getOrgTab().remove(org);
                swiat.getZyweOrg().remove(org);
                System.out.println("  Barszcz eksterminuje " + org.getName());
            }
            org = null;
        }

    }
}
