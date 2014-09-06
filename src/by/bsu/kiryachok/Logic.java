package by.bsu.kiryachok;

import MathcParser.MatchParser;
import by.bsu.kiryachok.R;

public class Logic {
	
	private boolean checkUnswer;
	private int countBracket;
	private StringBuffer input;
	
	public enum idButtons {	
					eZero,
					eOne,
					eTwo,
					eThre,
					eFour,
					eFive,
					eSix,
					eSeven,
					eEight,
					eNine,
					ePoint,
					eEqually,
					eDiv,
					eMylt,
					eSub,
					eAdd,
					eDelete,
					eCancel,
					eLeftBracket,
					eRightBracket,
					eCountButtons;
	};

	public Logic() {	
		input = new StringBuffer();
	}
	
	public StringBuffer getInput() {
		return input;
	}

	public void setInput(StringBuffer input) {
		this.input = input;
	}

	public void addInput(StringBuffer input) {
		this.input.append(input);
	}
	
	public int getCountBracket() {
		return countBracket;
	}

	public void setCountBracket(int countBracket) {
		this.countBracket = countBracket;
	}

	public void clearInput() {
		this.input.delete(0, input.length());
	}
	
	public boolean isCheckUnswer() {
		return checkUnswer;
	}

	public void setCheckUnswer(boolean checkUnswer) {
		this.checkUnswer = checkUnswer;
	}

	public void filter(int idButton, String textButton) {
    	
		switch(idButton) {
    	
    	case R.id.delete:
    		input.delete(0, input.length());
    		countBracket = 0;
    		break;
    	case R.id.cancel:
    		if(input.length() == 0)
    			break;
    		if(input.charAt((input.length()-1)) == '(') {
    			countBracket--;
    		}
    		if(input.charAt((input.length()-1)) == ')') {
    			countBracket++;
    		}
    		input.deleteCharAt(input.length()-1);
    		break;
    	case R.id.equally:
    		checkUnswer = true;
    		MatchParser mp = new MatchParser();
    		mp.setVariable("X", 2.0);			
			try {
				String tmp;
				tmp = mp.Parse(input.toString()) +"";
				input.delete(0, input.length());
				countBracket = 0;
				input.append(tmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break; 

    	case R.id.leftBracket:
    		if(checkUnswer) {
    			input.delete(0, input.length());
    			countBracket = 0;
    			checkUnswer = false;
    		}
    		if(input.length() == 0) {
    			break;
    		} 		
            if(!java.lang.Character.isDigit(input.charAt(input.length()-1))) {
            	input.append(textButton);
            	countBracket++;
            	break;
            }
            if(java.lang.Character.isDigit(input.charAt(input.length()-1))) {
            	input.append("*" + textButton);
            	countBracket++;
            	break;
            }
            break;
    	case R.id.rightBracket:
    		if(checkUnswer) {
    			input.delete(0, input.length());
    			countBracket = 0;
    			checkUnswer = false;
    		}
    		if(input.length() == 0) {
    			break;
    		}
    		if(countBracket <= 0) {
    			break;
    		}
    		if(input.charAt(input.length()-1) == ')') {
    			input.append(textButton);
    			countBracket--;
    			break;
    		}
        	if(java.lang.Character.isDigit(input.charAt(input.length()-1))) {
        		input.append(textButton);
        		countBracket--;
        		break;
        	}
        	break;

    	case R.id.sub:
    		if(checkUnswer) {
    			checkUnswer = false;
    		}
    		if(input.length() == 0) {
    			input.append(textButton);
    			break;
    		}
    		if(input.charAt(input.length()-1) == '(') {
    			input.append(textButton);
    			break;
    		}
    		if(!java.lang.Character.isDigit(input.charAt(input.length()-1)) 
    				&& input.charAt(input.length()-2) == 0) {
    			input.deleteCharAt(input.length());
    			input.append(textButton);
    			break;
    		}	
    		if(!java.lang.Character.isDigit(input.charAt(input.length()-1)) 
    				&& !java.lang.Character.isDigit(input.charAt(input.length()-2))) {
    			input.deleteCharAt(input.length()-1); 
    			input.deleteCharAt(input.length()-1);
    			input.append(textButton);
    			break;
    		}
    		if(!java.lang.Character.isDigit(input.charAt(input.length()-1)) 
    				&& java.lang.Character.isDigit(input.charAt(input.length()-2))) {
    			input.append(textButton);
    			break;
    		}
    		if(!java.lang.Character.isDigit(input.charAt(input.length()-1)))
    			input.deleteCharAt(input.length()-1);
    		input.append(textButton);
    		
    		break;
    	case R.id.div:
    	case R.id.mylt:
    	case R.id.add:
    		if(checkUnswer == true) {
    			checkUnswer = false;
    		}
    		if(input.length() == 0)
    			break;
    		if(input.charAt(input.length()-1) == '(') {
    			break;
    		}
    		if(input.charAt(input.length()-1) == ')') {
    			input.append(textButton);
    			break;
    		}
    		if(!java.lang.Character.isDigit(input.charAt(input.length()-1)) && !java.lang.Character.isDigit(input.charAt(input.length()-2))) {
    			input.deleteCharAt(input.length()-1); 
    			input.deleteCharAt(input.length()-1);
    		}
    		if(!java.lang.Character.isDigit(input.charAt(input.length()-1))) {
    			input.deleteCharAt(input.length()-1);
    		}
    		input.append(textButton);
    		break;
    	case R.id.zero:
    	case R.id.one:
    	case R.id.two:
    	case R.id.three:
    	case R.id.four:
    	case R.id.five:
    	case R.id.six:
    	case R.id.seven:
    	case R.id.eight:
    	case R.id.nine:
    	case R.id.point:
    		if(checkUnswer == true) {
        		input.delete(0, input.length());
        		countBracket = 0;
        		checkUnswer = false;
    		}
    		input.append(textButton);
    		break;
  		default:
   			break;
    	}
	}
}
