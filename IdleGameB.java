import java.io.*;
import java.math.*;
import java.util.*;

class IdleGameB {
	private static int[][] memo_table;
    private static int calculateMinimum(int maxLevel, int amountRequired, int[] values, int[] costs) {
    	memo_table=new int[maxLevel+1][costs[maxLevel]+1];
    	initialize(memo_table, maxLevel+1, costs[maxLevel]+1);
    	return calculateMin(maxLevel, 0, amountRequired, 0, values, costs);
    }
    private static void initialize(int[][] table, int numOfRows, int numOfCols) {
    	for (int row=0;row<numOfRows; row++ ) {
    		for(int col=0; col<numOfCols; col++) {
    			table[row][col]=-1;
    		}
    	}
    }
    private static int calculateMin(int maxLevel, int currLevel, int amountRequired, int currAmount, int[] values, int[] costs) {
    	if(memo_table[currLevel][currAmount]!=-1) {
			return memo_table[currLevel][currAmount];
		}
    	else {
	    	if (currAmount>=amountRequired) {
	    		return 0;
	    	}
	    	else if(currLevel==maxLevel && currAmount<amountRequired) {
	    		int value=values[maxLevel];
	    		int result=countTaps(currAmount, amountRequired, value);
	    		memo_table[currLevel][currAmount]=result;
	    		return result;
	    	}
	    	
	    	else {
	    		
	    		
	    		ArrayList<Integer> results= new ArrayList<Integer>();
	    		int result1=countTaps(currAmount,amountRequired, values[currLevel]);
	    		results.add(result1);
	    		for (int level=currLevel+1; level<=maxLevel; level++) {
	    			int cost=costs[level];
	    			int diff=cost-currAmount;
	    			int result;
	    			if(diff<=0) {
	    				result = calculateMin(maxLevel, level, amountRequired, currAmount-cost, values, costs );
	    			}
	    			else {
	    				int additionalTaps=countTaps(currAmount, cost, values[currLevel]);
	    				int newAmount=currAmount+additionalTaps*values[currLevel]-cost;
	    				result=additionalTaps + calculateMin(maxLevel, level, amountRequired,newAmount , values, costs );
	    			}
	    			results.add(result);
	    		}
	    		int answer=Collections.min(results);
	    		memo_table[currLevel][currAmount]=answer;
	    		return answer;
	    	}
    	}
    }
    private static int countTaps(int currAmount, int amountRequired, int value) {
		if (amountRequired<=currAmount) {
			return 0;
		}
		int diff=amountRequired-currAmount;
    	int taps= diff/value;
		if(taps*value+currAmount==amountRequired)
			return taps;
		else
			return taps+1;
    }

    public static void main(String[] args)  {
    	//System.setIn(new FileInputStream("test.txt"));
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int tc = 0; tc < testCases; tc++) {
            int maxLevel = sc.nextInt();
            int amountRequired = sc.nextInt();
            int[] values = new int[maxLevel + 1];
            for (int i = 0; i <= maxLevel; i++) {
                values[i] = sc.nextInt();
            }
            int[] costs = new int[maxLevel + 1];
            for (int i = 0; i <= maxLevel; i++) {
                costs[i] = sc.nextInt();
            }
            int solution = calculateMinimum(maxLevel, amountRequired, values, costs);
            System.out.println(solution);
        }
    }
}