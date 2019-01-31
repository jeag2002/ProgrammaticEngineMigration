require 'rubygems'
require 'sinatra'
require 'sinatra/base'
require 'json'

require 'rest-client'

class MyAppClass< Sinatra::Base 

set :logging, true 
set :dump_error, true 
set :raise_errors, true 
set :show_exceptions, true 

## set :bind, '0.0.0.0' 
## set :port, 8082 

def do_something_used_in_sinatra_app(var) end 

def keenKaleIntegration() 
	data = ""
	begin
		
		##text = '{"id":"dca01512ae9ce5b95712794dcd677d80","imp":[{"id":"6bbc5bc3eb7081e1b7f4f7cc29b815f9","instl":0,"tagid":"tx500674895","bidfloor":0.052500000000000005,"bidfloorcur":"USD","secure":1,"banner":{"id":"d06ca4a034793852b24a6bbc66e14690","w":320,"h":50,"mimes":["image/jpg","image/gif"],"battr":[],"wmax":320,"hmax":50,"wmin":300,"hmin":49,"api":[3,5]}}],"device":{"ua":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36","geo":{"country":"ESP","lat":41.427564,"lon":2.185005,"type":1},"dnt":0,"lmt":0,"ip":"88.1.48.62","devicetype":4,"make":"Samsung","model":"gt-9300","os":"Android","osv":"4.4.4","ifa":"1a58da58-4930-4adc-b1a4-2dc1ba386a96","connectiontype":2,"js":1,"language":"es"},"test":0,"at":1,"tmax":1500,"badv":[],"app":{"id":"10308","name":"Whatsdog","publisher":{"id":"67fbd985230c665850075df702d12c5e","name":"tappx","domain":"tappx.com"},"bundle":"com.secondlemon.whatsdogpremium","storeurl":"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium"},"user":{"geo":{"country":"ESP","lat":41.427564,"lon":2.185005,"type":1}}}'
		##jsonData = JSON.parse(text)
		
		response = RestClient::Request.execute(method: :post, url: 'http://rtb-useast.keenkale.com/rtb?zone=55764',
		payload: '{"id":"dca01512ae9ce5b95712794dcd677d80","imp":[{"id":"6bbc5bc3eb7081e1b7f4f7cc29b815f9","instl":0,"tagid":"tx500674895","bidfloor":0.052500000000000005,"bidfloorcur":"USD","secure":1,"banner":{"id":"d06ca4a034793852b24a6bbc66e14690","w":320,"h":50,"mimes":["image/jpg","image/gif"],"battr":[],"wmax":320,"hmax":50,"wmin":300,"hmin":49,"api":[3,5]}}],"device":{"ua":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36","geo":{"country":"ESP","lat":41.427564,"lon":2.185005,"type":1},"dnt":0,"lmt":0,"ip":"88.1.48.62","devicetype":4,"make":"Samsung","model":"gt-9300","os":"Android","osv":"4.4.4","ifa":"1a58da58-4930-4adc-b1a4-2dc1ba386a96","connectiontype":2,"js":1,"language":"es"},"test":0,"at":1,"tmax":1500,"badv":[],"app":{"id":"10308","name":"Whatsdog","publisher":{"id":"67fbd985230c665850075df702d12c5e","name":"tappx","domain":"tappx.com"},"bundle":"com.secondlemon.whatsdogpremium","storeurl":"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium"},"user":{"geo":{"country":"ESP","lat":41.427564,"lon":2.185005,"type":1}}}',
		headers: {
		"Content-Type" => "application/json", 
		"Accept" => "application/json",
        "Connection" => "Keep-Alive",
        "Accept-Charset" => "UTF-8",
        "Expect" => "x-openrtb-version: 2.4",
        "x-forwarded-for" => "216.15.125.142",
        "x-device-user-agent" => "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
        "x-original-user-agent" => "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.3"
		}
		)
		
		data = response.code << e.response.body  
	rescue RestClient::ExceptionWithResponse  => e 
		data = e.response.code << e.response.body  
	
	end
	data
end

	get '/hi' do 
		puts("Inside Hi") 
		return [200, {}, 'Hi, we are inside Hello World!!'] 
	end 
	
	get '/hi/:first' do 
		puts("Hello #{params[:first]}")
		return [200, {}, "Hi #{params[:first]}, we are inside Hello World!!"]
	end
	
	
	get '/rtbdispatcher/rtb/:id' do
		
		
		id = params[:id]
		
		puts("Processing #{id} integration")
		
		if id == "271"
		  response = keenKaleIntegration()
		  return [200, {}, "Integration #{id} Keenkale:  #{response}"]
		else
		  return [200, {}, "Integration #{id} not recognized"]	
		end
	
	end 
	
end 


