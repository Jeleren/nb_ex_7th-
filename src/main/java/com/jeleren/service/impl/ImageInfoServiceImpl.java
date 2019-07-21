package com.jeleren.service.impl;

import com.github.pagehelper.PageHelper;
import com.jeleren.bean.ImageAndUserResult;
import com.jeleren.bean.ImageInfo;
import com.jeleren.bean.ImageResult;
import com.jeleren.bean.SearchList;
import com.jeleren.dao.IImageInfoDao;
import com.jeleren.service.IImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: IImageInfoImpl <br/>
 * Description: <br/>
 * date: 2019/7/17 22:11<br/>
 *
 * @author a8243<br       />
 * @since JDK 1.8
 */
@Service
public class ImageInfoServiceImpl implements IImageInfoService {

    @Autowired
    private IImageInfoDao iImageInfoDao;

    @Override
    public int add(ImageInfo imageInfo) {
        return iImageInfoDao.add(imageInfo);
    }

    @Override
    public List<ImageResult> getMainImage(int user_id, int group_id, int page, int num) {
        PageHelper.startPage(page, num);
        return iImageInfoDao.getMainImage(user_id, group_id);
    }

    @Override
    public ImageResult getImageInfo(int image_id, int user_id) {
        return iImageInfoDao.getImageInfo(image_id, user_id);
    }

    @Override
    public List<ImageResult> searchImage(SearchList searchList) {
        List<ImageResult> imageResults = iImageInfoDao.searchImage(searchList);
        int count = imageResults.size();
        for (int i = 0; i < count; i++) {
            imageResults.get(i).setCount(count);
        }
//        imageResults.set
        PageHelper.startPage(searchList.getPage(), searchList.getSize());

        return imageResults;
    }

    @Override
    public List<ImageAndUserResult> getUserImages(int uid, int page, int size) {
        List<ImageAndUserResult> imageInfos =  iImageInfoDao.getUserImages(uid);
        int count;
        if (imageInfos.size() > 0) {
            count = imageInfos.get(0).getImageInfos().size();
            for (int i = 0; i < count; i++) {
                imageInfos.get(i).setCount(count);
            }
        }
        PageHelper.startPage(page, size);
        return imageInfos;
    }

    @Override
    public List<ImageAndUserResult> getImagesByActive(int uid, int page, int size, int if_active) {
//        star
        List<ImageAndUserResult> imageInfos = iImageInfoDao.getImagesByActive(uid, if_active);
        // 将图片数目放到结构中去，图片数目每个结果都保存一份
        int count;
        if (imageInfos.size() > 0) {
            count = imageInfos.get(0).getImageInfos().size();
            for (int i = 0; i < count; i++) {
                imageInfos.get(i).setCount(count);
            }
        }

        PageHelper.startPage(page, size);
        return imageInfos;
    }

    @Override
    public List<Integer> getActiveNum() {
        int tmp1 = iImageInfoDao.getActiveNum(1);
        int tmp2 = iImageInfoDao.getActiveNum(2);
        int tmp3 = iImageInfoDao.getActiveNum(3);
        int tmp4 = iImageInfoDao.getActiveNum(4);
        List<Integer> listResult = new ArrayList<>();
        listResult.add(tmp1);
        listResult.add(tmp2);
        listResult.add(tmp3);
        listResult.add(tmp4);


        return listResult;
    }
}
