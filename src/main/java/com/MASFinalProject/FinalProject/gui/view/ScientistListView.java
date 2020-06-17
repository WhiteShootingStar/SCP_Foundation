package com.MASFinalProject.FinalProject.gui.view;

import com.MASFinalProject.FinalProject.model.SCP;
import com.MASFinalProject.FinalProject.model.Scientist;

import javax.swing.*;
import java.awt.*;

public class ScientistListView {
    private JPanel mainPanel;
    private JList scientistList;
    private JList scpList;
    private JButton quitButton;
    private JButton addButton;





    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JList getScientistList() {
        return scientistList;
    }

    public JList getScpList() {
        return scpList;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JButton getAddButton() {
        return addButton;
    }



    private void createUIComponents() {
        scpList=new JList();
        scpList.setCellRenderer(new SCPCellRenderer());
        scientistList= new JList();
        scientistList.setCellRenderer(new ScientistCellRenderer());
    }

    private class SCPCellRenderer extends JLabel implements ListCellRenderer<SCP>{

        @Override
        public Component getListCellRendererComponent(JList<? extends SCP> list, SCP value, int index, boolean isSelected, boolean cellHasFocus) {
            setText("SCP "+value.getNumber());
            setOpaque(true);
            if(value.isConducted()){
                setBackground(Color.RED);
            }
            else setBackground(Color.GREEN);


            //System.out.println(value.getNumber() + " " +value.isConducted());

            return this;
        }
    }
    private class ScientistCellRenderer extends JLabel implements ListCellRenderer<Scientist>{

        @Override
        public Component getListCellRendererComponent(JList<? extends Scientist> list, Scientist value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getTitle()+ " "+value.getSurname());
            setOpaque(true);
            if(isSelected){
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }else{
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }


}
