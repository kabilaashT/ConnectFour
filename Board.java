package connectfour;

public class Board{
  private int[]columnToIndex={1,3,5,7,9,11,13};
  private int depth;
  private char winner;

  private char[][] board ={{'|','0','|','0','|','0','|','0','|','0','|','0','|','0','|'},
                           {'|','0','|','0','|','0','|','0','|','0','|','0','|','0','|'},
                           {'|','0','|','0','|','0','|','0','|','0','|','0','|','0','|'},
                           {'|','0','|','0','|','0','|','0','|','0','|','0','|','0','|'},
                           {'|','0','|','0','|','0','|','0','|','0','|','0','|','0','|'},
                           {'|','0','|','0','|','0','|','0','|','0','|','0','|','0','|'}};

  private int[] posToIndexRow ={0,0,0,0,0,0,0,
                                1,1,1,1,1,1,1,
                                2,2,2,2,2,2,2,
                                3,3,3,3,3,3,3,
                                4,4,4,4,4,4,4,
                                5,5,5,5,5,5,5};
  
  private int[] posToIndexColumn ={1,3,5,7,9,11,13,
                                   16,18,20,22,24,26,28,
                                   31,33,35,37,39,41,43,
                                   46,48,50,52,54,56,58,
                                   61,63,65,67,69,71,73,
                                   76,78,80,82,84,86,88};

  
  public Board(){
    depth = 0;
    winner = '?';
  }
  
  public boolean checkBoard(int position) {
    if (
      board[posToIndexRow[position]][posToIndexColumn[position]] == '1' 
      ||
      board[posToIndexRow[position]][posToIndexColumn[position]] == '2'
      ){
        return true;
      }
        return false;
    }

    public int updateBoard(int column, char player){
      for(int i=getBoard().length-1; i>=0; i--){
        if(getBoard()[i][columnToIndex[column]]=='0'){
          getBoard()[i][columnToIndex[column]]= player;
          return i;
        }
      }
      return -1;
    }

    public int leftUpWinnerCheck(int row, int column,int count, int moveRow, int columnIndex2, char player){
      //checking for left up diagonal winner
          
      count = 1;
  
      while (column > -1 && row > -1){
          if (board[row][columnToIndex[column]] == player){
              count+= 1;
              row-= 1;
              column-= 1;
        }else { 
              break;            
          }
        }
          
      row = moveRow + 1;
      column = columnIndex2 + 1;
          
      while (row < 6 && column < 7){            
          if (board[row][columnToIndex[column]] == player){
              count+= 1;
              row+= 1;
              column+= 1;
          }else{
              break;
          }
      }
      return count;
    }

    public int rightUpWinnerCheck(int row, int column,int count, int moveRow, int columnIndex2, char player){

      while (column > -1 && row < 6){
        if (board[row][columnToIndex[column]] == player){
            count+= 1;
            row+= 1;
            column-= 1;
        }else{
            break;            
        }
    }
         
    row = moveRow - 1;
    column = columnIndex2 + 1;
         
    while (row > -1 && column < 7){            
        if (board[row][columnToIndex[column]] == player){
            count+= 1;
            row-= 1;
            column+= 1;
        }else{
             break;
        }
    }
    return count;
    }
  
    public int horizontalWinnerCheck(int columnIndex, int count, int moveRow, char player, int columnIndex2){
      //checking for horizontal winner and check right
      while(columnIndex < 7 && count < 4){
        if(board[moveRow][columnToIndex[columnIndex]] == player){
          count++;
          columnIndex++;
        }else{
          break;
        }
      }
      columnIndex = columnIndex2-1;
      //check left
      if(count < 4){
        while(columnIndex >= 0 && count < 4){
          if(board[moveRow][columnToIndex[columnIndex]]== player){
            count++;
            columnIndex--;
          }else{
            break;
          }
        }
      }
      return count;
    }

    public char winner(int moveColumn, char player, int moveRow){
      int count = 1;
      int columnIndex = moveColumn+1;
      int columnIndex2 = moveColumn;
      moveRow = moveRow;
    //Get column and change to where it is in array
      moveColumn = columnToIndex[moveColumn];
    //checking for vertical winner
    if(moveRow <= 2){
      if(board[moveRow][moveColumn]== player 
        && board[moveRow+1][moveColumn]== player 
        && board[moveRow+2][moveColumn]== player 
        && board[moveRow+3][moveColumn]== player){
        winner = player;
        return player;
      }
    }

    count = horizontalWinnerCheck(columnIndex,count,moveRow,player,columnIndex2);
    //check if there has been a horizontal win
    if(count >= 4){
      winner = player;
      return player;
    }
    int row = moveRow - 1;
    int column = columnIndex2 - 1;
    count = leftUpWinnerCheck(row,column,count,moveRow,columnIndex2,player);
    if(count > 3){
      winner = player;
      return player;
    }
    //checking for right up diagonal winner
    row = moveRow + 1;
    column = columnIndex2 - 1;
    count = 1;
    count = rightUpWinnerCheck(row,column,count,moveRow,columnIndex2,player);
    if(count > 3){
      winner = player;
      return player;
    }
      if (depth == 42) {
        winner = 'T';
        return 'T';
      }
      winner = '?';
      return '?';
    }

    public int runMove(int column, char player){
      int updateBoardRow = updateBoard(column,player);
      if(updateBoardRow == -1){
        System.out.println("The column is full");
        return -1;
      }
      
      setDepth(getDepth()+1);
      return updateBoardRow;
    }

    public char[][]getBoard(){
      return board;
    }

    public int getDepth(){
      return depth;
    }
    
    public int getWinner(){
      return winner;
    }

    public void setWinner(char newWinner){
      winner = newWinner;
    }

    public char getSymbol(int row, int column){
      return board[row][columnToIndex[column]];
    }

    public int setSymbol(int row, int column, char symbol){
      board[row][columnToIndex[column]] = symbol;
      return 0;
    }
    
    public void setDepth(int newDepth){
      depth = newDepth;
    }

    public int returnSomething(){
        return 1;
    }

}
