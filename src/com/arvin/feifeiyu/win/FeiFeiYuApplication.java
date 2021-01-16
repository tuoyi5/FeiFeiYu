package com.arvin.feifeiyu.win;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.arvin.feifeiyu.win.action.MouseAction;
import com.arvin.feifeiyu.win.json.Application;
import com.arvin.feifeiyu.win.json.DefaultJson;
import com.arvin.feifeiyu.win.json.Onyx;
import com.arvin.feifeiyu.win.json.Platforms;
import com.arvin.feifeiyu.win.listener.CommandListener;
import com.arvin.feifeiyu.win.model.CommandLineProgram;
import com.arvin.feifeiyu.win.model.SelectedManager;
import com.arvin.feifeiyu.win.util.CommandUtils;
import com.arvin.feifeiyu.win.util.FileUtils;
import com.arvin.feifeiyu.win.util.ImageUtils;
import com.arvin.feifeiyu.win.util.StringUtils;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.event.MenuListener;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.eclipse.osgi.internal.loader.classpath.ClasspathEntry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

import javax.swing.event.MenuEvent;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

public class FeiFeiYuApplication {

	private JFrame jFrame;
	String command = null;
	CommandLineProgram commandLineProgram;

	JMenuItem jMenuItemOperation, jMenuItemSettings, jMenuItemPreSettings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeiFeiYuApplication window = new FeiFeiYuApplication();
					window.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FeiFeiYuApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame("飞飞鱼");
		jFrame.setBounds(100, 100, 1000, 565);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initBar();
		initSplitPane();
		initSplitPaneLeft();
		initSplitPaneRight();
		initOperationJPanel();
		initSettingsJPanel();
		initPreSettingsJPanel();

		updatePlatformList();
		updateDevices();

		initDefJson();
	}

	private void initDefJson() {
		DefaultJson defaultJson = DefaultJson.init();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(defaultJson.getPlatforms().get(0).getName());
		sBuilder.append(defaultJson.getApplication().toString());
		sBuilder.append(defaultJson.getApplication().getOnyx().toString());
	}

	public void initBar() {
		JMenuBar mb = new JMenuBar();
		JMenu newMenu = new JMenu("\u83DC\u5355"); // 实例一个菜单项);
		JMenu editor = new JMenu("\u7F16\u8F91");
		mb.add(newMenu); // 添加菜单项

		JMenuItem mntmNewMenuItem = new JMenuItem("\u6253\u5F00");
		newMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u5173\u95ED");
		newMenu.add(mntmNewMenuItem_1);

		mb.add(editor);

		jFrame.setJMenuBar(mb);
	}

	JSplitPane jSplitPaneLeft, jSplitPaneV;
	JPanel jPanelRiglt, jPanelLeft;
	JScrollPane scrollPane;

	public void initSplitPane() {
		jPanelLeft = new JPanel();
		jPanelLeft.setBackground(Color.pink);
		jPanelLeft.setOpaque(true);

		jPanelRiglt = new JPanel();
		jPanelRiglt.setBackground(Color.yellow);

		jSplitPaneV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, jPanelLeft, jPanelRiglt);
		jPanelRiglt.setLayout(new CardLayout(0, 0));
		jFrame.getContentPane().add(jSplitPaneV);

	}

	public void initSplitPaneLeft() {
		JToolBar toolBar;
		toolBar = new JToolBar();
		toolBar.setBackground(Color.PINK);
		toolBar.setFloatable(false);
		toolBar.setEnabled(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		jPanelLeft.add(toolBar);

		jMenuItemOperation = new JMenuItem("Operation");
		jMenuItemSettings = new JMenuItem("Settings");
		jMenuItemPreSettings = new JMenuItem("PreSettings");

		initMouseAction(jMenuItemOperation);
		initMouseAction(jMenuItemSettings);
		initMouseAction(jMenuItemPreSettings);

		toolBar.add(jMenuItemOperation);
		toolBar.add(jMenuItemSettings);
		toolBar.add(jMenuItemPreSettings);
	}

	CardLayout cardLayout;
	JPanel operationJPanel, settingsJPanel, preSettingsJPanel;
	private JScrollPane platformScrollPane;
	private JScrollPane modelScrollPane;
	private JScrollPane applicationNameScrollPane;
	private JScrollPane systemPrivateScrollPane;
	private JScrollPane applicationTypeScrollPane;

	private JList<String> platformList;
	private JList<String> modelList;
	private JList<String> systemPrivateList;
	private JList<String> applicationNameList;
	private JList<String> applicationTypeList;
	private JPanel panel;
	private Button installButton;
	private Button uninstallButton;
	private Button unandinstallButton;
	private Button uninstallPushButton;
	private JScrollPane cmdScrollPane;
	private JScrollPane devicesScroolPane;
	private JList<String> deviceList;
	private JTextArea cmdTextArea;
	private JTextPane cmdJTextPanel;
	private JCheckBox releaseCheckBox, debugCheckBox;
	private Button stopButton;
	private JCheckBox privCheckBox;

	public void initSplitPaneRight() {
		cardLayout = new CardLayout(0, 0);
		jPanelRiglt.setLayout(cardLayout);

		operationJPanel = new JPanel();
		operationJPanel.setBackground(Color.LIGHT_GRAY);
		operationJPanel.setOpaque(true);

		settingsJPanel = new JPanel();
		settingsJPanel.setBackground(Color.orange);
		settingsJPanel.setOpaque(true);

		preSettingsJPanel = new JPanel();
		preSettingsJPanel.setBackground(Color.pink);
		preSettingsJPanel.setOpaque(true);

		jPanelRiglt.add(jMenuItemOperation.getName(), operationJPanel);
		operationJPanel.setLayout(null);

		platformScrollPane = new JScrollPane();
		platformScrollPane.setToolTipText("platform");
		platformScrollPane.setBounds(10, 10, 130, 160);
		operationJPanel.add(platformScrollPane);

	}

	public void initOperationJPanel() {
		platformList = new JList();
		platformScrollPane.setViewportView(platformList);
		platformList.setFont(new Font("Consolas", Font.PLAIN, 16));

		modelScrollPane = new JScrollPane();
		modelScrollPane.setBounds(150, 10, 130, 160);
		operationJPanel.add(modelScrollPane);

		modelList = new JList();
		modelScrollPane.setViewportView(modelList);
		modelList.setFont(new Font("Consolas", Font.PLAIN, 18));

		applicationNameScrollPane = new JScrollPane();
		applicationNameScrollPane.setBounds(570, 10, 130, 160);
		operationJPanel.add(applicationNameScrollPane);

		applicationNameList = new JList();
		applicationNameScrollPane.setViewportView(applicationNameList);
		applicationNameList.setFont(new Font("Consolas", Font.PLAIN, 18));

		systemPrivateScrollPane = new JScrollPane();
		systemPrivateScrollPane.setBounds(430, 10, 130, 160);
		operationJPanel.add(systemPrivateScrollPane);

		systemPrivateList = new JList();
		systemPrivateScrollPane.setViewportView(systemPrivateList);
		systemPrivateList.setFont(new Font("Consolas", Font.PLAIN, 18));

		applicationTypeScrollPane = new JScrollPane();
		applicationTypeScrollPane.setBounds(290, 10, 130, 160);
		operationJPanel.add(applicationTypeScrollPane);

		applicationTypeList = new JList();
		applicationTypeScrollPane.setViewportView(applicationTypeList);
		applicationTypeList.setFont(new Font("Consolas", Font.PLAIN, 18));

		panel = new JPanel();
		panel.setBounds(613, 174, 230, 317);
		operationJPanel.add(panel);
		panel.setLayout(null);

		Button rebootButton = new Button("reboot");
		rebootButton.setBounds(19, 35, 80, 30);
		rebootButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				reboot();
			}
		});
		panel.add(rebootButton);

		installButton = new Button("安装");
		installButton.setBounds(141, 261, 80, 30);
		panel.add(installButton);
		installButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				updateAll();
				install();
			}
		});

		uninstallButton = new Button("卸载");
		uninstallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		uninstallButton.setBounds(19, 261, 80, 30);
		panel.add(uninstallButton);
		uninstallButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				updateAll();
				uninstall();
			}
		});

		unandinstallButton = new Button("卸载 安装");
		unandinstallButton.setBounds(141, 202, 80, 30);
		panel.add(unandinstallButton);
		unandinstallButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				updateAll();
				uninstall();
				install();
			}

		});

		uninstallPushButton = new Button("卸载 推入");
		uninstallPushButton.setBounds(19, 202, 80, 30);
		uninstallPushButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				updateAll();
				uninstall();
				push();
			}
		});
		panel.add(uninstallPushButton);

		debugCheckBox = new JCheckBox("debug");
		debugCheckBox.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		debugCheckBox.setBounds(141, 157, 65, 25);
		debugCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				debugCheckBox.setSelected(true);
				releaseCheckBox.setSelected(false);
			}
		});
		panel.add(debugCheckBox);

		releaseCheckBox = new JCheckBox("release");
		releaseCheckBox.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		releaseCheckBox.setBounds(19, 157, 85, 25);
		releaseCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				debugCheckBox.setSelected(false);
				releaseCheckBox.setSelected(true);
			}
		});
		panel.add(releaseCheckBox);
		debugCheckBox.setSelected(true);
		releaseCheckBox.setSelected(false);

		Button refreshButton = new Button("刷新");
		refreshButton.setBounds(141, 35, 80, 30);
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				updateDevices();
			}
		});
		panel.add(refreshButton);

		stopButton = new Button("中止");
		stopButton.setBounds(141, 94, 80, 30);
		stopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// arvin
			}
		});
		panel.add(stopButton);

		privCheckBox = new JCheckBox("priv");
		privCheckBox.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		privCheckBox.setBounds(19, 130, 65, 25);
		panel.add(privCheckBox);

		cmdScrollPane = new JScrollPane();
		cmdScrollPane.setBounds(10, 174, 593, 317);
		operationJPanel.add(cmdScrollPane);

		cmdTextArea = new JTextArea();
		cmdTextArea.setFont(new Font("Consolas", Font.PLAIN, 13));
		cmdTextArea.setLineWrap(true);
		cmdTextArea.setWrapStyleWord(true);
		//cmdScrollPane.setViewportView(cmdTextArea);
		
		cmdJTextPanel = new JTextPane();
		cmdJTextPanel.setEditable(false);
		cmdJTextPanel.setFont(new Font("Consolas", Font.PLAIN, 13));
		cmdScrollPane.setViewportView(cmdJTextPanel);

		devicesScroolPane = new JScrollPane();
		devicesScroolPane.setBounds(714, 10, 130, 160);
		operationJPanel.add(devicesScroolPane);

		deviceList = new JList();
		deviceList.setFont(new Font("Consolas", Font.PLAIN, 14));
		devicesScroolPane.setViewportView(deviceList);

		jPanelRiglt.add(jMenuItemSettings.getName(), settingsJPanel);
		jPanelRiglt.add(jMenuItemPreSettings.getName(), preSettingsJPanel);
	}

	public void initSettingsJPanel() {

	}

	public void initPreSettingsJPanel() {

	}

	private void initMouseAction(Component component) {
		if (component instanceof AbstractButton) {
			AbstractButton button = (AbstractButton) component;
			ImageIcon icon = new ImageIcon("res/drawable/jiong.png");
			button.setIcon(ImageUtils.getImageIcon("jiong", 20, 18));

			component.setName(button.getText());
		}
		MouseAction.initMouseAction(component, new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				updateJPanelRight(e.getSource());
			}
		});
	}

	private void updateJPanelRight(final Object object) {
		if (object != null && object instanceof Component) {
			Component component = (Component) object;
			String name = component.getName();
			if (!StringUtils.isNullOrEmpty(name)) {
				cardLayout.show(jPanelRiglt, name);
			}
		}
	}

	private void updateDevices() {
		ArrayList<String> devices = new ArrayList<>();
		CommandUtils.commandUpdateMessage("devices", null, new CommandListener() {

			@Override
			public void onLineListener(String type, String message) {
				if (type.equals(CommandLineProgram.STDOUT)) {
					String nameString = StringUtils.getSplitDevice(message);
					devices.add(nameString);
				} else if (type.equals(CommandLineProgram.FINISH)) {
					updateDeviceList(devices);
				}
			}
		});
	}

	private void updateDeviceList(final ArrayList<String> v) {
		ArrayList<String> list = new ArrayList<>();
		if (v == null || v.size() == 0) {
			
			return;
		}
		list.addAll(v);
		deviceList.setModel(new AbstractListModel<String>() {
			public int getSize() {
				return list.size();
			}

			public String getElementAt(int index) {
				return list.get(index);
			}
		});
		deviceList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SelectedManager.getSelectedManager().setDevices(deviceList.getSelectedValue());
			}
		});
		deviceList.setSelectedIndex(0);
		SelectedManager.getSelectedManager().setDevices(deviceList.getSelectedValue());
	}

	private void reboot() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" reboot ");
		CommandUtils.commandUpdateMessage(stringBuilder.toString(), commandListener);
	}

	private void uninstall() {
		String packageNameString = SelectedManager.getSelectedManager().getPackagesString();
		if (StringUtils.isNotBlank(packageNameString)) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" uninstall ");
			stringBuilder.append(SelectedManager.getSelectedManager().getPackagesString());
			CommandUtils.commandUpdateMessage(stringBuilder.toString(), commandListener);
		}

		String targetPath = SelectedManager.getSelectedManager().getTargetPath();
		if (StringUtils.isNotBlank(targetPath)) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" shell rm -r ");
			stringBuilder.append(SelectedManager.getSelectedManager().getTargetPath());
			stringBuilder.append("*.apk");
			if (targetPath.contains(SelectedManager.FRAMEWORK)) {
				return;
			}
			CommandUtils.commandUpdateMessage(stringBuilder.toString(), commandListener);
		}
	}

	public void install() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" install -r ");
		stringBuilder.append(SelectedManager.getSelectedManager().getLocalPath());
		CommandUtils.commandUpdateMessage(stringBuilder.toString(), commandListener);
	}

	public void push() {
		push(SelectedManager.getSelectedManager().getLocalPath(), SelectedManager.getSelectedManager().getTargetPath());
	}

	public void push(String localPath, String targetDir) {
		System.out.println("push, localPath: " + localPath + " targetDir: " + targetDir);
		if (localPath.contains("onyx\\kepler\\android")) {
			File file = new File(localPath);
			if (file.exists() && file.isFile()) {
				CommandUtils.pushCommand(file.getAbsolutePath(), targetDir, commandListener);
			}
		} else {
			List<File> fileList = new ArrayList<>();
			FileUtils.collectFiles(localPath, fileList);

			for (File file : fileList) {
				String unitpath = file.getAbsolutePath();
				if (file.isFile()) {
					CommandUtils.pushCommand(unitpath, targetDir, commandListener);
				} else if (file.isDirectory()) {
					String name = file.getName();
					String pathString = new StringBuilder(targetDir).append(name).append("/").toString();
					push(unitpath, pathString);
				}
			}
		}
	}

	private void updatePlatformList() {
		List<Platforms> pList = DefaultJson.init().getPlatforms();

		platformList.setModel(new AbstractListModel<String>() {

			public int getSize() {
				return pList.size();
			}

			public String getElementAt(int index) {
				return pList.get(index).getName();
			}
		});

		platformList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				updateModelList(pList.get(platformList.getSelectedIndex()));

			}
		});

		platformList.setSelectedIndex(0);
		updateModelList(pList.get(platformList.getSelectedIndex()));
		SelectedManager.getSelectedManager().setPlatformDir(pList.get(platformList.getSelectedIndex()).getDir());
	}

	private void updateModelList(Platforms platform) {
		List<String> models = platform.getModels();
		modelList.setModel(new AbstractListModel<String>() {

			@Override
			public int getSize() {
				// TODO 自动生成的方法存根
				return models.size();
			}

			@Override
			public String getElementAt(int index) {
				// TODO 自动生成的方法存根
				return models.get(index);
			}
		});
		modelList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String modelString = modelList.getSelectedValue();
				SelectedManager.getSelectedManager().setModels(modelString + "\\");
				updateApplicationType();

			}
		});
		modelList.setSelectedIndex(0);
		SelectedManager.getSelectedManager().setModels(modelList.getSelectedValue() + "\\");
		updateApplicationType();
	}

	private void updateApplicationType() {
		Application application = DefaultJson.init().getApplication();
		Field[] fields = application.getClass().getDeclaredFields();

		applicationTypeList.setModel(new AbstractListModel<String>() {

			public int getSize() {
				return fields.length;
			}

			public String getElementAt(int index) {
				return fields[index].getName();
			}
		});
		applicationTypeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				updateSystemPrivate();
			}
		});
		applicationTypeList.setSelectedIndex(0);
		String name = getApplicationType();
		SelectedManager.getSelectedManager().setApplicationType(name);
		updateSystemPrivate();
	}

	private void updateSystemPrivate() {
		Application application = DefaultJson.init().getApplication();
		com.arvin.feifeiyu.win.json.System system = application.getSystem();
		Onyx onyx = application.getOnyx();

		ArrayList<Field> arrayList = new ArrayList<>();
		Field[] fields = null;

		String type = getApplicationType();
		if (StringUtils.endsWith(system.getClass().getName(), type)) {
			fields = system.getClass().getDeclaredFields();
		} else if (StringUtils.endsWith(onyx.getClass().getName(), type)) {
			fields = onyx.getClass().getDeclaredFields();
		}
		arrayList.addAll(Arrays.asList(fields));

		systemPrivateList.setModel(new AbstractListModel<String>() {

			@Override
			public int getSize() {
				// TODO 自动生成的方法存根
				return arrayList.size();
			}

			@Override
			public String getElementAt(int index) {
				return arrayList.get(index).getName();
			}
		});
		systemPrivateList.setSelectedIndex(0);
		systemPrivateList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				updateApplicationName();
			}
		});
		systemPrivateList.setSelectedIndex(0);
		String name = getSystemPrivate();
		SelectedManager.getSelectedManager().setSystemPrivate(name);
		updateApplicationName();
		updateAll();
		SelectedManager.getSelectedManager().setTargetPath(type + "/" + name + "/");
	}

	public void updateApplicationName() {
		String type = getApplicationType();
		String dir = getSystemPrivate();

		Application application = DefaultJson.init().getApplication();
		ArrayList<String> arrayList = new ArrayList<>();

		if (type.equals(SelectedManager.SYSTEM)) {
			com.arvin.feifeiyu.win.json.System system = application.getSystem();
			arrayList.addAll(application.getSystem().getList(dir));
		} else if (type.equals(SelectedManager.ONYX)) {
			Onyx onyx = application.getOnyx();
			if (StringUtils.endsWith(onyx.getEreader().getClass().getName(), dir)) {
				arrayList.addAll(onyx.getEreader().getApp());
			} else if (StringUtils.endsWith(onyx.getCommon().getClass().getName(), dir)) {
				arrayList.addAll(onyx.getCommon().getApp());
			} else if (StringUtils.endsWith(onyx.getSample().getClass().getName(), dir)) {
				arrayList.addAll(onyx.getSample().getApp());
			} else if (StringUtils.endsWith(onyx.getAlreader().getClass().getName(), dir)) {
				arrayList.addAll(onyx.getAlreader().getApp());
			}
		}

		applicationNameList.setModel(new AbstractListModel<String>() {

			@Override
			public int getSize() {
				// TODO 自动生成的方法存根
				return arrayList.size();
			}

			@Override
			public String getElementAt(int index) {
				// TODO 自动生成的方法存根
				return arrayList.get(index);
			}
		});

		applicationNameList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				updateAll();

			}
		});
		updateAll();
	}

	private String getlatform() {
		return platformList.getSelectedValue();
	}

	private String getApplicationType() {
		return applicationTypeList.getSelectedValue();
	}

	private String getSystemPrivate() {
		String nameString = systemPrivateList.getSelectedValue();
		if (nameString.equals("privApp"))
			nameString = "priv-app";
		return nameString;
	}

	private void updateAll() {
		updateConfound();
		updatePlatformLast();
		udpateModelLast();
		updateApplicationTypeLast();
		updateSystemPrivateLast();
		udpateApplicationNameLast();
	};
	
	private void updateConfound() {
		if (releaseCheckBox.isSelected()) {
			SelectedManager.getSelectedManager().setConfusion(SelectedManager.RELEASE);
		} else {
			SelectedManager.getSelectedManager().setConfusion(SelectedManager.DEBUG);
		}
	}

	private void updatePlatformLast() {
		SelectedManager.getSelectedManager()
				.setPlatformDir(DefaultJson.init().getPlatforms().get(platformList.getSelectedIndex()).getDir());
	}

	private void udpateModelLast() {
		String modelString = modelList.getSelectedValue();
		SelectedManager.getSelectedManager().setModels(modelString + "\\");
	}

	private void updateApplicationTypeLast() {
		String name = getApplicationType();
		SelectedManager.getSelectedManager().setApplicationType(name);
	}

	private void updateSystemPrivateLast() {
		Onyx onyx = DefaultJson.init().getApplication().getOnyx();
		String type = getApplicationType();
		String dir = getSystemPrivate();
		SelectedManager.getSelectedManager().setSystemPrivate(dir);
		if (dir.equals(SelectedManager.FRAMEWORK)) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(type).append("/").append(dir);
			SelectedManager.getSelectedManager().setTargetPath(stringBuilder.toString());

			unandinstallButton.setEnabled(false);
			installButton.setEnabled(false);
			uninstallButton.setEnabled(false);
		} else {
			SelectedManager.getSelectedManager().setTargetPath(null);
			unandinstallButton.setEnabled(true);
			installButton.setEnabled(true);
			uninstallButton.setEnabled(true);
		}

		if (getApplicationType().equals(SelectedManager.ONYX)) {
			SelectedManager.getSelectedManager().setSourcePath(onyx.gatPath(dir));
		}

		privCheckBox.setSelected(dir.equals(SelectedManager.PRIV_APP));
	}

	private void udpateApplicationNameLast() {
		String type = getApplicationType();
		String dir = getSystemPrivate();
		String name = applicationNameList.getSelectedValue();
		SelectedManager.getSelectedManager().setApplicationName(name);
		SelectedManager.getSelectedManager().setApp(name);
		StringBuilder stringBuilder = new StringBuilder();

		if (getApplicationType().equals(SelectedManager.ONYX)) {
			String app = SelectedManager.APP;
			if (privCheckBox.isSelected()) {
				app = SelectedManager.PRIV_APP;
			}
			stringBuilder.append(SelectedManager.SYSTEM).append("/").append(app).append("/");
			if (!getlatform().contains("3128")) {
				stringBuilder.append(name).append("-").append(SelectedManager.RELEASE).append("/");
			}
		} else {
			stringBuilder.append(type).append("/").append(dir).append("/");
			if (!getlatform().contains("3128")) {
				stringBuilder.append(name).append("/");
			}
		}

		SelectedManager.getSelectedManager().setTargetPath(stringBuilder.toString());
	}

	CommandListener commandListener = new CommandListener() {

		@Override
		public void onLineListener(String type, String message) {
			System.out.println("type: " + type + "\n" + "message: " + message);
			String value = message;
			Color color = Color.black;
			if (type.contains(CommandLineProgram.ERROR)
					|| (StringUtils.isNotBlank(message) && message.contains("Failure"))) {
				color = Color.red;
			} else if (type.contains(CommandLineProgram.STDOUT)) {
				color = Color.black;
			} else if (type.contains(CommandLineProgram.FINISH)) {
				color = Color.green;
				value = "finish  ";
			}

			if (StringUtils.isNotBlank(value)) {
				appendTextPanel(value, color);
				appendTextPanel("\n", color);
				cmdJTextPanel.invalidate();
			}
		}

	};
	
	private void appendTextPanel(String value, Color color) {
		SimpleAttributeSet attr = new SimpleAttributeSet();
    	StyleConstants.setForeground(attr, color);
    	
		Document document = cmdJTextPanel.getDocument();
		try {
			document.insertString(document.getLength(), value, attr);
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}
