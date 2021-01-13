package com.arvin.feifeiyu.win;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MouseAction extends MouseAdapter{
	private static HashMap<String, MouseAction> mouseActionHashMap = new HashMap<>();
	private MouseAdapter mouseAction;
	private Component component;
	private Color proBackground;
	private MouseListener mouseListener;
	
	public MouseAction(Component component, MouseListener mouseListener) {
		this.component = component;
		this.mouseListener = mouseListener;
		setMouseAction(component);
	}
	
	public static void initMouseAction(Component component, MouseListener mouseListener) {
		String name = component.getName();
		if (name == null || name.length() == 0) {
			return;
		}
		mouseActionHashMap.put(name, new MouseAction(component, mouseListener));
	}
	
	public void setMouseAction(Component component) {
		mouseAction = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//点击离开
				if(mouseListener != null) {
					mouseListener.mouseClicked(e);
				}
				setPreBackground(e.getSource());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				//指向
				setBackground(e.getSource(), Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//指向离开
				setPreBackground(e.getSource());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//保持点击
				setBackground(e.getSource(), Color.red);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//setBackground(e.getSource(), proBackground);
			}
		};
		
		proBackground = component.getBackground();
		component.addMouseListener(mouseAction);
	}

	private void setBackground(Object object, Color color) {
		if (object instanceof Component) {
			Component component = (Component) object;
			component.setBackground(color);
		}
	}
	private void setPreBackground(Object object) {
		if (object instanceof Component) {
			Component component = (Component) object;
			String name = component.getName();
			Color c = Color.black;
			if (!StringUtils.isNullOrEmpty(name)) {
				MouseAction nMouseAction = mouseActionHashMap.get(name);
				if (nMouseAction != null) {
					c = nMouseAction.proBackground;
				}
			}
			component.setBackground(c);
		}
	}

}
