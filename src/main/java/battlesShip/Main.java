package battlesShip;
import java.util.Scanner;

public class Main {
    private static final int BOARD_SIZE = 10;
    private static final int MAX_SHIPS = 3;

    private static final int SHIP_SIZE_1 = 2;
    private static final int SHIP_SIZE_2 = 3;
    private static final int SHIP_SIZE_3 = 4;

    private static final char WATER = '~';
    private static final char SHIP = '#';
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] board1 = new char[BOARD_SIZE][BOARD_SIZE];
        char[][] board2 = new char[BOARD_SIZE][BOARD_SIZE];

        initializeBoard(board1);
        initializeBoard(board2);

        placeShips(board1, "Jugador 1");
        placeShips(board2, "Jugador 2");

        boolean gameOver = false;
        boolean player1Turn = true;

        while (!gameOver) {
            char[][] attackingBoard;
            char[][] defendingBoard;
            String playerName;

            if (player1Turn) {
                attackingBoard = board2;
                defendingBoard = board1;
                playerName = "Jugador 1";
            } else {
                attackingBoard = board1;
                defendingBoard = board2;
                playerName = "Jugador 2";
            }

            System.out.println("\n" + playerName + ", es tu turno de atacar.");
            int[] attack = getAttackPosition();
            int row = attack[0];
            int col = attack[1];

            if (defendingBoard[row][col] == SHIP) {
                attackingBoard[row][col] = HIT;
                defendingBoard[row][col] = HIT;
                System.out.println("¡Le has dado a un barco!");
                if (allShipsSunk(defendingBoard)) {
                    gameOver = true;
                    System.out.println("\n¡" + playerName + " ha ganado!");
                }
            } else {
                attackingBoard[row][col] = MISS;
                System.out.println("¡Has fallado!");
            }

            player1Turn = !player1Turn;
        }
    }

    private static void initializeBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = WATER;
            }
        }
    }

    private static void placeShips(char[][] board, String playerName) {
        System.out.println(playerName + ", configura tus barcos:");

        int shipsPlaced = 0;

        while (shipsPlaced < MAX_SHIPS) {
            int shipSize;
            char shipSymbol;
            switch (shipsPlaced) {
                case 0:
                    shipSize = SHIP_SIZE_1;
                    shipSymbol = 'A';
                    break;
                case 1:
                    shipSize = SHIP_SIZE_2;
                    shipSymbol = 'B';
                    break;
                default:
                    shipSize = SHIP_SIZE_3;
                    shipSymbol = 'C';
                    break;
            }

            System.out.println("Barco de tamaño " + shipSize + " (" + shipSymbol + "):");

            int[] shipPosition = getShipPosition();
            int row = shipPosition[0];
            int col = shipPosition[1];
            boolean isVertical = shipPosition[2] == 1;

            if (canPlaceShip(board, row, col, shipSize, isVertical)) {
                placeShip(board, row, col, shipSize, isVertical, shipSymbol);
                shipsPlaced++;
            } else {
                System.out.println("No es posible colocar el barco en esa posición. Inténtalo de nuevo.");
            }

            printBoard(board);
        }
    }

    private static boolean canPlaceShip(char[][] board, int row, int col, int shipSize, boolean isVertical) {
        if (isVertical) {
            if (row + shipSize > BOARD_SIZE) {
                return false;
            }
            for (int i = row; i < row + shipSize; i++) {
                if (board[i][col] != WATER) {
                    return false;
                }
            }
        } else {
            if (col + shipSize > BOARD_SIZE) {
                return false;
            }
            for (int i = col; i < col + shipSize; i++) {
                if (board[row][i] != WATER) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void placeShip(char[][] board, int row, int col, int shipSize, boolean isVertical, char shipSymbol) {
        if (isVertical) {
            for (int i = row; i < row + shipSize; i++) {
                board[i][col] = shipSymbol;
            }
        } else {
            for (int i = col; i < col + shipSize; i++) {
                board[row][i] = shipSymbol;
            }
        }
    }

    private static int[] getShipPosition() {
        System.out.print("Fila (0-9): ");
        int row = scanner.nextInt();
        System.out.print("Columna (0-9): ");
        int col = scanner.nextInt();
        System.out.print("Orientación (0 = horizontal, 1 = vertical): ");
        int orientation = scanner.nextInt();
        return new int[]{row, col, orientation};
    }

    private static int[] getAttackPosition() {
        System.out.print("Fila a atacar (0-9): ");
        int row = scanner.nextInt();
        System.out.print("Columna a atacar (0-9): ");
        int col = scanner.nextInt();
        return new int[]{row, col};
    }

    private static void printBoard(char[][] board) {
        System.out.println("   0 1 2 3 4 5 6 7 8 9");
        System.out.println("  -------------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|" + i);
        }
        System.out.println("  -------------------");
        System.out.println("   0 1 2 3 4 5 6 7 8 9");
    }

    private static boolean allShipsSunk(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == SHIP) {
                    return false;
                }
            }
        }
        return true;
    }
}
