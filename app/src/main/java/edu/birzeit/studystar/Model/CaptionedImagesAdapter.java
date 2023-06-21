package edu.birzeit.studystar.Model;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.birzeit.studystar.R;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{
    private Context context;
    private List<Item> items;

    public CaptionedImagesAdapter(Context context, List<Item> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,
                parent,
                false);

        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);
        CardView myCardView = holder.cardView;
        ImageView myimageView = (ImageView) myCardView.findViewById(R.id.image);
        Glide.with(context).load(item.getImage()).into(myimageView);

        TextView mytext = (TextView)myCardView.findViewById(R.id.txtName);
        mytext.setText(item.getName());
        myCardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Create a dialog and find views inside the dialog layout
                Dialog myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.my_dialog_card);
                ImageView image = myDialog.findViewById(R.id.image);
                TextView name = myDialog.findViewById(R.id.txtName);

                // Set mydialog views data
                Glide.with(context).load(item.getImage()).into(image);
                name.setText(item.getName());
                myDialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}

