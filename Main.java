import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("TicTacToe");
        TicTacToe game = new TicTacToe(); // New TicTacToe Object
        char playerOne;
        char playerTwo;
        int row, col;

        // Sets who will play first
        if (game.firstPlayer)
        {
            playerOne = 'X';
            playerTwo = 'O';
        }
        else
        {
            playerOne = 'O';
            playerTwo = 'X';
        }

        game.printBoard();


        while (!game.gameOver)
        {
            System.out.printf("Player %c's turn%n", playerOne);
            System.out.printf("Player %c: Enter row (0, 1 or 2) : ", playerOne);
            row = scanner.nextInt(); //Get user input for row
            System.out.printf("Player %c: Enter row (0, 1 or 2) : ", playerOne);
            col = scanner.nextInt(); //Gert user input for column

            // If move is valid game board is updated and symbol is added to board
            if (game.validMove(row, col))
            {
                game.printSymbol(row,col,playerOne);
            }
            // If user performs an invalid move, invalid move is printed and user loses move
            else
                System.out.println("Invalid Move");

            // Updated game beard is printed
            game.printBoard();

            // Game states is checked for appropriate player
            game.printStatus(playerOne);

            // If there is a winner or a draw game ends
            if(game.gameOver)
                break;


            // Player 2's turn
            // The following code is same as the previous code but for player 2
            System.out.printf("Player %c's turn%n", playerTwo);
            System.out.printf("Player %c: Enter row (0, 1 or 2) : ", playerTwo);
            row = scanner.nextInt();
            System.out.printf("Player %c: Enter row (0, 1 or 2) : ", playerTwo);
            col = scanner.nextInt();

            if (game.validMove(row, col))
            {
                game.printSymbol(row,col,playerTwo);
            }

            else
                System.out.println("Invalid Move");

            game.printBoard();
            game.printStatus(playerTwo);
        }


    }
}

class TicTacToe{
    int BOARDSIZE = 3;
    enum Status {WIN, DRAW, CONTINUE};
    private final char[][] board;
    boolean firstPlayer;
    boolean gameOver = false;

    //Constructor method
    public TicTacToe()
    {
        board = new char[BOARDSIZE][BOARDSIZE];

        //initialize tictactoe board to empty
        for (int i = 0; i < BOARDSIZE; i++) {
            for  (int j = 0; j < BOARDSIZE; j++)
            {
                board[i][j] = ' ';
            }
        }

        // randomly initializes first player to either true or false
        Random rand = new Random();
        int randomNum =  rand.nextInt(100);
        firstPlayer = randomNum % 2 == 0;

    }

    // Adds the symbol to the board array
    public void printSymbol(int row, int col, char symbol)
    {
        board[row][col] = symbol;
    }

    // Checks to see if the operation is valid
    // Operation is valid if row and col are inside the array and the array is empty
    public boolean validMove(int row, int col)
    {
        if (row <= 2 && col <= 2)
        {
          if (row >= 0 && col >= 0)
          {
              return board[row][col] == ' ';
          }
          return false;
        }
        return false;
    }

    // Checks game status
    public Status gameStatus()
    {
        int hit = 0;

        // This four loop iterates through the array to see if its empty
        // It keeps a hit counter and increments for every empty space
        for (int i = 0; i < BOARDSIZE; i++) {
            for  (int j = 0; j < BOARDSIZE; j++)
            {
                if (board[i][j] == ' ')
                    hit++;
            }
        }

        // If the array is fill there will be 0 hits and the game will end in a draw
        if (hit == 0)
        {
            gameOver = true;
            return Status.DRAW;

        }

        // CHECK FOR A WIN

        // Checks rows first
        // For loop goes through each row making checking it there is a win
        // It compared each symbol to each other making sure not to compare a blank space
        for (int i = 0; i < BOARDSIZE; i++)
        {
            if(board[i][0] != ' ' && (board[i][0] == board[i][1] && board[i][1] == board[i][2]))
                {
                gameOver = true;
                return Status.WIN;
                }
        }

        // Checks columns second
        // Same concept as code that checks rows first
        for (int i = 0; i < BOARDSIZE; i++)
        {
            if(board[0][i] != ' ' && (board[0][i] == board[1][i] && board[1][i] == board[2][i]))
            {
                gameOver = true;
                return Status.WIN;
            }
        }

        // Checks diagonals last
        // There are two possible diagonal combinations so we check the center to make sure its not empty first
        // We then compare what's in the center to the corresponding diagonal boxes
        if (board[1][1] != ' ')
        {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
            {
                gameOver = true;
                return Status.WIN;
            }
            else if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
                {
                gameOver = true;
                return Status.WIN;
                }
        }

        // If program make it this far there is no winner or draw so status remains continued
        return Status.CONTINUE;
    }

    // Prints the final status after a win or loss
    // If there is a winner the argument is used to print out the correct winner
    public void printStatus(char player)
    {
      
        if (gameStatus() == Status.WIN)
            {
            System.out.printf("Player %c Wins%n", player);
            }
         else if (gameStatus() == Status.DRAW)
            {
            System.out.printf("Draw%n");
            }

    }

    // Prints board
    public void printBoard(){
        System.out.println(" ----------------------- ");
        System.out.println("|       |       |       |");
        System.out.printf("|   %c   |   %c   |   %c   |%n", board[0][0], board[0][1], board[0][2]);
        System.out.println("|_______|_______|_______|");
        System.out.println("|       |       |       |");
        System.out.printf("|   %c   |   %c   |   %c   |%n", board[1][0], board[1][1], board[1][2]);
        System.out.println("|_______|_______|_______|");
        System.out.println("|       |       |       |");
        System.out.printf("|   %c   |   %c   |   %c   |%n", board[2][0], board[2][1], board[2][2]);
        System.out.println("|_______|_______|_______|");
    }
}
