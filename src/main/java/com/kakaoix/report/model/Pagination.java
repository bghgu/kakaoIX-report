package com.kakaoix.report.model;

import lombok.Builder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by ds on 2018-09-02.
 */

@Builder
public class Pagination implements Pageable {

    private int offset;
    private int limit;
    private final Sort sort;

    public Pagination(int offset, int limit, final Sort sort) {
        if (offset < 0) offset = 0;
        if (limit < 1) limit = 10;
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public Pagination(final int offset, final int limit, final Sort.Direction direction, final String... properties) {
        this(offset, limit, new Sort(direction, properties));
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public Pageable next() {
        return new Pagination((int) getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public Pagination previous() {
        return hasPrevious() ? new Pagination((int) getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new Pagination(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}
