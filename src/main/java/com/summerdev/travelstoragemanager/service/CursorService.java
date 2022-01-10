package com.summerdev.travelstoragemanager.service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:23
 */
public interface CursorService {
    /**
     * @return init cursor id for task
     */
    Long getFirstCursorId();

    /**
     * @param currCursorId current cursor id for task
     * @return next cursor id in order
     */
    Long getNextCursorId(long currCursorId);
}
