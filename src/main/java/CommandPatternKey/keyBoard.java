package CommandPatternKey;

import java.util.ArrayList;

public class keyBoard {

    public static ArrayList<keyBoard> keyBoards = new ArrayList<keyBoard>();
    private String cmt;
    private int valueKey;

    private Boolean state;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public int getValueKey() {
        return valueKey;
    }

    public void setValueKey(int valueKey) {
        this.valueKey = valueKey;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }



    public keyBoard(String cmt,int keyValue) {
        this.cmt = cmt;
        this.valueKey=keyValue;

    }

    public void onKey(){
        setState(true);

        System.out.println(getCmt());

        System.out.println("Was Press");
        System.out.println("___________");
    }
    public   void ofKey(){
        setState(false);

        System.out.println(getCmt());

        System.out.println("unPress");
        System.out.println("___________");
    }
}
