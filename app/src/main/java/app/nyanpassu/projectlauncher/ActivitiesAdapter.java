package app.nyanpassu.projectlauncher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nyanpassu.android.toolset.packagemanager.GetActivities;

/**
 * Created by 丢猫 on 2015/3/5.
 */
public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;

        TextView textView;

        Intent intent;

        int position;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);

            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (intent != null) {
                v.getContext().startActivity(intent);
            }
        }
    }

    int mSize;

    List<Drawable> mIconList = new ArrayList<>();

    List<String> mLabelList = new ArrayList<>();

    List<Intent> mIntentList = new ArrayList<>();

    public ActivitiesAdapter(Context context) {
        GetActivities.Result result = GetActivities.getPackageActivity(context,
                Intent.ACTION_MAIN,
                Intent.CATEGORY_LAUNCHER,
                null);
        for (int i = 0; i < result.activitiesIntentList.size(); i++) {
            Intent intent = result.activitiesIntentList.get(i);
            if (intent.getComponent().getPackageName().startsWith("app.nyanpassu")
                    && !intent.getComponent().getPackageName().equals(context.getPackageName())) {
                mIconList.add(result.activitiesIconList.get(i));
                mLabelList.add(result.activitiesLabelList.get(i));
                mIntentList.add(intent);
                mSize ++;
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activities, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.position = position;
        setImage(holder, mIconList.get(position));
        setText(holder, mLabelList.get(position));
        setIntent(holder, mIntentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSize;
    }

    private void setImage(ViewHolder holder, @Nullable Drawable drawable) {
        if (drawable == null) {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            holder.imageView.setImageDrawable(drawable);
        }
    }

    private void setText(ViewHolder holder, String label) {
        holder.textView.setText(label);
    }

    private void setIntent(ViewHolder holder, Intent intent) {
        holder.intent = intent;
    }
}
