package com.iscas.service.sample.service;


/**
 * @author : lvxianjin
 * @Date: 2020/4/27 10:24
 * @Description:
 */

public interface ProduceService {
    /**
     * 1.将LS文件同步更新到公共区域，只允许读
     * 2.样本制造的输入文件有：
     * （1）潮流断面数据
     * （2）故障集
     * （3）输出结果格式文件
     * 3.样本制造的输出文件：
     * （1）对应的时间
     * （2）暂稳计算结果
     * （3）对应的故障
     */
    public void createSample();
}
