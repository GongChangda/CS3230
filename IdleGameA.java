import java.io.*;
import java.math.*;
import java.util.*;

public class IdleGameA {
    private static int calculateMinimum(int maxLevel, int amountRequired, int[] values, int[] costs) {
    	return calculateMin(maxLevel, amountRequired,values,costs,0,0,0);
    }
    
    private static int calculateMin(int maxLevel, int amountRequired, int[] values, int[] costs, int currLevel, int currAmount, int totalTap) {
    	if (currAmount >= amountRequired) {
    		return totalTap;
    	}
    	else {
    		if(currLevel == maxLevel) {
    			
    			return calculateMin(maxLevel, amountRequired,values,costs,currLevel,currAmount+values[currLevel],totalTap+1);
    		}
    		else {
    			if (currAmount-costs[currLevel+1]>=0) {
    				ArrayList<Integer> totalTaps = new ArrayList<Integer>();
    				
    				for(int i=currLevel+1;i<=maxLevel; i++) {
    					if(currAmount-costs[i]>=0) {
    						int number1=calculateMin(maxLevel, amountRequired,values,costs,i,currAmount-costs[i],totalTap);
    						totalTaps.add(number1);
    					}
    				}
    				int number2=calculateMin(maxLevel, amountRequired,values,costs,currLevel,currAmount+values[currLevel],totalTap+1);
    			    totalTaps.add(number2);
    				return Collections.min(totalTaps);
    			}
    			else {
    				return calculateMin(maxLevel, amountRequired,values,costs,currLevel,currAmount+values[currLevel],totalTap+1);
    			}
    		}
    	}
    }

    public static void main(String[] args) {
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