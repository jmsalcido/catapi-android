package org.otfusion.votecats.common.model;

public class Cat {

    private String _id;
    private String _imageUrl;
    private String _providerName;
    private String _name;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
