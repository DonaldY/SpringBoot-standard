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
    public void test() {
        String url = "http://clink2-sh-voice.oss-cn-shanghai.aliyuncs.com/28062023/record/8005745/8005745-20230628142914-18129911513-8002--record-medias_1-1687933754.62355.mp3?Expires=1688701356&OSSAccessKeyId=LTAI5t7BX4C51jMAKtyebBcJ&Signature=JmdZ10uThZwqsO6gPKGQDosEDvs%3D&response-content-disposition=attachment%3B%20filename%3D8005745-20230628142914-18129911513-8002--record-medias_1-1687933754.62355.mp3";
        System.out.println(url.replace("http", "https"));
    }

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
