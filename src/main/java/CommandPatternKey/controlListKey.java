package CommandPatternKey;

public class controlListKey {
    command[] slotOn;
    command[] slotOff;


    public controlListKey(int numOfKey) {
        slotOn = new command[numOfKey];
        slotOff = new command[numOfKey];

        command nocommand = new noCommand();
        for (command Off:slotOff){
            Off = nocommand;
        }
        for (command On:slotOn){
            On = nocommand;
        }


    }
    public void setCommand(int slot, command commandOn, command commandOff){
        slotOn[slot]=commandOn;
        slotOff[slot]=commandOff;

    }
    public void onButtonWasPress(int slot){
        if (slotOn[slot]!=null){
            slotOn[slot].execute();
        }
    }
    public void offButtonWasPress(int slot){
        if (slotOff[slot]!=null){
            slotOff[slot].execute();
        }
    }


}
