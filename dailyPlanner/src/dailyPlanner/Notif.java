package dailyPlanner;


import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.TimerTask;

public class  Notif extends TimerTask {

	private String datay;
	
	public Notif(String data) {
		datay = data;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
        SystemTray tray = SystemTray.getSystemTray();

        Image img = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(img, "pepsi");
        trayIcon.setImageAutoSize(true);
       try {
    	   tray.add(trayIcon);
       } catch (AWTException e) {
    	   e.printStackTrace();
       }

        trayIcon.displayMessage(datay, "reminder" , MessageType.NONE);
	}



}
