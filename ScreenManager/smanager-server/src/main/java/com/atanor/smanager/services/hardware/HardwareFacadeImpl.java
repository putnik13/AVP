package com.atanor.smanager.services.hardware;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.telnet.TelnetClient;

import com.atanor.smanager.domain.entity.Preset;
import com.atanor.smanager.domain.entity.Window;

public class HardwareFacadeImpl implements HardwareFacade {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private String prompt = ">";
	private String server = "192.168.0.3", user = "admin", password = "admin";

	@Override
	public Boolean sendPresetConfiguration(Preset preset) {

		try {
			System.out.println("********************************");
			// Connect to the specified server
			telnet.connect(server, 23);

			// Get input and output stream references
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());

			write("start");
			System.out.println("*********");
			TimeUnit.MILLISECONDS.sleep(500);
			readUntil(prompt);
			write("ApplyLayout \"Demo-OneStretchToAll\"");
			TimeUnit.MILLISECONDS.sleep(700);
			readUntil(prompt);
			
			System.out.println("*********");
//			int nW = readExistingWindows();
//			System.out.println("Some error happened");
//			if (readExistingWindows() == 999) {
//				System.out.println("Some **"+nW);
//			} else if (readExistingWindows() < 888
//					&& readExistingWindows() > -1) {
//				for (int i = 0; i <= readExistingWindows(); i++) {
//					write("DeleteWindow \"Demo-OneStretchToAll\" " + i);
//				}
//			}
			
			int nn =0;
			for (Window win : preset.getWindows()) {
				if(win.isModified()){
					
				}
				write("AddWindow \"Demo-OneStretchToAll\" " + win.getXTopLeft()
						+ "," + win.getYTopLeft() + "," + win.getXBottomRight()
						+ "," + win.getYBottomRight() + " \"video\"");
				readUntil(prompt);
				TimeUnit.MILLISECONDS.sleep(500);
				write("SetWindow \"Demo-OneStretchToAll\" "+nn+" /S:\"video\" /R:0,0,0,0 /BC:0x00FFFF0"+nn+" /BT:Alpha");
			
			nn++;}
			;
			TimeUnit.MILLISECONDS.sleep(5000);
			write("stop");
			readUntil(prompt);
			telnet.disconnect();
			// System.out.println("sendPresetConfiguration() ++++ called..");
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	public int readExistingWindows() {
		try {
			write("Window \"Demo-OneStretchToAll\"");
			
			String pattern = "0-";
			String pattern2 = "NoWindow";
			StringUtils.substringAfter(str, "-");
			char lastChar = pattern.charAt(pattern.length() - 1);
			char lastChar2 = pattern2.charAt(pattern2.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			TimeUnit.MILLISECONDS.sleep(600);
			char ch = (char) in.read();
			while (true) {
				System.out.print(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						sb.append(ch);
						char windowN = sb.charAt(sb.length() - 1);
						return Character.getNumericValue(sb.charAt(sb.length() - 1));
					}
				} 
//				else if (ch == lastChar2) {
//					if (sb.toString().endsWith(pattern2)) {
//
//						return 888;
//					}
//					ch = (char) in.read();
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 999;
	}

	public String readUntil(String pattern) {
		try {

			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			char ch = (char) in.read();
			while (true) {
				System.out.print(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void write(String value) {
		try {
			out.println(value);
			out.flush();
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendCommand(String command) {
		try {
			write(command);
			return prompt + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
