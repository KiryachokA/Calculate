package MathcParser;

import java.util.StringTokenizer;
import java.util.Vector;

public class Solution {
	
	public StringBuffer sb = new StringBuffer();
	public double result;
	public Vector<String> vec = new Vector<String>();
	
	public Solution(StringBuffer sb) {
		this.sb = sb;
		this.result = 0;
	}
	
	
	public void go() { 
		
		StringTokenizer st = new StringTokenizer(sb.toString(),"+*=/", true);
		
		while(st.hasMoreTokens()) {
			String a = new String(st.nextToken());
			vec.add(a);
		}
		
		double tmp1, tmp2;
		int i = 0;
		while(vec.size() > 0) {
			if(vec.elementAt(i).charAt(0) == '*') {
			    tmp1 = Double.parseDouble(vec.elementAt(i-1));
				tmp1 = vec.elementAt(i).charAt(i);
				tmp2 = vec.elementAt(i).charAt(i+1);
			}
			else if(sb.charAt(i) == '/')	{
				tmp1 = sb.charAt(i-1);
				tmp2 = sb.charAt(i+1);
				result = tmp1/tmp2;
				sb.delete(i-1, i+1);
				sb.insert(i-1, result);
			}
			else if(sb.charAt(i) == '+') {
				tmp1 = sb.charAt(i-1);
				tmp2 = sb.charAt(i+1);
				result = tmp1+tmp2;
				sb.delete(i-1, i+1);
				sb.insert(i-1, result);
			}
			else if(sb.charAt(i) == '-') {
				tmp1 = sb.charAt(i-1);
				tmp2 = sb.charAt(i+1);
				result = tmp1-tmp2;
				sb.delete(i-1, i+1);
				sb.insert(i-1, result);
			}
			if(i >= sb.length())
				i = 0;
			i++;
		}
	}
	
	public StringBuffer getSB() {
		return this.sb;
	}
	
	public double getResult() {
		return this.result;
	}
}
