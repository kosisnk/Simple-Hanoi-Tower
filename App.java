import java.util.Scanner;
public class App{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("How many floors do you want?");
        int floors = scan.nextInt();
        int[] col1 = new int[floors];
        int[] col2 = new int[floors];
        int[] col3 = new int[floors];
        boolean win = false;
		
		
		// Fill first tower
        for(int i =floors-1,v=1; i>=0; i--,v++){
            col1[i] = v;
        }
		
		do{
            // Print towers
            for(int i=floors-1; i>=0;i--){
                System.out.print(col1[i] == 0 ? " " : col1[i]);
                System.out.print(col2[i] == 0 ? " " : col2[i]);
                System.out.print(col3[i] == 0 ? " " : col3[i]);
                System.out.println();
            }
			
			 //Move statemant
            System.out.print("Move from: ");
            int from = scan.nextInt();
            System.out.print("Move to: ");
            int to = scan.nextInt();
			
			if(from == to || from > 3 || to > 3 || from <= 0 || to <= 0){
				System.out.println("Invalid move!");
                continue;
            }
			
			int moveValue = 0;
			int fromIndex = 0;
            int[] fromCol = {};
            int[] targetCol = {};
			
			// Get source tower
            switch(from){
                case 1:
                    fromCol = col1;
                    break;                        
                case 2:
                    fromCol = col2;
                    break;
                case 3:
                    fromCol = col3;
                    break;
            }
			// Search upper floor
			for(int i=floors-1; i>=0; i--){
                if(fromCol[i] != 0){
                    moveValue = fromCol[i];
                }
                if(moveValue != 0){
                    fromIndex = i; // Save floor level, will be override if move is correct
                    break;
                }
            }
			// If tower is empty
			if(moveValue == 0){
				System.out.println("Invalid move!");
                continue;
			}
			
			//Try put, select target tower
            switch(to){
                case 1:
                    targetCol = col1;
                    break;                        
                case 2:
                    targetCol = col2;
                    break;
                case 3:
                    targetCol = col3;
                    break;
            }

            //search empty place
            for(int i=0;i<floors;i++){//Search from bottom
                if(targetCol[i] == 0){ // If is free space for floor
                    if(i== 0 || (i>0 && targetCol[i-1] > moveValue)){ // Check if lvl 0 is empty, or lower level is bigger
                        targetCol[i] = moveValue;
                        fromCol[fromIndex] = 0;
                    }
                }
            }
			
			 //win check
            int count = 0;
            for(int i=0;i<floors;i++){
                if(targetCol[i] != 0){ // just looking for empty levels in target tower
                    count++;
                }
            }
            if(count == floors){
                win = true;
                // Print towers
                for(int j=floors-1; j>=0;j--){
					System.out.print(col1[j] == 0 ? " " : col1[j]);
					System.out.print(col2[j] == 0 ? " " : col2[j]);
					System.out.print(col3[j] == 0 ? " " : col3[j]);
                    System.out.println();
                }
            }else{
                count=0;
            }

        }while(!win);
        System.out.println("You win!");


    }
}