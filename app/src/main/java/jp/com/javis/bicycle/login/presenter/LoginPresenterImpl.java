package jp.com.javis.bicycle.login.presenter;

import android.support.annotation.NonNull;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

import jp.com.javis.bicycle.base.utils.Logger;
import jp.com.javis.bicycle.login.view.LoginView;

/**
 * Created by QuanPV on 3/26/18.
 */

public class LoginPresenterImpl implements LoginPresenter{

    private final LoginView mLoginView;

    public LoginPresenterImpl(@NonNull LoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void start() {
        mLoginView.onInit();
    }

    @Override
    public void onSuccess() {
        Logger.i("Login", "Login success");
    }

    @Override
    public void onCancel() {
        Logger.i("Login", "Cancel");
    }

    @Override
    public void onError(Exception e) {
        Logger.i("Login", "Login error:"+e.getMessage());
    }
}
