(ns user
  (:require [portal.api :as p]))

(def p (p/open {:launcher :vs-code}))
(add-tap #'p/submit)

(comment
  (p/close)
  (tap> "test"))