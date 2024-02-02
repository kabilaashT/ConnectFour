package connectfour;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.File;
import java.util.Scanner; 

public class ConnectFour{
  
  private int depth;
  private char turn;
  private Board board;
  private TextUI textui;

  public ConnectFour(){
    depth = 0;
    turn = '1';
    board = new Board();
    textui = new TextUI();
  }

  public int saveGame(String fileName){
    try{
      FileWriter saveFile = new FileWriter(fileName + ".txt");
      for(int i=0; i<board.getBoard().length; i++){
        for(int j=0; j<7; j++){
          saveFile.write(board.getSymbol(i,j)+(j<6?",":""));
        }
        saveFile.write(i<5?"\n":"");
      }
      saveFile.close();
    }catch(IOException e){
      textui.printLine("Invalid filename.");
      System.exit(1);
    }

    return 0;
  }

  public int loadGame(String filename){
    try{
      File loadFile = new File(filename+".txt");
      Scanner reader = new Scanner(loadFile);
      int i = 0;
      while(reader.hasNextLine()){
        String line = reader.nextLine();
        String[] items = line.split(",");
        int j;
        for(j=0;j<items.length;j++){
          board.setSymbol(i,j,items[j].charAt(0));
        }
        i++;
      }
    }catch(Exception e){
      textui.printLine("Invalid filename.");
      System.exit(1);
    }
    return 0;
  }

  public int getDepth(){
    return depth;
  }

  public void setDepth(int newDepth){
    depth = newDepth;
  }

  public void setTurn(char newTurn){
    turn = newTurn;
  }

  public char getTurn(){
    return turn;
  }

  public void setBoard(Board newBoard){
    board = newBoard;
  }

  public Board getBoard(){
    return board;
  }

  public void setTextUI(TextUI newTextUI){
    textui = newTextUI;
  }

  public TextUI getTextUI(){
    return textui;
  }

  public static void main(String[] args){
    ConnectFour connectfour = new ConnectFour();
    // connectfour.saveGame("abc");
    connectfour.textui.printLine("Enter the name of the file you want to load (enter 0 for new game): ");
    String fileName = connectfour.textui.getLine();
    if(!fileName.equals("0")){
      connectfour.loadGame(fileName);
    }
    // connectfour.loadGame("abc");

    while(connectfour.board.getWinner() == '?'){
      connectfour.textui.printBoard(connectfour.board);

      char turn;
      if(connectfour.board.getDepth()%2 == 0){
        turn = '1';
      }else{
        turn = '2';
      }
      connectfour.textui.printLine("Player " + turn + "'s turn.");
      connectfour.textui.printLine("Enter a column (press -1 to save and exit game): ");
      int col = connectfour.textui.getInt();

      if(col == -1){
        connectfour.textui.printLine("Enter name of file to save: ");
        String saveFileName = connectfour.textui.getLine();
        connectfour.saveGame(saveFileName);
        System.exit(0);

      }else if(col < 0 || col > 6){
        connectfour.textui.printLine("Enter a valid column number");
      }else{
        int runMove = connectfour.board.runMove(col,turn);
        //check error
        connectfour.board.winner(col,turn,runMove);
      }
    }
    connectfour.textui.printBoard(connectfour.board);
    if(connectfour.board.getWinner() == '1'){
      connectfour.textui.printLine("The winner is 1.");
    }else if(connectfour.board.getWinner() == '2'){
      connectfour.textui.printLine("The winner is 2.");
    }else if(connectfour.board.getWinner() == 'T'){
      connectfour.textui.printLine("The game is a tie.");
    }
  }
}