(ns dleancloud.sms
  (:require [cheshire.core :refer [generate-string]]
            [dbox.map :refer [check-keys]]))

(def sms-url "https://api.leancloud.cn/1.1/")

(defn wrap-request [[url credential-map body]]
  [url {:body    (generate-string body)
        :headers (merge {:content-type "application/json"} credential-map)
        :as :auto}])

(defn send-code [credential-map phone-num & [voice?]]
  (let [params (if (nil? voice?)
                 {:mobilePhoneNumber (str phone-num)}
                 {:mobilePhoneNumber (str phone-num) :smsType :voice})]

    [(str sms-url "requestSmsCode") credential-map params]))

(defn verify-code [credential-map phone-num code]
  [(format "%sverifySmsCode/%s?mobilePhoneNumber=%s" sms-url code phone-num)
   credential-map nil])

(defn send-tpl [credential-map params]
  (check-keys [:mobilePhoneNumber :template] params)
  [(str sms-url "requestSmsCode") credential-map params])