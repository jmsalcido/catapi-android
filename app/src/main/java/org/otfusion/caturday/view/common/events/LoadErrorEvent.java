package org.otfusion.caturday.view.common.events;

import com.squareup.otto.Bus;

public class LoadErrorEvent implements VoteCatEvent {

    private Bus bus;
    private String errorMessage;

    public LoadErrorEvent(Bus bus, String errorMessage) {
        this.bus = bus;
        this.errorMessage = errorMessage;
    }

    @Override
    public void executeEvent(String source) {
        bus.post(this);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
