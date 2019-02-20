package main

//https://www.golang-book.com/books/intro/10 (GoRoutines + Channels)

import (
	"fmt"
	"net/http"
	"time"
	"io/ioutil"
)

func clientRest(c chan string){
	
	fmt.Println("Processing http://localhost:8082/rtbdispatcher/rtb/271")
	
	req, err := http.NewRequest("GET","http://localhost:8082/rtbdispatcher/rtb/271", nil)
	
	client := &http.Client{
		Timeout: time.Second * 10,
	}
	
	resp, err := client.Do(req)
	
	defer resp.Body.Close()
	
	
	if err != nil {
		responseStr := fmt.Sprint("GO Http request failed %s", err)
		c <- responseStr
	} else {
		data, _ := ioutil.ReadAll(resp.Body)
		responseStr := fmt.Sprint("GO Http status %d body %s", resp.StatusCode , string(data))
		c <- responseStr
	} 
	
	
	
	
}

func captureData(c chan string){
	for {
		msg := <- c
		fmt.Println(msg)
	}
}


func main() {
	
	var c chan string = make(chan string)
	
	for i:=0; i<10; i++ {
		go clientRest(c)
	}
	
	go captureData(c)
	
	var input string
	fmt.Scanln(&input)
}
