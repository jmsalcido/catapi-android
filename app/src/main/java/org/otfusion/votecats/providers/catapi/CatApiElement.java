package org.otfusion.votecats.providers.catapi;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class CatApiElement {

    @Element(name = "image")
    @Path("data/images/")
    private Image _image;

    @Root (name = "image")
    private static class Image {

        @Element (name = "id")
        private String _id;
        @Element (name = "url")
        private String _url;
        @Element (name = "source_url")
        private String _source;

    }

    public String getId() {
        return _image._id;
    }

    public String getUrl() {
        return _image._url;
    }

    public String getSource() {
        return _image._source;
    }

}
