package com.TomDog.Wkhtml2PDFOrImage;

import com.alibaba.fastjson.annotation.JSONField;

public class PathObject extends Object {

    @JSONField(name="SaveFilePath")
    private String saveFilePath;
    @JSONField(name = "SaveTime")
    private String saveTime;


    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return saveFilePath +"  "+ saveTime ;
    }
}
