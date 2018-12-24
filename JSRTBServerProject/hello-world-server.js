/*
 INFO:
 
 https://medium.com/car2godevs/there-are-expressjs-alternatives-590d14c58c1c
 https://www.fastify.io/
 https://www.npmjs.com/package/node-rest-client
 
 If module not found => install in the same directory as executable.
 https://stackoverflow.com/questions/9023672/how-do-i-resolve-cannot-find-module-error-using-node-js
 */

const fastify = require('fastify')({logger: true,  prettyPrint: true})
const Client = require('node-rest-client').Client;



var postRequestKI = function(callback){
	
		var client = new Client();
		
		var args = {
			    data: 
			    {
		              id: "dca01512ae9ce5b95712794dcd677d80",
		              imp: [
		                {
		                  id: "6bbc5bc3eb7081e1b7f4f7cc29b815f9",
		                  instl: 0,
		                  tagid: "tx500674895",
		                  bidfloor: 0.052500000000000005,
		                  bidfloorcur: "USD",
		                  secure: 1,
		                  banner: {
		                    id: "d06ca4a034793852b24a6bbc66e14690",
		                    w: 320,
		                    h: 50,
		                    mimes: [
		                      "image/jpg",
		                      "image/gif"
		                    ],
		                    battr: [
		                      
		                    ],
		                    wmax: 320,
		                    hmax: 50,
		                    wmin: 300,
		                    hmin: 49,
		                    api: [
		                      3,
		                      5
		                    ]
		                  }
		                }
		              ],
		              device: {
		                ua: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
		                geo: {
		                  country: "ESP",
		                  lat: 41.427564,
		                  lon: 2.185005,
		                  type: 1
		                },
		                dnt: 0,
		                lmt: 0,
		                ip: "88.1.48.62",
		                devicetype: 4,
		                make: "Samsung",
		                model: "gt-9300",
		                os: "Android",
		                osv: "4.4.4",
		                ifa: "1a58da58-4930-4adc-b1a4-2dc1ba386a96",
		                connectiontype: 2,
		                js: 1,
		                language: "es"
		              },
		              test: 0,
		              at: 1,
		              tmax: 1500,
		              badv: [
		                
		              ],
		              app: {
		                id: "10308",
		                name: "Whatsdog",
		                publisher: {
		                  id: "67fbd985230c665850075df702d12c5e",
		                  name: "tappx",
		                  domain: "tappx.com"
		                },
		                bundle: "com.secondlemon.whatsdogpremium",
		                storeurl: "https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium"
		              },
		              user: {
		                geo: {
		                  country: "ESP",
		                  lat: 41.427564,
		                  lon: 2.185005,
		                  type: 1
		                }
		              }
		        },
			    headers: { 
			    	"Content-Type": "application/json",
			    	"Connection":"Keep-Alive",
			        "Accept-Charset":"UTF-8",
			        "Expect":"x-openrtb-version: 2.4",
			        "x-forwarded-for":"216.15.125.142",
			        "x-device-user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
			        "x-original-user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36"
			    }
		};
		
		
		var req = client.post("http://rtb-useast.keenkale.com/rtb?zone=55764", args, function (data, response) {
			responseData = "JS Status " + response.statusCode + " Body " +  data; 
			console.log("[postRequestKI] " + responseData);
			callback(responseData);
		});
		
		req.on('error', function(err){
			console.log("[postRequestKI] ERROR " + err);
			callback(err);
		});
	
		
}


function keenkaleIntegration(reply){
	var responseData = '';	
	
	postRequestKI(function(data){
		console.log("[keenkaleIntegration] callback called");
		reply.send(data);
	});
	
}




// Declare a route
fastify.get('/rtbdispatcher/rtb/:id', async (request, reply) => {
	
	var inputParam = request.params.id;
	var response = "";
	
	if (inputParam == "271"){
		keenkaleIntegration(reply);
	}else{
		reply.send("Integration " + inputParam + " not developed");
	}
	
	
})

// Run the server!
const start = async () => {
  try {
    await fastify.listen(8082)
    fastify.log.info(`server listening on ${fastify.server.address().port}`)
  } catch (err) {
    fastify.log.error(err)
    process.exit(1)
  }
}
start()





/*
var http = require('http');

http.createServer(function handler(req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});
    res.end('Hello World\n');
}).listen(8082, '127.0.0.1');

console.log('Server running at http://127.0.0.1:1337/');
*/