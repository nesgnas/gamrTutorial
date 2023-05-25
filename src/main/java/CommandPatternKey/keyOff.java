package CommandPatternKey;

public class keyOff implements command{
    keyBoard keyBoard;

    public keyOff(CommandPatternKey.keyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    @Override
    public void execute() {
        keyBoard.ofKey();
    }
}
