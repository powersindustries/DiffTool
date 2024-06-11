package DiffTool.controller;

import DiffTool.service.DiffService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DiffService.performDiff();
    }

}