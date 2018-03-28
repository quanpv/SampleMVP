package jp.com.javis.bicycle.login.presenter;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

import jp.com.javis.bicycle.base.presenter.BasePresenter;

/**
 * Created by QuanPV on 3/26/18.
 */

public interface LoginPresenter extends BasePresenter {

    void onSuccess();

    void onCancel();

    void onError(Exception e);
}

