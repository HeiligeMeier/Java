import Board.Rules;

public class Test implements Rules {
    private int size;
    private int difficulty;
    private int[][] numbers;
    private int s = size * size;

    // Design eines Quadrats
    // 1 2 3 |
    // 4 5 6 |
    // 7 8 9 |
    // _______

    public Test(int size, int difficulty) {
        this.difficulty = difficulty;
        this.size = size;
        this.numbers = new int[size * size][size * size];
    }

    public int getRandomNummer () {
        int random = (int) (1 + (Math.random() * 90) % 9);
        return random;
    }

    public void fillNumbers() {
        for (int r = 0; r < s; r++) {
            for (int c = 0; c < s; c++) {
                numbers[r][c] = getRandomNummer();
            }
        }
    }

    public void drawField() {
        fillNumbers();
        System.out.print(' ');
        for (int i = 9 + 7 * (size - 1); i > 0; i--) {
            System.out.print('_');
        }
        for (int row = 1; row <= s; row++) {
            System.out.println("");
            for (int col = 0; col < s; col++) {
                if (col % 3 == 0) {
                    System.out.print("| ");
                }
                if (getRandomNummer() > difficulty) {
                    System.out.print(numbers[row - 1][col] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("|");
            if (row % 3 == 0) {
                System.out.println();
                System.out.print(' ');
                for (int i = 9 + 7 * (size - 1); i > 0; i--) {
                    System.out.print('_');
                }
            }
        }

    }


    @Override
    public Boolean numberIsValid(int r, int c) {
        int n = numbers[r][c];
        if (getNumsLeft(r, c, n) && getNumsRight(r, c, n) && getNumsLower(r, c, n) && getNumsUpper(r, c, n)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean getNumsLeft(int r, int c, int n) {
        if (c > 1) {
            for (; c > 1; --c) {
                if (n == numbers[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean getNumsRight(int r, int c, int n) {
        int s = size * size;
        if (c < s) {
            for (; c < s; ++c) {
                if (n == numbers[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean getNumsUpper(int r, int c, int n) {
        int s = size * size;
        if (r < s) {
            for (; r < s; ++r) {
                if (n == numbers[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean getNumsLower(int r, int c, int n) {
        if (r > 1) {
            for (; r > 1; --r) {
                if (n == numbers[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean getRect(int r, int c, int n) {
        if (r % size == 0 && c % size == 0) {

        }


        return true;
    }
}

