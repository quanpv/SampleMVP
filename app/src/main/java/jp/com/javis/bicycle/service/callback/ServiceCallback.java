package jp.com.javis.bicycle.service.callback;

import com.android.volley.VolleyError;

import jp.com.javis.bicycle.service.common.ServiceResult;

/**
 * Created by QuanPV on 3/30/18.
 */

public interface ServiceCallback {
    void onSuccess(ServiceResult resultCode, Object response);

    void onError(VolleyError error);
}
