package Board;

public class Board implements Rules {
    private final int size;
    private final int difficulty;
    private final int[][] numbers;

    // Design eines Quadrats
    // 1 2 3 |
    // 4 5 6 |
    // 7 8 9 |
    // _______

    public Board(int size, int difficulty) {
        this.difficulty = difficulty;
        this.size = size;
        this.numbers = new int[size * size][size * size];
    }

    public void printBoard() {

    }

    public int getRandomNummer () {
        return (int) (1 + (Math.random() * 90) % (size * size));
    }

    public void fillNumbers() {
        int[] r1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r4 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r6 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r7 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r8 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] r9 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c9 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c8 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c7 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c6 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c4 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec4 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec6 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec7 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec8 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] rec9 = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        numbers[0][0] = r1[(int) Math.random() * r1.length];
        r1.
        numbers[0][1] = r1[(int) Math.random() * r1.length];
        numbers[0][2] = r1[(int) Math.random() * r1.length];
        numbers[0][3] = r1[(int) Math.random() * r1.length];
        numbers[0][4] = r1[(int) Math.random() * r1.length];
        numbers[0][5] = r1[(int) Math.random() * r1.length];
        numbers[0][6] = r1[(int) Math.random() * r1.length];
        numbers[0][7] = r1[(int) Math.random() * r1.length];
        numbers[0][8] = r1[(int) Math.random() * r1.length];
        numbers[0][9] = r1[(int) Math.random() * r1.length];
        numbers[1][1] = (int) Math.random() * r1.length;
        numbers[1][2] = (int) Math.random() * r1.length;
        numbers[1][3] = (int) Math.random() * r1.length;
        numbers[1][4] = (int) Math.random() * r1.length;
        numbers[1][5] = (int) Math.random() * r1.length;
        numbers[1][6] = (int) Math.random() * r1.length;
        numbers[1][7] = (int) Math.random() * r1.length;
        numbers[1][8] = (int) Math.random() * r1.length;
        numbers[1][9] = (int) Math.random() * r1.length;
        numbers[2][1] = (int) Math.random() * r1.length;
        numbers[2][2] = (int) Math.random() * r1.length;
        numbers[2][3] = (int) Math.random() * r1.length;
        numbers[2][4] = (int) Math.random() * r1.length;
        numbers[2][5] = (int) Math.random() * r1.length;
        numbers[2][6] = (int) Math.random() * r1.length;
        numbers[2][7] = (int) Math.random() * r1.length;
        numbers[2][8] = (int) Math.random() * r1.length;
        numbers[2][9] = (int) Math.random() * r1.length;

//        for (int r = 0; r < size * size; r++) {
//            for (int c = 0; c < size * size; c++) {
//                numbers[r][c] = getRandomNummer();
//                while (!numberIsValid(r, c)) {
//                    numbers[r][c] = getRandomNummer();
//                }
//            }
//        }
    }

    public void drawField() {
        fillNumbers();
        System.out.print(' ');
        for (int i = size * size + 7 * (size - 1); i > 0; i--) {
            System.out.print('_');
        }
        for (int row = 1; row <= size * size; row++) {
            System.out.println();
            for (int col = 0; col < size * size; col++) {
                if (col % size == 0) {
                    System.out.print("| ");
                }
                if (getRandomNummer() > difficulty) {
                    System.out.print(numbers[row-1][col] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("|");
            if (row % size == 0) {
                System.out.println();
                System.out.print(' ');
                for (int i = size * size + 7 * (size - 1); i > 0; i--) {
                    System.out.print('_');
                }
            }
        }

    }


    @Override
    public Boolean numberIsValid(int r, int c) {
        int n = numbers[r][c];
        return getNumsLeft(r, c, n) && getNumsRight(r, c, n) && getNumsLower(r, c, n) && getNumsUpper(r, c, n) && getRect(r, c, n);
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
        // Fall oben links
        if (r % size == 1 && c % size == 1) {
            int count = 0;
            for (int i = 0; i < 3; r++) {
                if (count > 0) {
                    if (n == numbers[r][c] || n == numbers[r][c+1] || n == numbers[r][c+2])
                        return false;
                } else {
                    if (n == numbers[r][c+1] || n == numbers[r][c+2])
                        return false;
                }
                count++;
                i++;
            }
        }
        // Fall für oben mitte
        if (r % size == 1 && c % size == 2) {
            int count = 0;
            for (int i = 0; i < 3; r++) {
                if (count > 0) {
                    if (n == numbers[r][c-1] || n == numbers[r][c] || n == numbers[r][c+1])
                        return false;
                } else {
                    if (n == numbers[r][c-1] || n == numbers[r][c+1])
                        return false;
                }
                count++;
                i++;
            }
        }
        // Fall für oben rechts
        if (r % size == 1 && c % size == 0) {
            int count = 0;
            for (int i = 0; i < 3; r++) {
                if (count > 0) {
                    if (n == numbers[r][c-2] || n == numbers[r][c-1] || n == numbers[r][c])
                        return false;
                } else {
                    if (n == numbers[r][c-2] || n == numbers[r][c-1])
                        return false;
                }
                count++;
                i++;
            }
        }
        // Fall für mitte links
        if (r % size == 2 && c % size == 1) {
            int count = 0;
            for (int i = 0; i < 3; c++) {
                if (count > 0) {
                    if (n == numbers[r-1][c] || n == numbers[r][c] || n == numbers[r+1][c])
                        return false;
                } else {
                    if (n == numbers[r-1][c] || n == numbers[r+1][c])
                        return false;
                }
                count++;
                i++;
            }
        }
        // Fall für mitte mitte
        if (r % size == 2 && c % size == 2) {
            if (n == numbers[r-1][c-1] || n == numbers[r-1][c+1] || n == numbers[r-1][c] || n == numbers[r][c-1] ||
                    n == numbers[r][c+1] || n == numbers[r+1][c-1] || n == numbers[r+1][c] || n == numbers[r+1][c+1]) {
                return false;
            }
        }
        // Fall für mitte rechts
        if (r % size == 2 && c % size == 0) {
            int count = 0;
            for (int i = 0; i < 3; c--) {
                if (count > 0) {
                    if (n == numbers[r-2][c] || n == numbers[r-1][c] || n == numbers[r][c])
                        return false;
                } else {
                    if (n == numbers[r-2][c] || n == numbers[r-1][c])
                        return false;
                }
                count++;
                i++;
            }
        }
        // Fall für unten links
        if (r % size == 0 && c % size == 1) {
            int count = 0;
            for (int i = 0; i < 3; r--) {
                if (count > 0) {
                    if (n == numbers[r][c+2] || n == numbers[r][c+1] || n == numbers[r][c])
                        return false;
                } else {
                    if (n == numbers[r][c+2] || n == numbers[r][c+1])
                        return false;
                }
                count++;
                i++;
            }
        }
        // Fall für unten mitte
        if (r % size == 0 && c % size == 2) {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                if (count > 0) {
                    if (n == numbers[r][c-1] || n == numbers[r][c] || n == numbers[r][c+1])
                    return false;
                } else {
                    if (n == numbers[r][c-1] || n == numbers[r][c+1])
                    return false;
                }
                count++;
                r++;
            }
        }
        // Fall für unten rechts
        if (r % size == 0 && c % size == 0) {
            int count = 0;
            for (int i = 0; i < 3; r--) {
                if (count > 0) {
                    if (n == numbers[r][c-2] || n == numbers[r][c-1] || n == numbers[r][c])
                    return false;
                } else {
                    if (n == numbers[r][c-2] || n == numbers[r][c-1])
                    return false;
                }
                count++;
                i++;
            }
        }
        return true;
    }
}
