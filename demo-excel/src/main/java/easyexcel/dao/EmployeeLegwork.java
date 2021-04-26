package easyexcel.dao;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 外勤明细
 *
 * @author dzero
 */
@Data
public class EmployeeLegwork implements Serializable {

    @ExcelProperty(value = "姓名")
    private String employeeName;

    @ExcelProperty(value = "签到时间")
    private String signInTimeStr;

    public void setSignInTimeStr(String signInTimeStr) {
        this.signInTimeStr = signInTimeStr;
        if (signInTimeStr != null && !"".equals(signInTimeStr)) {
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime newTime = LocalDateTime.parse(signInTimeStr, dtf3);
            if (newTime != null) {
                this.signInTime = newTime;
            }
        }
    }

    @ExcelIgnore
    private LocalDateTime signInTime;

    @ExcelProperty(value = "内容")
    private String content;

    @ExcelProperty(value = "关联类型")
    private String relatedType;

    @ExcelProperty(value = "关联事项")
    private String relatedItem;

    @ExcelProperty(value = "所属客户")
    private String belongCustomer;

    @ExcelProperty(value = "联系类型")
    private String contactWay;

    @ExcelProperty(value = "联系人")
    private String contacts;

    @ExcelProperty(value = "签退时间")
    private String signOutTimeStr;

    public void setSignOutTimeStr(String signOutTimeStr) {
        this.signOutTimeStr = signOutTimeStr;
        if (signOutTimeStr != null && !"".equals(signOutTimeStr)) {
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime newTime = LocalDateTime.parse(signOutTimeStr, dtf3);
            if (newTime != null) {
                this.signOutTime = newTime;
            }
        }
    }

    @ExcelIgnore
    private LocalDateTime signOutTime;

    @ExcelProperty(value = "签退地点")
    private String signOutAddress;

    @ExcelProperty(value = "外勤时长")
    private String workDuration;
}
