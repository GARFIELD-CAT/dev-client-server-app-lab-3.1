CREATE TABLE IF NOT EXISTS analytics.user_events
(
    event_date  Date,
    event_time  DateTime,
    id          UInt64,
    event_type  String
)
ENGINE = MergeTree
PARTITION BY toYYYYMM(event_date)
ORDER BY (event_date, id);