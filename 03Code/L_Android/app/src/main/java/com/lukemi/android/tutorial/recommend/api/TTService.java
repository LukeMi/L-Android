package com.lukemi.android.tutorial.recommend.api;


import com.lukemi.android.tutorial.recommend.bean.RecommendBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TTService {

    @GET("2/article/v25/stream/?count=20&min_behot_time=1504621638&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1504622133&loc_mode=5&loc_time=1504564532&latitude=35.00125&longitude=113.56358166666665&city=焦作&lac=34197&cid=23201&iid=14534335953&device_id=38818211465&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SM-E7000&os_api=19&os_version=4.4.2&uuid=357698010742401&openudid=74f06d2f9d8c9664")
    public Observable<RecommendBean> getRecommend();
}
