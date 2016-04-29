package com.example.administrator.uiutilsdemo;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * Created by Administrator on 2016/4/27.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String diskCachePath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            diskCachePath = new StringBuffer()
                    .append(Environment.getExternalStorageDirectory())
                    .append(File.separator)
                    .append("/imageCache").toString();
            // /mnt/sdcard/qianfeng/imageCache
        }
        else
        {
            diskCachePath = new StringBuffer().append(Environment.getDataDirectory())
                    .append(File.separator)
                    .append(this.getPackageName())
                    .append("/imageCache").toString();
            // /data/data/com.example.volley_case_greentree_list/qianfeng/imageCache
        }
        DiskCacheConfig diskCacheConfig = com.facebook.cache.disk.DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(new File(diskCachePath))
                .setMaxCacheSize(1000)
                .setMaxCacheSizeOnLowDiskSpace(20*1024*1024)
                .build();

        ImagePipelineConfig imagePipelineConfig = com.facebook.imagepipeline.core.ImagePipelineConfig.newBuilder(this)
                .setDecodeFileDescriptorEnabled(true)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();

        Fresco.initialize(this,imagePipelineConfig);

    }
}
