package org.otfusion.votecats.events;

import org.otfusion.votecats.common.model.Cat;

public class CatLoadedEvent {

    private Cat _cat;

    public CatLoadedEvent(Cat cat) {
        _cat = cat;
    }

    public Cat getCat() {
        return _cat;
    }

}
