package connectfour;
import java.util.Scanner; 

public class TextUI{
    
    private Scanner scanner;

    public TextUI(){
        scanner = new Scanner(System.in);
    }

    public void printBoard(Board board){
        for(int i=0; i<board.getBoard().length; i++){
            for(int j=0; j<board.getBoard()[0].length; j++){
                System.out.print(board.getBoard()[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void printLine(String string){
        System.out.println(string);
    }

    public int getInt(){
        try{
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        }catch(Exception e){
            scanner.nextLine();
            return -10;
        }
    }

    public String getLine(){
        String string = scanner.nextLine();
        return string;
    }

}