package org.otfusion.votecats.ui.events;

import com.squareup.otto.Bus;

import org.otfusion.votecats.common.model.Cat;

public class FavoriteCatEvent implements VoteCatEvent {

    private Bus _bus;
    private Cat _cat;
    private String _source;

    public FavoriteCatEvent(Bus bus, Cat cat) {
        _bus = bus;
        _cat = cat;
    }

    @Override
    public void executeEvent(String source) {
        _source = source;
        _bus.post(this);
    }

    public Cat getCat() {
        return _cat;
    }

    public String getSource() {
        return _source;
    }
}
