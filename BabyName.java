import java.util.*;
import java.io.*;
import java. io.FileNotFoundException;


public class BabyName{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int minYear = 2001, maxYear = 2010;
		String retry = null;
		boolean anotherTry = false;
		do {
			int year = isWithinRange(minYear, maxYear);
			String gender = isValid();
			System.out.println("Please enter the name: ");
			String name = input.next();
			int rank = 0;
			
			File nameFile = new File("babynamesranking" + year + ".txt");
			if(!nameFile.exists()) {
				System.out.println("There are no records for the year " + year);
			}
			
			 try {
		            Scanner scan = new Scanner(nameFile);
		            while (scan.hasNext()) {
		            	
		                String str = scan.nextLine();
		                str = str.replace('\t', ' ');
		                String[] tempStr = str.split("\\s+");
		                
		                if (gender.equalsIgnoreCase("M") && tempStr[1].equalsIgnoreCase(name))
		                    rank = new Integer(tempStr[0]);
		                else if (tempStr[3].equalsIgnoreCase(name))
		                    rank = new Integer(tempStr[0]);
		            }
		        } catch (FileNotFoundException fnf) {
		            fnf.getMessage();
		        }

		        if (rank == 0) {
		            System.out.println("The name " + name + " is not ranked in year " + year);
		        } else {
		        	if(gender.equalsIgnoreCase("M")) {
		        	    System.out.println("Boy name " + name + " is ranked #" + rank + " in year "+ year);
		        	}
		        	else {
		        		System.out.println("Girl name " + name + " is ranked #" + rank + " in year "+ year);
		        	}
		        }
		        
		        System.out.println("Enter another inquery (Y/N)?");
		        if(input.hasNext("Y") || input.hasNext("y")) {
		        	retry = input.next();
		        	anotherTry = true;
		        }else if(input.hasNext("N") || input.hasNext("n")){
		        	System.out.println("Thanks for you time. Have a nice day!");
		        	retry = input.next();
		        	anotherTry = false;
		        	break;
		        }
		        
		}while(anotherTry);
		
	}
	
	@SuppressWarnings("resource")
	public static int isWithinRange(int min, int max) {
		int tempYear = 0;
		boolean flag = false;
		Scanner in = new Scanner(System.in);
		do {
			System.out.print("Please enter the year: ");
			if(in.hasNextInt()) {
				int isValidYear;
				isValidYear = in.nextInt();
				if(isValidYear <= max && isValidYear >= min) {
					tempYear = isValidYear;
					flag = true;
					return tempYear;
				}else {
					System.out.println("Please enter a year between 2001 and 2010");
				}
			}else {
				System.out.println("Please enter a year between 2001 and 2010");
				in.next();
			}
		}while(!flag);
		return tempYear;
	}
	
	@SuppressWarnings("resource")
	public static String isValid() {
		String sxTemp = null;
		boolean flag = false;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Please enter the gender: " );
			if(in.hasNext("M") || in.hasNext("m") || in.hasNext("F") || in.hasNext("f")) {
				sxTemp = in.next();
				flag = true;
			}else {
				System.out.println("Please enter M or F");
				flag = false;
				in.next();
			}
		}while(!flag);
		return sxTemp;
	}
	
}