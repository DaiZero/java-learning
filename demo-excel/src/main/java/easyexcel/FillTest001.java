package easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import easyexcel.dao.ProjectPayment;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dzero
 */
public class FillTest001 {
    private static void export001() {
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
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(list);
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

    public static void main(String[] args) {
        export001();
    }

}
