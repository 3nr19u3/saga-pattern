package com.lgutierrez.saga.commons.event;

import java.util.Date;
import java.util.UUID;

public interface Event {

    UUID getEvent();

    Date getDate();
}
