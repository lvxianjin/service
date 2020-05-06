package com.iscas.service.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileClient {

    public int writeResult(List<String> content, String filePath,String characterName) {
        int rest = 0;
        File file = new File(filePath);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), characterName));
            for (int i = 0; i < content.size(); i++) {
                bw.write(content.get(i) + "\t\n");
            }
            bw.close();
            rest = 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return rest;
    }

    public List<String> getContent(String filePath,String characterName) {
        List<String> content = new ArrayList<String>();
        BufferedReader br = null;
        File file = new File(filePath);
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),characterName));
            String str;
            while ((str = br.readLine()) != null) {
                content.add(str);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    /**
     *
     * 功能描述: 模糊匹配文件
     *
     * @param:
     * @return: 符合条件的文件名
     * @auther: lvxianjin
     * @date: 2020/5/3 10:47
     */
    public List<String> getPath(String path,String str){
        List<String> fileNames = new ArrayList<>();
        File file = new File(path);
        File[] FileList = file.listFiles();
        for (int i = 0; i < FileList.length; i++) {
            if(FileList[i].getName().contains(str)){
                fileNames.add(FileList[i].getName());
            }
        }
        return fileNames;
    }
}
