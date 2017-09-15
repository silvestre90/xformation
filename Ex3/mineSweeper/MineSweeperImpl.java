package mineSweeper;

import java.util.HashSet;
import java.util.Set;

public class MineSweeperImpl implements MineSweeper {

    private static final char NEW_LINE = '\n';
    private static final char MINE = '*';
    private static final char EMPTY = '.';

    private static final Set<Character> legalCharacters;

    static {
        legalCharacters = new HashSet<>();
        legalCharacters.add(NEW_LINE);
        legalCharacters.add(MINE);
        legalCharacters.add(EMPTY);
    }

    private String mineField;
    boolean[][] fieldWithBoundaries;
    private int rowNumber;
    private int rowLength;

    @Override
    public String getHintField() throws IllegalStateException {
        if (mineField.length() == 0) {
            return "";
        }


        // sol[i][j] = # bombs adjacent to cell (i, j)
        int[][] sol = new int[rowNumber + 2][rowLength + 2];
        for (int i = 1; i <= rowNumber; i++)
            for (int j = 1; j <= rowLength; j++)
                // (ii, jj) indexes neighboring cells
                for (int ii = i - 1; ii <= i + 1; ii++)
                    for (int jj = j - 1; jj <= j + 1; jj++)
                        if (fieldWithBoundaries[ii][jj]) sol[i][j]++;

        // print solution
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= rowNumber; i++) {
            for (int j = 1; j <= rowLength; j++) {

                 if (fieldWithBoundaries[i][j]) {
                    stringBuilder.append(MINE);
                } else {
                    stringBuilder.append(sol[i][j]);
                }
                if (j == rowLength && i!=rowNumber){
                    stringBuilder.append(NEW_LINE);
                }
            }
        }


//        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {
        String spliterator = "\n";
        validateIllegalCharacters(mineField);

        String[] rows = mineField.split(spliterator);
        validateDimensions(rows);

        fieldWithBoundaries = getFieldArray(rows);
        this.mineField = mineField;
    }

    private boolean[][] getFieldArray(String[] rows) {
        rowNumber = rows.length;
        rowLength = rowNumber != 0 ? rows[0].length() : 0;

        boolean[][] fieldWithBoundaries = new boolean[rowNumber + 2][rowLength + 2];
        for (int i = 1; i <= rowNumber; i++) {
            String currentRow = rows[i - 1];
            for (int j = 1; j <= rowLength; j++) {
                Character ch = currentRow.charAt(j - 1);
                fieldWithBoundaries[i][j] = getBomb(ch);
            }
        }

//        System.out.println(fieldWithBoundaries);
        return fieldWithBoundaries;
    }

    private boolean getBomb(Character ch) {
        return ch.equals(MINE);
    }

    private void validateDimensions(String[] rows) {
        if (rows.length == 0) {
            return;
        }

        int rowLenght = rows[0].length();
        for (String row : rows) {
            if (row.length() != rowLenght) {
                throw new IllegalArgumentException("This is not rectangle!!!");
            }
        }
    }

    private void validateIllegalCharacters(String mineField) {
        for (Character ch : mineField.toCharArray()) {
            if (!legalCharacters.contains(ch)) {
                throw new IllegalArgumentException("Illlegal character");
            }
        }
    }
}
