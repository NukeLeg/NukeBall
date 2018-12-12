package com.sir.black.Tools.Menu;

import com.sir.black.Tools.Character.InitialObject.GameObject;

public class TransparentCheckBox extends Button {
    boolean isCheck;
    public TransparentCheckBox(GameObject[] elementMenu, boolean isCheck) {
        super(elementMenu);
        setCheck(isCheck);
    }

    public TransparentCheckBox(GameObject elementMenu, boolean isCheck) {
        super(elementMenu);
        setCheck(isCheck);
    }
    public void setCheck(boolean check) {
        isCheck = check;
        if (isCheck) status = 1;
        else status = 0;
    }
}
