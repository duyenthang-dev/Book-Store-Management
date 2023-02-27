package util;

import javax.swing.JOptionPane;

public class NotificationUtils {
	public static void createNotification(String type, String content) {
		switch (type) {
		case "Success":
			JOptionPane.showMessageDialog(null, content, "Success", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "Warning":
			JOptionPane.showMessageDialog(null, content, "Warning", JOptionPane.WARNING_MESSAGE);
			break;
		case "Error":
			JOptionPane.showMessageDialog(null, content, "Error", JOptionPane.ERROR_MESSAGE);
			break;

		default:
			break;
		}
		
	}
}
