
//https://github.com/alexcrichton/curl-rust
//https://stackoverflow.com/questions/14154753/how-do-i-make-an-http-request-from-rust

#![recursion_limit="128"]
#![feature(proc_macro_hygiene, decl_macro)]
#[macro_use] 


extern crate serde_derive;
extern crate serde_json;
extern crate reqwest;
extern crate actix_web;

#[cfg(test)] 
mod tests;


use std::collections::HashMap;
use std::error::Error;
use serde_json::json;

use actix_web::{web, App, HttpServer, HttpRequest, Responder};

fn process_keen_kale() -> String{	
	
	let mut response = String::new();
	let mut url = String::new();
	//let mut map = HashMap::new();
	//map.insert("id","dca01512ae9ce5b95712794dcd677d80");
	
	let gist_body = json!(
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
	);
		
	let client = reqwest::Client::new();
	url = String::from("http://rtb-useast.keenkale.com/rtb?zone=55764");
	
	println!("launch POST keen_kale {}",url);

	let res = client.post(&url).
	header("Content-Type","application/json").
	header("Connection","Keep-Alive").
	header("Accept-Charset","UTF-8").
	header("Expect","x-openrtb-version: 2.4").
	header("x-forwarded-for","216.15.125.142").
	header("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36").
	header("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36").
	//json(&map).
	json(&gist_body).
	send();
	
	
	match res {
		Ok(s) => {
			let mut s_mut = s;
			let data = s_mut.text();
			response = format!("status [{}] body [{:?}]", s_mut.status(), data)
			},
		Err(e) => {
			let mut e_mut = e;
			response = format!("Error status [{:?}] cause [{:?}] description [{:?}] ", e_mut.status(), e_mut.cause(), e_mut.description())
		}
	}
	
	return format!("{}", response);
}

fn process(req: HttpRequest) -> String{
	
	let id = req.match_info().get("id").unwrap_or("271");
	
	if id == "271" {
	    let result = process_keen_kale();
		return format!("{}",result)
		
	} else {
		return format!("id {} not recognized",id)
	}
}


fn hello() -> &'static str {
    "Hello, world!"
}



fn main(){
	HttpServer::new(|| {
		
		App::new()
			.route("/", web::get().to(hello))
			.route("/rtbdispatcher/rtb/{id}",web::get().to(process))		
		
	})
	.bind("127.0.0.1:8082")
	.expect("Can not bind to port 8082")
	.run()
	.unwrap();
}





/*

#[get("/rtbdispatcher/rtb/<id>")]
fn process(id: &RawStr) -> String{
	
	if id == "271" {
	    let result = process_keen_kale();
		return format!("{}",result)
		
	} else {
		return format!("id {} not recognized",id)
	}
}

#[get("/")]
fn hello() -> &'static str {
    "Hello, world!"
}

fn main() {
    rocket::ignite().mount("/", routes![hello,process]).launch();
}
*/