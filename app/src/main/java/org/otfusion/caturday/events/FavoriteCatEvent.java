package org.otfusion.caturday.events;

import com.squareup.otto.Bus;

import org.otfusion.caturday.common.model.Cat;

public class FavoriteCatEvent implements VoteCatEvent {

    private Bus bus;
    private Cat cat;
    private String source;

    public FavoriteCatEvent(Bus bus, Cat cat) {
        this.bus = bus;
        this.cat = cat;
    }

    @Override
    public void executeEvent(String source) {
        this.source = source;
        bus.post(this);
    }

    public Cat getCat() {
        return cat;
    }

    public String getSource() {
        return source;
    }
}
