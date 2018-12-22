
//https://www.codementor.io/codehakase/building-a-restful-api-with-golang-a6yivzqdo
//https://github.com/gorilla/context/issues/7
//https://meshstudio.io/blog/2017-11-06-serving-html-with-golang/
//https://github.com/gorilla/mux
//https://www.thepolyglotdeveloper.com/2017/07/consume-restful-api-endpoints-golang-application/
//https://stackoverflow.com/questions/40429296/converting-string-to-json-or-struct-in-golang

package main

import (
	"bytes"
	"encoding/json"
	"time"
	"io/ioutil"
	"fmt"
    "log"
    "net/http"
    "github.com/gorilla/mux"
)


func processKeenKale(w http.ResponseWriter){
	
	fmt.Println("[KEENKALE - INTEGRATION] Connect to KeenKale")
	
	
		in := []byte(`{
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
			}`)
	
	var raw map[string]interface{}
	json.Unmarshal(in, &raw)
	//jsonValue, _ := json.Marshal(raw)
	
	req, err := http.NewRequest("POST","http://rtb-useast.keenkale.com/rtb?zone=55764", bytes.NewBuffer(in))
	
	req.Header.Add("Content-Type","application/json");
	req.Header.Add("Connection","Keep-Alive");
	req.Header.Add("Accept-Charset","UTF-8");
	req.Header.Add("Expect","x-openrtb-version: 2.4");
	req.Header.Add("x-forwarded-for","216.15.125.142");
	req.Header.Add("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
	req.Header.Add("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
	
	

	//req.Body.Read(jsonValue)
	
	
	
	
	/*
	jsonData := map[string]string{"firstname": "Nic", "lastname": "Raboy"}
    jsonValue, _ := json.Marshal(jsonData)
	req.Body.Read(jsonValue)
	*/
	
	/*
	in := []byte(`{ "votes": { "option_A": "3" } }`)
    var raw map[string]interface{}
    json.Unmarshal(in, &raw)
    raw["count"] = 1
    out, _ := json.Marshal(raw)
    println(string(out))
	*/
	
	
	
	client := &http.Client{
		Timeout: time.Second * 10,
	}
	
	
	resp, err := client.Do(req)
	
	defer resp.Body.Close()
	
	if err != nil {
		fmt.Fprintf(w, "Http request failed %s", err)
		fmt.Println("[KEENKALE - INTEGRATION RESP] Http request failed %s", err)
	} else {
		data, _ := ioutil.ReadAll(resp.Body)
		fmt.Fprintf(w, "Http status %d body %s", resp.StatusCode , string(data))
		fmt.Println("[KEENKALE - INTEGRATION RESP] Http status %d body %s", resp.StatusCode , string(data))
	} 
}



func processIntegration(w http.ResponseWriter, r *http.Request) {
	
	params := mux.Vars(r)
	
	fmt.Println("[GOINTEGRATION] Processing integration ", params["id"])
	
	if params["id"] == "271"{
		processKeenKale(w)
	} else {
		w.WriteHeader(http.StatusOK)
		fmt.Fprintf(w, "integration %v not recognized", params["id"])
	}
	
}

func main() {
	fmt.Println("[GOINTEGRATION] LAUNCH NEW SERVER LISTEN AT 8082")
	router := mux.NewRouter()
	router.HandleFunc("/rtbdispatcher/rtb/{id}",processIntegration).Methods("GET")	
    log.Fatal(http.ListenAndServe(":8082", router))
}
