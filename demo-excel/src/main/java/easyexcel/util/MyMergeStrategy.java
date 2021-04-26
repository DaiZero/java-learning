package easyexcel.util;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @author dzero
 */
public class MyMergeStrategy extends AbstractMergeStrategy {

    private final List<Object> dataList;
    private final List<Integer> dataGroupCount;
    private Sheet sheet;

    public MyMergeStrategy(List<Object> dataList, List<Integer> dataGroupCount) {
        this.dataList = dataList;
        this.dataGroupCount = dataGroupCount;
    }

    /**
     * 将该列全部合并成一个单元格
     *
     * @param index
     */
    private void mergeCommonColumn(Integer index) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(1, dataList.size(), index, index);
        sheet.addMergedRegionUnsafe(cellRangeAddress);
    }

    /**
     * 按照分组将各种类别分别合并成一个单元格
     *
     * @param index
     */
    private void mergeGroupColumn(Integer index) {
        int rowCnt = 1;
        for (Integer count : dataGroupCount) {
            int lastCnt = rowCnt + count - 1;
            if (lastCnt > rowCnt) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(rowCnt, rowCnt + count - 1, index, index);
                sheet.addMergedRegionUnsafe(cellRangeAddress);
            }
            rowCnt += count;
        }
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer integer) {
        this.sheet = sheet;
        if (cell.getRowIndex() == 1) {
            switch (cell.getColumnIndex()) {
                case 0:
                    this.mergeGroupColumn(0);
                    break;
//                    case 3:
//                        this.mergeCommonColumn(3);
//                        break;
                default:
                    break;
            }
        }
    }
}
