package jp.com.javis.bicycle.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.com.javis.bicycle.MainActivity;
import jp.com.javis.bicycle.R;
import jp.com.javis.bicycle.base.presenter.BasePresenter;
import jp.com.javis.bicycle.base.utils.NetworkStateManager;
import jp.com.javis.bicycle.base.utils.ProcessDialog;

/**
 * Created by QuanPV on 3/20/18.
 */

public abstract class BaseFragment <T extends BasePresenter> extends Fragment {

    private Unbinder mUnbind;

    private View mParent;

    protected T mPresenter;

    protected MainActivity activity;

    protected ProcessDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParent = getView(inflater, container);
        if (mParent == null)
            throw new NullPointerException("Chua khoi tao view");
        mUnbind = ButterKnife.bind(this, mParent);

        activity = (MainActivity) getActivity();
        mPresenter = getPresenter();
        mPresenter.start();

        return mParent;
    }

    public abstract View getView(LayoutInflater inflater, @Nullable ViewGroup container);

    public abstract T getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbind.unbind();
    }

    protected boolean isNetworkAvailable(boolean showToastMessage) {
        boolean isNetworkAvailable = NetworkStateManager.isNetworkAvailable(getContext());
        if (!isNetworkAvailable && showToastMessage) {
            Toast.makeText(getContext(), R.string.error_not_connected_to_network, Toast.LENGTH_LONG).show();
        }
        return isNetworkAvailable;
    }

    public void showProgress() {
        showProgress("Loading...", false);
    }

    public void showProgress(String message, boolean cancelable) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProcessDialog(activity);
        }
        try {
            mProgressDialog.showDialog(message, cancelable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void hideProgress() {
        if (mProgressDialog != null) {
            try {
                mProgressDialog.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
