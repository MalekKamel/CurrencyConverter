package currencyconverter.common.core.io;

import android.graphics.Bitmap;

public class BitmapUtil {

    public static Bitmap empty(){
        return Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
        );
    }



}
