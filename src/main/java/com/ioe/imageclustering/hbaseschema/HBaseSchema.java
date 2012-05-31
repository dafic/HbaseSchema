/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioe.imageclustering.hbaseschema;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phoenix
 */
public class HBaseSchema {    
    public static boolean createImageDatabase(){
        String tableName="ImageDatabase";
        List<String> colFamily=new ArrayList<String>();
        colFamily.add("Image");
        colFamily.add("ImageInfo");
        
        return(HBaseUtils.createHBaseTable(tableName, colFamily));
    }   
    public static boolean createClusterDatabase(){
        String tableName="ClusterDatabase";
        List<String> colFamily=new ArrayList<String>();
        colFamily.add("ImageList");
        colFamily.add("ClusterInfo");
        
        return(HBaseUtils.createHBaseTable(tableName, colFamily));
    }   
    public static boolean createTableTest(){
        String tableName="Test";
        List<String> colFamily=new ArrayList<String>();
        colFamily.add("ColF1");
        colFamily.add("ColF2");
        
        return(HBaseUtils.createHBaseTable(tableName, colFamily));
    }
}
