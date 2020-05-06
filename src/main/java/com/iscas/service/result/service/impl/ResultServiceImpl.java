package com.iscas.service.result.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iscas.service.result.service.ResultService;
import com.iscas.service.tool.HbaseClient;
import org.apache.hadoop.mapreduce.ID;

import java.util.List;
import java.util.Map;

public class ResultServiceImpl implements ResultService {
    HbaseClient hb=new HbaseClient();
    long totalMilliSeconds = System.currentTimeMillis();
    @Override
    public boolean saveWeakInfo(String value) {
        /**
         * 1.将json字符串转成数组
         * 2.遍历数组，将id，WeakName，Index_Before，Index_After解析出来 保存到hbase中
         * 注：rowkey为当前时间（毫秒）+“-”+id
         * */
        Object object = JSONArray.parse(value);
        List<Map<String,String>> list_w = (List<Map<String, String>>)object;
        for (int i = 0; i < list_w.size(); i++) {
            Map<String,String> map = list_w.get(i);
            String id_w =map.get("id");
            String Rowkey_w=totalMilliSeconds+"_"+id_w;
            String WeakName_w=map.get("WeakName");
            String Index_Before_w=map.get("Index_Before");
            String Index_After_w=map.get("Index_After");
            hb.writeToHBase("weakInfo",Rowkey_w,"cf1","WeakName",WeakName_w);
            hb.writeToHBase("weakInfo",Rowkey_w,"cf1","Index_Before",Index_Before_w);
            hb.writeToHBase("weakInfo",Rowkey_w,"cf1","Index_After",Index_After_w);
        }
        return false;
    }

    /**
     * 1.将json字符串转成数组
     * 2.遍历数组，将id，DeviceName，IIndex解析出来 保存到hbase中
     * 注：rowkey为当前时间（毫秒）+“-”+id
     * */
    @Override
    public boolean savePolicyInfo(String value) {
        Object object = JSONArray.parse(value);
        List<Map<String,String>> list_p = (List<Map<String, String>>)object;
        for (int i = 0; i < list_p.size(); i++) {
            Map<String,String> map = list_p.get(i);
            String id_p =map.get("id");
            String Rowkey_p=totalMilliSeconds+"_"+id_p;
            String DeviceName_p=map.get("DeviceName");
            String IIndex_p=map.get("IIndex");
            hb.writeToHBase("policyInfo",Rowkey_p,"cf1","DeviceName",DeviceName_p);
            hb.writeToHBase("policyInfo",Rowkey_p,"cf1","IIndex",IIndex_p);
        }
        return false;
    }

    /**
     * 1.将json字符串转成数组
     * 2.遍历数组，将percent，hz解析出来 保存到hbase中
     * 注：rowkey为当前时间（毫秒）
     * */
    @Override
    public boolean saveSysModelInfo(String value) {
        Object object = JSONArray.parse(value);
        List<Map<String,String>> list_m = (List<Map<String, String>>)object;
        for (int i = 0; i < list_m.size(); i++) {
            Map<String,String> map = list_m.get(i);
            String Rowkey_s=String.valueOf(totalMilliSeconds);
            String percent_s=map.get("percent");
            String hz_s=map.get("hz");
            hb.writeToHBase("SysModelInfo",Rowkey_s,"cf1","percent",percent_s);
            hb.writeToHBase("SysModelInfo",Rowkey_s,"cf1","hz",hz_s);
        }
        return false;
    }

    /**
     * 1.将json字符串转成数组
     * 2.遍历数组，将id，DeviceName，Value解析出来 保存到hbase中
     * 注：rowkey为当前时间（毫秒）+“-”+id
     * */
    @Override
    public boolean saveModalInfo(String value) {
        Object object = JSONArray.parse(value);
        List<Map<String,String>> list_m = (List<Map<String, String>>)object;
        for (int i = 0; i < list_m.size(); i++) {
            Map<String, String> map = list_m.get(i);
            String id_m = map.get("id");
            String Rowkey_m = totalMilliSeconds + "_" + id_m;
            String DeviceName_m = map.get("DeviceName");
            String Value_m = map.get("Value");
            hb.writeToHBase("modalInfo", Rowkey_m, "cf1", "DeviceName", DeviceName_m);
            hb.writeToHBase("modalInfo", Rowkey_m, "cf1", "Value", Value_m);
        }
        return false;
    }

        /**
         * 1.将json字符串转成数组
         * 2.遍历数组，将ErrorName，Time解析出来 保存到hbase中
         * 注：rowkey为当前时间（毫秒）
         * */
    @Override
    public boolean saveCctInfo(String value) {
            Object object = JSONArray.parse(value);
            List<Map<String,String>> list_m = (List<Map<String, String>>)object;
            for (int i = 0; i < list_m.size(); i++) {
                Map<String, String> map = list_m.get(i);
                String Rowkey_c = String.valueOf(totalMilliSeconds);
                String ErrorName_m = map.get("ErrorName");
                String Time_m = map.get("Time");
                hb.writeToHBase("cctInfo", Rowkey_c, "cf1", "ErrorName", ErrorName_m);
                hb.writeToHBase("cctInfo", Rowkey_c, "cf1", "Time", Time_m);
            }
        return false;
    }

    /**
     * 1.将json字符串转成数组
     * 2.遍历数组，将ErrorName，Rate解析出来 保存到hbase中
     * 注：rowkey为当前时间（毫秒）
     * */
    @Override
    public boolean saveErrorRateInfo(String value) {
        Object object = JSONArray.parse(value);
        List<Map<String,String>> list_m = (List<Map<String, String>>)object;
        for (int i = 0; i < list_m.size(); i++) {
            Map<String, String> map = list_m.get(i);
            String Rowkey_e = String.valueOf(totalMilliSeconds);
            String ErrorName_e = map.get("ErrorName");
            String Rate_e = map.get("Rate");
            hb.writeToHBase("errorRateInfo", Rowkey_e, "cf1", "ErrorName", ErrorName_e);
            hb.writeToHBase("errorRateInfo", Rowkey_e, "cf1", "Rate", Rate_e);
        }
        return false;
    }
}
