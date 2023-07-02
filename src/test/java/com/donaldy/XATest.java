package com.donaldy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.donaldy.dto.XARobotDataDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author donald
 * @date 2023/7/2
 */
public class XATest {

    @Test
    public void read() {
        String intFileName = "/Users/yangyf/Downloads/任务导入模板-乒乓智能人机耦合话术_20230529113801_kj830116 (1).xlsx";

        List<XARobotDataDto> robotDataList = new ArrayList<>();
        EasyExcel.read(intFileName, XARobotDataDto.class, new PageReadListener<XARobotDataDto>(dataList -> {
            for (XARobotDataDto data : dataList) {
                robotDataList.add(data);
            }
        })).sheet().doRead();

        for (XARobotDataDto robotData : robotDataList) {
            String originPhone = robotData.getPhone().replace("_x0001_", "\u0001");
            robotData.setPhone(originPhone);
            robotData.setAddress("");
            robotData.setCompanyName("");
        }

        // 输出文档
        //EasyExcel.write(outFileName, RobotData.class).sheet().doWrite(robotDataList);
        System.out.println(JSON.toJSONString(robotDataList));
    }
}
