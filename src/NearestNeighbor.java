import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;

	public class NearestNeighbor {

		private static int rows = 75;
		private static int cols = 4; //data entries
	
		public NearestNeighbor() {
	
			trainArr = new double [rows][cols];
				testArr = new double [rows][cols];
	
			trainTypes = new String [rows];
				testTypes = new String [rows];

		}
	
		private static double trainArr [][];
		private static double testArr [][]; //2d array for data sets
	
		private static String trainTypes [];
		private static String testTypes []; //plant species

	
public static void main(String[] args) throws Exception { 
	
	System.out.println("Programming Fundamentals\nName: Allison MacDonald\nAssignment #3");
	
	NearestNeighbor  data = new NearestNeighbor();
	
	System.out.println("Please Enter Path for Training Data: ");
		String trainPath;
		Scanner scan = new Scanner(System.in);
		trainPath = scan.nextLine();
		
		data.dataImport(trainPath, trainArr, trainTypes);
		
	
	System.out.println("Please Enter Path for Testing Data: ");
		String testPath;
		testPath = scan.nextLine();
		
		data.dataImport(testPath, testArr, testTypes);
		scan.close();
		
	double dataExport = data.accuracyMethod();
	
			System.out.println("ACCURACY: " + dataExport);

	}
	
	public void dataImport(String pathFile, double entries [][], String types[]) throws Exception {
	
		Scanner scanData = new Scanner(new FileReader(pathFile));
			int a = 0;
			
		while (scanData.hasNextLine()) {
			
			String input = scanData.nextLine();
			String [] arr = null;
			
				if (input != null) {
					
					arr = input.split(",");
					}
				
					if (arr != null && arr.length == 5) {
					
						entries[a][0] = Double.parseDouble(arr[0]);
						entries[a][1] = Double.parseDouble(arr[1]);
						entries[a][2] = Double.parseDouble(arr[2]);
						entries[a][3] = Double.parseDouble(arr[3]);
					
							types[a] = arr[4];
								a++;
			}
		}
	}	

	private double accuracyMethod() {
			double minimumDis = 999999;
			double accuratePred = 0;
			int closestSample = 0;
		

			for (int y = 0; y < rows; y++) {
				minimumDis = 999999;
				for (int x = 0; x < rows; x++) {
					double calcDistance = dist(trainArr[x][0], trainArr[x][1], trainArr[x][2], trainArr[x][3], 
													testArr[y][0], testArr[y][1], testArr[y][2], testArr[y][3]);
					if(calcDistance < minimumDis) {
						minimumDis = calcDistance;
						closestSample = x;
				}
			}
				
				if (testTypes[y].equals(trainTypes[closestSample])) {
					accuratePred++;
				}

				System.out.println( y + 1 + ": " + testTypes[y] + " " + trainTypes[closestSample]);
			
		}
			
			double predictionAccuracy  = accuratePred/rows;
			return predictionAccuracy;
	}
			
	public double dist(double slx, double swx, double plx, double pwx, double sly, double swy, double ply, double pwy) {
				
			
				double dis1 = Math.pow((slx - sly), 2);
				double dis2 = Math.pow((swx - swy), 2);
				double dis3 = Math.pow((plx - ply), 2);
				double dis4 = Math.pow((pwx - pwy),2);
				
			return Math.sqrt(dis1 + dis2 + dis3 + dis4);
			
			}	
		}



