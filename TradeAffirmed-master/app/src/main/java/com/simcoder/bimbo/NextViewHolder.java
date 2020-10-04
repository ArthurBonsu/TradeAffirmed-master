package com.simcoder.bimbo;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by manel on 10/10/2017.
 */

class NextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView rideId;
    public TextView time;
    public NextViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        rideId = itemView.findViewById(R.id.rideId);
        time = itemView.findViewById(R.id.time);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), HistorySingleActivity.class);
        Bundle b = new Bundle();
        b.putString("rideId", rideId.getText().toString());
        intent.putExtras(b);
        v.getContext().startActivity(intent);
    }
}
