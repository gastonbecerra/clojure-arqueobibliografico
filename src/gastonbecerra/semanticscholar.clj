(ns gastonbecerra.clojure-arqueobibliografico
  (:require [org.httpkit.client :as hk-client]
            [martian.core :as martian]
            ; [martian.clj-http :as martian-http]
            [martian.httpkit :as kit])
  (:import java.io.IOException)
  )


(comment

  (def m {:resp "xxx"
          :token "xxx"
          :data {:data 555
                 :titulo "xxx"}})

  (get m :resp)
  (get-in m [:data :titulo])
  (:resp m))

@(hk-client/get 
  (str "https://api.semanticscholar.org/graph/v1/paper/search?" 
       (hk-client/query-string {:query "Habermas"})))


(def semantic-scholar-api 
  (kit/bootstrap-openapi "https://api.semanticscholar.org/graph/v1/swagger.json"))

(martian/explore semantic-scholar-api)



(def resp1 @(martian/response-for semantic-scholar-api :get-graph-paper-bulk-search {:query "Luhmann"
                                                                          :fields "title,year,authors"}))

(def resp2 @(martian/response-for semantic-scholar-api :get-graph-paper-bulk-search {:query "Luhmann"
                                                                                     :fields "title,year"}))

(:status resp2)
(:body resp2)
(get-in resp2 [:body :data 0])
(-> resp2 :body :data first :year)
