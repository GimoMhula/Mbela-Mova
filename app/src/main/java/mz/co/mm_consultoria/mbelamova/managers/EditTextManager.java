package mz.co.mm_consultoria.mbelamova.managers;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import mz.co.mm_consultoria.mbelamova.R;

public class EditTextManager {
    private Context context;

    public EditTextManager(Context context) {
        this.context=context;
    }

    public void checkEmptyFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if(TextUtils.isEmpty(getEditTextString(editText))){
                editText.setError(getEmptyError());
            }
        }
    }

    public String getEditTextString(EditText editText){
        return editText.getText().toString();
    }
    public int getEditTextInteger(EditText editText){
        return Integer.parseInt(getEditTextString(editText));
    }

    private String getEmptyError(){
        return getString(R.string.cannot_be_empty);
    }

    private String getString(int stringResource){
        return context.getString(stringResource);
    }
}
