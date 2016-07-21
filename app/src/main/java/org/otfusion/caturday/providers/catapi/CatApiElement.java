package org.otfusion.caturday.providers.catapi;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class CatApiElement {

    public CatApiElement() {
    }

    @Element(name = "image")
    @Path("data/images/")
    private Image image;

    @Root (name = "image")
    private static class Image {

        @Element (name = "id")
        private String id;
        @Element (name = "url")
        private String url;
        @Element (name = "source_url")
        private String source;

    }

    public String getId() {
        return image.id;
    }

    public String getUrl() {
        return image.url;
    }

    public String getSource() {
        return image.source;
    }

}
