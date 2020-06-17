package com.MASFinalProject.FinalProject.gui.controller;

import com.MASFinalProject.FinalProject.gui.view.SCPSelectionView;
import com.MASFinalProject.FinalProject.model.SCP;
import com.MASFinalProject.FinalProject.model.Scientist;
import com.MASFinalProject.FinalProject.repository.SCPRepo;
import com.MASFinalProject.FinalProject.repository.ScientistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Component
public class SCPSelectionController {
    private SCPSelectionView view;

    @Autowired
    private ScientistListController scientistListController;
    @Autowired
    private SCPRepo scpRepo;
    @Autowired
    private ScientistRepo scientistRepo;

    private Scientist scientist;

    public SCPSelectionController() {
        view = new SCPSelectionView();
        initListeners();
        initSCPModel();
    }

    private void initSCPModel() {
        view.getSCPSelectionComboBox().setModel(new DefaultComboBoxModel<SCP>());

    }

    private void initListeners() {
        view.getAddSCPButton().addActionListener(e -> {
            addNewSCP();
        });
        view.getCancelButton().addActionListener(e -> {
            switchToScientistList();
        });
        view.addWindowListener(new SCPSelectionWindowListener());
    }

    private void addNewSCP() {
        SCP selectedSCP = (SCP) view.getSCPSelectionComboBox().getModel().getSelectedItem();
        boolean isConducted = view.getRadioConductedButton().isSelected();
        Scientist sc = scientistRepo.findByPersonIdAll(scientist.getPersonId()).get();
        SCP s = scpRepo.findBySCPIdAll(selectedSCP.getSCPId()).get();
        sc.removeFromConducted(s, true);//This method with true parameter will remove any element from subset and and main part. After that later in the code it will be inserted again
        if (isConducted) {
            sc.addToConducted(s);
        } else {
            sc.addToObserved(s);
        }
        scientistRepo.save(sc);
        scientistListController.getSetsAndUpdateSCPList();
        switchToScientistList();
    }

    private void switchToScientistList() {
        scientistListController.enableMainView();
        view.dispose();
    }

    public void showGUI(Scientist selectedScientist) {
        scientist = selectedScientist;
        scientistListController.showSCPSelection(view);
        setScientistLabel(scientist);
        view.getRadioConductedButton().setSelected(false);
        getSCPListFromDB();
    }

    private void getSCPListFromDB() {
        List<SCP> allSCPs = scpRepo.findAll();
        DefaultComboBoxModel<SCP> scpComboBoxModel = (DefaultComboBoxModel<SCP>) view.getSCPSelectionComboBox().getModel();
        scpComboBoxModel.removeAllElements();
        for (SCP scp : allSCPs) {
            scpComboBoxModel.addElement(scp);
        }
    }


    private class SCPSelectionWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {

            scientistListController.enableMainView();
        }
        @Override
        public void windowClosed(WindowEvent e) {
            scientistListController.enableMainView();
        }
    }

    private void setScientistLabel(Scientist value) {
        view.getDoctorLabel().setText(value.getTitle() + " " + value.getSurname());
    }
}
