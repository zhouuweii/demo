package com.zw.admin.framework.common.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 统计代码行数
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Component
public class CountCodeLineUtil {

    List<File> list = new ArrayList<File>();
    int linenumber = 0;

    FileReader fr = null;
    BufferedReader br = null;

    public String counter(String path) {
        System.out.println("项目路径:" + path);
        File file = new File(path);
        File files[] = null;
        files = file.listFiles();
        addFile(files);
        isDirectory(files);
        readLinePerFile();
        System.out.println("Totle:" + linenumber + "行");
        return "Totle:" + linenumber + "行";
    }

    /**
     * 判断是否是目录
     * @param files
     * @return void
     **/
    private void isDirectory(File[] files) {
        for (File s : files) {
            if (s.isDirectory()) {
                File file[] = s.listFiles();
                addFile(file);
                isDirectory(file);
                continue;
            }
        }
    }

    /**
     * 将src下所有文件组织成list
     * @param file
     * @return void
     **/
    private void addFile(File file[]) {
        for (int index = 0; index < file.length; index++) {
            list.add(file[index]);
            // System.out.println(list.size());
        }
    }

    /**
     * 读取非空白行
     * @param
     * @return void
     **/
    private void readLinePerFile() {
        try {
            for (File s : list) {
                int yuan = linenumber;
                if (s.isDirectory()) {
                    continue;
                }
                if (s.getName().lastIndexOf(".java") > 0 || s.getName().lastIndexOf(".jsp") > 0 || s.getName().lastIndexOf(".js") > 0 || s.getName().lastIndexOf(".css") > 0) {

                } else {
                    continue;
                }
                fr = new FileReader(s);
                br = new BufferedReader(fr);
                String i = "";
                while ((i = br.readLine()) != null) {
                    if (isBlankLine(i))
                        linenumber++;
                }
                System.out.print(s.getName());
                System.out.println("\t\t有" + (linenumber - yuan) + "行");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 是否是空行
     * @param i
     * @return boolean
     **/
    private boolean isBlankLine(String i) {
        if (i.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
