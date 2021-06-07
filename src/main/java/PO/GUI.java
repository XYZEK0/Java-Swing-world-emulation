package PO;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

public class GUI implements KeyListener, ActionListener
{

    private Swiat swiat;
    private JPanel[][]gridPanels;
    private JPanel boardPanel;
    private JPanel legend;
    private JFrame worldFrame;
    private JTextArea consoleOutput;
    private JPanel pan;
    private JPanel colorBox;
    private JPanel consolePanel;
    private JButton turnButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel panText;
    private JScrollPane scroll;
    private Czlowiek player;
    private SaveGame saveGame;


    public GUI()
    {
        Font font = new Font("Verdana",Font.BOLD,12);
        this.swiat = new Swiat(this);
        this.worldFrame = new JFrame();
        this.worldFrame.setFocusable(true);
        this.worldFrame.addKeyListener(this);
        this.turnButton = new JButton("Make turn");
        this.saveButton = new JButton("Save Game");
        this.loadButton = new JButton("Load Game");

        this.gridPanels = new JPanel[swiat.getSizeX()][swiat.getSizeY()];
        this.boardPanel = new JPanel();
        this.legend = new JPanel();
        this.consolePanel = new JPanel();
        this.consoleOutput = new JTextArea(18,20);
        this.consoleOutput.setFont(font);

        this.turnButton.setPreferredSize(new Dimension(100,50));
        this.turnButton.setFocusable(true);
        this.turnButton.addActionListener(this);

        this.saveButton.setPreferredSize(new Dimension(100,50));
        this.saveButton.setFocusable(true);
        this.saveButton.addActionListener(this);

        this.loadButton.setPreferredSize(new Dimension(100,20));
        this.loadButton.setFocusable(true);
        this.loadButton.addActionListener(this);

        this.consoleOutput.setEditable(false);
        this.consoleOutput.setAutoscrolls(true);
        this.consoleOutput.setLineWrap(true);


        this.scroll = new JScrollPane(this.consoleOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.consolePanel.add(scroll);


        this.boardPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        this.boardPanel.setLayout(new GridLayout(swiat.getSizeX(),swiat.getSizeY()));


        this.legend.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        this.legend.setPreferredSize( new Dimension(300,this.boardPanel.getHeight()));


        PrintStream printStream = new PrintStream(new CustomOutputStream(this.consoleOutput));

        this.legend.add(consolePanel);

        for (Organizm org: swiat.getLegendOrgs())
        {
            pan = new JPanel();
            colorBox = new JPanel();
            panText = new JLabel();

            pan.setMaximumSize(new Dimension(100,70));
            pan.setAlignmentX(Component.LEFT_ALIGNMENT);
            pan.setLayout(new FlowLayout(FlowLayout.LEADING));

            colorBox.setBackground(org.getOrgColor());
            colorBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            colorBox.setPreferredSize(new Dimension(30,30));
            colorBox.setMaximumSize(new Dimension(30,30));

            panText.setPreferredSize(new Dimension(100,15));
            panText.setText(" - " + org.getName());
            panText.setAlignmentX(Component.RIGHT_ALIGNMENT);

            pan.add(colorBox);
            pan.add(panText);

            legend.add(pan);
        }

        this.legend.add(turnButton);
        this.legend.add(saveButton);
        this.legend.add(loadButton);


        for(int i=0; i<swiat.getSizeY(); i++)
        {
            for(int j=0; j<swiat.getSizeX(); j++)
            {
                gridPanels[i][j] = new JPanel();
                if(swiat.getOrganizmy()[i][j]!=null)
                    gridPanels[i][j].setBackground(swiat.getOrganizmy()[i][j].getOrgColor());
                else gridPanels[i][j].setBackground(Color.white);
                boardPanel.add(gridPanels[i][j]);
            }
        }

        System.setOut(printStream);
        //System.setErr(printStream);
        this.worldFrame.add(boardPanel);
        this.worldFrame.add(legend,BorderLayout.EAST);
        this.worldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.worldFrame.setTitle("Wirtualny świat");
        this.worldFrame.pack();
        this.worldFrame.setResizable(false);
        this.worldFrame.setSize(new Dimension(1400,1000));
        this.worldFrame.setVisible(true);

        this.swiat.setOrgTab(this.swiat.makeOrgTab(this.swiat.getOrganizmy()));
        swiat.sortOrg(swiat.getOrgTab(), 0, swiat.getOrgTab().size() - 1);
        this.swiat.setZyweOrg( new Vector<>( this.swiat.getOrgTab())); ;

    }

    public void updateBoard()
    {
        for(int i=0; i<swiat.getSizeY(); i++)
        {
            for(int j=0; j<swiat.getSizeX(); j++)
            {
                gridPanels[i][j] = null;
            }
        }
        boardPanel.removeAll();
        for(int i=0; i<swiat.getSizeY(); i++)
        {
            for(int j=0; j<swiat.getSizeX(); j++)
            {
                gridPanels[i][j] = new JPanel();
                if(swiat.getOrganizmy()[i][j]!=null)
                    gridPanels[i][j].setBackground(swiat.getOrganizmy()[i][j].getOrgColor());
                else gridPanels[i][j].setBackground(Color.white);
                boardPanel.add(gridPanels[i][j]);
            }
        }

        worldFrame.add(boardPanel);
        worldFrame.add(legend,BorderLayout.EAST);
        worldFrame.setVisible(true);
    }

    //<editor-fold desc="Getters and Setters">

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public void setLoadButton(JButton loadButton) {
        this.loadButton = loadButton;
    }

    public SaveGame getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }

    public JButton getTurnButton() {
        return turnButton;
    }

    public void setTurnButton(JButton turnButton) {
        this.turnButton = turnButton;
    }

    public Czlowiek getPlayer() {
        return player;
    }

    public void setPlayer(Czlowiek player) {
        this.player = player;
    }

    public Swiat getSwiat()
    {
        return swiat;
    }

    public void setSwiat(Swiat swiat) {

        this.swiat = swiat;
    }

    public JPanel[][] getGridPanels() {
        return gridPanels;
    }

    public void setGridPanels(JPanel[][] gridPanels) {
        this.gridPanels = gridPanels;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(JPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public JPanel getLegend() {
        return legend;
    }

    public void setLegend(JPanel legend) {
        this.legend = legend;
    }

    public JFrame getWorldFrame() {
        return worldFrame;
    }

    public void setWorldFrame(JFrame worldFrame) {
        this.worldFrame = worldFrame;
    }

    public JTextArea getConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(JTextArea consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public JPanel getPan() {
        return pan;
    }

    public void setPan(JPanel pan) {
        this.pan = pan;
    }

    public JPanel getColorBox() {
        return colorBox;
    }

    public void setColorBox(JPanel colorBox) {
        this.colorBox = colorBox;
    }

    public JPanel getConsolePanel() {
        return consolePanel;
    }

    public void setConsolePanel(JPanel consolePanel) {
        this.consolePanel = consolePanel;
    }

    public JLabel getPanText() {
        return panText;
    }

    public void setPanText(JLabel panText) {
        this.panText = panText;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }
    //</editor-fold>

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(!player.isAlive())
        {
            System.out.println("Player is DEAD!");
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            player.setPlayerMoveDir(Czlowiek.Directions.UP);
            System.out.println("Player movement is set to UP");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            player.setPlayerMoveDir(Czlowiek.Directions.DOWN);
            System.out.println("Player movement is set to DOWN");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.setPlayerMoveDir(Czlowiek.Directions.LEFT);
            System.out.println("Player movement is set to LEFT");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.setPlayerMoveDir(Czlowiek.Directions.RIGHT);
            System.out.println("Player movement is set to RIGHT");
        }
        if (e.getKeyCode() == KeyEvent.VK_P)
        {

            if(!player.isPowerOn() && !player.isPowerOnCooldown())
            {
                System.out.println("  POWER IS ON");
                player.setPowerOn(true);
                player.setPowerTurns(5);
            }
            else if(player.isPowerOnCooldown())
            {
                System.out.println("  Power is currently on cooldown for " + player.getCooldownTurns());
            }
            else
                System.out.println("  Power is currently on for : " + player.getPowerTurns() + " turns");
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
       if( e.getSource() == turnButton)
       {
           this.swiat.makeTurn();
           this.swiat.setTurnCounter(this.swiat.getTurnCounter() + 1);
           turnButton.setFocusable(false);
       }
       if(e.getSource() == saveButton)
       {
           try {
              this.saveGame = new SaveGame(swiat);
           } catch (IOException ioException) {
               ioException.printStackTrace();
           }
           saveButton.setFocusable(false);
       }
       if(e.getSource() == loadButton)
       {
           try {
              new LoadGame(this.saveGame,swiat);
           } catch (IOException ioException) {
               ioException.printStackTrace();
           }
           loadButton.setFocusable(false);
       }
    }
}
