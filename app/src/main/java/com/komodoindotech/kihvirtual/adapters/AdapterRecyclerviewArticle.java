package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.komodoindotech.kihvirtual.ArticleShowActivity;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.ArticleObject;

import java.util.List;

public class AdapterRecyclerviewArticle extends RecyclerView.Adapter<AdapterRecyclerviewArticle.viewHolder> {

    Context mContext;
    List<ArticleObject> articles;

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

    public void updateList(List<ArticleObject> articleObjects) {
        this.articles = articleObjects;
        this.notifyDataSetChanged();
    }


    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private static final String TAG = "articlevh";
        ImageView cover;
        TextView title, description;
        CardView container;
        Context mContext;
        ArticleObject articleObject;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Context mContext, ArticleObject articleObject, int itemViewType) {
            this.mContext = mContext;
            this.articleObject = articleObject;
            cover = itemView.findViewById(R.id.article_cover);
            title = itemView.findViewById(R.id.article_title);
            description = itemView.findViewById(R.id.article_description);
            container = itemView.findViewById(R.id.article_container);

            container.setOnClickListener(this);

            title.setText(articleObject.getTitle());
            description.setText(articleObject.getDescription());


            try {
                if(articleObject.getCover() != null && articleObject.getCover().length() > 0){
                    cover.clearColorFilter();
                    Glide.with(mContext)
                            .load(articleObject.getCover())
                            .centerCrop()
                            .into(cover);
                }
            } catch (Exception e){
                Log.d(TAG, "bind: " + e.getMessage());
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext.getApplicationContext(), ArticleShowActivity.class);
            intent.putExtra("id", articleObject.getId());
            mContext.startActivity(intent);
        }
    }
}
