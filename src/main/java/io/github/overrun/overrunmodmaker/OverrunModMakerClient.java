package io.github.overrun.overrunmodmaker;

import io.github.overrun.overrunmodmaker.gui.LanguageGUI;
import io.github.overrun.overrunmodmaker.project.Project;
import io.github.overrun.overrunmodmaker.project.ProjectTableModel;
import io.github.overrun.overrunmodmaker.project.TableImageCell;
import io.github.overrun.overrunmodmaker.util.Language;
import io.github.overrun.overrunmodmaker.util.WebUrl;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class OverrunModMakerClient {

    private static JFrame FRAME;   //窗口框架

    // 配置文件:
    public static int LanguageInt = 0;
    public static String LanguageString = "zh_cn";


    // LanguageGUI

    // 启动项目面板--------------------------------------------------
    private JPanel ProjectFormPanel;            //项目表单面板
    private JPanel ProjectButtonPanel;          //项目按钮面板
    private JPanel ProjectButtonPanel1;         //项目按钮面板 1

    private static JButton NewProjectButton;           //新建项目按钮
    private static JButton OpenImportProjectButton;    //打开导入项目按钮
    private static JButton SponsorButton;              //赞助商按钮
    private static JButton LogButton;                  //日志按钮
    private static JButton MultiLingualButton;         //多语言按钮
    private static JButton SetupButton;                //设置按钮

    private static JTable jTable;

    private TableColumn tc1;
    private JScrollPane scrollPane;             //滚动窗格
    //--------------------------------------------------

    private static java.util.List<Project> getProject() {
        List<Project> users = new ArrayList<Project>();
        for (int i = 0; i < 10; i++) {
            Project apx = new Project();
            apx.setProjectNum(String.valueOf(i));


            // ImageIO.read(Images.class.getResourceAsStream(imageName));
            // BufferedImage

            apx.setProjectLogo("texture/48/forge.png");


            //new Image(MangoModMakerClient.class.getResourceAsStream("/Resources/48/forge.png")));


            //apx.setProjectLogo("Resources/48/forge.png");
            apx.setProjectPath("地址");
            apx.setProjectName("<html>" + "Forge MOD Project Name " + i + "<br/>" + "E:\\Users" + " <html/>");
            users.add(apx);
        }
        return users;
    }

    // private StartProjectPanelGUI a1 = new StartProjectPanelGUI();

    public void setLanguage(String lang) {
        Language.init();
        Language.currentLang = lang;
        LanguageString = lang;
    }

    public static void setName(String Name) {
        FRAME.setTitle(Name);
    }


    public void CreateWindow(String name, int width, int height) {
        setName(name);
        setSize(width, height);
        FRAME.setLocationRelativeTo(null);
        FRAME.setVisible(true);
    }


    public static void UpdateLanguageInit() {
        setName(Language.getTranslationName("title.name") + " v" + OverrunModMaker.VERSION);

        NewProjectButton.setText(Language.getTranslationName("button.new_project"));
        OpenImportProjectButton.setText(Language.getTranslationName("button.open_import_project"));
        SponsorButton.setText(Language.getTranslationName("button.sponsor"));
        LogButton.setText(Language.getTranslationName("button.update_log"));
        MultiLingualButton.setText(Language.getTranslationName("button.multi_lingual"));
        SetupButton.setText(Language.getTranslationName("button.setup"));
    }


    public void setSize(int width, int height) {
        FRAME.setSize(width, height);
        FRAME.setMinimumSize(new Dimension(width, height));
    }


    public void init() {

        // 项目表格---------------------------------------------------------------


        ProjectFormPanel = new JPanel(new BorderLayout());

        jTable = new JTable(new ProjectTableModel(getProject()));
        tc1 = jTable.getTableHeader().getColumnModel().getColumn(0);
        tc1.setMaxWidth(64);
        tc1.setPreferredWidth(64);
        tc1.setWidth(64);
        tc1.setMinWidth(64);
        jTable.setRowHeight(64);
        jTable.getColumnModel().getColumn(0).setCellRenderer(new TableImageCell());
        scrollPane = new JScrollPane(jTable);
        ProjectFormPanel.add(scrollPane);

        //---------------------------------------------------------------

        // StartProjectPanelGUI


        // 项目按钮---------------------------------------------------------------
        ProjectButtonPanel = new JPanel();
        NewProjectButton = new JButton();
        OpenImportProjectButton = new JButton();
        SponsorButton = new JButton();
        LogButton = new JButton();
        MultiLingualButton = new JButton();
        SetupButton = new JButton();

        NewProjectButton.setPreferredSize(new Dimension(200, 50));
        OpenImportProjectButton.setPreferredSize(new Dimension(200, 50));
        SponsorButton.setPreferredSize(new Dimension(200, 50));
        LogButton.setPreferredSize(new Dimension(200, 50));


        // 语言和设置按钮---------------------------------------------------------------
        ProjectButtonPanel1 = new JPanel(new BorderLayout());

        ProjectButtonPanel1.setPreferredSize(new Dimension(200, 50));
        MultiLingualButton.setPreferredSize(new Dimension(98, 50));
        SetupButton.setPreferredSize(new Dimension(98, 50));

        ProjectButtonPanel1.add(MultiLingualButton, BorderLayout.WEST);
        ProjectButtonPanel1.add(SetupButton, BorderLayout.EAST);


        ProjectButtonPanel.add(NewProjectButton);
        ProjectButtonPanel.add(OpenImportProjectButton);
        ProjectButtonPanel.add(SponsorButton);
        ProjectButtonPanel.add(LogButton);

        ProjectButtonPanel.add(ProjectButtonPanel1);


        ProjectButtonPanel.setPreferredSize(new Dimension(210, 50));
        // frame.add(ProjectButtonPanel, BorderLayout.EAST);

        //---------------------------------------------------------------


        NewProjectButton.setText(Language.getTranslationName("button.new_project"));
        OpenImportProjectButton.setText(Language.getTranslationName("button.open_import_project"));
        SponsorButton.setText(Language.getTranslationName("button.sponsor"));
        LogButton.setText(Language.getTranslationName("button.update_log"));
        MultiLingualButton.setText(Language.getTranslationName("button.multi_lingual"));
        SetupButton.setText(Language.getTranslationName("button.setup"));


        FRAME.add(ProjectFormPanel, BorderLayout.CENTER);
        FRAME.add(ProjectButtonPanel, BorderLayout.EAST);


        MultiLingualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LanguageGUI dialog = new LanguageGUI();
                dialog.showLanguageDialog(FRAME, FRAME);
            }
        });


        SponsorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebUrl.OpenWebUrl(OverrunModMaker.DONATE_URL);
            }
        });


        LogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebUrl.OpenWebUrl(OverrunModMaker.UPDATE_LOG_URL);
            }
        });


        SetupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LanguageGUI lo = new LanguageGUI();
                JOptionPane.showMessageDialog(FRAME, "你好，世界", "信息...", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        FRAME.setVisible(true);
    }


    public OverrunModMakerClient() {
        //frame.setIconImages(getIcons());
        FRAME = new JFrame();
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // FRAME.setVisible(true);
        // 窗口关闭
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


}
