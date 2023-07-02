package com.donaldy.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author donald
 * @date 2023/7/2
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class XARobotDataDto {
    @JSONField(name = "姓名")
    private String name;
    @JSONField(name = "电话")
    private String phone;
    @JSONField(name = "地址")
    private String address = "";
    @JSONField(name = "公司名称")
    private String companyName = "";
    @JSONField(name = "备注")
    private String remark;
}