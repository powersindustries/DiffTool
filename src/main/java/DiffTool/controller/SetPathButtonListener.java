package DiffTool.controller;

import DiffTool.service.DiffService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SetPathButtonListener extends Component implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            System.out.println(selectedDirectory.getAbsolutePath());

            String buttonCommand = e.getActionCommand();
            if (buttonCommand.equals("path1")) {
                DiffService.setPath1(selectedDirectory.getAbsolutePath());
            } else {
                DiffService.setPath2(selectedDirectory.getAbsolutePath());
            }

        }
    }
}