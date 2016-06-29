# dleancloud

A Clojure library designed to support leancloud API


## Usage

dleancloud use clj-http (or aleph) as http client, make sure add clj-http dependence.
eg:
```
[clj-http "2.1.0"]
```


Add dleancloud dependence:

[![Clojars Project](https://img.shields.io/clojars/v/dleancloud.svg)](https://clojars.org/dleancloud)

Examples:

```clojure
(require '[clj-http.client :as c])
(require '[dleancloud.sms :as s])

(def credential {:X-LC-Id "xxx", :X-LC-Key "xxx"})

(->> (s/send-code credential "telephone-num-should-be-string")
     s/wrap-request
     (apply c/post))
;=>
;{:status 200,
; :headers {"Server" "Tengine",
;           "Date" "Wed, 29 Jun 2016 01:36:05 GMT",
;           "Content-Type" "application/json;charset=utf-8",
;           "Content-Length" "2",
;           "Connection" "close",
;           "Cache-Control" "no-cache,no-store",
;           "Pragma" "no-cache",
;           "Strict-Transport-Security" "max-age=31536000"},
; :body {},
; :request-time 342,
; :trace-redirects ["https://api.leancloud.cn/1.1/requestSmsCode"],
; :orig-content-encoding nil,
; :content-type :application/json,
; :content-type-params {:charset "utf-8"}}



(->> (s/verify-code credential "telephone-num-should-be-string" XX-6-digital-code-xx)
     s/wrap-request
     (apply c/post))
;=>
;{:status 200,
; :headers {"Server" "Tengine",
;           "Date" "Wed, 29 Jun 2016 01:43:10 GMT",
;           "Content-Type" "application/json;charset=utf-8",
;           "Content-Length" "2",
;           "Connection" "close",
;           "Cache-Control" "no-cache,no-store",
;           "Pragma" "no-cache",
;           "Strict-Transport-Security" "max-age=31536000"},
; :body {},
; :request-time 256,
; :trace-redirects ["https://api.leancloud.cn/1.1/verifySmsCode/732229??mobilePhoneNumber=telephone-num-should-be-string"],
; :orig-content-encoding nil,
; :content-type :application/json,
; :content-type-params {:charset "utf-8"}}



(->> (s/send-tpl credential {:mobilePhoneNumber "telephone-num-should-be-string"
                           :template          "template-name"
                           :name              "XXXX"
                           :another-param     "XXX"})
     s/wrap-request
     (apply c/post))

=>
;{:status 200,
; :headers {"Server" "Tengine",
;           "Date" "Wed, 29 Jun 2016 01:56:01 GMT",
;           "Content-Type" "application/json;charset=utf-8",
;           "Content-Length" "2",
;           "Connection" "close",
;           "Cache-Control" "no-cache,no-store",
;           "Pragma" "no-cache",
;           "Strict-Transport-Security" "max-age=31536000"},
; :body {},
; :request-time 624,
; :trace-redirects ["https://api.leancloud.cn/1.1/requestSmsCode"],
; :orig-content-encoding nil,
; :content-type :application/json,
; :content-type-params {:charset "utf-8"}}

```

## License

Copyright © 2016 Michael Wong

Distributed under the Eclipse Public License .
