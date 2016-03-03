package com.cshuig.panel;

import com.cshuig.model.TableCheckbox;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by cshuig on 15/4/26.
 */
public class ViewContentPanel {

    private JPanel mainPanel;
    private JComboBox jComboBoxDbDrivers;           //数据库下拉选择框
    private JTextField jTextFieldUserName;          //用户名
    private JTextField jTextFieldPassword;          //密码 可以为空
    private JTextField jTextFieldUrl;               //数据库url
    private JTextField jTextFieldPackageName;       //生成的报名
    private JTextField jTextFieldOutDir;            //代码生成的目录

    private JTable jTableTableList;                 //数据库的所有表
    private TableCheckbox tableCheckbox;

    private JButton jButtonTestConnect;             //测试链接
    private JButton jButtonQueryData;               //查询表
    private JButton jButtonGenerate;                //生成

    private JTextField jTextFieldAuthor;            //作者

    private final int labelWidth = 90;
    private final int labelHeight = 15;
    private final int textWidth = 250;
    private final int textHeight = 25;

    public static ViewContentPanel getInstance() {
        return new ViewContentPanel();
    }

    public void addComppnent(Component component) {
        this.getMainPanel().add(component);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setjComboBoxDbDrivers(JComboBox jComboBoxDbDrivers) {
        this.jComboBoxDbDrivers = jComboBoxDbDrivers;
        this.jComboBoxDbDrivers.setBounds(110, 30, textWidth, textHeight);    //设置元素的边界值
        this.addComppnent(jComboBoxDbDrivers);

        JLabel jLabel = new JLabel("数据库驱动:");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setBounds(10, 35, labelWidth, labelHeight);
        this.addComppnent(jLabel);
    }

    public void setjTextFieldUserName(JTextField jTextFieldUserName) {
        this.jTextFieldUserName = jTextFieldUserName;
        this.jTextFieldUserName.setText("un_dev");
        this.jTextFieldUserName.setBounds(110, 70, textWidth, textHeight);
        this.addComppnent(jTextFieldUserName);

        JLabel jLabel = new JLabel("用户名:");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setBounds(10, 75, labelWidth, labelHeight);
        this.addComppnent(jLabel);
    }

    public void setjTextFieldPassword(JTextField jTextFieldPassword) {
        this.jTextFieldPassword = jTextFieldPassword;
        this.jTextFieldPassword.setText("123AsdF");
        this.jTextFieldPassword.setBounds(110, 110, textWidth, textHeight);
        this.addComppnent(jTextFieldPassword);

        JLabel jLabel = new JLabel("密 码:");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setBounds(10, 115, labelWidth, labelHeight);
        this.addComppnent(jLabel);
    }

    public void setjTextFieldUrl(JTextField jTextFieldUrl) {
        this.jTextFieldUrl = jTextFieldUrl;
        this.jTextFieldUrl.setText("jdbc:mysql://");
        this.jTextFieldUrl.setBounds(110, 150, textWidth, textHeight);
        this.addComppnent(jTextFieldUrl);

        JLabel jLabel = new JLabel("数据库URL:");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setBounds(10, 155, labelWidth, labelHeight);
        this.addComppnent(jLabel);
    }

    public void setjTextFieldPackageName(JTextField jTextFieldPackageName) {
        this.jTextFieldPackageName = jTextFieldPackageName;
        this.jTextFieldPackageName.setText("com.cshuig.packagename");
        this.jTextFieldPackageName.setBounds(110, 190, textWidth, textHeight);
        this.addComppnent(this.getjTextFieldPackageName());

        JLabel jLabel = new JLabel("生成包名字:");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setBounds(10, 195, labelWidth, labelHeight);
        this.addComppnent(jLabel);
    }

    public void setjTextFieldOutDir(JTextField jTextFieldOutDir) {
        this.jTextFieldOutDir = jTextFieldOutDir;
        this.jTextFieldOutDir.setText("c:\\db");
        this.jTextFieldOutDir.setBounds(110, 230, textWidth, textHeight);
        this.addComppnent(jTextFieldOutDir);

        JLabel jLabel = new JLabel("输出目录:");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setBounds(10, 235, labelWidth, labelHeight);
        this.addComppnent(jLabel);
    }

    public void setjTextFieldAuthor(JTextField jTextFieldAuthor) {
        this.jTextFieldAuthor = jTextFieldAuthor;
        this.jTextFieldAuthor.setText("hogan.lin");
        this.jTextFieldAuthor.setBounds(110, 270, textWidth, textHeight);
        this.addComppnent(jTextFieldAuthor);

        JLabel jLabel = new JLabel("作者:");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setBounds(10, 275, labelWidth, labelHeight);
        this.addComppnent(jLabel);
    }

    public void setjTableTableList(final JTable jTableTableList) {
        this.jTableTableList = jTableTableList;
        JScrollPane jScrollPane = new JScrollPane(this.jTableTableList);
        jScrollPane.setBounds(385, 30, 240, 370);
        this.addComppnent(jScrollPane);
        jTableTableList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jTableTableList.getSelectedColumn() == 0) return;
            }
        });
    }

    public void setTableCheckbox(TableCheckbox tableCheckbox) {
        this.tableCheckbox = tableCheckbox;
    }

    public void setjButtonTestConnect(JButton jButtonTestConnect) {
        this.jButtonTestConnect = jButtonTestConnect;
        this.jButtonTestConnect.setUI(new BEButtonUI().setNormalColor(NormalColor.green));
        this.jButtonTestConnect.setBounds(60, 370, labelWidth, labelHeight);
        this.jButtonTestConnect.setSize(80, 30);
        this.addComppnent(jButtonTestConnect);

    }

    public void setjButtonQueryData(JButton jButtonQueryData) {
        this.jButtonQueryData = jButtonQueryData;
        this.jButtonQueryData.setUI(new BEButtonUI().setNormalColor(NormalColor.blue));
        this.jButtonQueryData.setBounds(160, 370, labelWidth, labelHeight);
        this.jButtonQueryData.setSize(80, 30);
        this.addComppnent(jButtonQueryData);
    }

    public void setjButtonGenerate(JButton jButtonGenerate) {
        this.jButtonGenerate = jButtonGenerate;
        this.jButtonGenerate.setUI(new BEButtonUI().setNormalColor(NormalColor.lightBlue));
        this.jButtonGenerate.setBounds(260, 370, labelWidth, labelHeight);
        this.jButtonGenerate.setSize(80, 30);
        this.addComppnent(jButtonGenerate);
    }


    public JComboBox getjComboBoxDbDrivers() {
        return jComboBoxDbDrivers;
    }

    public JTextField getjTextFieldUserName() {
        return jTextFieldUserName;
    }

    public JTextField getjTextFieldPassword() {
        return jTextFieldPassword;
    }

    public JTextField getjTextFieldUrl() {
        return jTextFieldUrl;
    }

    public JTextField getjTextFieldPackageName() {
        return jTextFieldPackageName;
    }

    public JTextField getjTextFieldOutDir() {
        return jTextFieldOutDir;
    }

    public JTextField getjTextFieldAuthor() {
        return jTextFieldAuthor;
    }

    public JTable getjTableTableList() {
        return jTableTableList;
    }

    public TableCheckbox getTableCheckbox() {
        return tableCheckbox;
    }
}
