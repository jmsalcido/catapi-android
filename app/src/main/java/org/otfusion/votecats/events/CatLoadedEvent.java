package org.otfusion.votecats.events;

import com.squareup.otto.Bus;

import org.otfusion.votecats.common.model.Cat;

public class CatLoadedEvent implements VoteCatEvent {

    private Cat _cat;
    private Bus _bus;

    public CatLoadedEvent(Cat cat, Bus bus) {
        _cat = cat;
        _bus = bus;
    }

    public Cat getCat() {
        return _cat;
    }

    @Override
    public void executeEvent(String source) {
        _bus.post(this);
    }
}
