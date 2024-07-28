package main.excelsheetlibrary;

public class Cell {
    private final Object value;
    private final ExcelSheet sheet;

    public Cell(Object value, ExcelSheet sheet) {
        this.value = value;
        this.sheet = sheet;
    }

    public int evaluate() throws Exception {
        if (value instanceof Integer) {
            return (int) value;
        } else if (value instanceof String formula) {
            int calculateValue;
            if (formula.contains("+")) {
                String[] cellsArray = formula.split("\\+");
                calculateValue = calculateCells(cellsArray);

            } else if (formula.contains("-")) {
                String[] cellsArray = formula.split("-");
                calculateValue = calculateCells(cellsArray);

            } else if (formula.contains("*")) {
                String[] cellsArray = formula.split("\\*");
                calculateValue = calculateCells(cellsArray);
            } else {
                if (formula.matches("[^a-zA-Z\\d]")) {
                    return 0;
                } else {
                    return sheet.evaluateCells(formula);
                }
            }


            return calculateValue;
        }

        return 0;
    }

    private int calculateCells(String[] cellsArray) throws Exception {
        int sum = 0;
        for (String cell : cellsArray) {
            if (cell.matches("\\d")) {
                sum += Integer.parseInt(cell);
            } else {
                sum += sheet.evaluateCells(cell);
            }
        }

        return sum;

    }

    public static void main(String[] args) throws Exception {
        ExcelSheet excelSheet = new ExcelSheet();
        excelSheet.setCellValue("A1", 9);
        excelSheet.setCellValue("A2", "A1");
        excelSheet.setCellValue("B1", "A1+A2");

        System.out.println("Value of A1 is: " + excelSheet.evaluateCells("A1"));
        System.out.println("Value of A2 is: " + excelSheet.evaluateCells("A2"));
        System.out.println("Value of B1 is: " + excelSheet.evaluateCells("B1"));
    }
}
