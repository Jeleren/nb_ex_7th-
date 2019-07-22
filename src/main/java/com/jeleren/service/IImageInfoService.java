package com.jeleren.service;

import com.jeleren.bean.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.List;

/**
 * ClassName: IImageInfoService <br/>
 * Description: <br/>
 * date: 2019/7/17 22:11<br/>
 *
 * @author a8243<br />
 * @since JDK 1.8
 */
public interface IImageInfoService {
    int add(ImageInfo imageInfo);
    List<ImageResult> getMainImage(int user_id, int group_id, int page, int num);

    ImageResult getImageInfo(int image_id, int user_id);
    List<ImageResult> searchImage(SearchList searchList);
    List<ImageAndUserResult> getUserImages(int uid, int page, int size);
    List<ImageAndUserResult> getImagesByActive(int uid,int page,int size,int if_active);
    List<Integer> getActiveNum();
    List<CollectionInfo> getCollectionInfo(int uid);
}
