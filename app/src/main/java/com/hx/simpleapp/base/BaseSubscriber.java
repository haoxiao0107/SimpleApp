package com.hx.simpleapp.base;

import android.content.Context;

import com.hx.simpleapp.api.exception.ApiErrorCode;
import com.hx.simpleapp.api.exception.ApiErrorHelper;
import com.hx.simpleapp.api.exception.ApiException;
import com.hx.simpleapp.util.OSUtil;

import rx.Subscriber;

public class BaseSubscriber<T> extends Subscriber<T> {
    private Context mContext;

    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {
        if (!OSUtil.hasInternet()) {
            this.onError(new ApiException(ApiErrorCode.ERROR_NO_INTERNET, "network interrupt"));
            return;
        }

    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ApiErrorHelper.handleCommonError(mContext, e);
    }

    @Override
    public void onNext(T t) {

    }
}
