package com.arvin.feifeiyu.win;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageUtils {

	public static final String PARENT_PATH = "res/drawable/";

	public static ImageIcon getImageIcon(String name, int width, int height) {
		if (StringUtils.isNullOrEmpty(name)) {
			return null;
		}
		StringBuilder path = new StringBuilder(PARENT_PATH).append(name);
		ImageIcon icon = new ImageIcon(PARENT_PATH + name + ".png");
		if (width == 0 || height == 0 || icon == null) {
			return icon;
		}
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}
}
