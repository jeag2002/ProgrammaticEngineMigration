//https://www.npmjs.com/package/asyncawait

var workerpool = require('workerpool');

var pool = workerpool.pool({maxWorkers: 10});
 
function process(index){
	
	var response = "";
	
	let getRequest = new Promise(
			
			function (resolve, reject){
				const Client = require('node-rest-client').Client;
				
				var client = new Client();
				
				var req = client.get("http://localhost:8082/rtbdispatcher/rtb/271", args, function (data, response) {
					responseData = "JS Status " + response.statusCode + " Body " +  data; 
					resolve(responseData);
				});
				
				req.on('error', function(err){
					responseData = "JS Err " + err;
					reject(responseData);
				});
			}
	);
	
	var process = async function(){
		let result = await getRequest;
		return "Thread [" + index + "] (" + result + ")";
	}
	
	response = process();

	
	/*
	var responseRequest = function(){
		
		getRequest
		.then(
			function (fulfilled) {
				console.log(fulfilled);
	        })
        .catch(function (error) {
        	console.log(error);
        });
		
		
	};
	
	response = responseRequest();
	*/
	return response;
}

for(var i=0; i<10; i++){

pool.exec(process, [i])
    .then(function (result) {
      console.log('result', result); // outputs 7
    })
    .catch(function (err) {
      console.error(err);
    })
    .then(function () {
      pool.terminate(); // terminate all workers when done
    });
}