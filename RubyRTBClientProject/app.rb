require 'rubygems'
require 'thread/pool'
require 'thread/channel'
require 'thread'
require 'rest-client'
require 'concurrent-ruby'
require 'net/http'

class ClientThreadPool



def process()
  pool = Concurrent::FixedThreadPool.new(10)
  errors = Concurrent::Array.new
  
  mutex = Mutex.new
  
  10.times do |j|
    pool.post do
		data = ""
		
		test_thread = Thread.new {
			escaped_address = URI.escape("http://127.0.0.1:8082/rtbdispatcher/rtb/271") 
			uri = URI.parse(escaped_address)
			response = Net::HTTP.get(uri)	
			puts "#{response}\n"
		}
		test_thread.join
    end
  end
 
  wait_for_pool_to_finish(pool, errors)
end

def wait_for_pool_to_finish(pool, errors)
  pool.shutdown
  until pool.shutdown?
    if errors.any?
      pool.kill
      fail errors.first
    end
    sleep 1
  end
  pool.wait_for_termination
end


end

c = ClientThreadPool.new
c.process()
