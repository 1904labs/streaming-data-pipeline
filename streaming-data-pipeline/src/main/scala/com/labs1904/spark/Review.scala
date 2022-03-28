package com.labs1904.spark


case class Review(marketplace: String,
                  customer_id: String,
                  review_id: String,
                  product_id: String,
                  product_parent: String,
                  product_title: String,
                  product_category: String,
                  star_rating: String,
                  helpful_votes: String,
                  total_votes: String,
                  vine: String,
                  verified_purchase: String,
                  review_headline: String,
                  review_body: String,
                  review_date: String
                 )


case class EnrichedReview(marketplace: String,
                          customer_id: String,
                          review_id: String,
                          product_id: String,
                          product_parent: String,
                          product_title: String,
                          product_category: String,
                          star_rating: String,
                          helpful_votes: String,
                          total_votes: String,
                          vine: String,
                          verified_purchase: String,
                          review_headline: String,
                          review_body: String,
                          review_date: String,
                          name: String,
                          birthdate: String,
                          mail: String,
                          sex: String,
                          username: String
                         )

case class User(name: String,
                birthdate: String,
                mail: String,
                sex: String,
                username: String
               )

case class EnrichedReviewNested(review: Review, user: User)