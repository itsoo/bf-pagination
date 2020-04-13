package com.sncfc.baseframework.pageplugin.core;

import com.sncfc.baseframework.pageplugin.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 分页切面
 *
 * @author zxy
 */
@Aspect
@Component
public class PageAspect {

    @Around("@annotation(com.sncfc.baseframework.pageplugin.core.Page)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 分页参数索引
        final int index = getTargetIndex(point.getArgs());
        if (index == -1) {
            PageInfo pageInfo;
            for (Object object : point.getArgs()) {
                if ((pageInfo = handlePageInfo(point.proceed(), object)) != null) {
                    return pageInfo;
                }
            }

            return null;
        }

        return handlePageInfo(point.proceed(), point.getArgs()[index]);
    }

    private int getTargetIndex(Object[] objects) {
        for (int i = 0, len = objects == null ? 0 : objects.length; i < len; i++) {
            final Object obj = objects[i];
            if (obj instanceof PageInfo) {
                if (hasKeyOfPageInfo((PageInfo) obj)) {
                    return i;
                }
            } else if (hasKeyOfPageInfo(PageInfo.instance(obj))) {
                return i;
            }
        }

        return -1;
    }

    private PageInfo handlePageInfo(Object src, Object obj) {
        if (obj instanceof PageInfo) {
            return ((PageInfo) obj).setDataList((List) src);
        }

        return PageInfo.instance(obj).setDataList((List) src);
    }

    private boolean hasKeyOfPageInfo(PageInfo pageInfo) {
        return pageInfo != null && pageInfo.getPageNum() != null;
    }
}
