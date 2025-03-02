package com.reactnativehidekeyboard;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = HideKeyboardModule.NAME)
public class HideKeyboardModule extends ReactContextBaseJavaModule {
    public static final String NAME = "HideKeyboard";
    ReactApplicationContext contexts;

    public HideKeyboardModule(ReactApplicationContext reactContext) {
      super(reactContext);
      contexts = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void hideKeyboard(Promise promise){
        try {
          InputMethodManager inputMethodManager = (InputMethodManager) contexts.getSystemService(Activity.INPUT_METHOD_SERVICE);
          if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
              getCurrentActivity().getCurrentFocus().getWindowToken(),
              0
            );
          }
          promise.resolve(true);
        } catch (Exception e){
          promise.resolve(false);
        }
    }

    @ReactMethod
    public void showKeyboard(Promise promise){
      try {
        InputMethodManager show = (InputMethodManager) contexts.getSystemService(Activity.INPUT_METHOD_SERVICE);
        show.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        promise.resolve(true);
      } catch(Exception e){
        promise.resolve(false);
      }
    }
}
