package com.MASFinalProject.FinalProject.gui.view;

import com.MASFinalProject.FinalProject.model.SCP;

import javax.swing.*;
import java.awt.*;

public class SCPSelectionView extends JFrame{
    private JComboBox SCPSelectionComboBox;
    private JRadioButton radioConductedButton;
    private JLabel doctorLabel;
    private JButton cancelButton;
    private JButton AddSCPButton;
    private JPanel SCPSelection;

    public SCPSelectionView(){
        setSize(800,600);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(SCPSelection);
        this.setLocationRelativeTo(null); // Places window in the center
    }
    public JPanel getSCPSelection() {
        return SCPSelection;
    }

    public JComboBox getSCPSelectionComboBox() {
        return SCPSelectionComboBox;
    }

    public JRadioButton getRadioConductedButton() {
        return radioConductedButton;
    }

    public JLabel getDoctorLabel() {
        return doctorLabel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getAddSCPButton() {
        return AddSCPButton;
    }

    private void createUIComponents() {
        SCPSelectionComboBox= new JComboBox();
        SCPSelectionComboBox.setRenderer(new SCPComboBoxRenderer());
    }

    private class SCPComboBoxRenderer extends JLabel implements ListCellRenderer<SCP>{

        @Override
        public Component getListCellRendererComponent(JList<? extends SCP> list, SCP value, int index, boolean isSelected, boolean cellHasFocus) {
            setText("SCP " +value.getNumber());
            return this;
        }
    }
}
