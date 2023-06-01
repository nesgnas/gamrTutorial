package CommandPatternKey;

public class keyOn implements command{
    keyBoard keyBoard;

    public keyOn(CommandPatternKey.keyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    @Override
    public void execute() {
        keyBoard.onKey();

    }
}
