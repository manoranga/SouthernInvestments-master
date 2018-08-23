package com.android.mlpj.southerninvestments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {
    private List<CustomerDetails> mCustomersDetails;
    private Context mContext;
    private FragmentManager fragmentManager;

    public CustomerListAdapter(List<CustomerDetails> customerDetails, Context context, FragmentManager fragmentManager) {
        this.mCustomersDetails = customerDetails;
        this.mContext = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.customer_card, parent, false);
        return new ViewHolder(v);


    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set status(active...)
        holder.Name.setText(mCustomersDetails.get(position).getName());
        holder.Email.setText(mCustomersDetails.get(position).getEmail());
/*

        holder.popUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked", Toast.LENGTH_LONG).
                        show();
                InsertPaidAmountFragment insertPaidAmountFragment = new InsertPaidAmountFragment();
                //commentFragment.setArguments(bundle);

                insertPaidAmountFragment.show(fragmentManager, "InsertPaidAmountDialog");

            }
        });
*/

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "0714545852"));
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mContext.startActivity(callIntent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return  mCustomersDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      public   TextView Name;
      public   TextView Email;
      public   CardView Card;
      public   ImageButton call;
      public   ImageButton popUp;

        public ViewHolder(View itemView) {
            super(itemView);

            Name              = itemView.findViewById(R.id.head);
            Email             = itemView.findViewById(R.id.description);
            Card              = itemView.findViewById(R.id.card);
            call              = itemView.findViewById(R.id.btn_call);
            popUp             = itemView.findViewById(R.id.touch_dots);
        }
    }

    public void upDateList(List<CustomerDetails> list){
        mCustomersDetails = new ArrayList<>();
        mCustomersDetails.addAll(list);
        notifyDataSetChanged();
}
}
