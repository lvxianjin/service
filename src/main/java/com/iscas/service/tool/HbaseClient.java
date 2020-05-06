package com.iscas.service.tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//插入、读取hbase数据
public class HbaseClient implements Serializable {
    private static Admin admin = null;
    private static Connection connection;
    private static Configuration configuration;
    public HbaseClient() {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","master,slave1,slave2");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 向HBase中插入数据
     * @param tableName 表名
     * @param rowkey 行键
     * @param colFamily 列簇
     * @param col 列名
     * @param val 值
     * @Version 1.0
     */
    public boolean writeToHBase(String tableName, String rowkey, String colFamily, String col, String val){
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(val));
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    /**
     *
     * 功能描述: 批量插入数据
     *
     * @param:
     * @return:
     * @auther: lvxianjin
     * @date: 2019/12/8 13:22
     */
    public void putList(String tableName,List<Map<String,String>> values){
        try {
            BufferedMutator table = connection.getBufferedMutator(TableName.valueOf(tableName));
            List<Mutation> mutations = new ArrayList<Mutation>();
            for (int i = 0; i <values.size() ; i++) {
                Put put = new Put(Bytes.toBytes(values.get(i).get("RowKey")));
                for (String column:values.get(0).keySet()
                     ) {
                    if(!column.equals("RowKey")){
                        put.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes(column), Bytes.toBytes(values.get(i).get(column)));
                    }
                }
                mutations.add(put);
            }
            table.mutate(mutations);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 向HBase读取数据
     * @param tableName 表名
     * @param rowkey 行键
     * @param colFamily 列簇
     * @param col 列名
     * */
    public String readFromHBase(String tableName, String rowkey, String colFamily, String col){
        String value = "";
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowkey));
            get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
            Result result = table.get(get);
            for  (Cell cell : result.rawCells()) {
                value = Bytes.toString(CellUtil.cloneValue(cell));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}