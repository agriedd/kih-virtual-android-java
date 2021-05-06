package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.ArticleObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerviewArticle extends RecyclerView.Adapter<AdapterRecyclerviewArticle.viewHolder> {

    Context mContext;
    List<ArticleObject> articles = new ArrayList<>();

    public AdapterRecyclerviewArticle(Context mContext, List<ArticleObject> articles) {
        this.mContext = mContext;
        this.articles = articles;
    }

    @Override
    public int getItemViewType(int position) {
        return articles.get(position).getTitle() != null ? 1 : 0;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.component_card_article, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.bind(mContext, articles.get(position), getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public static class viewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "articlevh";
        ImageView cover;
        TextView title, description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Context mContext, ArticleObject articleObject, int itemViewType) {
            cover = itemView.findViewById(R.id.article_cover);
            title = itemView.findViewById(R.id.article_title);
            description = itemView.findViewById(R.id.article_description);

            title.setText(articleObject.getTitle());
            description.setText(articleObject.getDescription());

            try {
                if(articleObject.getCover() != null && articleObject.getCover().length() > 0)
                    Glide.with(mContext)
                            .load(articleObject.getCover())
                            .into(cover);
            } catch (Exception e){
                Log.d(TAG, "bind: " + e.getMessage());
            }
        }
    }
}
