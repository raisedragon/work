package com.winit.svr.identity;

import com.winit.svr.query.NativeQuery;

/**
 * Allows querying of {@link com.winit.svr.identity.Group}s via native (SQL) queries
 * @author Henry Yan(http://www.kafeitu.me)
 */
public interface NativeGroupQuery extends NativeQuery<NativeGroupQuery, Group> {

}