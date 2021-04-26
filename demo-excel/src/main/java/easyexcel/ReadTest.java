package easyexcel;

import com.alibaba.excel.EasyExcel;
import easyexcel.dao.EmployeeLegwork;

import java.util.List;

/**
 * 读取Excel的Demo
 *
 * @author dzero
 */
public class ReadTest {

    public static void main(String[] args) {
        readExcel001();
    }

    public static void readExcel001() {
        String fileName = ClassLoader.getSystemResource("template/ReadTest001.xls").getPath();
        List<EmployeeLegwork> list = EasyExcel.read(fileName).head(EmployeeLegwork.class).sheet().doReadSync();
        list.forEach(System.out::println);
    }
}
