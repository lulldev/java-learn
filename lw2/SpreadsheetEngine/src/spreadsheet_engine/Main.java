package spreadsheet_engine;

import spreadsheet_engine.core.*;

public class Main {
    public static void main(String[] args) {
        try {
            Spreadsheet spreadsheet = new Spreadsheet();
            SpreadsheetController controller = new SpreadsheetController(spreadsheet);
            controller.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
