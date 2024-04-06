import java.util.*;
import java.lang.*;

class TicTacToe{
     static char [][]board;

     //constructor to initialize
    TicTacToe(){
        board=new char[3][3];
        initBoard();
    }

    //initialization of board values by replacing default value by empty space
   static void initBoard(){
         for(int i=0;i<board.length;i++){
             for(int j=0;j<board[0].length;j++){
                 board[i][j]=' ';
             }
         }
    }

    //display the table
    static void displayBoard() {
        System.out.println("------------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]+"  |  ");
            }
            System.out.println();
            System.out.println("------------------");
        }
    }

    //mark the box with user specified value
    static void placeMark(int row,int col,char mark){
        if(row>=0 && row<=2 && col>=0 && col<=2 && (mark=='X' || mark=='0')){
            board[row][col]=mark;
            return;
        }
        System.out.println("Invalid input");
        return;
    }

    //checking column win
    static boolean colWin(){
        for(int j=0;j<board[0].length;j++){
            if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j])
            {
                return true;
            }
        }
        return false;
    }

    //checking column win
    static boolean rowWin(){
        for(int i=0;i<board.length;i++){
            if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2])
            {
                return true;
            }
        }
        return false;
    }

    //checking diagnoal win
    static boolean diagonalWIn(){
           if(board[0][0]!=' '&& board[0][0]==board[1][1] && board[1][1]==board[2][2] ||board[0][2]!=' ' &&
                   board[0][2]==board[1][1] && board[1][1]==board[2][0]){
               return true;
           }
           return false;
    }

    static boolean checkDraw(){
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                if(board[i][j]==' ')
                    return false;
            }
        }
        return true;
    }

}

//Hierarchical Inheritance
abstract  class Player{   //abstract class
    String name;
    char mark;

    abstract  void makeMove();   //abstract method with no body

    //function to validate the row and column number entered by the user
    boolean isValid(int row,int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(TicTacToe.board[row][col]==' ')
                return true;
        }
        return false;
    }

}

class HumanPlayers extends Player{


     HumanPlayers(String name,char mark){
         this.name=name;
         this.mark=mark;
     }

     //make move function
    void makeMove(){
           int row;
           int col;
           Scanner sch=new Scanner(System.in);
           do{    //we should chance every time user enter wrong input
               System.out.println("Enter row and column assuming row and column starts from 0 to 2");
               row=sch.nextInt();
               col=sch.nextInt();
           }while(!isValid(row,col));
           TicTacToe.placeMark(row,col,mark);
    }

}

//Ai Players clas
class AIPlayers extends  Player{

    AIPlayers(String name,char mark){
        this.name=name;
        this.mark=mark;
    }

    //make move function
    void makeMove(){
        int row;
        int col;
        do{    //we should chance every time user enter wrong input
            Random r=new Random();
            row=r.nextInt(3);
            col=r.nextInt(3);
        }while(!isValid(row,col));
        TicTacToe.placeMark(row,col,mark);
    }
}


public class LauchGame {
    public static void main(String[] args) {
        int ch;
        String name1, name2;
        char mark;
        Boolean choice=true;
        Scanner sc = new Scanner(System.in);
        TicTacToe t=new TicTacToe();
        System.out.println("Enter your choice\n1 Two players\n2 AI Player");
        ch = sc.nextInt();
        Player cp;
     switch (ch) {

            case 1:
                System.out.println("Enter Player 1 name");
                name1 = sc.nextLine();
                sc.nextLine();
                System.out.println("Enter Player 2 name");
                name2 = sc.nextLine();
                HumanPlayers p1 = new HumanPlayers(name1, 'X');
                HumanPlayers p2 = new HumanPlayers(name2, '0');
                cp = p1;
                while (true) {
                    System.out.println(cp.name+ " turn ");
                    cp.makeMove();
                    TicTacToe.displayBoard();
                    if (TicTacToe.rowWin() || TicTacToe.colWin() || TicTacToe.diagonalWIn()) {
                        System.out.println(cp.name + " wins ");
                        break;
                    } else if (TicTacToe.checkDraw()) {
                        System.out.println("It is a draw");
                        break;
                    } else {
                        if (cp == p1)
                            cp = p2;
                        else
                            cp = p1;
                    }
                }
                break;

            case 2: System.out.println("Enter Player 1 name");
                sc.nextLine();
                 name1 = sc.nextLine();
                 HumanPlayers p3 = new HumanPlayers(name1, 'X');
                 AIPlayers p4 = new AIPlayers("AI", '0');
                 cp = p3;
                while (true) {
                    System.out.println(cp.name + " turn ");
                    cp.makeMove();
                    TicTacToe.displayBoard();
                    if (TicTacToe.rowWin() || TicTacToe.colWin() || TicTacToe.diagonalWIn()) {
                        System.out.println(cp.name + " wins ");
                        break;
                    } else if (TicTacToe.checkDraw()) {
                        System.out.println("It is a draw");
                        break;
                    } else {
                        if (cp==p3)
                            cp = p4;
                        else
                            cp = p3;
                    }
                }
                break;

            default:
                System.out.println("Invalid choice\nPlease Re-Enter the choice");
                break;


        }

    }
}