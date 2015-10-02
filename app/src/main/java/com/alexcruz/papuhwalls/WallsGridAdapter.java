package com.alexcruz.papuhwalls;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexcruz.papuhwalls.api.Wall;
import com.balysv.materialripple.MaterialRippleLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WallsGridAdapter extends BaseAdapter {

    private int numColumns;

    private WallsHolder holder;

    List<Wall> wallList;

    private Context context;
    Preferences Preferences;

    public WallsGridAdapter(Context context, List<Wall> wallList, int numColumns) {
        super();
        this.context = context;
        this.numColumns = numColumns;
        this.wallList = wallList;
    }

    @Override
    public int getCount() {
        return wallList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View wallitem = convertView;
        holder = null;
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int imageWidth = (int) (width / numColumns);

        if (wallitem == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            wallitem = inflater.inflate(R.layout.wallpaper_item, parent, false);
            holder = new WallsHolder(wallitem);
            wallitem.setTag(holder);
        } else {
            holder = (WallsHolder) wallitem.getTag();

        }

        Wall currentWall = wallList.get(position);

        holder.name.setText(currentWall.getName());
        holder.author.setText(currentWall.getAuthor());

        holder.wall.startAnimation(anim);
        Picasso.with(context)
                .load(currentWall.getLocation())
                .resize(imageWidth, imageWidth)
                .centerCrop()
                .transform(Palette.instance())
                .into(holder.wall);

        return wallitem;
    }

    class WallsHolder {
        ImageView wall;
        TextView name;
        TextView author;
        final MaterialRippleLayout content;

        WallsHolder(View v) {
            wall = (ImageView) v.findViewById(R.id.wall);
            name = (TextView) v.findViewById(R.id.name);
            author = (TextView) v.findViewById(R.id.author);
            content = (MaterialRippleLayout) v.findViewById(R.id.walls_ripple);
        }

    }

}