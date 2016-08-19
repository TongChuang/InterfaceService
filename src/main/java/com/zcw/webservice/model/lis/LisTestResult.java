package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * LIS检验结果
 * Created by zcw on 2016/8/19.
 */

public class LisTestResult implements Serializable {

    private static final long serialVersionUID = 4480405612538704449L;

    @JSONField(name = "SampleNo")
    private String sampleNo;    //样本号

    @JSONField(name = "TestId")
    private String testId;      //检验项目id（每个id对应一个确定的检验项目）

    @JSONField(name = "TestName")
    private String testName;    //检验项目名称

    @JSONField(name = "TestResult")
    private String testResult; //检验结果

    @JSONField(name = "ResultFlag")
    private String resultFlag; //标注 检验结果 异常情况

    @JSONField(name = "TestStatus")
    private int testStatus;     //检验状态

    @JSONField(name = "CorrectFlag")
    private String correctFlag; //正确标志

    @JSONField(name = "SampleType")
    private String sampleType; //检验项目类型、来源

    @JSONField(name = "RefLo")
    private String refLo; // 参考范围低值

    @JSONField(name = "RefHi")
    private String refHi; // 参考范围高值

    @JSONField(name = "DeviceId")
    private String deviceId; // 设备号

    @JSONField(name = "MeasureTime")
    private Date measureTime; // 检验时间

    @JSONField(name = "Operator")
    private String operator; // 操作者

    @JSONField(name = "Unit")
    private String unit;    //单位

    @JSONField(name = "IsPrint")
    private int isprint;        //是否打印

    @JSONField(name = "EditMark")
    private int editMark;

    @JSONField(name = "Method")
    private String method;      //方法

    @JSONField(name = "Hint")
    private String hint;        //提示
}
