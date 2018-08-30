import java.io.*;
import java.util.*;


public class CSX_351_HW3_16103042 {

	public static void main(String[] args) throws Exception{
		File file=new File("C:\\Users\\ACER\\Desktop\\csx-351-hw3-ketanchopra-master\\HW3-unsorted-keywords.txt");
		int noOfKeywords=0;					//Count variable for counting number of Keywords
		
		Scanner scanner=new Scanner(file);
		
		//loop for counting number of keywords in the given file
		while(scanner.hasNextLine()) {
			scanner.nextLine();
			noOfKeywords++;
		}
		//First Part done
		
		String arrayKeywords[]=new String[noOfKeywords];				//dynamically allocating memory for keywords to store in string array
		//Second Part done
		
		int i=0;
		scanner.close();
		
		scanner=new Scanner(file);
		
		//loop to store the keywords in a dynamically allocated array of strings
		while(scanner.hasNextLine()){
			arrayKeywords[i]=scanner.nextLine();
			i++;
		}
		//Third Part done
		
		scanner.close();
		sortArray(arrayKeywords);									//method to sort the keywords alphabetically
		//Fourth Part done
		
		file=new File("C:\\Users\\ACER\\Desktop\\csx-351-hw3-ketanchopra-master\\HW3-input-code.cpp");
		
		scanner=new Scanner(file);
		
		PrintWriter out=new PrintWriter("HW3_output_16103042.txt");		//output text file created to store the results in a desired format
		
		int lineCounter=0, keywordCount=0;
		
		while(scanner.hasNextLine()) {
			boolean isFound=false;
			lineCounter++;
			String line[]=scanner.nextLine().split("//");
			String arrayLine[]=line[0].split("\\t|,|;|\\.|!|-|:|\\[|\\]|\\(|\\)| +|\\*|/");							//split the string in the cpp program file ignoring the comments since they always start with "//" character
			String str=String.format("Line %3d", lineCounter);				//format the string in desired format and store in the string array
			for(String a: arrayLine) {
				boolean check=keywordSearch(arrayKeywords, a);
				if(check) {
					isFound=true;
					str+=String.format("%12s(%d)", a, line[0].indexOf(a));		//concatenate the string to meet the required format
					keywordCount++;
				}
			}
			if(isFound) {
				System.out.println(str);
				out.println(str);
			}
		}
		System.out.println("\nNumber of Keywords Found: "+keywordCount);
		out.println();
		out.print("Number of Keywords Found: "+keywordCount);			// Store the number of keywords in the output file
		scanner.close();
		out.close();
	}
	
	//Sort the given string array passes as array[] alphabetically
	public static void sortArray(String array[]) {
		int i,j;
		boolean check=true;
		int n=array.length;
		
		//check variable to check whether the array is already sorted in one go
		for(i=0;i<n-1&&check;i++) {
			check=false;
			for(j=0;j<n-i-1;j++) {
				if(array[j].compareTo(array[j+1]) > 0){
					String temp=array[j+1];
					array[j+1]=array[j];
					array[j]=temp;
					check=true;
				}
			}
		}
	}

	//method to Search a given keyword passed as string in the given string array
	
	public static boolean keywordSearch(String array[], String str) {
		int low=0;
		int high=array.length-1;
		int mid;
		
		while(low<=high) {
			mid=(low+high)/2;
			
			if(array[mid].compareTo(str)<0)
				low=mid+1;
			else if(array[mid].compareTo(str)>0)
				high=mid-1;
			else
				return true;
		}
		return false;
	}
}
