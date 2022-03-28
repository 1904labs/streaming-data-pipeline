CREATE EXTERNAL TABLE IF NOT EXISTS wfarrell.reviews_ext_json(
    marketplace STRING,
    customer_id STRING,
    review_id STRING,
    product_id STRING,
    product_parent STRING,
    product_title STRING,
    product_category STRING,
    star_rating STRING,
    helpful_votes STRING,
    total_votes STRING,
    vine STRING,
    verified_purchase STRING,
    review_headline STRING,
    review_body STRING,
    review_date STRING
)
ROW FORMAT SERDE
'org.apache.hive.hcatalog.data.JsonSerDe'
STORED AS TEXTFILE
LOCATION '/user/wfarrell/reviewss'

CREATE EXTERNAL TABLE IF NOT EXISTS wfarrell.reviews_partitioned(
    marketplace STRING,
    customer_id STRING,
    review_id STRING,
    product_id STRING,
    product_parent STRING,
    product_title STRING,
    product_category STRING,
    helpful_votes STRING,
    total_votes STRING,
    vine STRING,
    verified_purchase STRING,
    review_headline STRING,
    review_body STRING,
    review_date STRING
)
PARTITIONED BY (
    star_rating STRING)
ROW FORMAT SERDE
'org.apache.hive.hcatalog.data.JsonSerDe'
STORED AS TEXTFILE
LOCATION '/user/wfarrell/reviews_partitioned'


ALTER TABLE wfarrell.reviews_partitioned ADD PARTITION(star_rating="1");
ALTER TABLE wfarrell.reviews_partitioned ADD PARTITION(star_rating="2");
ALTER TABLE wfarrell.reviews_partitioned ADD PARTITION(star_rating="3");
ALTER TABLE wfarrell.reviews_partitioned ADD PARTITION(star_rating="4");
ALTER TABLE wfarrell.reviews_partitioned ADD PARTITION(star_rating="5");