package com.shantoo.develop.library.ui.widget.LinkageView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.shantoo.develop.library.R;
import com.shantoo.develop.library.ui.widget.wheelview.adapter.ArrayWheelAdapter;
import com.shantoo.develop.library.ui.widget.wheelview.model.CityModel;
import com.shantoo.develop.library.ui.widget.wheelview.model.DistrictModel;
import com.shantoo.develop.library.ui.widget.wheelview.model.ProvinceModel;
import com.shantoo.develop.library.ui.widget.wheelview.service.XmlParserHandler;
import com.shantoo.develop.library.ui.widget.wheelview.widget.WheelView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 作者: shantoo on 2017/6/13 16:53.
 * 省市区三级联动
 */

public class ThreeLevelAddressLinkageView extends BaseLevelLinkageView {

    private String mAssetsXml;
    private TextView submit, cancel;
    private WheelView mViewProvince, mViewCity, mViewDistrict;

    /**
     * 省份的数据数组
     */
    protected String[] mProvinceDatas;

    /**
     * key - values 城市的键值对
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<>();

    /**
     * key - values 区的键值对
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<>();

    /**
     * key - values 邮政编码的键值对
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<>();

    /**
     * 省份名称,城市名称,区县的名称,邮政编码
     */
    protected String mCurrentProviceName, mCurrentCityName, mCurrentDistrictName, mCurrentZipCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_three_level_address_linkage_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
        setUpListener();
        setUpData();
    }

    @Override
    public void onClick(View v) {
        if(R.id.id_submit == v.getId()){
            mOnSubmitClickListener
                    .onSubmit(mCurrentProviceName+mCurrentCityName+mCurrentDistrictName,mCurrentZipCode);
        }
    }

    private void setUpViews(View view) {
        cancel = (TextView) view.findViewById(R.id.id_cancel);
        submit = (TextView) view.findViewById(R.id.id_submit);
        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
    }

    private void setUpListener() {
        // 省份滑动时的监听
        mViewProvince.addChangingListener(this);
        //城市滑动时的监听
        mViewCity.addChangingListener(this);
        // 区滑动时的监听
        mViewDistrict.addChangingListener(this);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    public ThreeLevelAddressLinkageView setAssetXml(String assetsXml) {
        mAssetsXml = assetsXml;
        return this;
    }

    private void setUpData() {
        initProvinceDatas();
        ArrayWheelAdapter adapter = new ArrayWheelAdapter(getActivity(), mProvinceDatas);
        mViewProvince.setViewAdapter(adapter);
        //设置显示item的条目数
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList;
        AssetManager asset = getActivity().getAssets();
        try {
            //以流形式进行从配置文件中数据读取
            InputStream input = asset.open(mAssetsXml);
            // xml解析工厂
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 创建解析器对象
            SAXParser parser = spf.newSAXParser();
            //创建解析器Handle
            XmlParserHandler handler = new XmlParserHandler();
            //将给定的流解析成Xml
            parser.parse(input, handler);
            //关闭流
            input.close();
            // 获取省份的list
            provinceList = handler.getDataList();
            //从list里取出省份并且关联市和区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //把取到的数据用String 数组封装
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 根据省份的位置变换其他两个
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    //根据市的变化关联区
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 从区的列表里取出相应的数据
                        DistrictModel districtModel =
                                new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 以map 的形式存储区和邮政编码
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    //取出一个市对应的一堆区
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 将取出的省和市装入map中
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        //如果滚到了  某一个省份，则要更新城市
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            updateDistrictAndCode();
        }
    }

    private void updateAreas() {
        int cCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[cCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<>(getActivity(), areas));
        mViewDistrict.setCurrentItem(0);
        updateDistrictAndCode();
    }

    private void updateDistrictAndCode() {
        int dCurrent = mViewDistrict.getCurrentItem();
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[dCurrent];
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
    }

    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<>(getActivity(), cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        onFocusChange(v, hasFocus);
        /*if (hasFocus) {
            cascadeAddress.setVisibility(View.VISIBLE);
        } else {
            cascadeAddress.setVisibility(View.GONE);
        }*/
    }
}
