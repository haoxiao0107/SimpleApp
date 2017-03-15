package com.hx.simpleapp.ui.setting.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hx.simpleapp.R;
import com.hx.simpleapp.base.adapter.ListBaseAdapter;
import com.hx.simpleapp.model.LikeRecord;
import com.hx.simpleapp.util.ImageLoaderUtil;
import com.hx.simpleapp.view.SquareImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LikeReadAdapter extends ListBaseAdapter<LikeRecord> {
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.item_like_article, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        LikeRecord record = mDatas.get(position);
        vh.title.setText(record.getTitle());
        if (record.getImage() != null)
            ImageLoaderUtil.load(parent.getContext(), record.getImage(), vh.image);

        long time = record.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(time);
        vh.time.setText(formatter.format(date));

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_article_image)
        SquareImageView image;
        @Bind(R.id.tv_article_title)
        TextView title;
        @Bind(R.id.tv_article_time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
