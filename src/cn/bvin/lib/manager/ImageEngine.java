package cn.bvin.lib.manager;

import android.content.Context;
import android.widget.ImageView;

public class ImageEngine {
	
	static ImageEngine singleton = null;
	
	Context context;
	

	private ImageEngine(Context context) {
		this.context = context;
	}


	public static ImageEngine with(Context context) {
		if (singleton == null) {
		      synchronized (ImageEngine.class) {
		        if (singleton == null) {
		          singleton = new ImageEngine(context);
		        }
		      }
		    }
	    return singleton;
	}

	public void load(String path) {
		
	}
	
	public void into(ImageView target) {
		
	}
}
