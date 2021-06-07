package PO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoadGame
{

    private SaveGame saveGame;
    private Swiat worldReference;

    public LoadGame(SaveGame saveGame,Swiat worldReference) throws IOException
    {
        this.saveGame = saveGame;
        this.worldReference = worldReference;
        this.worldReference.getZyweOrg().clear();
        readFromFile();
    }

    private void readFromFile()
    {
        try
        {
            File readFile = new File("saveGame.txt");
            Scanner myReader = new Scanner(readFile);

            String data = myReader.nextLine();


            data = data.trim();
            worldReference.setTurnCounter(Integer.parseInt(data));

            while (myReader.hasNextLine())
            {
                data = myReader.nextLine();
                parseLine(data);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not Found");
            e.printStackTrace();
        }

        System.out.println("SAVE LOADED!");
        worldReference.checkIfPlayerisAlive();
        worldReference.updateOrganism();
        worldReference.getGui().updateBoard();
    }

    private void parseLine(String line)
    {
        Organizm org = null;
        String className = "";
        String posX = "";
        String posY = "";
        String age = "";
        String strength = "";

        int spaceIterator = 0;

        for(int i=0; i<line.length(); i++)
        {
            if(line.charAt(i) == ' ') spaceIterator++;

            if(spaceIterator == 0)
                className = className.concat(Character.toString(line.charAt(i)));
            else if(spaceIterator == 1)
               age = age.concat(Character.toString(line.charAt(i)));
            else if(spaceIterator == 2)
                posX = posX.concat(Character.toString(line.charAt(i)));
            else if(spaceIterator == 3)
                posY = posY.concat(Character.toString(line.charAt(i)));
            else if(spaceIterator == 4)
                strength = strength.concat(Character.toString(line.charAt(i)));

        }
         className =  className.trim();
         age = age.trim();
         posX = posX.trim();
         posY = posY.trim();
        strength = strength.trim();

        switch (className)
        {
            case "Wilk" -> {
                org = new Wilk(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Antylopa" -> {
                org = new Antylopa(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Barszcz" -> {
                org = new Barszcz(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "CyberOwca" -> {
                org = new CyberOwca(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Czlowiek" -> {
                org = new Czlowiek(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
                worldReference.getGui().setPlayer((Czlowiek)org);
                worldReference.setPlayerReference((Czlowiek)org);
            }
            case "Guarana" -> {
                org = new Guarana(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Lis" -> {
                org = new Lis(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Mlecz" -> {
                org = new Mlecz(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Owca" -> {
                org = new Owca(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Trawa" -> {
                org = new Trawa(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "WilczaJagoda" -> {
                org = new WilczaJagoda(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
            case "Zolw" -> {
                org = new Zolw(new Point(Integer.parseInt(posX), Integer.parseInt(posY)), worldReference);
            }
        }
        if(org!=null)
        {
            org.setWiek(Integer.parseInt(age));
            org.setSila(Integer.parseInt(strength));
            worldReference.getZyweOrg().add(org);
        }

    }

    public SaveGame getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }

    public Swiat getWorldReference() {
        return worldReference;
    }

    public void setWorldReference(Swiat worldReference) {
        this.worldReference = worldReference;
    }

}
