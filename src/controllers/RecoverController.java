package controllers;

import models.RecoverModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverController implements ActionListener {

    private RecoverModel recoverModel;

    public RecoverController(RecoverModel recoverModel) { this.recoverModel = recoverModel; }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(recoverModel.getCancelButton().getText())) {
            recoverModel.showMenuCard();
        } else if (event.getActionCommand().equals(recoverModel.getRecoverButton().getText())) {
            recoverModel.recover();
        }
    }
}
