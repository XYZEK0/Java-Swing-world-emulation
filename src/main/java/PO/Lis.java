package PO;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Lis extends Zwierze {
    public Lis(Point pos, Swiat world) {
        super(3, 7, pos, null, "Lis", world, new Color(255, 235, 5));
    }

    @Override
    public void akcja()
    {

        this.setCanMove(true);
        boolean shouldBreak;
        boolean hasMoved = false;
        Point destination = null;
        Vector<Integer> forbiddenFields = new Vector<>();
        Random rand = new Random();

        while (!hasMoved)
        {
            shouldBreak = false;
            int number = rand.nextInt(4);
            for (Integer integer : forbiddenFields)
            {
                if (number == integer)
                {
                    shouldBreak = true;
                    break;
                }
            }

            if (!shouldBreak)
            switch (number)
            {
                case 0:
                    if (this.pos.getY() - 1 >= 0)
                    {
                        if (swiat.getOrganizmy()[this.pos.getY() - 1][this.pos.getX() ] != null)
                        {
                            if (swiat.compareStrength(this, swiat.getOrganizmy()[this.pos.getY() - 1][this.pos.getX() ]) > 0 )
                            {
                                destination = new Point(this.getPos().getX() - 1, this.getPos().getY());
                                hasMoved = true;
                            } else
                            {
                                hasMoved = false;
                                forbiddenFields.add(0);
                            }
                        } else
                        {
                            destination = new Point(this.getPos().getX() , this.getPos().getY() - 1);
                            hasMoved = true;
                        }
                    }
                    break;
                case 1:
                    if (this.pos.getY() + 1 < swiat.getSizeY())
                    {
                        if (swiat.getOrganizmy()[this.pos.getY() + 1][this.pos.getX()] != null)
                        {
                            if (swiat.compareStrength(this, swiat.getOrganizmy()[this.pos.getY() + 1][this.pos.getX() ]) > 0)
                            {
                                destination = new Point(this.getPos().getX() , this.getPos().getY() + 1);
                                hasMoved = true;
                            } else {
                                hasMoved = false;
                                forbiddenFields.add(1);
                            }
                        }
                        else
                        {
                            destination = new Point(this.getPos().getX() , this.getPos().getY() + 1);
                            hasMoved = true;
                        }
                    }
                    break;
                case 2:
                    if (this.pos.getX() - 1 >= 0)
                    {
                        if (swiat.getOrganizmy()[this.pos.getY() ][this.pos.getX() - 1] != null)
                        {
                            if (swiat.compareStrength(this, swiat.getOrganizmy()[this.pos.getY() ][this.pos.getX() - 1]) > 0)
                            {
                                destination = new Point(this.getPos().getX() - 1, this.getPos().getY() );
                                hasMoved = true;
                            }
                            else
                            {
                                hasMoved = false;
                                forbiddenFields.add(2);
                            }
                        }
                        else
                        {
                            destination = new Point(this.getPos().getX() - 1, this.getPos().getY() );
                            hasMoved = true;
                        }
                    }
                    break;
                case 3:
                    if (this.pos.getX() + 1 < swiat.getSizeX())
                    {
                        if (swiat.getOrganizmy()[this.pos.getY() ][this.pos.getX() + 1] != null)
                        {
                            if (swiat.compareStrength(this, swiat.getOrganizmy()[this.pos.getY() ][this.pos.getX() + 1]) > 0 )
                            {
                                destination = new Point(this.getPos().getX() + 1, this.getPos().getY() );
                                hasMoved = true;
                            } else
                            {
                                hasMoved = false;
                                forbiddenFields.add(3);
                            }
                        }
                        else
                        {
                            destination = new Point(this.getPos().getX() + 1, this.getPos().getY() );
                            hasMoved = true;
                        }
                    }
                    break;
                default: break;
            }
        }

        if (swiat.getOrganizmy()[destination.getY()][destination.getX()] != null)
        {
            swiat.getOrganizmy()[destination.getY()][destination.getX()].kolizja(this);
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
            this.walka(org);
        }
    }
}
