package com.ganbatee.ugfood.ugfood.ViewHolder;

import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ganbatee.ugfood.ugfood.Interface.ItemClickListener;
import com.ganbatee.ugfood.ugfood.R;

import org.w3c.dom.Text;

/**
 * Created by enno on 18/11/17.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderAddress,txtOrderTime;

    private ItemClickListener itemClickListener;


    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderAddress = (TextView) itemView.findViewById(R.id.order_address);
        txtOrderId = (TextView) itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_phone);
        txtOrderTime = (TextView) itemView.findViewById(R.id.order_time);


        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);


    }
}
