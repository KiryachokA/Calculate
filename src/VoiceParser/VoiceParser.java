package VoiceParser;

import java.util.StringTokenizer;

public class VoiceParser {
	private StringBuffer result;
	private StringTokenizer st;
	
	public VoiceParser(String input) {
		this.result = new StringBuffer();
		this.st = new StringTokenizer(input.toString()," ", true);
	}
	
	public StringBuffer go() {
		while(st.hasMoreTokens()) {
			String a = new String(st.nextToken());
			
			if(a.equals("плюс") || a.equals("+")) {
				result.append("+");
			} else if(a.equals("минус") || a.equals("-")) {
				result.append("-");
			} else if(a.equals("умножить") || a.equals("*")) {
				result.append("*");
			} else if(a.equals("делить") || a.equals("разделить") || a.equals("/")) {
				result.append("/");
			} else if(java.lang.Character.isDigit(a.charAt(0))) {
				result.append(a);
			}
		}
		return result;
	}
}
