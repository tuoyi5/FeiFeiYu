package com.arvin.feifeiyu.win;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import javax.swing.JMenuItem;
import java.awt.List;
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
import java.awt.CardLayout;

public class FeiFeiYuApplication {

	public static final String ADB_HOME = "D:\\JAVA\\Android\\platform-tools\\";

	private JFrame jFrame;
	JList<String> deviceList = new JList<String>();
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
		jFrame.setBounds(100, 100, 730, 492);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initBar();
		initSplitPane();
		initSplitPaneLeft();
		initSplitPaneRight();
		initOperationJPanel();
		initSettingsJPanel();
		initPreSettingsJPanel();
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
	private JList modelList;
	private JList systemPrivateList;
	private JList applicationNameList;
	private JList applicationTypeList;
	
	private JTextArea platformJTextArea, deviceJTextArea, applicationTypeJTextArea, systemPrivateJTextArea, applicationNameJTextArea;
	public void initSplitPaneRight() {
		cardLayout = new CardLayout(0, 0);
		jPanelRiglt.setLayout(cardLayout);
		
		operationJPanel = new JPanel();
		operationJPanel.setBackground(Color.LIGHT_GRAY);
		operationJPanel.setOpaque(true);
		
		settingsJPanel= new JPanel();
		settingsJPanel.setBackground(Color.orange);
		settingsJPanel.setOpaque(true);
		
		preSettingsJPanel = new JPanel();
		preSettingsJPanel.setBackground(Color.pink);
		preSettingsJPanel.setOpaque(true);
	
		
		jPanelRiglt.add(jMenuItemOperation.getName(), operationJPanel);
		operationJPanel.setLayout(null);
		
		platformScrollPane = new JScrollPane();
		platformScrollPane.setToolTipText("platform");
		platformScrollPane.setBounds(10, 10, 120, 300);
		operationJPanel.add(platformScrollPane);
		
		JList platformList = new JList();
		platformScrollPane.setViewportView(platformList);
		platformList.setFont(new Font("Consolas", Font.PLAIN, 20));
		platformList.setModel(new AbstractListModel() {
			String[] values = new String[] {"3128", "3288", "8953", "636", "720"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		modelScrollPane = new JScrollPane();
		modelScrollPane.setBounds(136, 10, 120, 300);
		operationJPanel.add(modelScrollPane);
		
		modelList = new JList();
		modelScrollPane.setViewportView(modelList);
		
		applicationNameScrollPane = new JScrollPane();
		applicationNameScrollPane.setBounds(396, 10, 120, 300);
		operationJPanel.add(applicationNameScrollPane);
		
		applicationNameList = new JList();
		applicationNameScrollPane.setViewportView(applicationNameList);
		
		systemPrivateScrollPane = new JScrollPane();
		systemPrivateScrollPane.setBounds(266, 10, 120, 125);
		operationJPanel.add(systemPrivateScrollPane);
		
		systemPrivateList = new JList();
		systemPrivateScrollPane.setViewportView(systemPrivateList);
		
		applicationTypeScrollPane = new JScrollPane();
		applicationTypeScrollPane.setBounds(266, 180, 120, 130);
		operationJPanel.add(applicationTypeScrollPane);
		
		applicationTypeList = new JList();
		applicationTypeScrollPane.setViewportView(applicationTypeList);
		
		platformJTextArea = new JTextArea();
		platformJTextArea.setLineWrap(true);
		platformJTextArea.setText("平台");
		platformJTextArea.setBounds(10, 316, 120, 29);
		operationJPanel.add(platformJTextArea);
		platformJTextArea.setColumns(10);
		
		deviceJTextArea = new JTextArea();
		deviceJTextArea.setText("设备");
		deviceJTextArea.setColumns(10);
		deviceJTextArea.setBounds(136, 316, 120, 29);
		operationJPanel.add(deviceJTextArea);
		
		applicationTypeJTextArea = new JTextArea();
		applicationTypeJTextArea.setText("应用类型");
		applicationTypeJTextArea.setColumns(10);
		applicationTypeJTextArea.setBounds(266, 141, 120, 29);
		operationJPanel.add(applicationTypeJTextArea);
		
		systemPrivateJTextArea = new JTextArea();
		systemPrivateJTextArea.setText("应用私有");
		systemPrivateJTextArea.setColumns(10);
		systemPrivateJTextArea.setBounds(266, 316, 120, 29);
		operationJPanel.add(systemPrivateJTextArea);
		
		applicationNameJTextArea = new JTextArea();
		applicationNameJTextArea.setText("应用名称");
		applicationNameJTextArea.setColumns(10);
		applicationNameJTextArea.setBounds(396, 316, 120, 29);
		operationJPanel.add(applicationNameJTextArea);

		jPanelRiglt.add(jMenuItemSettings.getName(),settingsJPanel);
		jPanelRiglt.add(jMenuItemPreSettings.getName(), preSettingsJPanel);
	}
	
	public void initOperationJPanel() {
	}
	
	public void initSettingsJPanel() {
			
	}
	
	public void initPreSettingsJPanel() {
		
	}
	
	
	
	private void initMouseAction(Component component) {
		if (component instanceof AbstractButton) {
			AbstractButton button = (AbstractButton)component;
			ImageIcon icon = new ImageIcon("res/drawable/jiong.png");
			button.setIcon(ImageUtils.getImageIcon("jiong", 20, 20));
			
			component.setName(button.getText());
		}
		MouseAction.initMouseAction(component, new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				updateJPanelRight(e.getSource());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	}
	
	private void updateJPanelRight(final Object object) {
		if (object != null && object instanceof Component) {
			Component component = (Component) object;
			String name = component.getName();
			System.out.println("mouseClicked: " + name);
			if (!StringUtils.isNullOrEmpty(name)) {
				cardLayout.show(jPanelRiglt, name);
			}
		}
	}

	private void updateDevices() {
		String adb = getAdb();
		StringBuffer stringbuffer = new StringBuffer(adb).append(" devices");
		String command = stringbuffer.toString();
		command(command, new CommandListener() {

			@Override
			public void onLineListener(String type, String message) {
				System.out.println("type: " + type + " ,message: " + message);
				ArrayList<String> devices = new ArrayList<>();
				if (type.equals(CommandLineProgram.STDOUT)) {
					devices.add(message);
				}
				updateDeviceList(devices);
			}
		});
	}

	private void updateDeviceList(final ArrayList<String> values) {
		deviceList.setModel(new AbstractListModel<String>() {
			public int getSize() {
				return values.size();
			}

			public String getElementAt(int index) {
				return values.get(index);
			}
		});
	}

	private String getAdb() {
		String device = deviceList.getSelectedValue();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(ADB_HOME).append("adb");
		if (device != null && device.length() > 0) {
			stringbuffer.append(" -s ").append(device).append(" ");
		}
		return stringbuffer.toString();
	}

	private void command(String command, CommandListener listener) {
		if (command == null || command.length() == 0) {
			return;
		}
		System.out.println("command----------: " + command);
		if (commandLineProgram == null) {
			commandLineProgram = new CommandLineProgram();
		}
		commandLineProgram.run(command, listener);
	}

	private void command(String command) {
		command(command, new CommandListener() {

			@Override
			public void onLineListener(String type, String message) {
				if (type.equals(CommandLineProgram.STDOUT)) {
				} else if (type.equals(CommandLineProgram.ERROR)) {
				}

			}
		});

	}
}
