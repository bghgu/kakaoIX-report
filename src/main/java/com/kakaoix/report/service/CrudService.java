package com.kakaoix.report.service;

import com.kakaoix.report.model.res.DefaultRes;

/**
 * Created by ds on 2018-08-30.
 */

public interface CrudService<T> {
    DefaultRes<T> findOne(final int idx);
    DefaultRes<T> save(final T t);
    DefaultRes<T> update(final int idx, T t);
    DefaultRes<T> delete(final int idx);
}
