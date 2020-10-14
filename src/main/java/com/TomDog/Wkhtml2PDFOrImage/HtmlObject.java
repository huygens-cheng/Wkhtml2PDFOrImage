package com.TomDog.Wkhtml2PDFOrImage;

import com.alibaba.fastjson.annotation.JSONField;

public class HtmlObject extends Object{


    @JSONField(name="Html")
    private String html;
    @JSONField(name = "SaveTime")
    private String saveTime;


    public String getSaveFilePath() {
        return html;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.html = saveFilePath;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return html +"  "+ saveTime ;
    }
}
