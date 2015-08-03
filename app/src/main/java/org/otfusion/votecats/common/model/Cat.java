package org.otfusion.votecats.common.model;

public class Cat {

    private String _id;
    private String _imageUrl;
    private String _providerName;
    private boolean _favorite;

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

    public boolean isFavorite() {
        return _favorite;
    }

    public void setFavorite(boolean favorite) {
        _favorite = favorite;
    }
}
