package com.TomDog.Wkhtml2PDFOrImage;


import org.springframework.boot.system.ApplicationHome;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.util.Vector;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wkhtml2PDFOrImage extends JFrame{

    //日志
    private static final Logger logger = LoggerFactory.getLogger(Wkhtml2PDFOrImage.class);


    private JPanel panel;

    //等待转化的html
    private String html;
    private String savedFile;

    private Vector<HtmlObject> htmlRecords;
    private Vector<PathObject> saveFilesRecords;

    private JComboBox htmlHistory;
    private JComboBox saveFilesHistroy;

    private JRadioButton html2PDF;
    private JRadioButton html2Image;

    private JButton okBtn;
    private JButton canBtn;

    private JButton reserveBtn;
    private JButton browseBtn;

    //桌面路径
    String desktopPath;
    //jar包路径
    String jarPath;

//    private JTextField inputHtml;
//    private JTextField imputSavedFile;


    public Wkhtml2PDFOrImage(){

        init();
        loadHistroy();
        setLayout();

        this.setLayout(new GridBagLayout());
        this.add(panel,new GridBagConstraints(0, 0,1, 1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,0,5,0), 0, 0));

        this.setTitle("保存HTML到文件");
    }

    //初始化变量
    private void init(){
        this.panel = new JPanel();
        this.html = "";
        this.savedFile = "";
        this.htmlRecords = new Vector<HtmlObject>();
        this.saveFilesRecords = new Vector<PathObject>();
        this.htmlHistory = new JComboBox();
        this.htmlHistory.setEditable(true);
        this.htmlHistory.setName("HTML");
        this.saveFilesHistroy = new JComboBox();
        this.saveFilesHistroy.setEditable(true);
        this.htmlHistory.setName("保存");
        this.html2PDF = new JRadioButton("PDF");
        this.html2Image =  new JRadioButton("Image");;

        this.okBtn = new JButton("确定");
        this.canBtn = new JButton("取消");

        this.reserveBtn = new JButton("确定");
        this.browseBtn = new JButton("选择路径");

        desktopPath = new String();
//        this.inputHtml = new JTextField();
//        this.imputSavedFile =  new JTextField();;

    }


    /**
     * 创建布局
     */
    private void setLayout(){

        //保存选项
        JPanel convertType = new JPanel();

        ButtonGroup bg = new ButtonGroup();
        bg.add(html2PDF);
        bg.add(html2Image);
        html2PDF.setSelected(true);
        convertType.setLayout( new GridBagLayout());
        convertType.setBorder(new TitledBorder("保存文件类型"));
        convertType.add(html2PDF,new GridBagConstraints(0, 0,1, 1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,0), 0, 0));

        convertType.add(html2Image,new GridBagConstraints(1, 0,1, 1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,5,5), 0, 0));

        //转换和保存路径
        JPanel path = new JPanel();

        path.setLayout( new GridBagLayout());
        path.setBorder(new TitledBorder("HTML和保存文件路径"));
        path.add(htmlHistory,new GridBagConstraints(0, 0,5, 1,1,0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0), 0, 0));
        path.add(reserveBtn,new GridBagConstraints(5, 0,1, 1,0,0,
                GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,0,0,0), 0, 0));

        path.add(saveFilesHistroy,new GridBagConstraints(0, 1,5, 1,1,0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0), 0, 0));
        path.add(browseBtn,new GridBagConstraints(5, 1,1, 1,0,0,
                GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,0,0,0), 0, 0));

        //程序确定，取消按钮
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(okBtn,new GridBagConstraints(0, 0,1, 1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,0), 0, 0));

        buttonPanel.add(canBtn,new GridBagConstraints(1, 0,1, 1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,5,5), 0, 0));



        panel.setLayout(new GridBagLayout());

        panel.add(convertType,new GridBagConstraints(0, 0,1, 1,1,0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,5,0), 0, 0));

        panel.add(path,new GridBagConstraints(0, 1,1, 1,1,0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,0,5,0), 0, 0));
        panel.add(buttonPanel,new GridBagConstraints(0, 2,1, 1,1,0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,0,5,0), 0, 0));
        panel.add(new JPanel(),new GridBagConstraints(0, 3,1, 1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,5,0), 0, 0));


    }

    /**
     * 加载使用历史
     */
    private void loadHistroy(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory(); //这两行行是获取桌面路径的具体方法。

        desktopPath = com.getAbsolutePath();
        System.out.println(desktopPath);

        //默认路径
        String path =desktopPath + "html2Pdf.pdf";
        saveFilesHistroy.addItem(path);


        //获取当前路径
        ApplicationHome home = new ApplicationHome(getClass());
        File jarFile = home.getSource();
        jarPath = jarFile.getParent();



    }


    public static void main(String[] args) {

        Wkhtml2PDFOrImage convert = new Wkhtml2PDFOrImage();
        convert.setSize(600,250);
        convert.setLocation(700,380);
        convert.setVisible(true);

    }


}
