package com.winit.svr.identity;

import com.winit.svr.query.NativeQuery;

/**
 * Allows querying of {@link com.winit.svr.identity.User}s via native (SQL) queries
 * @author Henry Yan(http://www.kafeitu.me)
 */
public interface NativeUserQuery extends NativeQuery<NativeUserQuery, User> {

}