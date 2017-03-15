package com.hx.simpleapp.ui.gan.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import com.hx.simpleapp.AppContext;
import com.hx.simpleapp.api.remote.ApiFactory;
import com.hx.simpleapp.base.adapter.ListBaseAdapter;
import com.hx.simpleapp.base.ui.BaseListFragment;
import com.hx.simpleapp.db.RealmHelper;
import com.hx.simpleapp.model.ReadRecordRealm;
import com.hx.simpleapp.model.response.gan.GanItem;
import com.hx.simpleapp.router.Router;
import com.hx.simpleapp.ui.gan.adapter.GanAdapter;
import com.trello.rxlifecycle.FragmentEvent;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArticleFragment extends BaseListFragment<GanItem> {

    @Override
    protected void sendRequest() {
        String keyword = getSearchKeyword();
        ApiFactory.getGanApi().getArticles(keyword, getPageSize(), mCurrentPage).compose(this.bindUntilEvent(FragmentEvent.STOP))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);

    }

    @NonNull
    private String getSearchKeyword() {
        String keyword = "";
        switch (mCatalog) {
            case 0:
                keyword = "Android";
                break;
            case 1:
                keyword = "iOS";
                break;
            case 2:
                keyword = "前端";
                break;
        }
        return keyword;
    }

    @Override
    protected ListBaseAdapter getListAdapter() {
        GanAdapter adapter = new GanAdapter();
        return adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GanItem item = mAdapter.getItem(position);
        if (item != null)
            Router.showDetail(getActivity(), item.getDesc(), item.getUrl(), "", "", "");
        RealmHelper realmHelper = new RealmHelper(AppContext.context());
        if (realmHelper.findReadRecord(item.getDesc()) != null) {
            return;
        }
        ReadRecordRealm recordRealm = new ReadRecordRealm();
        recordRealm.setId(item.getUrl());
        recordRealm.setTitle(item.getDesc());
        recordRealm.setTime(System.currentTimeMillis());
        realmHelper.addReadRecord(recordRealm);
    }
}
