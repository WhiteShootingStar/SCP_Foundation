package com.MASFinalProject.FinalProject.gui.view;

import javax.swing.*;

public class MainWindow extends JFrame {

    private JMenuItem menuItemScientistList;

    public MainWindow(){
        setTitle("Secure.Contain.Protect");
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //initMenuBar();
    }

    public void initMenuBar(){
        JMenuBar bar= new JMenuBar();
        JMenu menuScientist=new JMenu("Scientist");
        bar.add(menuScientist);
        menuItemScientistList=new JMenuItem("Scientist list");
        menuScientist.add(menuItemScientistList);
        this.setJMenuBar(bar);
    }

    public JMenuItem getMenuItemScientistList() {
        return menuItemScientistList;
    }
}
