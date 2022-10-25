import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    private int rowsCount;
    private int columnsCount;
    private String[][] board;
    private String[][] mines;
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        getSizesFromUser();
        prepare();
        play();
    }

    private void play() {
        play(0);
    }

    private void play(int attempt) {
        int row;
        int column;
        System.out.print("Satır giriniz : ");
        row = scanner.nextInt();
        System.out.print("Sütun giriniz : ");
        column = scanner.nextInt();

        if (mines[row][column].equals("*")) {
            System.out.print("Kaybettiniz ! Oyun bitti !");
            return;
        }

        board[row][column] = String.valueOf(calculateMinesCount(row, column));

        printBoard();

        if (attempt < rowsCount * columnsCount / 4 * 3) play(++attempt);
    }


    public int calculateMinesCount(int row, int column) {
        int count = 0;
        if (checkForMine(row, column + 1)) count++;
        if (checkForMine(row + 1, column + 1)) count++;
        if (checkForMine(row + 1, column)) count++;
        if (checkForMine(row + 1, column - 1)) count++;
        if (checkForMine(row, column - 1)) count++;
        if (checkForMine(row - 1, column - 1)) count++;
        if (checkForMine(row - 1, column)) count++;
        if (checkForMine(row - 1, column + 1)) count++;
        return count;
    }

    private boolean checkForMine(int row, int column) {
        try {
            return mines[row][column].equals("*");
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private void getSizesFromUser() {
        System.out.print("Oyun boyutunu Giriniz, Satır : ");
        rowsCount = scanner.nextInt();
        System.out.print("Oyun boyutunu Giriniz, Sütun : ");
        columnsCount = scanner.nextInt();
    }

    private void prepare() {
        board = new String[rowsCount][columnsCount];
        mines = new String[rowsCount][columnsCount];
        fillBoard();
        fillMines(rowsCount * columnsCount / 4);

        for (String[] items : mines) {
            for (String item : items) {
              /*System.out.print(item);
                System.out.print(" ");*/
            }
            //System.out.println();
        }
    }


    private void fillBoard() {
        for (String[] strings : board) Arrays.fill(strings, "-");
    }

    private void fillMines(int count) {
        for (String[] strings : mines) Arrays.fill(strings, "-");
        Random random = new Random();
        while (count > 0) {
            int row = random.nextInt(rowsCount);
            int column = random.nextInt(columnsCount);
            if (mines[row][column].equals("*")) continue;
            mines[row][column] = "*";
            count--;
        }
    }

    private void printBoard() {
        for (String[] items : board) {
            for (String item : items) {
                System.out.print(item);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
