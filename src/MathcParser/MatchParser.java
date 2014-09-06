package MathcParser;


import java.util.HashMap;

public class MatchParser {
	
	private HashMap<String, Double> variables;
    
	public MatchParser() {
        variables = new HashMap<String, Double>();
    }

    public void setVariable(String variableName, Double variableValue) {
        variables.put(variableName, variableValue);
    }

    public Double getVariable(String variableName) {
        if (!variables.containsKey(variableName)) {
            return 0.0;
        }
        return variables.get(variableName);
    }

    public double Parse(String s) throws Exception {
    	for(int i = 0; i < s.length(); i++) {
    		if(java.lang.Character.isDigit(s.charAt(i))) {
    			if(i == s.length()-1)
                    return Double.parseDouble(s);
    		}
            else break;
    	}
        Result result = PlusMinus(s);
        double output = result.acc;
        output = output * 100000;
        int i = (int) Math.round(output);
        output = (double)i / 100000;
        return output;
    }

    private Result PlusMinus(String s) throws Exception {
        Result current = MulDiv(s);
        double acc = current.acc;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) break;

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            current = MulDiv(next);
            if (sign == '+') {
                acc += current.acc;
            } else {
                acc -= current.acc;
            }
        }
        return new Result(acc, current.rest);
    }

    private Result Bracket(String s) throws Exception {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            Result r = PlusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } 
            return r;
        }
        return FunctionVariable(s);
    }

    private Result FunctionVariable(String s) throws Exception {
        String f = "";
        int i = 0;
        
        while (i < s.length() && (Character.isLetter(s.charAt(i)) || ( Character.isDigit(s.charAt(i)) && i > 0 ) )) {
            f += s.charAt(i);
            i++;
        }
        if (!f.isEmpty()) { 
            if ( s.length() > i && s.charAt( i ) == '(') { 
                Result r = Bracket(s.substring(f.length()));
                return processFunction(f, r);
            } else { 
                return new Result(getVariable(f), s.substring(f.length()));
            }
        }
        return Num(s);
    }

    private Result MulDiv(String s) throws Exception {
        Result current = Bracket(s);

        double acc = current.acc;
        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/')) return current;

            String next = current.rest.substring(1);
            Result right = Bracket(next);

            if (sign == '*') {
                acc *= right.acc;
            } else {
                acc /= right.acc;
            }

            current = new Result(acc, right.rest);
        }
    }

   private Result Num(String s) throws Exception {
        int i = 0;
        int dot_cnt = 0;
        boolean negative = false;
  
        if( s.charAt(0) == '-' ) {
            negative = true;
            s = s.substring( 1 );
        }
      
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
           
            if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                throw new Exception("not valid number '" + s.substring(0, i + 1) + "'");
            }
            i++;
        }
        if( i == 0 ){
            throw new Exception( "can't get valid number in '" + s + "'" );
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        if( negative ) dPart = -dPart;
        String restPart = s.substring(i);

        return new Result(dPart, restPart);
    }

   
    private Result processFunction(String func, Result r) {
      /*  if (func.equals("sin")) {
            return new Result(Math.sin(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("cos")) {
            return new Result(Math.cos(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("tg")) {
            return new Result(Math.tan(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("ctg")) {
            return new Result(1./Math.tan(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("pow")) {
        	return new Result(Math.pow(r.acc, 2), r.rest);
        }*/
        return r;
    }
} 