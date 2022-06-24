import requests

url = "http://yq-compass.dmop.baidu.com:8081?action=login&username=dhp&password=dhp123"

payload={}
headers = {
    'Cookie': 'compass.browser.session.id=1f21375f-ba31-44e8-bb17-690a0bd89500'
}

response = requests.request("POST", url, headers=headers, data=payload)

print(response.text)
