package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity
import play.api.libs.json._

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.ExecutionContext
import scala.collection.mutable.ArrayBuffer
import scala.util.parsing.json._
import scala.concurrent.{ ExecutionContext, ExecutionContext$, Future, Promise, Await }
import scala.concurrent.duration._


@Singleton
class RTBController @Inject()(ws:WSClient, cc: ControllerComponents) extends AbstractController(cc) {
  
  def query(index:Int) = Action { implicit request: Request[AnyContent] => 
    Ok(queryURL(index))
  }
  
  def queryURL(index:Int): String = {
    
    var output = ""
    if (index == 271){  
      output = keenkaleIntegration()
    }else{
      output = "Integration " +  index + " not recognized"
    }
    
    return output
  }
  
  def keenkaleIntegration(): String = {
    var output = ""
    
    val jsonData:JsValue = Json.parse("""
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
                "storeurl": "http://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium"
              },
              "user": {
                "geo": {
                  "country": "ESP",
                  "lat": 41.427564,
                  "lon": 2.185005,
                  "type": 1
                }
              }
            }""")
    
    
    val requestKeenKale: WSRequest = ws.url("http://rtb-useast.keenkale.com/rtb?zone=55764")
    
    val futureResponse: Future[WSResponse] = requestKeenKale.addHttpHeaders(
        "Content-Type" -> "application/json",
        "Connection" -> "Keep-Alive",
        "Accept-Charset" -> "UTF-8",
        "Expect" -> "x-openrtb-version: 2.4",
        "x-forwarded-for" -> "216.15.125.142",
        "x-device-user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
        "x-original-user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36").post(jsonData)
    
    val responseKeenKale:WSResponse = Await.result(futureResponse, 3.seconds)
    
    output = "Scala status body " + responseKeenKale.toString()
    
    
    return output
  }
  
  
  
}