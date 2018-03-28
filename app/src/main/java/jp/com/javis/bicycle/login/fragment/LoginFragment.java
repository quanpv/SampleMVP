package jp.com.javis.bicycle.login.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.Arrays;

import butterknife.BindView;
import jp.com.javis.bicycle.R;
import jp.com.javis.bicycle.base.fragment.BaseFragment;
import jp.com.javis.bicycle.login.presenter.LoginPresenter;
import jp.com.javis.bicycle.login.presenter.LoginPresenterImpl;
import jp.com.javis.bicycle.login.view.LoginView;

/**
 * Created by QuanPV on 3/26/18.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginView {

    @BindView(R.id.login_button)
    LoginButton loginButton;
    @BindView(R.id.login_button_twitter)
    TwitterLoginButton twitterLoginButton;

    private static final String EMAIL = "email";
    private static final String USER_POSTS = "user_posts";

    private CallbackManager mCallbackManager;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onInit() {
        // facebook
        mCallbackManager = CallbackManager.Factory.create();
        // Set the initial permissions to request from the user while logging in
        loginButton.setReadPermissions(Arrays.asList(EMAIL, USER_POSTS));

        loginButton.setFragment(this);

        // Register a callback to respond to the user
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mPresenter.onSuccess();
                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                mPresenter.onCancel();
            }

            @Override
            public void onError(FacebookException e) {
                // Handle exception
                mPresenter.onError(e);
                Toast.makeText(getContext(), "Login failure", Toast.LENGTH_SHORT).show();
            }
        });

        // twitter
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                mPresenter.onSuccess();
                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                mPresenter.onError(exception);
                Toast.makeText(getContext(), "Login failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View getView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);// facebook
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);// twitter
    }
}
