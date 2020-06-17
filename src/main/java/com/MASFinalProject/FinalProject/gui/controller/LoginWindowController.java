package com.MASFinalProject.FinalProject.gui.controller;

import com.MASFinalProject.FinalProject.gui.view.LoginWindowView;
import com.MASFinalProject.FinalProject.model.O12Member;
import com.MASFinalProject.FinalProject.repository.O12MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Optional;

@Component
public class LoginWindowController {

    private LoginWindowView view;

    @Autowired
    private O12MemberRepo o12MemberRepo;
    @Autowired
    private MainWindowController mainWindowController;

    public LoginWindowController() {
        view = new LoginWindowView();

        initializeListeners();
        disableError();
    }

    private void initializeListeners() {
        view.getLoginText().addFocusListener(new LoginFocusListener());
        view.getPasswordText().addFocusListener(new LoginFocusListener());
        view.getLoginButton().addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                String login = view.getLoginText().getText();
                String password = String.valueOf(view.getPasswordText().getPassword());
                loginInSystem(login, password);
            });

        });
    }

    public LoginWindowView getView() {
        return view;
    }

    private boolean loginSuccessful(String login, String password) {
        Optional<O12Member> potentialMembers = o12MemberRepo.getO12MemberByLoginAndPassword(login, password);
        if (potentialMembers.isPresent()) {
            return true;
        }
        return false;
    }

    public void showGUI(MainWindowController mainWindowController) {

        mainWindowController.showView(view.getLoginWindow());
    }

    private void loginInSystem(String login, String password) {
        if (loginSuccessful(login, password)) {
            mainWindowController.showMainScientistWindow();
        } else showError();
    }

    private void showError() {
        view.getErrorLabel().setVisible(true);
    }

    private void disableError() {
        view.getErrorLabel().setVisible(false);
    }

    private boolean isShowingError() {
        return view.getErrorLabel().isVisible();
    }

    private class LoginFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (isShowingError()) {
                disableError();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }
}
