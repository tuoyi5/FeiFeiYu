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
	JPanel jPanel1, jPanel2;
	JScrollPane scrollPane;
	public void initSplitPane() {
		jPanel1 = new JPanel();
		jPanel1.setBackground(Color.green);
		jPanel1.setOpaque(true);

		jPanel2 = new JPanel();
		jPanel2.setBackground(Color.pink);
		jPanel2.setOpaque(true);
		
		scrollPane = new JScrollPane();
		scrollPane.add(jPanel2);
		scrollPane.setOpaque(true);

		jSplitPaneLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, scrollPane, jPanel2);
		jSplitPaneLeft.setDividerLocation(0.3);
		jSplitPaneLeft.setOneTouchExpandable(true);
		jSplitPaneLeft.setDividerSize(10);


		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.yellow);
		jPanel.setOpaque(true);
		
		jSplitPaneV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, jPanel2, jPanel);
		jFrame.getContentPane().add(jSplitPaneV);
		
	}
	
	public void initSplitPaneLeft() {
		JToolBar toolBar;
		toolBar = new JToolBar();
		toolBar.setBackground(Color.PINK);
		toolBar.setFloatable(false);
		toolBar.setEnabled(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		jPanel2.add(toolBar);
		
		jMenuItemOperation = new JMenuItem("Operation");
		jMenuItemSettings = new JMenuItem("Settings");
		jMenuItemPreSettings = new JMenuItem("Pre-Settings");
		
		initMouseAction(jMenuItemOperation);
		initMouseAction(jMenuItemSettings);
		initMouseAction(jMenuItemPreSettings);
		
		toolBar.add(jMenuItemOperation);
		toolBar.add(jMenuItemSettings);
		toolBar.add(jMenuItemPreSettings);
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
				// TODO 自动生成的方法存根
				
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
				// TODO 自动生成的方法存根
				
			}
		});
	}

	
	public void initSplitPaneRight() {
		
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
