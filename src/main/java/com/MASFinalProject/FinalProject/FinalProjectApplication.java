package com.MASFinalProject.FinalProject;

import com.MASFinalProject.FinalProject.gui.controller.LoginWindowController;
import com.MASFinalProject.FinalProject.gui.controller.MainWindowController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class FinalProjectApplication {

    @Autowired
    private DataCreator dataCreator;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FinalProjectApplication.class).headless(false).run(args);

        //context.getBean(DataCreator.class).generateData(); //used to initialize data

        SwingUtilities.invokeLater(() -> {
                    context.getBean(MainWindowController.class).showGUI();

                }
        );

    }

}
