package Board;

public interface Rules {
    Boolean numberIsValid(int row, int col);
    Boolean getNumsLeft(int row, int col, int num);
    Boolean getNumsRight(int row, int col, int num);
    Boolean getNumsUpper(int row, int col, int num);
    Boolean getNumsLower(int row, int col, int num);
    Boolean getRect(int row, int col, int num);
}
