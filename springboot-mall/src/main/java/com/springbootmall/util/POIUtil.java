package com.springbootmall.util;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * POI工具类
 *
 */
public class POIUtil {
    /**
     * 复制行
     * @param startRow 开始行
     * @param endRow 结束行
     * @param pPosition 目标行
     * @param sheet 工作表对象
     */
    public static void copyRows(int startRow, int endRow, int pPosition, Sheet sheet){
        int pStartRow=startRow;
        int pEndRow=endRow;
        int targetRowFrom;
        int targetRowTo;
        int columnCount;
        CellRangeAddress region=null;
        int i;
        int j;
        if(pStartRow == -1 || pEndRow == -1) {
            return;
        }
        // 拷贝合并的单元格
        for(i=0;i<sheet.getNumMergedRegions();i++){
            region=sheet.getMergedRegion(i);
            if((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
                targetRowFrom=region.getFirstRow()-pStartRow+pPosition;
                targetRowTo=region.getLastRow()-pStartRow+pPosition;
                CellRangeAddress newRegion=region.copy();
                newRegion.setFirstRow(targetRowFrom);
                newRegion.setFirstColumn(region.getFirstColumn());
                newRegion.setLastRow(targetRowTo);
                newRegion.setLastColumn(region.getLastColumn());
                sheet.addMergedRegion(newRegion);
            }
        }
        // 设置列宽
        for(i=pStartRow;i<=pEndRow;i++){
            Row sourceRow=sheet.getRow(i);
            if(sourceRow != null){
                columnCount=sourceRow.getLastCellNum();
                Row newRow=sheet.createRow(pPosition - pStartRow + i);
                newRow.setHeight(sourceRow.getHeight());
                for(j=0;j<columnCount;j++){
                    Cell templateCell=sourceRow.getCell(j);
                    if(templateCell != null){
                        Cell newCell=newRow.createCell(j);
                        copyCell(templateCell,newCell);
                    }
                }
            }
        }
    }
    /**
     * 复制单元格
     * @param srcCell 原始单元格
     * @param distCell 目标单元格
     */
    public static void copyCell(Cell srcCell,Cell distCell){
        distCell.setCellStyle(srcCell.getCellStyle());
        if(srcCell.getCellComment() != null){
            distCell.setCellComment(srcCell.getCellComment());
        }
        if (srcCell.getCellType().equals(CellType.STRING)){
            distCell.setCellValue(srcCell.getStringCellValue());
        }

    }
    /**
     * 表格中指定位置插入行
     * @param sheet 工作表对象
     * @param rowIndex 指定的行数
     * @return 当前行对象
     */
    public static Row insertRow(Sheet sheet,int rowIndex) {
        Row row=null;
        if(sheet.getRow(rowIndex) != null) {
            int lastRowNo=sheet.getLastRowNum();
            sheet.shiftRows(rowIndex,lastRowNo,1);
        }
        row=sheet.createRow(rowIndex);
        return row;
    }
}
