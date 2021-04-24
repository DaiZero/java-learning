package easyexcel.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目回款信息
 * @author dzero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPayment implements Serializable {

    private String projectManager;

    private String accountManager;

    private String accountName;

    private String contractNo;

    private String groupName;

    private BigDecimal firstQuarterPayment;

    private BigDecimal secondQuarterPayment;

    private BigDecimal thirdQuarterPayment;

    private BigDecimal fourthQuarterPayment;

    private BigDecimal yearPayment;

}
