package client

import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors

import java.net.{URL, HttpURLConnection}


class HttpClient() {
      
}

class RestClientProcessorRunnable(index: Integer) extends Runnable {
   
    @throws(classOf[java.io.IOException])
    @throws(classOf[java.net.SocketTimeoutException])
    def get(url: String,
            connectTimeout: Int = 5000,
            readTimeout: Int = 5000,
            requestMethod: String = "GET") = {
      
            val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
            connection.setConnectTimeout(connectTimeout)
            connection.setReadTimeout(readTimeout)
            connection.setRequestMethod(requestMethod)
            val inputStream = connection.getInputStream
            val content = io.Source.fromInputStream(inputStream).mkString
            if (inputStream != null) inputStream.close
            content
    }
  
  
    override def run(){
      
        var response = ""
      
        try{
          
          response = "OUTPUT " + get("http://localhost:9000/rtbdispatcher/rtb/271")
          
        } catch {
          
          case ioe: java.io.IOException => response = "IOERROR " + ioe.getMessage()
          case ste: java.net.SocketTimeoutException => response = "SOCKETIMEOUT " + ste.getMessage()
          
        }
      
        println("Task running [" + index + "] response [" + response + "]")
    }
}

object RTBClient {  
  
  def main(args: Array[String]): Unit = {
       val threadPool = Executors.newFixedThreadPool(10)
       var  a = 0;
       for( a <-1 to 10){threadPool.submit(new RestClientProcessorRunnable(a))}
       threadPool.shutdown()
       
  }
}