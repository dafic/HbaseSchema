package com.ioe.imageclustering.hbaseschema;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(HBaseSchema.createImageDatabase()){
            System.out.println("ImageDatabase successfully created.\n\n");
        }
        if(HBaseSchema.createClusterDatabase()){
            System.out.println("ClusterDatabase successfully created.\n\n");
        }
    }
}
