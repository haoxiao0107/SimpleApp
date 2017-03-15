package com.hx.simpleapp.ui.setting.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.hx.simpleapp.AppContext;
import com.hx.simpleapp.R;
import com.hx.simpleapp.base.adapter.ListBaseAdapter;
import com.hx.simpleapp.base.ui.BaseListFragment;
import com.hx.simpleapp.db.RealmHelper;
import com.hx.simpleapp.model.ReadRecord;
import com.hx.simpleapp.model.ReadRecordRealm;
import com.hx.simpleapp.model.response.wx.WXResult;
import com.hx.simpleapp.router.Router;
import com.hx.simpleapp.ui.setting.adapter.RecentReadAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by God
 * on 2016/10/7.
 */

public class RecentReadFragment extends BaseListFragment<ReadRecord> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void sendRequest() {
        RealmHelper realmHelper = new RealmHelper(AppContext.context());


        Observable.just(realmHelper.findAllReadRecord()).flatMap(new Func1<List<ReadRecordRealm>, Observable<WXResult<List<ReadRecord>>>>() {

            @Override
            public Observable<WXResult<List<ReadRecord>>> call(List<ReadRecordRealm> likeRecordRealms) {
                List<ReadRecord> records = new ArrayList<>();
                ReadRecord record = null;
                for (ReadRecordRealm recordRealm : likeRecordRealms) {
                    record = new ReadRecord();
                    record.setId(recordRealm.getId());
                    record.setImage(recordRealm.getImage());
                    record.setTime(recordRealm.getTime());
                    record.setTitle(recordRealm.getTitle());
                    record.setType(recordRealm.getType());
                    records.add(record);

                }

                final WXResult<List<ReadRecord>> result = new WXResult<>();
                result.setCode(200);
                result.setNewslist(records);
                return Observable.just(result);


            }
        })
                .subscribe(mSubscriber);

//                .compose(this.<Result<List<LikeRecord>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mSubscriber);
    }

    @Override
    protected ListBaseAdapter<ReadRecord> getListAdapter() {
        RecentReadAdapter adapter = new RecentReadAdapter();
        return adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReadRecord item = mAdapter.getItem(position);
        if (item != null) {
            Router.showDetail(getActivity(), item.getTitle(), item.getId(), item.getImage(), "", "");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.recent_read, menu);
        menu.findItem(R.id.clear_recent_read);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RealmHelper realmHelper = new RealmHelper(AppContext.context());
        realmHelper.removeAllReadRecord();
        mAdapter.clear();
        refresh();
        return true;
    }
}
