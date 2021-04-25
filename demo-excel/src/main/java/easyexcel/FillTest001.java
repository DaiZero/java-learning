package easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sun.javafx.collections.MappingChange;
import easyexcel.dao.ProjectPayment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;


import java.util.stream.Collectors;

/**
 * @author dzero
 */
public class FillTest001 {

    public static void main(String[] args) throws Exception {
        export001();
    }

    private static void export001() throws Exception {
        List list = new ArrayList<ProjectPayment>();
        for (int i = 0; i < 5; i++) {
            String pm = "项目经理00" + i;
            int times = randInt(1, 4);
            for (int j = 0; j < times; j++) {
                int k = randInt(1, 10000);
                String am = "客户经理00" + k;
                String accName = "客户名称00" + k;
                String cNo = "合同编号00" + k;
                ProjectPayment projectPayment1 = new ProjectPayment(pm, am, accName, cNo, "产品",
                        BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(11000), BigDecimal.valueOf(10002), BigDecimal.valueOf(50000));
                list.add(projectPayment1);
            }
        }
        list.forEach(System.out::println);
        String templateFileName = ClassLoader.getSystemResource("template/FillTest001.xlsx").getPath();
        String fileName = "D:/temp/" + "listFill" + System.currentTimeMillis() + ".xlsx";
        Object collect = list.stream().collect(Collectors.groupingBy(ProjectPayment::getProjectManager, Collectors.counting()));
        HashMap<String, Long> hashMap;
        if (collect instanceof HashMap) {
            hashMap = (HashMap<String, Long>) collect;
        } else {
            return;
        }
        List<Integer> gl = new ArrayList<>();
        hashMap.forEach((k, v) -> {
            gl.add(Integer.valueOf(v.toString()));
        });
        System.out.println(collect);
        MyMergeStrategy myMergeStrategy = new MyMergeStrategy(list, gl);
        EasyExcel.write(fileName).withTemplate(templateFileName).registerWriteHandler(myMergeStrategy).sheet().doFill(list);
    }

    public static int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }


    static class MyMergeStrategy extends AbstractMergeStrategy {

        private List<Object> fruitList;
        private List<Integer> fruitGroupCount;
        private Sheet sheet;

        public MyMergeStrategy(List<Object> fruitList, List<Integer> fruitGroupCount) {
            this.fruitList = fruitList;
            this.fruitGroupCount = fruitGroupCount;
        }

        // 将该列全部合并成一个单元格
        private void mergeCommonColumn(Integer index) {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(1, fruitList.size(), index, index);
            sheet.addMergedRegionUnsafe(cellRangeAddress);
        }

        // 按照分组将各种类别分别合并成一个单元格
        private void mergeGroupColumn(Integer index) {
            Integer rowCnt = 1;
            for (Integer count : fruitGroupCount) {
                int lastCnt=rowCnt + count - 1;
                if(lastCnt>rowCnt){
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
//                    case 1:
//                        this.mergeGroupColumn(1);
//                        break;
//                    case 2:
//                        break;
//                    case 3:
//                        this.mergeCommonColumn(3);
//                        break;
                    default:
                        break;
                }
            }
        }
    }
}


