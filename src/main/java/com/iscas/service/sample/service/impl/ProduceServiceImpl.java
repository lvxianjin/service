package com.iscas.service.sample.service.impl;

import com.iscas.service.sample.service.ProduceService;
import com.iscas.service.tool.FileClient;
import com.iscas.service.tool.STClient;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;

/**
 * @author : lvxianjin
 * @Date: 2020/5/5 10:46
 * @Description:
 */
@Service
public class ProduceServiceImpl implements ProduceService {
    @Override
    public void createSample() {
        FileClient fileClient = new FileClient();
        /**
         *  输入文件根路径
         * */
        String rootPath = "/LF";
        /**
         *  输出文件根路径
         * */
        String resultPath = "/result/sample";
        String userName = "lv";
        STClient stClient = new STClient();
        //读取LS文件中的摘要信息
        List<String> pathList = fileClient.getContent(rootPath + "/result_index.txt", "utf-8");
        for (int i = 0; i < pathList.size(); i++) {
            List<String> errorList = fileClient.getPath(rootPath + pathList.get(i), "S11");
            for (int j = 0; j < errorList.size(); j++) {
                /**
                 * 1.依次读取故障集，转换成st.s11文件
                 * （1）故障集从0开始编码
                 * 2.带入输出结果格式
                 * 3.启动仿真计算
                 * 4.读取仿真结果，写入结果目录
                 **/
                //依次读取故障集的内容
                List<String> error = fileClient.getContent(rootPath + pathList.get(i) + "/"+errorList.get(j), "utf-8");
                //将故障内容写入
                fileClient.writeResult(error, rootPath + pathList.get(i) + "/ST.S11", "utf-8");
                //启动仿真计算
                boolean isOK = stClient.startST(rootPath + pathList.get(i));
                //读取结果，将故障集与仿真结果保存在结果目录
                if(isOK){
                    String res = resultPath+pathList.get(i)+"/"+userName+"/"+String.valueOf(j+1);
                    File file = new File(res);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    //将故障集带入结果目录下
                    fileClient.writeResult(error, res + "/ST.S11", "utf-8");
                    //将仿真结果文件带入结果目录下
                    List<String> result_str = fileClient.getContent(rootPath + pathList.get(i) + "/"+"STERR.LIS","utf-8");
                    fileClient.writeResult(result_str, res + "/STERR.LIS", "utf-8");
                    fileClient.writeResult(error, res + "/ST.S11", "utf-8");
                }
            }
        }
    }
}
