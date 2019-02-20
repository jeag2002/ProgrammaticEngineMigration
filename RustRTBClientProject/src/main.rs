
/*
https://rust-lang-nursery.github.io/rust-cookbook/concurrency/parallel.html#test-in-parallel-if-any-or-all-elements-of-a-collection-match-a-given-predicate
https://medium.freecodecamp.org/concurrency-ideologies-of-programming-languages-java-c-c-c-go-and-rust-bd4671d943f
https://doc.rust-lang.org/1.30.0/book/first-edition/concurrency.html
*/
 
#[warn(unused_mut)]
#[warn(non_snake_case)]
extern crate reqwest;


use std::error::Error;
use std::sync::{Arc, Mutex};
use std::thread;
use std::sync::mpsc;

fn process_client() -> String{
	
	let mut response = String::new();
	let mut url = String::new();
	
	let client = reqwest::Client::new();
	url = String::from("http://localhost:8082/rtbdispatcher/rtb/271");
	
	println!("launch GET {}",url);
	
	let res = client.get(&url).send();
	
	match res {
		Ok(s) => {
			let mut s_mut = s;
			let data = s_mut.text();
			response = format!("[{:?}]", data)
			},
		Err(e) => {
			let mut e_mut = e;
			response = format!("RUST Error status [{:?}] cause [{:?}] description [{:?}] ", e_mut.status(), e_mut.cause(), e_mut.description())
		}
	}
	
	return format!("{}", response);
	
}


fn thread_pool(){
	
	
	let (tx, rx) = mpsc::channel();

    for i in 0..10 {
        let tx = tx.clone();

        thread::spawn(move || {
            let answer = process_client();
            tx.send(answer).unwrap();
        });
    }

    for _ in 0..10 {
        println!("{}", rx.recv().unwrap());
    }
	

	println!("End Process");
	
}


fn main() {
    println!("Init concurrent process");
	thread_pool();
}
