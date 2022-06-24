# import requests
#
# # Requests 是 Python HTTP 的客户端库
# r = requests.get('http://www.douban.com')
# r.text
# r.content
#
# r = requests.post('http://xxx.com', data={'key': 'value'})

#
# import http.client
# import json
#
# conn = http.client.HTTPConnection("yunkun.cloud.test.baidu-int.com")
# payload = json.dumps({
#     "startTime": "2022-03-20",
#     "endTime": "2022-03-21",
#     "pageNum": 1,
#     "pageSize": 10000
# })
# headers = {
#     'accessKey': 'ykclient-91d7fef101554375aada5e5281edb747',
#     'sign': 'b8c9d4562aa0053beb2fe7dab0669c5ae7288bf35b39ee70b5e6407480361ebb',
#     'User-Agent': 'python-requests/2.26.0',
#     'Transfer-Encoding': 'chunked',
#     'Content-Type': 'application/json'
# }
# conn.request("POST", "/v1/dataCenter/compute/getSoldData", payload, headers)
# res = conn.getresponse()
# data = res.read()
# print(data.decode("utf-8"))

import requests
import json

url = "http://yunkun.cloud.test.baidu-int.com/v1/dataCenter/compute/getSoldData"

payload = {
    "startTime": "2022-03-20",
    "endTime": "2022-03-21",
    "pageNum": 1,
    "pageSize": 10000
}
headers = {
    'accessKey': 'ykclient-91d7fef101554375aada5e5281edb747',
    'sign': 'b8c9d4562aa0053beb2fe7dab0669c5ae7288bf35b39ee70b5e6407480361ebb',
    'User-Agent': 'python-requests/2.26.0',
    'Content-Type': 'application/json'
}

# response = requests.request("POST", url, headers=headers, json=payload)
response = requests.post(url, json=payload, headers=headers)

print(response.text)
