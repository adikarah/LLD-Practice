package main.excelsheetlibrary;

import java.util.HashMap;
import java.util.Map;

public class ExcelSheet {
    private final Map<String, Cell> cells;

    ExcelSheet() {
        this.cells = new HashMap<>();
    }

    public void setCellValue(String cellName, Object value) {
        cells.put(cellName, new Cell(value, this));
    }

    public int evaluateCells(String cellName) throws Exception {
        if (cells.containsKey(cellName)) {
            return cells.get(cellName).evaluate();

        } else {
            throw new Exception("No cell found with given name");
        }
    }

}
