package com.iscas.service.tool;

/**
 * @author : lvxianjin
 * @Date: 2020/5/5 11:41
 * @Description:
 */
public class STClient {
    public boolean startST(String inPath){
        Runtime sc =Runtime.getRuntime();
        Process ps = null;
        String shell = "/sh/ST.sh "+inPath+" "+inPath;
        String[] cmd = {"/bin/sh","-c",shell};
        try {
            ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
