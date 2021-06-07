package PO;

import com.sun.jdi.InternalException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SaveGame
{

    private Swiat worldReference;

    public SaveGame(Swiat worldReference) throws IOException
    {
        this.worldReference = worldReference;
        new File("saveGame.txt");
        System.out.println("GAME SAVED!");
        fillSave();
    }

    public void fillSave() throws IOException
    {
        FileWriter fileWriter = new FileWriter("saveGame.txt");

        fileWriter.write(Integer.toString(worldReference.getTurnCounter()) + "\n");
        for(Organizm org: worldReference.getZyweOrg())
        {
            fileWriter.write(org.getName() + " " + org.getWiek() + " " + org.getPos().getX() + " " + org.getPos().getY() + " " + org.getSila() + "\n");
        }
        fileWriter.close();
    }


    public Swiat getWorldReference() {
        return worldReference;
    }

    public void setWorldReference(Swiat worldReference) {
        this.worldReference = worldReference;
    }

}
