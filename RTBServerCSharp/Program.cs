using System;
using System.Net;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using System.IO;
using System.Text;
using System.Xml;

using Unosquare.Labs.EmbedIO;
using Unosquare.Labs.EmbedIO.Modules;
using Unosquare.Labs.EmbedIO.Constants;
    
namespace workC
{

    class Program
    {

        static void Main(string[] args)
        {
            using (var server =  new WebServer("http://localhost:8082/", RoutingStrategy.Regex))
            {           
                server.EnableCors();
                server.RegisterModule(new WebApiModule());
                server.Module<WebApiModule>().RegisterController<PeopleController>();
                server.WithLocalSession();
                var task = server.RunAsync();
                
                Console.ReadKey(true);
                task.Wait();
                server.Dispose();




            }
        }
    }

    class PeopleController : WebApiController
    {
        public PeopleController(IHttpContext context): base(context)
        {
        }
        

        public String processKeenkale(){

            String result = "";

            try{
                HttpWebRequest request = WebRequest.Create("http://rtb-useast.keenkale.com/rtb?zone=55764") as HttpWebRequest;

                if (request != null){
                    request.Method = "POST";
                    request.Headers.Add("Content-Type","application/json");
                    request.Headers.Add("Connection","Keep-Alive");
                    request.Headers.Add("Accept-Charset","UTF-8");
                    request.Headers.Add("Expect","x-openrtb-version: 2.4");
                    request.Headers.Add("x-forwarded-for","216.15.125.142");
                    request.Headers.Add("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
                    request.Headers.Add("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");

                    result = "{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}";

                    var buffer = Encoding.UTF8.GetBytes(result);    

                    using (var stream = request.GetRequestStream())
                    {
                        stream.Write(buffer, 0, buffer.Length);
                    }
                

                    using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
                    {
                        StreamReader reader = new StreamReader(response.GetResponseStream());
                        result =  "Status code [" + (int)response.StatusCode + "] Body [" +  reader.ReadToEnd() + "]";
                    }
                }else{
                    result = "Cannot connect with remote endpoint";
                }

            } catch(Exception ex){
                result = ex.Message;
            }
            
            return result;
        }

        
        
        [WebApiHandler(HttpVerbs.Get, "/rtbdispatcher/rtb/{id}")]
        public bool GetProgrammatica(WebServer server, HttpListenerContext context, int id)
        {

            String textResult = "";
        
            try
            {
                Console.WriteLine("Processing integration (" + id + ")");
                
                
                
                if (id == 271){
                    textResult = "Integration code [" + id + "] " + processKeenkale();
                }else{
                    textResult = "Integration code [" + id + "] not found";
                }

                var buffer = Encoding.UTF8.GetBytes(textResult);
                this.Response.ContentType = "text/plain";
                this.Response.OutputStream.Write(buffer, 0, buffer.Length);
                return true;


            }
            catch (Exception ex)
            {
                 textResult = "Something happened " + ex.Message;

                var buffer = Encoding.UTF8.GetBytes(textResult);
                context.Response.ContentType = "text/plain";
                context.Response.OutputStream.Write(buffer, 0, buffer.Length);
                return true;
            }


        }
        

        //You can override the default headers and add custom headers to each API Response.
        //public override void SetDefaultHeaders(HttpListenerContext context) => context.NoCache();
    }

}