package com.hx.simpleapp.ui.weixin.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hx.simpleapp.R;
import com.hx.simpleapp.base.adapter.ListBaseAdapter;
import com.hx.simpleapp.model.response.wx.WXItem;
import com.hx.simpleapp.util.ImageLoaderUtil;
import com.hx.simpleapp.view.SquareImageView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WXAdapter extends ListBaseAdapter<WXItem> {

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.item_wechat, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final WXItem item = mDatas.get(position);
        ImageLoaderUtil.load(parent.getContext(), item.getPicUrl(), vh.image);
        vh.title.setText(item.getTitle());
        vh.from.setText(item.getDescription());
        vh.time.setText(item.getCtime());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.iv_wechat_item_image)
        SquareImageView image;
        @Bind(R.id.tv_wechat_item_title)
        TextView title;
        @Bind(R.id.tv_wechat_item_from)
        TextView from;
        @Bind(R.id.tv_wechat_item_time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
