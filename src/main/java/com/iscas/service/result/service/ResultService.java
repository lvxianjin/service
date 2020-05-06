package com.iscas.service.result.service;

/**
 * @author : lvxianjin
 * @Date: 2020/5/5 17:24
 * @Description:
 */
public interface ResultService {
    /**
     *
     * 功能描述: 保存薄弱节点信息
     *
     * @param value json串，里面包含 id 薄弱节点序号 WeakName 薄弱节点名称
     * Index_Before 调控前裕度 Index_After 调控后裕度
     * @return:
     * @auther: lvxianjin
     * @date: 2020/5/6 9:14
     */
     boolean saveWeakInfo(String value);
     /**
      *
      * 功能描述: 保存调控策略信息
      *
      * @param: value json串，里面包含 DeviceName 设备名称，Index 调控量
      * @return:
      * @auther: lvxianjin
      * @date: 2020/5/6 9:14
      */
     boolean savePolicyInfo(String value);
     /**
      *
      * 功能描述: 保存模式信息
      *
      * @param: value json串,里面包含 percent 阻尼比，hz 振荡频率
      * @return:
      * @auther: lvxianjin
      * @date: 2020/5/6 9:14
      */
     boolean saveSysModelInfo(String value);
     /**
      *
      * 功能描述: 保存模态信息
      *
      * @param: value json串，里面包含 DeviceName 设备名称 ，Value 模态值
      * @return:
      * @auther: lvxianjin
      * @date: 2020/5/6 9:14
      */
     boolean saveModalInfo(String value);
     /**
      *
      * 功能描述:保存cct信息
      *
      * @param: value json串，里面包含 ErrorName 故障名称，Time 切除时间
      * @return:
      * @auther: lvxianjin
      * @date: 2020/5/6 9:20
      */
     boolean saveCctInfo(String value);
     /**
      *
      * 功能描述:保存预想故障下暂态稳定分析
      *
      * @param value json串，里面包含 ErrorName 故障名称，Rate 稳定概率
      * @return: 
      * @auther: lvxianjin
      * @date: 2020/5/6 9:20
      */
     boolean saveErrorRateInfo(String value);
}
