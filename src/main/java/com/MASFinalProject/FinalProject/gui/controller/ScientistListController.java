package com.MASFinalProject.FinalProject.gui.controller;

import com.MASFinalProject.FinalProject.gui.view.SCPSelectionView;
import com.MASFinalProject.FinalProject.gui.view.ScientistListView;
import com.MASFinalProject.FinalProject.model.SCP;
import com.MASFinalProject.FinalProject.model.Scientist;
import com.MASFinalProject.FinalProject.repository.ScientistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ScientistListController {


    private ScientistListView view;
    @Autowired
    private ScientistRepo scientistRepo;
    @Autowired
    private SCPSelectionController selectionController;
    @Autowired
    private MainWindowController windowController;

    private Scientist selectedScientist;
    private int selectedIndex;

    public ScientistListController() {
        view = new ScientistListView();
        initListModels();
        initListeners();
    }

    private void initListeners() {
        view.getScientistList().addListSelectionListener(e -> {

            SwingUtilities.invokeLater(() -> {
                if (!e.getValueIsAdjusting()) {
                    selectedScientist = (Scientist) view.getScientistList().getSelectedValue();
                    selectedIndex = view.getScientistList().getSelectedIndex();
                    getSetsAndUpdateSCPList();

                }
            });
        });

        view.getQuitButton().addActionListener(e -> {
            System.exit(0);
        });

        view.getAddButton().addActionListener(e -> {
            if (selectedScientist != null) {
                System.out.println("Will render");
                selectionController.showGUI(selectedScientist);
            }
        });
    }

    public void getSetsAndUpdateSCPList() {
        if (selectedScientist != null) {
            Optional<Scientist> observer = scientistRepo.findByPersonId(selectedScientist.getPersonId());
            Optional<Scientist> conductor = scientistRepo.findByPersonIdConducting(selectedScientist.getPersonId());
            Set<SCP>observes=new HashSet<>();
            Set<SCP>conductsOn= new HashSet<>();
            if(observer.isPresent()) observes=observer.get().getObservedSCP();
            if(conductor.isPresent()) conductsOn=conductor.get().getConductedSCP();
            updateSCPList(observes, conductsOn); //First set are all SCP by this scientist, the other one are SCPs Conducted by him
        }
    }

    private void initListModels() {
        view.getScientistList().setModel(new DefaultListModel<Scientist>());
        view.getScpList().setModel(new DefaultListModel<SCP>());
    }

    public void showGUI(MainWindowController mainWindowController) {
        updateScientistListFromDB();
        mainWindowController.showView(view.getMainPanel());
    }

    private void updateScientistListFromDB() {
        List<Scientist> scientists = scientistRepo.findAll();

        DefaultListModel<Scientist> scientistModel = (DefaultListModel<Scientist>) view.getScientistList().getModel();
        scientistModel.removeAllElements();
        for (Scientist s : scientists) {
            scientistModel.addElement(s);
        }
    }

    public void updateSCPList(Set<SCP> scps, Set<SCP> conductedOn) {
        DefaultListModel<SCP> scpListModel = (DefaultListModel<SCP>) view.getScpList().getModel();
        scpListModel.removeAllElements();
        for (SCP scp : scps) {
            assignTheSubset(scp, conductedOn); //manually assign the transient field
            scpListModel.addElement(scp);
        }

    }

    private void assignTheSubset(SCP scp, Set<SCP> scps) {

        if (scps.contains(scp)) {

            scp.setConducted(true);

        } else {
            scp.setConducted(false);
        }

    }

    public void showSCPSelection(SCPSelectionView scpSelectionView) {
        scpSelectionView.setVisible(true);
        disableMainView();
    }

    public void enableMainView() {

        windowController.getView().setEnabled(true);
        updateScientistListFromDB();
        if (selectedScientist != null)
            view.getScientistList().setSelectedIndex(selectedIndex);

    }

    public void disableMainView() {
        windowController.getView().setEnabled(false);
    }
}
