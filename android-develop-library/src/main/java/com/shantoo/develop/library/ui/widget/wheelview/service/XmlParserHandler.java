package com.shantoo.develop.library.ui.widget.wheelview.service;

import com.shantoo.develop.library.ui.widget.wheelview.model.CityModel;
import com.shantoo.develop.library.ui.widget.wheelview.model.DistrictModel;
import com.shantoo.develop.library.ui.widget.wheelview.model.ProvinceModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XmlParserHandler extends DefaultHandler {

    /**
     * 把省份放在List集合里
     */
    private List<ProvinceModel> provinceList = new ArrayList<>();

    //无参构造
    public XmlParserHandler() {
    }

    public List<ProvinceModel> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    //创建实例
    private ProvinceModel provinceModel = new ProvinceModel();
    private CityModel cityModel = new CityModel();
    private DistrictModel districtModel = new DistrictModel();

    //开始标签
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equals("province")) {
            provinceModel = new ProvinceModel();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setCityList(new ArrayList<CityModel>());
        } else if (qName.equals("city")) {
            cityModel = new CityModel();
            cityModel.setName(attributes.getValue(0));
            cityModel.setDistrictList(new ArrayList<DistrictModel>());
        } else if (qName.equals("district")) {
            districtModel = new DistrictModel();
            districtModel.setName(attributes.getValue(0));
            districtModel.setZipcode(attributes.getValue(1));
        }
    }

    //结束标签
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // 判断标签名称是否是district如果是就把district加入城市的list里
        switch (qName) {
            case "district":
                cityModel.getDistrictList().add(districtModel);
                break;
            case "city":
                provinceModel.getCityList().add(cityModel);
                break;
            case "province":
                provinceList.add(provinceModel);
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }
}
