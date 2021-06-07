package PO;


import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class Swiat
{
    private Czlowiek playerReference;
    private final static int sizeX = 20;
    private final static int sizeY = 20;
    private boolean isPlayerAlive;
    private int turnCounter;
    private GUI gui;
    private Organizm[][]organizmy;
    private Vector<Organizm> legendOrgs;
    private Vector<Organizm>orgTab;
    private Vector<Organizm>zyweOrg;


    public Swiat(GUI guiReference)
    {


        gui = guiReference;
        turnCounter = 1;
        initializeOrganizmy();

    }


    public void initializeOrganizmy()
    {
        organizmy = new Organizm[sizeX][sizeY];
        for(int i=0; i<sizeX; i++)
        {
            for(int j=0; j<sizeY; j++)
            {
                organizmy[i][j] = null;
            }
        }

        //<editor-fold desc="Organisms initialization">
        Organizm tempOrg;


        tempOrg = new Owca(new Point(5,6),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Antylopa(new Point(5,14),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Wilk(new Point(10,5),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Zolw(new Point(7,15),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Antylopa(new Point(5,14),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new CyberOwca(new Point(5,2),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new CyberOwca(new Point(5,3),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Trawa(new Point(18,8),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Guarana(new Point(13,5),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

         tempOrg = new Mlecz(new Point(11,17),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Mlecz(new Point(18,2),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new WilczaJagoda(new Point(6,16),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Barszcz(new Point(7,7),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Barszcz(new Point(10,10),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Barszcz(new Point(17,17),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        tempOrg = new Lis(new Point(12,6),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;


        tempOrg = new Trawa(new Point(10,8),this);
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;


        tempOrg = new Czlowiek(new Point(5,5),this);
        gui.setPlayer((Czlowiek)tempOrg);
        this.playerReference = (Czlowiek)tempOrg;
        organizmy[tempOrg.getPos().getY()][tempOrg.getPos().getX()] = tempOrg;

        legendOrgs = new Vector<>();

        tempOrg = new Antylopa(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Barszcz(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new CyberOwca(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Czlowiek(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Guarana(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Lis(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Mlecz(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Owca(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Trawa(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new WilczaJagoda(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Wilk(null, null);
        legendOrgs.add(tempOrg);
        tempOrg = new Zolw(null, null);
        legendOrgs.add(tempOrg);

        //</editor-fold>

    }

    public void updateOrganism()
    {
        for(int i=0; i<sizeY; i++)
        {
            for(int j=0; j<sizeX; j++)
            {
                organizmy[i][j] = null;
            }
        }
        for(Organizm org: zyweOrg)
        {
            organizmy[org.getPos().getY()][org.getPos().getX()] = org;
        }
    }

    public void makeTurn()
    {
        if(playerReference.isAlive())
        checkIfPlayerisAlive();

        System.out.println("  ------------ TURN " + " #"+this.turnCounter + " -------------");
        orgTab = makeOrgTab(organizmy);
        sortOrg(orgTab, 0, orgTab.size() - 1);
        zyweOrg = new Vector<>(orgTab);

        System.out.println("  Number of organisms: " + orgTab.size());

        while (!this.orgTab.isEmpty())
        {
            Organizm temp = this.getOrgTab().firstElement();
           // System.out.println("  Akcja " + orgTab.firstElement().getName());
            this.orgTab.firstElement().akcja();

            if (!this.orgTab.isEmpty())
            {
                if (this.orgTab.firstElement() == temp)
                {
                    this.orgTab.remove(this.orgTab.firstElement());
                }
            }
            updateOrganism();
           //tu jest miejsce na smierc gracza
        }

        updateAge();
        gui.updateBoard();

    }



    public Vector<Organizm> makeOrgTab(Organizm[][] organizmy)
    {
        Vector<Organizm> orgTab = new Vector<>();
        for (int i = 0; i < sizeY; i++)
        {
            for (int j = 0; j < sizeX; j++)
            {
                if (this.getOrganizmy()[i][j] != null)
                {
                    orgTab.add(organizmy[i][j]);
                }
            }
        }

        return orgTab;
    }

    public void sortOrg(Vector<Organizm> orgTab, int first, int last)
    {
        if (first < last)
        {
            int parIndex = partition(orgTab, first, last);

            sortOrg(orgTab, first, parIndex - 1);
            sortOrg(orgTab, parIndex + 1, last);
        }
    }

    public int partition(Vector<Organizm> orgTab, int first, int last)
    {
        Organizm pivot = orgTab.get(last);

        int i = first - 1;

        for (int j = first; j <= last - 1; j++)
        {
            if (orgTab.get(j).getInicjatywa() > pivot.getInicjatywa())
            {
                i++;
                swap(orgTab.get(i),i, orgTab.get(j),j);
            }
            else if (orgTab.get(j).getInicjatywa() == pivot.getInicjatywa())
            {
                if (orgTab.get(j).getWiek() > pivot.getWiek())
                {
                    i++;
                    swap(orgTab.get(i),i, orgTab.get(j),j);
                }
            }
        }
        swap(orgTab.get(i+1),i+1, orgTab.get(last),last);

        return (i + 1);
    }

    public void swap(Organizm org1, int index1, Organizm org2, int index2)
    {
        orgTab.set(index2,org1);
        orgTab.set(index1,org2);
    }

    public int compareStrength(Organizm org1, Organizm org2)
    {
        if(org1.getSila() > org2.getSila()) return 1;
        else if (org1.getSila() == org2.getSila()) return 0;
        else return -1;
    }

    public void updateAge()
    {
        for(Organizm org: zyweOrg)
        {
            org.setWiek(org.getWiek() + 1);
        }
    }

    public Point determineSpawnPos(Organizm org)
    {
        Point position = null;
        boolean wasPosSet = false;

        if (org.getPos().getY() + 1 < sizeY)
        {
            if(getOrganizmy()[org.getPos().getY() + 1][org.getPos().getX()] == null) {
                position = new Point(org.getPos().getX(), org.getPos().getY() + 1);
                wasPosSet = true;
            }
        }
        if (org.getPos().getY() - 1 >= 0 && !wasPosSet)
        {
            if(getOrganizmy()[org.getPos().getY() - 1][org.getPos().getX()]  == null) {
                position = new Point(org.getPos().getX(), org.getPos().getY() - 1);
                wasPosSet = true;
            }
        }
        if (org.getPos().getY() + 1 < sizeY && org.getPos().getX() - 1 >= 0 && !wasPosSet)
        {
            if(getOrganizmy()[org.getPos().getY() + 1][org.getPos().getX() - 1] == null) {
                position = new Point(org.getPos().getX() - 1, org.getPos().getY() + 1);
                wasPosSet = true;
            }
        }
        if (org.getPos().getY() + 1 < sizeY && org.getPos().getX() + 1 < sizeX && !wasPosSet)
        {
            if(getOrganizmy()[org.getPos().getY() + 1][org.getPos().getX() + 1] == null) {
                position = new Point(org.getPos().getX() + 1, org.getPos().getY() + 1);
                wasPosSet = true;
            }
        }
        if (org.getPos().getX() + 1 < sizeX && !wasPosSet)
        {
            if(getOrganizmy()[org.getPos().getY()][org.getPos().getX() + 1] == null ) {
                position = new Point(org.getPos().getX() + 1, org.getPos().getY());
                wasPosSet = true;
            }
        }
        if (org.getPos().getX() - 1 >= 0 && !wasPosSet)
        {
            if(getOrganizmy()[org.getPos().getY()][org.getPos().getX() - 1] == null) {
                position = new Point(org.getPos().getX() - 1, org.getPos().getY());
                wasPosSet = true;
            }
        }
        if (org.getPos().getY() - 1 >= 0 && org.getPos().getX() + 1 < sizeX && !wasPosSet)
        {
            if(getOrganizmy()[org.getPos().getY() - 1][org.getPos().getX() + 1] == null) {
                position = new Point(org.getPos().getX() + 1, org.getPos().getY() - 1);
                wasPosSet = true;
            }
        }
        if (org.getPos().getY() - 1 >= 0 && org.getPos().getX() - 1 >= 0 && !wasPosSet)
        {
            if(getOrganizmy()[org.getPos().getY() - 1][org.getPos().getX() - 1] == null) {
                position = new Point(org.getPos().getX() - 1, org.getPos().getY() - 1);

            }
        }
        return position;
    }

    public void checkIfPlayerisAlive()
    {
            playerReference.setAlive(false);
            for(Organizm org: zyweOrg)
            {
                if(org.getName().equals("Czlowiek"))
                {
                    playerReference.setAlive(true);
                    break;
                }
            }
    }

    //<editor-fold desc="Getters and Setters">


    public Czlowiek getPlayerReference() {
        return playerReference;
    }

    public void setPlayerReference(Czlowiek playerReference) {
        this.playerReference = playerReference;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public boolean isPlayerAlive()
    {
        return isPlayerAlive;
    }

    public void setPlayerAlive(boolean playerAlive)
    {
        isPlayerAlive = playerAlive;
    }

    public Organizm[][] getOrganizmy() {
        return organizmy;
    }

    public void setOrganizmy(Organizm[][] organizmy) {
        this.organizmy = organizmy;
    }

    public Vector<Organizm> getOrgTab() {
        return orgTab;
    }

    public void setOrgTab(Vector<Organizm> orgTab) {
        this.orgTab = orgTab;
    }

    public Vector<Organizm> getZyweOrg() {
        return zyweOrg;
    }

    public void setZyweOrg(Vector<Organizm> zyweOrg) {
        this.zyweOrg = zyweOrg;
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public Vector<Organizm> getLegendOrgs() {
        return legendOrgs;
    }

    public void setLegendOrgs(Vector<Organizm> legendOrgs) {
        this.legendOrgs = legendOrgs;
    }
    //</editor-fold>


}
