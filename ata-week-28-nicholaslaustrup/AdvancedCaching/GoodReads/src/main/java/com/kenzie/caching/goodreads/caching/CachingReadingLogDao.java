package com.kenzie.caching.goodreads.caching;


import com.kenzie.caching.goodreads.dao.NonCachingReadingLogDao;
import com.kenzie.caching.goodreads.dao.ReadingLogDao;
import com.kenzie.caching.goodreads.dao.models.ReadingLog;

import java.time.ZonedDateTime;
import javax.inject.Inject;

public class CachingReadingLogDao implements ReadingLogDao {

    private CacheClient cacheClient;
    private NonCachingReadingLogDao readingDoa;

    @Inject
    public CachingReadingLogDao(CacheClient cacheClient, NonCachingReadingLogDao readingDoa) {
        this.cacheClient = cacheClient;
        this.readingDoa = readingDoa;
    }

    @Override
    public ReadingLog updateReadingProgress(String userId, String isbn, ZonedDateTime timestamp,
                                            int pageNumber, boolean isFinished) {
       String key = "books-read::" + userId + "::" + timestamp.getYear();
       if (isFinished){cacheClient.invalidate(key);}
       return readingDoa.updateReadingProgress(userId, isbn, timestamp, pageNumber, isFinished);
    }

    @Override
    public int getBooksReadInYear(String userId, int year) {
        String key = "books-read::" + userId + "::" + year;
        String booksRead = cacheClient.getValue(key);
        if (booksRead == null){
            int booksReadLog = readingDoa.getBooksReadInYear(userId, year);
            cacheClient.setValue(key, String.valueOf(booksReadLog));
            return booksReadLog;
        }
        return Integer.parseInt(booksRead);
    }
}
