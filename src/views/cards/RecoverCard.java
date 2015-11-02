package views.cards;

import controllers.RecoverController;
import models.RecoverModel;

import javax.swing.*;
import java.awt.*;

public class RecoverCard {
    private RecoverModel recoverModel;

    public RecoverCard(RecoverModel recoverModel) { this.recoverModel = recoverModel; }

    public JPanel createLayout() {


        JPanel recoverPanel = recoverModel.getRecoverPanel();
        recoverModel.getErrorLabel().setForeground(Color.RED);

        GridBagConstraints constraints = new GridBagConstraints();

        //Anchor to left side
        constraints.anchor = GridBagConstraints.WEST;
        //Pad the cells
        constraints.insets = new Insets(10,10,10,10);

        //add comp to panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        recoverPanel.add(recoverModel.getErrorLabel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        recoverPanel.add(recoverModel.getRecoverGuidelines(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        recoverPanel.add(recoverModel.getEmailLabel(), constraints);

        constraints.gridx = 1;
        recoverPanel.add(recoverModel.getEmailField(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        recoverPanel.add(recoverModel.getRecoverButton(), constraints);

        constraints.gridx = 1;
        recoverPanel.add(recoverModel.getCancelButton(), constraints);

        RecoverController recoverController = new RecoverController(recoverModel);

        recoverModel.getRecoverButton().addActionListener(recoverController);
        recoverModel.getCancelButton().addActionListener(recoverController);

        return recoverPanel;
    }
}