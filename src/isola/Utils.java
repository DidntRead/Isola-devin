package isola;

import java.io.IOException;

public class Utils {
	private static ProcessBuilder windowsProcess = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
	
	public static boolean clearConsole() {
		try {
			String os = System.getProperty("os.name");
			if(os.contains("Windows")) {
				windowsProcess.start().waitFor();
			} else {
				Runtime.getRuntime().exec("clear");
			}
			return true;
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String center(String s, int size, char pad) {
		if(s == null || size <= s.length()) {
			return s;
		}
		
		StringBuilder sb = new StringBuilder(size);
		for(int i = 0; i < (size - s .length()) / 2;i++) {
			sb.append(pad);
		}
		sb.append(s);
		while(sb.length() < size) {
			sb.append(pad);
		}
		return sb.toString();
	}
}
