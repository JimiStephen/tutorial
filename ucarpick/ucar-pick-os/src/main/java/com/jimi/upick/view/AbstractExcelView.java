package com.jimi.upick.view;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public abstract class AbstractExcelView extends AbstractXlsView {

    /**
     * Convenient method to obtain the cell in the given sheet, row and column.
     * <p>
     * Creates the row and the cell if they still doesn't already exist. Thus, the column can be passed as an int, the
     * method making the needed downcasts.
     * 
     * @param sheet a sheet object. The first sheet is usually obtained by workbook.getSheetAt(0)
     * @param row thr row number
     * @param col the column number
     * @return the HSSFCell
     */
    protected Cell getCell(Sheet sheet, int row, int col) {
        Row sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }
        Cell cell = sheetRow.getCell((short)col);
        if (cell == null) {
            cell = sheetRow.createCell((short)col);
        }
        return cell;
    }

    /**
     * Convenient method to set a String as text content in a cell.
     * 
     * @param cell the cell in which the text must be put
     * @param text the text to put in the cell
     */
    protected void setText(Cell cell, String text) {
        cell.setCellType(CellType.STRING);
        cell.setCellValue(text);
    }

    /**
     * Convenient method to set a String as text content in a cell.
     * 
     * @param cell the cell in which the text must be put
     * @param number the number to put in the cell
     */
    protected void setNumber(Cell cell, double number) {
        cell.setCellType(CellType.NUMERIC);
        cell.setCellValue(number);
    }
}
