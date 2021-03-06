// Copyright (c) 2014, Facebook, Inc.  All rights reserved.
// This source code is licensed under the BSD-style license found in the
// LICENSE file in the root directory of this source tree. An additional grant
// of patent rights can be found in the PATENTS file in the same directory.

package org.rocksdb;

public interface DBOptionsInterface {

  /**
   * If this value is set to true, then the database will be created
   * if it is missing during {@code RocksDB.open()}.
   * Default: false
   *
   * @param flag a flag indicating whether to create a database the
   *     specified database in {@link RocksDB#open(org.rocksdb.Options, String)} operation
   *     is missing.
   * @return the instance of the current Options
   * @see RocksDB#open(org.rocksdb.Options, String)
   */
  Object setCreateIfMissing(boolean flag);

  /**
   * Return true if the create_if_missing flag is set to true.
   * If true, the database will be created if it is missing.
   *
   * @return true if the createIfMissing option is set to true.
   * @see #setCreateIfMissing(boolean)
   */
  boolean createIfMissing();

  /**
   * <p>If true, missing column families will be automatically created</p>
   *
   * <p>Default: false</p>
   *
   * @param flag a flag indicating if missing column families shall be
   *     created automatically.
   * @return true if missing column families shall be created automatically
   *     on open.
   */
  Object setCreateMissingColumnFamilies(boolean flag);

  /**
   * Return true if the create_missing_column_families flag is set
   * to true. If true column families be created if missing.
   *
   * @return true if the createMissingColumnFamilies is set to
   *     true.
   * @see #setCreateMissingColumnFamilies(boolean)
   */
  boolean createMissingColumnFamilies();

  /**
   * If true, an error will be thrown during RocksDB.open() if the
   * database already exists.
   * Default: false
   *
   * @param errorIfExists if true, an exception will be thrown
   *     during {@code RocksDB.open()} if the database already exists.
   * @return the reference to the current option.
   * @see RocksDB#open(org.rocksdb.Options, String)
   */
  Object setErrorIfExists(boolean errorIfExists);

  /**
   * If true, an error will be thrown during RocksDB.open() if the
   * database already exists.
   *
   * @return if true, an error is raised when the specified database
   *    already exists before open.
   */
  boolean errorIfExists();

  /**
   * If true, the implementation will do aggressive checking of the
   * data it is processing and will stop early if it detects any
   * errors.  This may have unforeseen ramifications: for example, a
   * corruption of one DB entry may cause a large number of entries to
   * become unreadable or for the entire DB to become unopenable.
   * If any of the  writes to the database fails (Put, Delete, Merge, Write),
   * the database will switch to read-only mode and fail all other
   * Write operations.
   * Default: true
   *
   * @param paranoidChecks a flag to indicate whether paranoid-check
   *     is on.
   * @return the reference to the current option.
   */
  Object setParanoidChecks(boolean paranoidChecks);

  /**
   * If true, the implementation will do aggressive checking of the
   * data it is processing and will stop early if it detects any
   * errors.  This may have unforeseen ramifications: for example, a
   * corruption of one DB entry may cause a large number of entries to
   * become unreadable or for the entire DB to become unopenable.
   * If any of the  writes to the database fails (Put, Delete, Merge, Write),
   * the database will switch to read-only mode and fail all other
   * Write operations.
   *
   * @return a boolean indicating whether paranoid-check is on.
   */
  boolean paranoidChecks();

  /**
   * Use to control write rate of flush and compaction. Flush has higher
   * priority than compaction. Rate limiting is disabled if nullptr.
   * Default: nullptr
   *
   * @param config rate limiter config.
   * @return the instance of the current Object.
   */
  Object setRateLimiterConfig(RateLimiterConfig config);

  /**
   * Number of open files that can be used by the DB.  You may need to
   * increase this if your database has a large working set. Value -1 means
   * files opened are always kept open. You can estimate number of files based
   * on {@code target_file_size_base} and {@code target_file_size_multiplier}
   * for level-based compaction. For universal-style compaction, you can usually
   * set it to -1.
   * Default: 5000
   *
   * @param maxOpenFiles the maximum number of open files.
   * @return the reference to the current DBOptions.
   */
  Object setMaxOpenFiles(int maxOpenFiles);

  /**
   * Number of open files that can be used by the DB.  You may need to
   * increase this if your database has a large working set. Value -1 means
   * files opened are always kept open. You can estimate number of files based
   * on {@code target_file_size_base} and {@code target_file_size_multiplier}
   * for level-based compaction. For universal-style compaction, you can usually
   * set it to -1.
   *
   * @return the maximum number of open files.
   */
  int maxOpenFiles();

  /**
   * <p>Once write-ahead logs exceed this size, we will start forcing the
   * flush of column families whose memtables are backed by the oldest live
   * WAL file (i.e. the ones that are causing all the space amplification).
   * </p>
   * <p>If set to 0 (default), we will dynamically choose the WAL size limit to
   * be [sum of all write_buffer_size * max_write_buffer_number] * 2</p>
   * <p>Default: 0</p>
   */
  Object setMaxTotalWalSize(long maxTotalWalSize);

  /**
   * <p>Returns the max total wal size. Once write-ahead logs exceed this size,
   * we will start forcing the flush of column families whose memtables are
   * backed by the oldest live WAL file (i.e. the ones that are causing all
   * the space amplification).</p>
   *
   * <p>If set to 0 (default), we will dynamically choose the WAL size limit
   * to be [sum of all write_buffer_size * max_write_buffer_number] * 2
   * </p>
   *
   * @return max total wal size
   */
  long maxTotalWalSize();

  /**
   * <p>Creates statistics object which collects metrics about database operations.
   * Statistics objects should not be shared between DB instances as
   * it does not use any locks to prevent concurrent updates.</p>
   *
   * @return the instance of the current Object.
   * @see RocksDB#open(org.rocksdb.Options, String)
   */
  Object createStatistics();

  /**
   * <p>Returns statistics object. Calls {@link #createStatistics()} if
   * C++ returns {@code nullptr} for statistics.</p>
   *
   * @return the instance of the statistics object.
   * @see #createStatistics()
   */
  Statistics statisticsPtr();

  /**
   * <p>If true, then the contents of manifest and data files are
   * not synced to stable storage. Their contents remain in the
   * OS buffers till theOS decides to flush them.</p>
   *
   * <p>This option is good for bulk-loading of data.</p>
   *
   * <p>Once the bulk-loading is complete, please issue a sync to
   * the OS to flush all dirty buffers to stable storage.</p>
   *
   * <p>Default: false</p>
   *
   * @param disableDataSync a boolean flag to specify whether to
   *     disable data sync.
   * @return the reference to the current DBOptions.
   */
  Object setDisableDataSync(boolean disableDataSync);

  /**
   * If true, then the contents of data files are not synced
   * to stable storage. Their contents remain in the OS buffers till the
   * OS decides to flush them. This option is good for bulk-loading
   * of data. Once the bulk-loading is complete, please issue a
   * sync to the OS to flush all dirty buffers to stable storage.
   *
   * @return if true, then data-sync is disabled.
   */
  boolean disableDataSync();

  /**
   * <p>If true, then every store to stable storage will issue a fsync.</p>
   * <p>If false, then every store to stable storage will issue a fdatasync.
   * This parameter should be set to true while storing data to
   * filesystem like ext3 that can lose files after a reboot.</p>
   * <p>Default: false</p>
   *
   * @param useFsync a boolean flag to specify whether to use fsync
   * @return the instance of the current Object.
   */
  Object setUseFsync(boolean useFsync);

  /**
   * <p>If true, then every store to stable storage will issue a fsync.</p>
   * <p>If false, then every store to stable storage will issue a fdatasync.
   * This parameter should be set to true while storing data to
   * filesystem like ext3 that can lose files after a reboot.</p>
   *
   * @return boolean value indicating if fsync is used.
   */
  boolean useFsync();

  /**
   * This specifies the info LOG dir.
   * If it is empty, the log files will be in the same dir as data.
   * If it is non empty, the log files will be in the specified dir,
   * and the db data dir's absolute path will be used as the log file
   * name's prefix.
   *
   * @param dbLogDir the path to the info log directory
   * @return the instance of the current Object.
   */
  Object setDbLogDir(String dbLogDir);

  /**
   * Returns the directory of info log.
   *
   * If it is empty, the log files will be in the same dir as data.
   * If it is non empty, the log files will be in the specified dir,
   * and the db data dir's absolute path will be used as the log file
   * name's prefix.
   *
   * @return the path to the info log directory
   */
  String dbLogDir();

  /**
   * This specifies the absolute dir path for write-ahead logs (WAL).
   * If it is empty, the log files will be in the same dir as data,
   *   dbname is used as the data dir by default
   * If it is non empty, the log files will be in kept the specified dir.
   * When destroying the db,
   *   all log files in wal_dir and the dir itself is deleted
   *
   * @param walDir the path to the write-ahead-log directory.
   * @return the instance of the current Object.
   */
  Object setWalDir(String walDir);

  /**
   * Returns the path to the write-ahead-logs (WAL) directory.
   *
   * If it is empty, the log files will be in the same dir as data,
   *   dbname is used as the data dir by default
   * If it is non empty, the log files will be in kept the specified dir.
   * When destroying the db,
   *   all log files in wal_dir and the dir itself is deleted
   *
   * @return the path to the write-ahead-logs (WAL) directory.
   */
  String walDir();

  /**
   * The periodicity when obsolete files get deleted. The default
   * value is 6 hours. The files that get out of scope by compaction
   * process will still get automatically delete on every compaction,
   * regardless of this setting
   *
   * @param micros the time interval in micros
   * @return the instance of the current Object.
   */
  Object setDeleteObsoleteFilesPeriodMicros(long micros);

  /**
   * The periodicity when obsolete files get deleted. The default
   * value is 6 hours. The files that get out of scope by compaction
   * process will still get automatically delete on every compaction,
   * regardless of this setting
   *
   * @return the time interval in micros when obsolete files will be deleted.
   */
  long deleteObsoleteFilesPeriodMicros();

  /**
   * Specifies the maximum number of concurrent background compaction jobs,
   * submitted to the default LOW priority thread pool.
   * If you're increasing this, also consider increasing number of threads in
   * LOW priority thread pool. For more information, see
   * Default: 1
   *
   * @param maxBackgroundCompactions the maximum number of background
   *     compaction jobs.
   * @return the instance of the current Object.
   *
   * @see RocksEnv#setBackgroundThreads(int)
   * @see RocksEnv#setBackgroundThreads(int, int)
   * @see #maxBackgroundFlushes()
   */
  Object setMaxBackgroundCompactions(int maxBackgroundCompactions);

  /**
   * Returns the maximum number of concurrent background compaction jobs,
   * submitted to the default LOW priority thread pool.
   * When increasing this number, we may also want to consider increasing
   * number of threads in LOW priority thread pool.
   * Default: 1
   *
   * @return the maximum number of concurrent background compaction jobs.
   * @see RocksEnv#setBackgroundThreads(int)
   * @see RocksEnv#setBackgroundThreads(int, int)
   */
  int maxBackgroundCompactions();

  /**
   * Specifies the maximum number of concurrent background flush jobs.
   * If you're increasing this, also consider increasing number of threads in
   * HIGH priority thread pool. For more information, see
   * Default: 1
   *
   * @param maxBackgroundFlushes number of max concurrent flush jobs
   * @return the instance of the current Object.
   *
   * @see RocksEnv#setBackgroundThreads(int)
   * @see RocksEnv#setBackgroundThreads(int, int)
   * @see #maxBackgroundCompactions()
   */
  Object setMaxBackgroundFlushes(int maxBackgroundFlushes);

  /**
   * Returns the maximum number of concurrent background flush jobs.
   * If you're increasing this, also consider increasing number of threads in
   * HIGH priority thread pool. For more information, see
   * Default: 1
   *
   * @return the maximum number of concurrent background flush jobs.
   * @see RocksEnv#setBackgroundThreads(int)
   * @see RocksEnv#setBackgroundThreads(int, int)
   */
  int maxBackgroundFlushes();

  /**
   * Specifies the maximum size of a info log file. If the current log file
   * is larger than `max_log_file_size`, a new info log file will
   * be created.
   * If 0, all logs will be written to one log file.
   *
   * @param maxLogFileSize the maximum size of a info log file.
   * @return the instance of the current Object.
   * @throws org.rocksdb.RocksDBException
   */
  Object setMaxLogFileSize(long maxLogFileSize)
      throws RocksDBException;

  /**
   * Returns the maximum size of a info log file. If the current log file
   * is larger than this size, a new info log file will be created.
   * If 0, all logs will be written to one log file.
   *
   * @return the maximum size of the info log file.
   */
  long maxLogFileSize();

  /**
   * Specifies the time interval for the info log file to roll (in seconds).
   * If specified with non-zero value, log file will be rolled
   * if it has been active longer than `log_file_time_to_roll`.
   * Default: 0 (disabled)
   *
   * @param logFileTimeToRoll the time interval in seconds.
   * @return the instance of the current Object.
   * @throws org.rocksdb.RocksDBException
   */
  Object setLogFileTimeToRoll(long logFileTimeToRoll)
      throws RocksDBException;

  /**
   * Returns the time interval for the info log file to roll (in seconds).
   * If specified with non-zero value, log file will be rolled
   * if it has been active longer than `log_file_time_to_roll`.
   * Default: 0 (disabled)
   *
   * @return the time interval in seconds.
   */
  long logFileTimeToRoll();

  /**
   * Specifies the maximum number of info log files to be kept.
   * Default: 1000
   *
   * @param keepLogFileNum the maximum number of info log files to be kept.
   * @return the instance of the current Object.
   * @throws org.rocksdb.RocksDBException
   */
  Object setKeepLogFileNum(long keepLogFileNum)
      throws RocksDBException;

  /**
   * Returns the maximum number of info log files to be kept.
   * Default: 1000
   *
   * @return the maximum number of info log files to be kept.
   */
  long keepLogFileNum();

  /**
   * Manifest file is rolled over on reaching this limit.
   * The older manifest file be deleted.
   * The default value is MAX_INT so that roll-over does not take place.
   *
   * @param maxManifestFileSize the size limit of a manifest file.
   * @return the instance of the current Object.
   */
  Object setMaxManifestFileSize(long maxManifestFileSize);

  /**
   * Manifest file is rolled over on reaching this limit.
   * The older manifest file be deleted.
   * The default value is MAX_INT so that roll-over does not take place.
   *
   * @return the size limit of a manifest file.
   */
  long maxManifestFileSize();

  /**
   * Number of shards used for table cache.
   *
   * @param tableCacheNumshardbits the number of chards
   * @return the instance of the current Object.
   */
  Object setTableCacheNumshardbits(int tableCacheNumshardbits);

  /**
   * Number of shards used for table cache.
   *
   * @return the number of shards used for table cache.
   */
  int tableCacheNumshardbits();

  /**
   * During data eviction of table's LRU cache, it would be inefficient
   * to strictly follow LRU because this piece of memory will not really
   * be released unless its refcount falls to zero. Instead, make two
   * passes: the first pass will release items with refcount = 1,
   * and if not enough space releases after scanning the number of
   * elements specified by this parameter, we will remove items in LRU
   * order.
   *
   * @param limit scan count limit
   * @return the instance of the current Object.
   */
  Object setTableCacheRemoveScanCountLimit(int limit);

  /**
   * During data eviction of table's LRU cache, it would be inefficient
   * to strictly follow LRU because this piece of memory will not really
   * be released unless its refcount falls to zero. Instead, make two
   * passes: the first pass will release items with refcount = 1,
   * and if not enough space releases after scanning the number of
   * elements specified by this parameter, we will remove items in LRU
   * order.
   *
   * @return scan count limit
   */
  int tableCacheRemoveScanCountLimit();

  /**
   * {@link #walTtlSeconds()} and {@link #walSizeLimitMB()} affect how archived logs
   * will be deleted.
   * <ol>
   * <li>If both set to 0, logs will be deleted asap and will not get into
   * the archive.</li>
   * <li>If WAL_ttl_seconds is 0 and WAL_size_limit_MB is not 0,
   *    WAL files will be checked every 10 min and if total size is greater
   *    then WAL_size_limit_MB, they will be deleted starting with the
   *    earliest until size_limit is met. All empty files will be deleted.</li>
   * <li>If WAL_ttl_seconds is not 0 and WAL_size_limit_MB is 0, then
   *    WAL files will be checked every WAL_ttl_secondsi / 2 and those that
   *    are older than WAL_ttl_seconds will be deleted.</li>
   * <li>If both are not 0, WAL files will be checked every 10 min and both
   *    checks will be performed with ttl being first.</li>
   * </ol>
   *
   * @param walTtlSeconds the ttl seconds
   * @return the instance of the current Object.
   * @see #setWalSizeLimitMB(long)
   */
  Object setWalTtlSeconds(long walTtlSeconds);

  /**
   * WalTtlSeconds() and walSizeLimitMB() affect how archived logs
   * will be deleted.
   * <ol>
   * <li>If both set to 0, logs will be deleted asap and will not get into
   * the archive.</li>
   * <li>If WAL_ttl_seconds is 0 and WAL_size_limit_MB is not 0,
   * WAL files will be checked every 10 min and if total size is greater
   * then WAL_size_limit_MB, they will be deleted starting with the
   * earliest until size_limit is met. All empty files will be deleted.</li>
   * <li>If WAL_ttl_seconds is not 0 and WAL_size_limit_MB is 0, then
   * WAL files will be checked every WAL_ttl_secondsi / 2 and those that
   * are older than WAL_ttl_seconds will be deleted.</li>
   * <li>If both are not 0, WAL files will be checked every 10 min and both
   * checks will be performed with ttl being first.</li>
   * </ol>
   *
   * @return the wal-ttl seconds
   * @see #walSizeLimitMB()
   */
  long walTtlSeconds();

  /**
   * WalTtlSeconds() and walSizeLimitMB() affect how archived logs
   * will be deleted.
   * <ol>
   * <li>If both set to 0, logs will be deleted asap and will not get into
   *    the archive.</li>
   * <li>If WAL_ttl_seconds is 0 and WAL_size_limit_MB is not 0,
   *    WAL files will be checked every 10 min and if total size is greater
   *    then WAL_size_limit_MB, they will be deleted starting with the
   *    earliest until size_limit is met. All empty files will be deleted.</li>
   * <li>If WAL_ttl_seconds is not 0 and WAL_size_limit_MB is 0, then
   *    WAL files will be checked every WAL_ttl_secondsi / 2 and those that
   *    are older than WAL_ttl_seconds will be deleted.</li>
   * <li>If both are not 0, WAL files will be checked every 10 min and both
   *    checks will be performed with ttl being first.</li>
   * </ol> 
   *
   * @param sizeLimitMB size limit in mega-bytes.
   * @return the instance of the current Object.
   * @see #setWalSizeLimitMB(long)
   */
  Object setWalSizeLimitMB(long sizeLimitMB);

  /**
   * {@link #walTtlSeconds()} and {@code #walSizeLimitMB()} affect how archived logs
   * will be deleted.
   * <ol>
   * <li>If both set to 0, logs will be deleted asap and will not get into
   *    the archive.</li>
   * <li>If WAL_ttl_seconds is 0 and WAL_size_limit_MB is not 0,
   *    WAL files will be checked every 10 min and if total size is greater
   *    then WAL_size_limit_MB, they will be deleted starting with the
   *    earliest until size_limit is met. All empty files will be deleted.</li>
   * <li>If WAL_ttl_seconds is not 0 and WAL_size_limit_MB is 0, then
   *    WAL files will be checked every WAL_ttl_seconds i / 2 and those that
   *    are older than WAL_ttl_seconds will be deleted.</li>
   * <li>If both are not 0, WAL files will be checked every 10 min and both
   *    checks will be performed with ttl being first.</li>
   * </ol>
   * @return size limit in mega-bytes.
   * @see #walSizeLimitMB()
   */
  long walSizeLimitMB();

  /**
   * Number of bytes to preallocate (via fallocate) the manifest
   * files.  Default is 4mb, which is reasonable to reduce random IO
   * as well as prevent overallocation for mounts that preallocate
   * large amounts of data (such as xfs's allocsize option).
   *
   * @param size the size in byte
   * @return the instance of the current Object.
   * @throws org.rocksdb.RocksDBException
   */
  Object setManifestPreallocationSize(long size)
      throws RocksDBException;

  /**
   * Number of bytes to preallocate (via fallocate) the manifest
   * files.  Default is 4mb, which is reasonable to reduce random IO
   * as well as prevent overallocation for mounts that preallocate
   * large amounts of data (such as xfs's allocsize option).
   *
   * @return size in bytes.
   */
  long manifestPreallocationSize();

  /**
   * Data being read from file storage may be buffered in the OS
   * Default: true
   *
   * @param allowOsBuffer if true, then OS buffering is allowed.
   * @return the instance of the current Object.
   */
  Object setAllowOsBuffer(boolean allowOsBuffer);

  /**
   * Data being read from file storage may be buffered in the OS
   * Default: true
   *
   * @return if true, then OS buffering is allowed.
   */
  boolean allowOsBuffer();

  /**
   * Allow the OS to mmap file for reading sst tables.
   * Default: false
   *
   * @param allowMmapReads true if mmap reads are allowed.
   * @return the instance of the current Object.
   */
  Object setAllowMmapReads(boolean allowMmapReads);

  /**
   * Allow the OS to mmap file for reading sst tables.
   * Default: false
   *
   * @return true if mmap reads are allowed.
   */
  boolean allowMmapReads();

  /**
   * Allow the OS to mmap file for writing. Default: false
   *
   * @param allowMmapWrites true if mmap writes are allowd.
   * @return the instance of the current Object.
   */
  Object setAllowMmapWrites(boolean allowMmapWrites);

  /**
   * Allow the OS to mmap file for writing. Default: false
   *
   * @return true if mmap writes are allowed.
   */
  boolean allowMmapWrites();

  /**
   * Disable child process inherit open files. Default: true
   *
   * @param isFdCloseOnExec true if child process inheriting open
   *     files is disabled.
   * @return the instance of the current Object.
   */
  Object setIsFdCloseOnExec(boolean isFdCloseOnExec);

  /**
   * Disable child process inherit open files. Default: true
   *
   * @return true if child process inheriting open files is disabled.
   */
  boolean isFdCloseOnExec();

  /**
   * Skip log corruption error on recovery (If client is ok with
   * losing most recent changes)
   * Default: false
   *
   * @param skip true if log corruption errors are skipped during recovery.
   * @return the instance of the current Object.
   */
  Object setSkipLogErrorOnRecovery(boolean skip);

  /**
   * Skip log corruption error on recovery (If client is ok with
   * losing most recent changes)
   * Default: false
   *
   * @return true if log corruption errors are skipped during recovery.
   */
  boolean skipLogErrorOnRecovery();

  /**
   * if not zero, dump rocksdb.stats to LOG every stats_dump_period_sec
   * Default: 3600 (1 hour)
   *
   * @param statsDumpPeriodSec time interval in seconds.
   * @return the instance of the current Object.
   */
  Object setStatsDumpPeriodSec(int statsDumpPeriodSec);

  /**
   * If not zero, dump rocksdb.stats to LOG every stats_dump_period_sec
   * Default: 3600 (1 hour)
   *
   * @return time interval in seconds.
   */
  int statsDumpPeriodSec();

  /**
   * If set true, will hint the underlying file system that the file
   * access pattern is random, when a sst file is opened.
   * Default: true
   *
   * @param adviseRandomOnOpen true if hinting random access is on.
   * @return the instance of the current Object.
   */
  Object setAdviseRandomOnOpen(boolean adviseRandomOnOpen);

  /**
   * If set true, will hint the underlying file system that the file
   * access pattern is random, when a sst file is opened.
   * Default: true
   *
   * @return true if hinting random access is on.
   */
  boolean adviseRandomOnOpen();

  /**
   * Use adaptive mutex, which spins in the user space before resorting
   * to kernel. This could reduce context switch when the mutex is not
   * heavily contended. However, if the mutex is hot, we could end up
   * wasting spin time.
   * Default: false
   *
   * @param useAdaptiveMutex true if adaptive mutex is used.
   * @return the instance of the current Object.
   */
  Object setUseAdaptiveMutex(boolean useAdaptiveMutex);

  /**
   * Use adaptive mutex, which spins in the user space before resorting
   * to kernel. This could reduce context switch when the mutex is not
   * heavily contended. However, if the mutex is hot, we could end up
   * wasting spin time.
   * Default: false
   *
   * @return true if adaptive mutex is used.
   */
  boolean useAdaptiveMutex();

  /**
   * Allows OS to incrementally sync files to disk while they are being
   * written, asynchronously, in the background.
   * Issue one request for every bytes_per_sync written. 0 turns it off.
   * Default: 0
   *
   * @param bytesPerSync size in bytes
   * @return the instance of the current Object.
   */
  Object setBytesPerSync(long bytesPerSync);

  /**
   * Allows OS to incrementally sync files to disk while they are being
   * written, asynchronously, in the background.
   * Issue one request for every bytes_per_sync written. 0 turns it off.
   * Default: 0
   *
   * @return size in bytes
   */
  long bytesPerSync();
}
