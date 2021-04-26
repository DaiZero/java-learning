package easyexcel;

import com.alibaba.excel.EasyExcel;
import easyexcel.dao.ProjectPayment;
import easyexcel.util.MyMergeStrategy;
import easyexcel.util.RandomNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dzero
 */
public class FillTest {

    public static void main(String[] args) throws Exception {
        export001();
    }

    private static void export001() throws Exception {
        List list = createData();
        // 数据分组
        Object collect = list.stream()
                .collect(Collectors.groupingBy(ProjectPayment::getProjectManager, Collectors.counting()));
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
        // 填充数据
        String templateFileName = ClassLoader.getSystemResource("template/FillTest001.xlsx").getPath();
        String fileName = "D:/temp/" + "listFill" + System.currentTimeMillis() + ".xlsx";
        MyMergeStrategy myMergeStrategy = new MyMergeStrategy(list, gl);
        EasyExcel.write(fileName)
                .withTemplate(templateFileName)
                .registerWriteHandler(myMergeStrategy)
                .sheet().doFill(list);
    }

    private static List<ProjectPayment> createData() {
        List list = new ArrayList<ProjectPayment>();
        for (int i = 0; i < 5; i++) {
            String pm = "项目经理00" + i;
            int times = RandomNumber.randInt(1, 4);
            for (int j = 0; j < times; j++) {
                int k = RandomNumber.randInt(1, 10000);
                String am = "客户经理00" + k;
                String accName = "客户名称00" + k;
                String cNo = "合同编号00" + k;
                ProjectPayment projectPayment1 = new ProjectPayment(pm, am, accName, cNo, "产品",
                        BigDecimal.valueOf(10000),
                        BigDecimal.valueOf(10000),
                        BigDecimal.valueOf(11000),
                        BigDecimal.valueOf(10002),
                        BigDecimal.valueOf(50000));
                list.add(projectPayment1);
            }
        }
        list.forEach(System.out::println);
        return list;
    }


}


