
'''
http://elliothallmark.com/2016/12/23/requests-with-concurrent-futures-in-python-2-7/
'''

import requests
import json

from concurrent.futures import ThreadPoolExecutor, wait, as_completed
from time import time



def clientHTTP():
    
    url = 'http://localhost:8082/rtbdispatcher/rtb/271'
    message = ''
    try:
        response = requests.get(url)
        message = 'PYTHON Status code ' + str(response.status_code) + ' body ' + response.content
        
    except requests.exceptions.RequestException as e:
        message = 'PYTHON Something happens ' + str(e)

    return message     
        

def concurrentThreads():
    
    with ThreadPoolExecutor(max_workers=10) as executor:
        futures = [executor.submit(clientHTTP) for i in range(0,10)]
        for future in as_completed(futures):
            try:
                print(future.result())
            except Exception as exc:
                print('something happened %s', exc)

if __name__ == '__main__':
    concurrentThreads()
