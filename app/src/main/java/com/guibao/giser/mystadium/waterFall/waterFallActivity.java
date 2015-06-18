
package com.guibao.giser.mystadium.waterFall;

        import android.app.Activity;
        import android.content.res.AssetManager;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.view.ViewGroup.LayoutParams;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.guibao.giser.mystadium.R;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class waterFallActivity extends Activity implements
        LazyScrollView.OnScrollListener {
    private LazyScrollView lazyScrollView;
    private LinearLayout waterfall_container;
    private ArrayList<LinearLayout> linearLayouts;// 鍒楀竷灞?
    private LinearLayout progressbar;// 杩涘害鏉?
    private TextView loadtext;// 搴曢儴鍔犺浇view

    private AssetManager assetManager;

    private List<String> image_filenames; // 鍥剧墖闆嗗悎
    private ImageDownLoadAsyncTask asyncTask;

    private int current_page = 0;// 椤电爜
    private int count = 20;// 姣忛〉鏄剧ず鐨勪釜鏁?	private int column = 4;// 鏄剧ず鍒楁暟s

    private int item_width;// 姣忎竴涓猧tem鐨勫搴?	private final String file = "images";
    int column =1;
    String file= "images";
    /***
     * init view
     */
    public void initView() {
//        setContentView(R.layout.lazyscroll);
        lazyScrollView = (LazyScrollView) findViewById(R.id.waterfall_scroll);
        lazyScrollView.getView();
        lazyScrollView.setOnScrollListener(this);
        waterfall_container = (LinearLayout) findViewById(R.id.waterfall_container);
        progressbar = (LinearLayout) findViewById(R.id.progressbar);
        loadtext = (TextView) findViewById(R.id.loadtext);

        item_width = getWindowManager().getDefaultDisplay().getWidth();
        linearLayouts = new ArrayList<LinearLayout>();

        // 娣诲姞涓夊垪鍒皐aterfall_container

        for (int i = 0; i < column; i++) {
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(
                    item_width, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(itemParam);
            linearLayouts.add(layout);
            waterfall_container.addView(layout);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        assetManager = this.getAssets();
        // 获取显示图片宽度
        int Image_width = (getWindowManager().getDefaultDisplay().getWidth() - 4) / 3;
        try {
            image_filenames = Arrays.asList(assetManager.list(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 绗竴娆″姞杞?
        addImage(current_page, count);

    }

    /***
     * 鍔犺浇鏇村
     *
     * @param current_page
     * @param count
     */
    private void addImage(int current_page, int count) {
        int j = 0;
        int imagecount = image_filenames.size();
        for (int i = current_page * count; i < count * (current_page + 1)
                && i < imagecount; i++) {
            addBitMapToImage(image_filenames.get(i), j, i);
            j++;
            if (j >= column)
                j = 0;
        }

    }


    private void addBitMapToImage(String imageName, int j, int i) {
        ImageView imageView = getImageview(imageName);
        asyncTask = new ImageDownLoadAsyncTask(this, imageName, imageView,
                item_width);

        asyncTask.setProgressbar(progressbar);
        asyncTask.setLoadtext(loadtext);
        asyncTask.execute();

        imageView.setTag(i);
        // 娣诲姞鐩稿簲view
        linearLayouts.get(j).addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				Toast.makeText(waterFallActivity.this,
                        "鎮ㄧ偣鍑讳簡" + v.getTag() + "涓狪tem", Toast.LENGTH_SHORT)
						.show();

            }
        });
    }

    /***
     * 鑾峰彇imageview
     *
     * @param imageName
     * @return
     */
    public ImageView getImageview(String imageName) {
        BitmapFactory.Options options = getBitmapBounds(imageName);
        // 鍒涘缓鏄剧ず鍥剧墖鐨勫璞?
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        imageView.setLayoutParams(layoutParams);
        //
        imageView.setMinimumHeight(options.outHeight);
        imageView.setMinimumWidth(options.outWidth);
        imageView.setPadding(2, 0, 2, 2);
        imageView.setBackgroundResource(R.drawable.image_border);
        if (options != null)
            options = null;
        return imageView;
    }

    /***
     *
     * 鑾峰彇鐩稿簲鍥剧墖鐨?BitmapFactory.Options
     */
    public BitmapFactory.Options getBitmapBounds(String imageName) {
        int h, w;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 鍙繑鍥瀊itmap鐨勫ぇ灏忥紝鍙互鍑忓皯鍐呭瓨浣跨敤锛岄槻姝OM.
        InputStream is = null;
        try {
            is = assetManager.open(file + "/" + imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.decodeStream(is, null, options);
        return options;

    }

    @Override
    public void onBottom() {
        addImage(++current_page, count);

    }

    @Override
    public void onTop() {

    }

    @Override
    public void onScroll() {

    }

}