package hw3_prj.view;

import hw3_prj.utils.Callbacks.MSG_Callback;

public abstract class View {

    public abstract void display(String msg);
    public MSG_Callback getCallback() {
        return this::display;
    }
}
