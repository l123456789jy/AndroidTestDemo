package com.example.administrator.myapplication.circledemo.utiles;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 作者：Administrator on 2016/3/19 12:28
 * 邮箱：906514731@qq.com
 */
public class AssetDatabaseOpenHelper {

    /**
     * 获取asset文件下的资源文件信息
     * @param fileName
     * @return
     */
    public static String getFromAssets(String fileName,Context context) {

        try {
            InputStreamReader inputReader = new InputStreamReader(
                    context.getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}