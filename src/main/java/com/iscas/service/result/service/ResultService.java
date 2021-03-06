package com.iscas.service.result.service;

import java.util.List;
import java.util.Map;

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
     /************************************************************************************/

 /**
  *
  * 功能描述: 读取薄弱节点信息
  * 1. 创建HbaseClient对象
  * 2. 调用前缀过滤器，传入两个参数，（1）表名，（2）需要查询的时间，得到满足条件的RowKey
  * 3. 遍历满足条件的RowKey，获取相应的值

  */
 List<Map<String,String>> getWeakInfo(String totalMilliSeconds);
 /**
  *
  * 功能描述: 读取调控策略信息
  *
  * @param: value json串，里面包含 DeviceName 设备名称，Index 调控量
  * @return:
  * @auther: lvxianjin
  * @date: 2020/5/6 9:14
  */
 List<Map<String,String>> getPolicyInfo(String totalMilliSeconds);
 /**
  *
  * 功能描述: 读取模式信息
  *
  * @param: value json串,里面包含 percent 阻尼比，hz 振荡频率
  * @return:
  * @auther: lvxianjin
  * @date: 2020/5/6 9:14
  */
 Map<String,String> getSysModelInfo(String totalMilliSeconds);
 /**
  *
  * 功能描述: 读取模态信息
  *
  * @param: value json串，里面包含 DeviceName 设备名称 ，Value 模态值
  * @return:
  * @auther: lvxianjin
  * @date: 2020/5/6 9:14
  */
 List<Map<String,String>> getModalInfo(String totalMilliSeconds);
 /**
  *
  * 功能描述:读取cct信息
  *
  * @param: value json串，里面包含 ErrorName 故障名称，Time 切除时间
  * @return:
  * @auther: lvxianjin
  * @date: 2020/5/6 9:20
  */
 Map<String,String> getCctInfo(String totalMilliSeconds);
 /**
  *
  * 功能描述:读取预想故障下暂态稳定分析
  *
  * @param totalMilliSeconds json串，里面包含 ErrorName 故障名称，Rate 稳定概率
  * @return:
  * @auther: lvxianjin
  * @date: 2020/5/6 9:20
  */
 Map<String,String> getErrorRateInfo(String totalMilliSeconds);
}
