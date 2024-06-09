package DiffTool;

import DiffTool.model.IgnoreList;
import DiffTool.view.MainFrame;

public class Main {

    public static void main(String[] args) {

        IgnoreList.populateIgnoreList();

        javax.swing.SwingUtilities.invokeLater(MainFrame::new);
    }
}