import com.cshuig.model.Database;
import com.cshuig.model.InputInfo;
import com.cshuig.model.Table;
import com.cshuig.model.TableCheckbox;
import com.cshuig.panel.ViewContentPanel;
import com.cshuig.utils.DbUtils;
import com.cshuig.utils.VelocityUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by cshuig on 15/4/26.
 */
public class App extends JFrame {

    private static final long serialVersionUID = -8667920279388305018L;
    private final ViewContentPanel viewContentPanel;
    private Database database = null;
    private final String[] dbDrivers = {"com.mysql.jdbc.Driver", "oracle.jdbc.dirver.OracleDriver"};
    private final String[] tableColumnsLabel = {"选择", "表名"};

    public App() {
        this.viewContentPanel = ViewContentPanel.getInstance();
        initView();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    UIManager.put("RootPane.setupButtonVisible", false);
                    BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.translucencyAppleLike;
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    App app = new App();
                    app.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        setTitle("代码生成小工具");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setBounds(100, 100, 700, 500);
        viewContentPanel.setMainPanel(new JPanel());
        viewContentPanel.getMainPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(viewContentPanel.getMainPanel());
        viewContentPanel.getMainPanel().setLayout(null);

        viewContentPanel.setjComboBoxDbDrivers(new JComboBox(dbDrivers));
        viewContentPanel.setjTextFieldUserName(new JTextField());
        viewContentPanel.setjTextFieldPassword(new JTextField());
        viewContentPanel.setjTextFieldUrl(new JTextField());
        viewContentPanel.setjTextFieldPackageName(new JTextField());
        viewContentPanel.setjTextFieldAuthor(new JTextField());
        viewContentPanel.setjTextFieldOutDir(new JTextField());

        TableCheckbox tableCheckbox = new TableCheckbox(new Objects[][] {}, tableColumnsLabel);
        viewContentPanel.setTableCheckbox(tableCheckbox);
        viewContentPanel.setjTableTableList(new JTable(tableCheckbox));

        viewContentPanel.setjButtonTestConnect(this.createTestJButton());
        viewContentPanel.setjButtonQueryData(this.createQueryJButton());
        viewContentPanel.setjButtonGenerate(this.createGenerateJButton());

        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JButton createTestJButton() {
        JButton jbutton = new JButton("测试连接");
        jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validText()) {
                    Database database = getInputData();
                    if (null == DbUtils.initConnection(database)) {
                        JOptionPane.showConfirmDialog(null, "数据库链接失败", "信息", JOptionPane.DEFAULT_OPTION);
                    } else {
                        JOptionPane.showConfirmDialog(null, "数据库链接成功", "信息", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        });
        return jbutton;
    }

    private JButton createQueryJButton() {
        JButton jbutton = new JButton("查询表");
        jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validText()) {
                    database = getInputData();
                    if (null == DbUtils.initConnection(database)) {
                        JOptionPane.showConfirmDialog(null, "数据库链接失败", "信息", JOptionPane.DEFAULT_OPTION);
                        return;
                    }
                    try {
                        DbUtils.getDBInfo(database);
                        int rowCount = viewContentPanel.getTableCheckbox().getRowCount();
                        int dcount = 0;
                        while (dcount < rowCount) {
                            viewContentPanel.getTableCheckbox().removeRow(0);
                            dcount++;
                        }
                        for (int i = 0; i < database.getTableList().size(); i++) {
                            Table table = database.getTableList().get(i);
                            viewContentPanel.getTableCheckbox().addRow(new Object[]{false, table.getTableName()});
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        return jbutton;
    }

    private JButton createGenerateJButton() {
        JButton jbutton = new JButton("代码生成");
        jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validText()) {
                    if (null == database) return;
                    if (!new File(database.getInputInfo().getOurDir().toString()).exists()) {
                        JOptionPane.showConfirmDialog(null, "目录不存在", "信息", JOptionPane.DEFAULT_OPTION);
                        return;
                    }
                    int rowCount = viewContentPanel.getTableCheckbox().getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        String isSelect = viewContentPanel.getjTableTableList().getValueAt(i, 0).toString();
                        if ("true".equals(isSelect)) {
                            database.getInputInfo().getSelectTableList().add(viewContentPanel.getjTableTableList().getValueAt(i, 1).toString());
                        }
                    }
                    if (database.getInputInfo().getSelectTableList().size() <= 0) {
                        JOptionPane.showConfirmDialog(null, "请勾选要生成的表", "信息", JOptionPane.DEFAULT_OPTION);
                        return ;
                    }
                    // generate code start
                    VelocityUtils.generateCode(database);
                }
            }
        });
        return jbutton;
    }

    public boolean validText() {
        if (isEmpty(viewContentPanel.getjTextFieldUserName())) {
            JOptionPane.showMessageDialog(viewContentPanel.getjTextFieldUserName(), "用户名不能为空");
            return false;
        }
        if (isEmpty(viewContentPanel.getjTextFieldUrl())) {
            JOptionPane.showMessageDialog(viewContentPanel.getjTextFieldUrl(), "URL不能为空");
            return false;
        }
        if (isEmpty(viewContentPanel.getjTextFieldPackageName())) {
            JOptionPane.showMessageDialog(viewContentPanel.getjTextFieldPackageName(), "包名不能为空");
            return false;
        }
        if (isEmpty(viewContentPanel.getjTextFieldOutDir())) {
            JOptionPane.showMessageDialog(viewContentPanel.getjTextFieldOutDir(), "输出目录不能为空");
            return false;
        }
        return true;
    }

    public boolean isEmpty(JTextComponent component){
        String content = component.getText();
        return null == content || content.equals("");
    }

    public Database getInputData() {
        Database database = new Database();
        InputInfo inputInfo = new InputInfo();
        inputInfo.setDbDriver(viewContentPanel.getjComboBoxDbDrivers().getSelectedItem().toString());
        inputInfo.setUrl(viewContentPanel.getjTextFieldUrl().getText());
        inputInfo.setUserName(viewContentPanel.getjTextFieldUserName().getText());
        inputInfo.setPassword(viewContentPanel.getjTextFieldPassword().getText());
        inputInfo.setOurDir(viewContentPanel.getjTextFieldOutDir().getText());
        inputInfo.setPackageName(viewContentPanel.getjTextFieldPackageName().getText());
        inputInfo.setAuthor(viewContentPanel.getjTextFieldAuthor().getText());
        database.setInputInfo(inputInfo);
        return database;
    }

}
