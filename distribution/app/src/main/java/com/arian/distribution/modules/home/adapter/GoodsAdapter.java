package com.arian.distribution.modules.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arian.distribution.R;
import com.arian.distribution.modules.home.bean.GoodsAdapterBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaoyi on 2017/9/14.
 */

public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<GoodsAdapterBean> beans;
    private MyItemClickListener mItemClickListener;

    public GoodsAdapter(Context context, List<GoodsAdapterBean> beans) {
        this.mContext = context;
        this.beans = beans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_goods, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
//        设置item点击事件
        itemView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        GoodsAdapterBean bean = beans.get(position);
        // 商品名
        String goodName = bean.getGoodName();
        if (!TextUtils.isEmpty(goodName)) {
            viewHolder.tv_goods_name.setText(goodName);
            viewHolder.tv_goods_name.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv_goods_name.setVisibility(View.INVISIBLE);
        }
        // 商品价格
        String goodPrice = bean.getGoodPrice();
        if (!TextUtils.isEmpty(goodPrice)) {
            viewHolder.tv_goods_price.setText(String.format("￥%s", goodPrice));
            viewHolder.tv_goods_price.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv_goods_price.setVisibility(View.INVISIBLE);
        }
        // 商品销量
        String goodCount = bean.getGoodCount();
        if (!TextUtils.isEmpty(goodCount)) {
            viewHolder.tv_goods_count.setText(goodCount);
            viewHolder.tv_goods_count.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv_goods_count.setVisibility(View.INVISIBLE);
        }
        // 商品图标
        String goodIcon = bean.getGoodIcon();
        if (!TextUtils.isEmpty(goodIcon)) {
            Glide.with(mContext)
                    .load(goodIcon)
//                    .centerCrop()
                    .placeholder(R.mipmap.icon_goods_default)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .listener(myGlideRequestListener)
//                .dontAnimate()
                    .into(viewHolder.iv_goods_icon);

        }
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            int position = (int) v.getTag();
            mItemClickListener.onItemClick(v, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_goods_name)
        TextView tv_goods_name;
        @BindView(R.id.tv_goods_price)
        TextView tv_goods_price;
        @BindView(R.id.tv_goods_count)
        TextView tv_goods_count;
        @BindView(R.id.iv_goods_icon)
        ImageView iv_goods_icon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * item点击事件的监听
     **/
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }


}
