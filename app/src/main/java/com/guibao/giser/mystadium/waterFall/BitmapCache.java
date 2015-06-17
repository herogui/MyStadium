package com.guibao.giser.mystadium.waterFall;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/***
 * 鍥剧墖缂撳瓨
 *
 * (鍗曞埄妯″紡)
 *
 * @author zhangjia
 *
 */
public class BitmapCache {

	static private BitmapCache cache;
	// 杞紩鐢?
	private HashMap<String, SoftReference<Bitmap>> imageCache;

	public BitmapCache() {
		imageCache = new HashMap<String, SoftReference<Bitmap>>();
	}

	/**
	 * 鍙栧緱缂撳瓨鍣ㄥ疄渚?
	 */
	public static BitmapCache getInstance() {
		if (cache == null) {
			cache = new BitmapCache();
		}
		return cache;

	}

	/***
	 * 鑾峰彇缂撳瓨鍥剧墖
	 *
	 * @param key
	 *            image name
	 * @return
	 */
	public Bitmap getBitmap(String key) {
		if (imageCache.containsKey(key)) {
			SoftReference<Bitmap> reference = imageCache.get(key);
			Bitmap bitmap = reference.get();
			if (bitmap != null)
				return bitmap;
		}
		return null;

	}

	/***
	 * 灏嗗浘鐗囨坊鍔犲埌杞紩鐢ㄤ腑
	 * 
	 * @param bitmap
	 * @param key
	 */
	public void putSoftReference(Bitmap bitmap, String key) {
		imageCache.put(key, new SoftReference<Bitmap>(bitmap));

	}

}
