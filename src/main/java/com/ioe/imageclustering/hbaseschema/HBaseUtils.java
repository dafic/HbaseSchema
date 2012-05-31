/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioe.imageclustering.hbaseschema;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author phoenix
 */
public class HBaseUtils {

    public static boolean createHBaseTable(String tablename, List<String> colFamily) {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.master", "127.0.0.1:60000");
        try {
            HBaseAdmin hbase = new HBaseAdmin(conf);
            HTableDescriptor desc = new HTableDescriptor(tablename);
            for (String cf : colFamily) {
                HColumnDescriptor hcd = new HColumnDescriptor(cf.getBytes());
                desc.addFamily(hcd);
            }
            hbase.createTable(desc);
        } catch (Exception e) {
            System.out.println("error creating table:" + tablename + "\n" + e);
            return false;
        }
        return true;
    }

    public static void readTable(HTable table, String key) throws IOException {
        Get get = new Get(key.getBytes());
        List<KeyValue> values = table.get(get).list();
        for (KeyValue kv : values) {
            System.out.println("row" + Bytes.toString(kv.getRow()));
            System.out.println("col" + Bytes.toString(kv.getFamily()) + ":" + Bytes.toString(kv.getQualifier()));
            System.out.println("value" + Bytes.toString(kv.getValue()));
        }
    }

    public static void insert(String tableName, String rowKey, String colFamily, List<String> colQualifier, List<String> values) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.master", "127.0.0.1:60000");
        HTable table = new HTable(conf, tableName);
        Put put = new Put(rowKey.getBytes());
        int qualifierSize = colQualifier.size();
        if (qualifierSize != values.size()) {
            System.out.println("Error : No. of column qualifier and values do not match.\n");
            return;
        }
        for (int i = 0; i < qualifierSize; i++) {
            put.add(colFamily.getBytes(), colQualifier.get(i).getBytes(), values.get(i).getBytes());
        }
        table.put(put);
        readTable(table, rowKey);
    }
}
