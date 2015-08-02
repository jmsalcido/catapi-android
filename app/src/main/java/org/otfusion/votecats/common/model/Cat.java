package org.otfusion.votecats.common.model;

public class Cat {

    private int _id;
    private String _imageUrl;
    private String _providerName;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getImageUrl() {
        return _imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        _imageUrl = imageUrl;
    }

    public String getProviderName() {
        return _providerName;
    }

    public void setProviderName(String providerName) {
        _providerName = providerName;
    }

}
