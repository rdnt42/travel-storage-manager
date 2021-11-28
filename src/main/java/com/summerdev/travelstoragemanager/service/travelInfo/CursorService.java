package com.summerdev.travelstoragemanager.service.travelInfo;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:23
 */
public interface CursorService {
    Long getFirstCursorId();

    Long getNextCursorId(long currCursorId);
}
