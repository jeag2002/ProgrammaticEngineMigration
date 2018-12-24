'''
Created on 24 dic. 2018

URLS:

https://www.fullstackpython.com/wsgi-servers.html
https://stackoverflow.com/questions/16511337/correct-way-to-try-except-using-python-requests-module
http://www.ntu.edu.sg/home/ehchua/programming/webprogramming/Python3_Flask.html
http://timmyreilly.azurewebsites.net/python-flask-windows-development-environment-setup/
https://blog.miguelgrinberg.com/post/the-flask-mega-tutorial-part-i-hello-world
http://docs.python-requests.org/en/master/_modules/requests/sessions/


@author: jeag2
'''

from flask import Flask  
import requests
import json

app = Flask(__name__)    

@app.route('/')   
def main():
    return 'Hello, world!'

    
@app.route('/rtbdispatcher/rtb/<int:id>', methods=['GET'])
def process(id):
    
    if id == 271:
        return keenkaleIntegration()
    else:
        return 'integration ' + str(id) + ' not recognized'
    
def keenkaleIntegration():
    
    url = 'http://rtb-useast.keenkale.com/rtb?zone=55764'
    
    data = '''
            {
              "id": "dca01512ae9ce5b95712794dcd677d80",
              "imp": [
                {
                  "id": "6bbc5bc3eb7081e1b7f4f7cc29b815f9",
                  "instl": 0,
                  "tagid": "tx500674895",
                  "bidfloor": 0.052500000000000005,
                  "bidfloorcur": "USD",
                  "secure": 1,
                  "banner": {
                    "id": "d06ca4a034793852b24a6bbc66e14690",
                    "w": 320,
                    "h": 50,
                    "mimes": [
                      "image/jpg",
                      "image/gif"
                    ],
                    "battr": [
                      
                    ],
                    "wmax": 320,
                    "hmax": 50,
                    "wmin": 300,
                    "hmin": 49,
                    "api": [
                      3,
                      5
                    ]
                  }
                }
              ],
              "device": {
                "ua": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
                "geo": {
                  "country": "ESP",
                  "lat": 41.427564,
                  "lon": 2.185005,
                  "type": 1
                },
                "dnt": 0,
                "lmt": 0,
                "ip": "88.1.48.62",
                "devicetype": 4,
                "make": "Samsung",
                "model": "gt-9300",
                "os": "Android",
                "osv": "4.4.4",
                "ifa": "1a58da58-4930-4adc-b1a4-2dc1ba386a96",
                "connectiontype": 2,
                "js": 1,
                "language": "es"
              },
              "test": 0,
              "at": 1,
              "tmax": 1500,
              "badv": [
                
              ],
              "app": {
                "id": "10308",
                "name": "Whatsdog",
                "publisher": {
                  "id": "67fbd985230c665850075df702d12c5e",
                  "name": "tappx",
                  "domain": "tappx.com"
                },
                "bundle": "com.secondlemon.whatsdogpremium",
                "storeurl": "https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium"
              },
              "user": {
                "geo": {
                  "country": "ESP",
                  "lat": 41.427564,
                  "lon": 2.185005,
                  "type": 1
                }
              }
            }
    '''
    
    headers = {
        "Content-Type":"application/json",
        "Connection":"Keep-Alive",
        "Accept-Charset":"UTF-8",
        "Expect":"x-openrtb-version: 2.4",
        "x-forwarded-for":"216.15.125.142",
        "x-device-user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
        "x-original-user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36"
        }
    
    
    message = ''
    
    try:
        response = requests.post(url, data=data, headers=headers)
        message = 'Status code ' + str(response.status_code) + ' body ' + response.content
    except requests.exceptions.RequestException as e:
        message = 'Something happens ' + e
        
    return message


if __name__ == '__main__':  
    app.run(host='0.0.0.0', port= 8082, debug=True)  