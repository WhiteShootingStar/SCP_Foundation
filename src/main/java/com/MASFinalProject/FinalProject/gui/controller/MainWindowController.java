package com.MASFinalProject.FinalProject.gui.controller;

import com.MASFinalProject.FinalProject.gui.view.MainWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

@Component
public class MainWindowController {

    private MainWindow view;
    @Autowired
    private ScientistListController scientistListController;
    @Autowired
    private LoginWindowController loginWindowController;

    public MainWindowController() throws InvocationTargetException, InterruptedException {
        view = new MainWindow();


    }

    public MainWindow getView() {
        return view;
    }

    public void showGUI() {
        view.setVisible(true);
        loginWindowController.showGUI(this);
    }
    public void showMainScientistWindow(){
        scientistListController.showGUI(this);
    }
    public void showView(JPanel panelView) {
        view.getContentPane().removeAll();
        view.getContentPane().add(panelView);
        view.revalidate();
    }
}
